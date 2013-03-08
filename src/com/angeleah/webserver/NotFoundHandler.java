package com.angeleah.webserver;

public class NotFoundHandler implements RequestHandler{

    public RequestStore handle(RequestStore requestStore, String directory) {
          HtmlBodyBuilder htmlBodyBuilder = new HtmlBodyBuilder();
          String body = htmlBodyBuilder.createHtmlNotFoundBody();
          requestStore.setBody(body.getBytes());
          requestStore.setContentLength(body.length());
          requestStore.set404();
          return requestStore;
    }
}
