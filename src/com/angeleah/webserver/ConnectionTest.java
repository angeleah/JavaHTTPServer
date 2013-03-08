package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.net.*;
import java.io.*;

public class ConnectionTest {

    @Test
    public void itShouldBeAbleToParseAPort() throws ArgsException {
        String[] args = new String[]{"java -jar ang/server/cool", "-p", "5000", "-d", "what!"};
        assertEquals(5000, Connection.parsePort(args));
    }

    @Test
    public void itShouldBeAbleToParseADirectory() throws ArgsException {
        String[] args = new String[]{"java -jar ang/server/cool.jar", "-p", "5000", "-d", "com/angeleah/webserver/TestDirectory"};
        assertEquals("com/angeleah/webserver/TestDirectory", Connection.parseDirectory(args));
    }
}