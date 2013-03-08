package com.angeleah.webserver;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class IndexHandler implements RequestHandler {

    public RequestStore handle(RequestStore requestStore, String directory, int port) {
        HtmlBodyBuilder htmlBodyBuilder = new HtmlBodyBuilder();
        ArrayList<String> dirContents = readDirectory(directory);
        String body = htmlBodyBuilder.createHtmlBodyWithDirectoryContents(dirContents);
        requestStore.setBody(body.getBytes());
        requestStore.setContentLength(body.length());
        requestStore.setOk();
        return requestStore;
    }

    public ArrayList<String> readDirectory(String dir) {
        ArrayList<String> directoryContents = new ArrayList<String>();
        File directory = new File(dir);
        String[] contents = directory.list();
        for (String name : contents) {
            directoryContents.add(name);
        }
        return directoryContents;
    }
}
