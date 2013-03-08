package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import static junit.framework.Assert.assertEquals;

public class ResponseBuilderTest {

    private ResponseBuilder responseBuilder;
    private RequestStore requestStore;
    private Date date;

    @Before
    public void setUp() {
        requestStore = new RequestStore();
        date = new  Date();
        responseBuilder = new ResponseBuilder(requestStore, date);
    }

    public RequestStore testSetUp(String testRequest) throws IOException {
        StringReader request = new StringReader(testRequest);
        BufferedReader in = new BufferedReader(request);
        RequestParser requestParser = new RequestParser(in, requestStore);
        try {
            requestParser.processRequest(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestStore;
    }

    @Test
    public void itShouldBeAbleToBuildTheHeaders() throws IOException {
        testSetUp("GET / HTTP/1.1\nHost: www.Superawesome.com\n\r\n");
        requestStore.setOk();
        String stringResponse = "HTTP/1.1 200 OK\r\n";
        byte[] b1 = stringResponse.getBytes();
        byte[] b2 = responseBuilder.buildResponseHeaders(date);
        assert(TestHelper.FileByteArrayCompare(b1, b2));
    }

    @Test
        public void itShouldBeAbleToBuildTheBody() throws IOException {
        String body = "my = data value1 = hello";
        requestStore.setBody(body.getBytes());
        requestStore.setOk();
        byte[] b1 = requestStore.getBody();
        byte[] b2 = responseBuilder.buildResponseBody();
        assert(TestHelper.FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldIBeAbleToConstructTheInitialResponseLine() throws IOException {
        testSetUp("GET / HTTP/1.1\nHost: www.Superawesome.com\n\r\n");
        requestStore.setOk();
        assertEquals("HTTP/1.1 200 OK\r\n",responseBuilder.buildInitialResponseLine());
    }

    @Test
    public void itShouldBeAbleToConstructTheLocationHeader(){
        requestStore.setLocation("http://localhost:5000/");
        assertEquals("Location: http://localhost:5000/\r\n", responseBuilder.buildLocationResponseLine());
    }

    @Test
    public void itShouldBeAbleToConstructTheContentType(){
        requestStore.setMimeType("text/html");
        assertEquals("Content-Type: text/html\r\n", responseBuilder.buildContentTypeResponseLine());
    }

    @Test
    public void itSHouldBeAbleToGetTheContentLength(){
        requestStore.setContentLength(24);
        assertEquals("Content-Length: 24\r\n", responseBuilder.buildContentLengthResponseLine());
    }
}