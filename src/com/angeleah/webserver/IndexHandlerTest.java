package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: angeleah
* Date: 2/8/13
* Time: 3:03 PM
* To change this template use File | Settings | File Templates.
*/
public class IndexHandlerTest {

    public IndexHandler indexHandler;

    @Before
    public void SetUp() {
        indexHandler = new IndexHandler();
    }

//    dependency on the exact files in the directory;

    @Test
    public void indexHandlerShouldBeAbleToReadADirectory() {
        String directory = "com/angeleah/webserver/TestDirectory";
        ArrayList<String> dirContents = new ArrayList<String>();
        dirContents.add("anotherTestFile.html");
        dirContents.add("awesomePage.html");
        dirContents.add("file1");
        dirContents.add("helloWorld.html");
        dirContents.add("imageTest.jpeg");
        dirContents.add("testFile.txt");
        assertEquals(dirContents, indexHandler.readDirectory(directory));
    }

    @Test
    public void itShouldBeAbleToRemoveThe404PageFromTheDirectoryContents() {
        String directory = "com/angeleah/webserver/TestDirectory";
        ArrayList<String> dirContentsWithout404 = new ArrayList<String>();
        dirContentsWithout404.add("anotherTestFile.html");
        dirContentsWithout404.add("awesomePage.html");
        dirContentsWithout404.add("file1");
        dirContentsWithout404.add("helloWorld.html");
        dirContentsWithout404.add("imageTest.jpeg");
        dirContentsWithout404.add("testFile.txt");
        ArrayList<String> dirContents = indexHandler.readDirectory(directory);
        assertEquals(dirContentsWithout404, indexHandler.remove404FromTheDirectoryContents(dirContents));
    }

    @Test
    public void itShouldBeAbleToHandleTheRequest() {
        RequestStore requestStore = new RequestStore();
        String directory = "com/angeleah/webserver/TestDirectory";
        requestStore.setDirectory(directory);
        indexHandler.handle(requestStore);
        String code = requestStore.getCode();
        String status = requestStore.getStatus();
        assertEquals("200", code );
        assertEquals("OK", status);
    }

//    public void itShouldBeAbleToConvertTheBodyToBytes() {
//        String body = "<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<a href=\"/anotherTestFile.html\">anotherTestFile.html</a>\n<a href=\"/awesomePage.html\">awesomePage.html</a>\n<a href=\"/file1\">file1</a>\n<a href=\"/helloWorld.html\">helloWorld.html</a>\n<a href=\"/imageTest.jpeg\">imageTest.jpeg</a>\n</body>";
//        assertEquals(body.getBytes(),indexHandler.convertBodyToByteArray(body));
//    }

}
