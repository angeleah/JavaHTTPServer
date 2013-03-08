package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 3/7/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestHelper {

    public static boolean FileByteArrayCompare(byte[] b1, byte[] b2){
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
}
