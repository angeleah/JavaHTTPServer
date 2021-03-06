package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BinaryReaderTest {

    public BinaryReader reader;

    @Before
    public void SetUp() {
        reader = new BinaryReader();
    }

    @Test
    public void itShouldBeAbleToReturnTheSizeOfABinaryFile() {
        String file = "binary.dat";
        String directory = "com/angeleah/webserver/TestDirectory/";
        byte[] data = reader.read(directory, file);
        assertEquals(4, data.length);
        assertEquals(1, data[0]);
    }

    @Test
    public void itShouldBeAbleToReadARegularTextFile() {
        String file = "textTest.txt";
        String directory = "com/angeleah/webserver/TestDirectory/";
        byte[] data = reader.read(directory, file);
        String fileContents = new String(data);
        assertEquals("Hello", fileContents);
    }
}