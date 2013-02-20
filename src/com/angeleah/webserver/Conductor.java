package com.angeleah.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */

//ideas for a test to add would be that host is present for http1.1 or else it returns 400 bad request.
//    one or more blank lines can be parsed from the body.


public class Conductor {

    public BufferedReader in;
    public RequestStore requestStore;
    public String directory;

    public Conductor(BufferedReader in, String directory) {
        this.in = in;
        this.directory = directory;
        RequestStore requestStore = new RequestStore(); //why is this never used?
    }

    public String conductTheProcess() throws IOException {
          requestStore.setDirectory(directory);
          parseRequest(in, requestStore);
//          routeRequest(requestStore.getRequestUri) //code I want to be there
//          byte[] response = buildRequest(dataToBuildResponse);

        String response = "HTTP/1.0 200 OK\r\nDate: Fri, 31 Dec 1999 23:59:59 GMT\r\nContent-Type: text/html\r\nContent-Length: 1\r\n\r\nA\r\n";
        return response;
    }

    public void parseRequest(BufferedReader input, RequestStore store) throws IOException {
        RequestParser parser = new RequestParser(input, store);
        parser.processRequest(input);
    }

//    public void routeRequest(RequestStore requestStore) {
//        Router router = new Router();
//        router.route(requestStore);
//    }

//      public byte[] buildRequest() {
          //        ResponseBuilder responseBuilder = new ResponseBuilder(date);
//        return responseBuilder.buildResponse(dataToBuildResponse);
//      }
}
