package com.angeleah.webserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;

public class Conductor {

    private BufferedReader in;
    private RequestStore requestStore;
    private Router router;

    public Conductor(BufferedReader in, Router router) {
        this.in = in;
        this.router = router;
    }

    public byte[] conductTheProcess() throws IOException {
        RequestStore requestStore = new RequestStore();
        parseRequest(in, requestStore);
        routeRequest(requestStore);
        Date date = new Date();
        byte[] response = buildRequest(requestStore, date);
        return response;
    }

    public void parseRequest(BufferedReader input, RequestStore store) throws IOException {
        RequestParser parser = new RequestParser(input, store);
        parser.processRequest(input);
    }

    public void routeRequest(RequestStore requestStore) {
        registerRoutes(router);
        router.routeRequest(requestStore);
    }

      public byte[] buildRequest(RequestStore store, Date date) {
        ResponseBuilder responseBuilder = new ResponseBuilder(store, date);
        return responseBuilder.buildResponse(date);
      }

    private void registerRoutes(Router router) {
        router.register("/", new IndexHandler());
        router.register("/some-script-url", new ParamsHandler());
        router.register("/form", new FormHandler());
        router.register("/redirect", new RedirectHandler());
    }
}