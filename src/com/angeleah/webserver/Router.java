package com.angeleah.webserver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Router {

    private HashMap<String, Object> routes = new HashMap<String, Object>();

    private String directory;
    private int port;

    public Router(String directory, int port) {
        this.directory = directory;
        this.port = port;
    }

    public void register(String path, RequestHandler handler) {
        routes.put(path, handler);
    }

    public void routeRequest(RequestStore requestStore) {
        String path = requestStore.getRequestUri();
        RequestHandler handler = getRequestHandlerForPath(directory,port, path);
        handler.handle(requestStore,directory,port);
    }

    private RequestHandler getRequestHandlerForPath(String directory, int port, String path) {
        if (routes.containsKey(path)) {
            return (RequestHandler) routes.get(path);
        } else if (pathIsAFile(directory, path)) {
            return new FileHandler();
        } else {
            return new NotFoundHandler();
        }
    }

    public boolean pathIsAFile(String directory , String path) {
        if (directory == null) {
            return false;
        }
        ArrayList<String> directoryContents =  readDirectory(directory);
        if (directoryContents.contains(path)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> readDirectory(String dir) {
        ArrayList<String> directoryContents = new ArrayList<String>();
        File directory = new File(dir);
        String[] contents = directory.list();
        for (String name : contents) {
            directoryContents.add("/" + name);
        }
        return directoryContents;
    }
}