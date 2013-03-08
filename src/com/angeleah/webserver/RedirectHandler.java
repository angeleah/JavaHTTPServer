package com.angeleah.webserver;

public class RedirectHandler implements RequestHandler{

    public RequestStore handle(RequestStore requestStore, String directory) {
        requestStore.setRedirect();
        return requestStore;
    }
}
