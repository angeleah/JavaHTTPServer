package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class RedirectHandlerTest {

    private RedirectHandler redirectHandler;
    private String directory = "com/angeleah/webserver/TestDirectory/";
    public int port = 5000;


    @Before
    public void SetUp() {
        redirectHandler = new RedirectHandler();
    }

    @Test
    public void itShouldbeAbleToHandleARedirectProperly() {
        RequestStore requestStore = new RequestStore();
        requestStore.setHeader("Host", "localhost");
        redirectHandler.handle(requestStore,directory,port);
        assertEquals("redirect",requestStore.getStatus());
        assertEquals("302",requestStore.getCode());
        assertEquals("http://localhost:5000/", requestStore.getLocation());
    }
}