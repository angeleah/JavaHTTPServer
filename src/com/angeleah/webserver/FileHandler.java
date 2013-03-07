package com.angeleah.webserver;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileHandler implements RequestHandler {

    public RequestStore handle(RequestStore requestStore) {
        if (requestStore.getMethod().equals("GET") && (requestStore.getStartRange() == null)) {
            BinaryReader reader = new BinaryReader();
            byte[] body = reader.read(requestStore.getDirectory(), requestStore.getRequestUri());
            requestStore.setBody(body);
            requestStore.setContentLength(body.length);
            requestStore.setOk();
//        } else if (requestStore.getMethod().equals("GET") && (requestStore.getStartRange() != null)) {
//            BinaryReader reader = new BinaryReader();
//            byte[] body = reader.read(requestStore.getDirectory(), requestStore.getRequestUri());
//            byte[] partialBody = Arrays.copyOfRange(body, requestStore.getStartRange(), requestStore.getEndRange());
////            requestStore.setBody(body);   want to only set the range length
//            requestStore.setContentLength(partialBody.length);
//            requestStore.set206();
        }
          else{
            requestStore.set405();
        }
        return requestStore;
    }
}