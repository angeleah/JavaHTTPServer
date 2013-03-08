package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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
        assert(TestHelper.FileByteArrayCompare(b1, b2));
    }
}
