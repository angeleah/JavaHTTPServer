package com.angeleah.webserver;

public class FileHandler implements RequestHandler {

    public RequestStore handle(RequestStore requestStore, String directory, int port) {
        if (requestStore.getMethod().equals("GET") && (requestStore.getStartRange() == null)) {
            BinaryReader reader = new BinaryReader();
            byte[] body = reader.read(directory, requestStore.getRequestUri());
            requestStore.setBody(body);
            requestStore.setContentLength(body.length);
            requestStore.setOk();
        } else if (requestStore.getMethod().equals("GET") && (requestStore.getStartRange() != null)) {
            BinaryReader reader = new BinaryReader();
            byte[] body = reader.read(directory, requestStore.getRequestUri());
            byte[] partialBody = java.util.Arrays.copyOfRange(body, requestStore.getStartRange(), requestStore.getEndRange());
            requestStore.setBody(partialBody);
            requestStore.setContentLength(partialBody.length);
            requestStore.set206();
        } else{
            requestStore.set405();
        }
        return requestStore;
    }
}