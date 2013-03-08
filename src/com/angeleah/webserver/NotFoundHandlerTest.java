package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class NotFoundHandlerTest {

    public NotFoundHandler notFoundHandler;
    public String directory = "com/angeleah/webserver/TestDirectory/";
    public int port = 5000;

    @Before
    public void SetUp() {
        notFoundHandler = new NotFoundHandler();
    }

    @Test
    public void itShouldbeAbleToHandleA404Properly() {
        RequestStore requestStore = new RequestStore();
        notFoundHandler.handle(requestStore, directory, port);
        assertEquals("404", requestStore.getCode());
        assertEquals("Not Found", requestStore.getStatus());
    }
}
