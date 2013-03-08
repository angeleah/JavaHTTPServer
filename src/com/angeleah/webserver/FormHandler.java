package com.angeleah.webserver;

public class FormHandler implements RequestHandler{

    public RequestStore handle(RequestStore requestStore, String directory) {
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        StringBuilder body = new StringBuilder();
        if ((requestStore.getParams() != null) && (requestStore.getRequestBody() != null))  {
            body.append(builder.createHtmlBodyWithParamsAndBodyContent(requestStore.getParams(), requestStore.getRequestBody()));
        } else if ((requestStore.getParams() != null) && (requestStore.getRequestBody() == null)) {
            body.append(builder.createHtmlBodyWithParamsContent(requestStore.getParams()));
        } else if ((requestStore.getParams() == null) && (requestStore.getRequestBody() != null)){
            body.append(builder.createHtmlBodyWithRequestContent(requestStore.getRequestBody()));
        }
        requestStore.setBody(body.toString().getBytes());
        requestStore.setContentLength(body.length());
        requestStore.setOk();
        return requestStore;
    }
}

