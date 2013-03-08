package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


import java.util.HashMap;

public class ParamsHandlerTest {

    public ParamsHandler paramsHandler;
    public String directory = "com/angeleah/webserver/TestDirectory/";
    public int port = 5000;

    @Before
    public void SetUp() {
        paramsHandler = new ParamsHandler();
    }

    @Test
    public void itShouldBeAbleToHandleQueryStringsCorrectly() {
        RequestStore requestStore = new RequestStore();
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        HashMap params = new HashMap();
        params.put("variable_1", "123459876");
        params.put("variable_2", "some_value");
        requestStore.setParams(params);
        String body = builder.createHtmlBodyWithParamsContent(requestStore.getParams());
        paramsHandler.handle(requestStore, directory, port);
        assertEquals(body, "<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<p>variable_2 = some_value</p>\n<p>variable_1 = 123459876</p>\n</body>\n</html>");
        byte[] b1 = requestStore.getBody();
        byte[] b2 = body.getBytes();
        assertEquals("200", requestStore.getCode());
        assertEquals("OK", requestStore.getStatus());
        assert(TestHelper.FileByteArrayCompare(b1, b2));
    }
}
