package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IndexHandlerTest {

    public IndexHandler indexHandler;
    public String directory = "com/angeleah/webserver/TestDirectory/";

    @Before
    public void SetUp() {
        indexHandler = new IndexHandler();
    }

    @Test
    public void indexHandlerShouldBeAbleToReadADirectory() {
        ArrayList<String> dirContents = new ArrayList<String>();
        dirContents.add("anotherTestFile.html");
        dirContents.add("awesomePage.html");
        dirContents.add("binary.dat");
        dirContents.add("file1");
        dirContents.add("helloWorld.html");
        dirContents.add("imageTest.jpeg");
        dirContents.add("testFile.txt");
        dirContents.add("textTest.txt");
        assertEquals(dirContents, indexHandler.readDirectory(directory));
    }

    @Test
    public void itShouldBeAbleToHandleTheRequest() {
        RequestStore requestStore = new RequestStore();
        indexHandler.handle(requestStore, directory);
        String code = requestStore.getCode();
        String status = requestStore.getStatus();
        assertEquals("200", code );
        assertEquals("OK", status);
    }
}
