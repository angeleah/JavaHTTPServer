package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundHandlerTest {

    public NotFoundHandler notFoundHandler;
    public String directory = "com/angeleah/webserver/TestDirectory/";

    @Before
    public void SetUp() {
        notFoundHandler = new NotFoundHandler();
    }

    @Test
    public void itShouldbeAbleToHandleA404Properly() {
        RequestStore requestStore = new RequestStore();
        String body ="<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<h1>Not Found</h1>\n</body>\n</html>";
        notFoundHandler.handle(requestStore, directory);
        byte[] b1 = requestStore.getBody();
        byte[] b2 = body.getBytes();
        assertEquals(true, FileByteArrayCompare(b1, b2));
    }

    public boolean FileByteArrayCompare(byte[] b1, byte[] b2){
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
}
