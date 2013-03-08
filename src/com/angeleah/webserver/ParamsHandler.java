package com.angeleah.webserver;
import java.util.HashMap;

public class ParamsHandler implements RequestHandler {

    public RequestStore handle(RequestStore requestStore, String directory, int port) {
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        HashMap<String,String> params = requestStore.getParams();
        String body = builder.createHtmlBodyWithParamsContent(params);
        requestStore.setBody(body.getBytes());
        requestStore.setContentLength(body.length());
        requestStore.setOk();
        return requestStore;
    }
}
