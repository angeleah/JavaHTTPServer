package com.angeleah.webserver;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;

public class RouterTest {

    public String directory = "com/angeleah/webserver/TestDirectory/";

    class TestHandler implements RequestHandler {

        public boolean wasHandled = false;

        @Override
        public RequestStore handle(RequestStore requestStore, String directory) {
            wasHandled = true;
            return requestStore;
        }
    }

    @Test
    public void itShouldBeAbleToRegisterARoute() {
        RequestStore requestStore = new RequestStore();
        requestStore.setRequestUri("/");
        Router router = new Router(directory);
        TestHandler handler = new TestHandler();
        router.register("/", handler);
        router.routeRequest(requestStore);
        assertTrue(handler.wasHandled);
    }

    @Test
    public void itShouldBeAbleToHandleARouteThatPointsToAFile() {
        RequestStore requestStore = new RequestStore();
        requestStore.setRequestUri("/awesomePage.html");
        requestStore.setMethod("GET");
        Router router = new Router(directory);
        router.routeRequest(requestStore);
        String body =  "<p>This Page is awesome</p>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(TestHelper.FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldHandleARouteThatIsNotFound(){
        RequestStore requestStore = new RequestStore();
        requestStore.setRequestUri("/coolawesomesweetness");
        Router router = new Router(directory);
        router.routeRequest(requestStore);
        String body ="<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<h1>Not Found</h1>\n</body>\n</html>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(TestHelper.FileByteArrayCompare(b1, b2));
    }
}
