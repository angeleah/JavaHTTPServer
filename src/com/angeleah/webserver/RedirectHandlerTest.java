package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class RedirectHandlerTest {

    private RedirectHandler redirectHandler;
    private String directory = "com/angeleah/webserver/TestDirectory/";

    @Before
    public void SetUp() {
        redirectHandler = new RedirectHandler();
    }

    @Test
    public void itShouldbeAbleToHandleARedirectProperly() {
        RequestStore requestStore = new RequestStore();
        redirectHandler.handle(requestStore,directory);
        assertEquals("redirect",requestStore.getStatus());
        assertEquals("302",requestStore.getCode());
        assertEquals("http://localhost:5000/", requestStore.getLocation());
    }
}