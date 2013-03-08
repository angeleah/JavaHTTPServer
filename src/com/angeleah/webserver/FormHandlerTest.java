package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class FormHandlerTest {

    public FormHandler formHandler;
    public String directory = "com/angeleah/webserver/TestDirectory/";

    @Before
    public void SetUp() {
        formHandler = new FormHandler();
    }

    @Test
    public void itShouldBeAbleToHandleAFormCorrectly() {
        RequestStore requestStore = new RequestStore();
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        requestStore.setMethod("POST");
        String content = "my = data value1 = hello";
        String body = builder.createHtmlBodyWithRequestContent(content);
        requestStore.setRequestBody(content);
        formHandler.handle(requestStore, directory);
        byte[] b1 = requestStore.getBody();
        byte[] b2 = body.getBytes();
        assertEquals("200", requestStore.getCode());
        assertEquals("OK", requestStore.getStatus());
        assert(TestHelper.FileByteArrayCompare(b1, b2));
    }
}
