package com.angeleah.webserver;
import org.junit.Test;

public class ArgsExceptionTest {

    @Test(expected = ArgsException.class)
    public void itShouldThrowAnExceptionWhenNotPassedADirectory() throws ArgsException {
        String[] args = new String[]{"java -jar ang/server/cool", "-p", "5000"};
            Connection.parseDirectory(args);
    }
}
