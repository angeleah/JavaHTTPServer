package com.angeleah.webserver;

public interface RequestHandler {

    public RequestStore handle(RequestStore requestStore, String directory);
}
