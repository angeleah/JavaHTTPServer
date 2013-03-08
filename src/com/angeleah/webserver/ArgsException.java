package com.angeleah.webserver;

public class ArgsException extends Exception {

    public ArgsException() {
        super("You must provide a port and a directory argument. ex: java -jar pathToJarfile/jarfile.jar -p 5000 -d directoryPath");
    }
}
