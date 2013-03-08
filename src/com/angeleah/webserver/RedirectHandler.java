package com.angeleah.webserver;

public class RedirectHandler implements RequestHandler{

    public RequestStore handle(RequestStore requestStore, String directory, int port) {
        requestStore.setRedirect(port);
        return requestStore;
    }
}
