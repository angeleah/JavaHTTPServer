package com.angeleah.webserver;
import java.io.*;

public class BinaryReader {

    byte[] read(String directory, String fileName) {
        File file = new File(directory.concat(fileName));
        byte[] result = null;
        try {
            InputStream input = new BufferedInputStream(new FileInputStream(file));
            result = readAndClose(input);
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }
        return  result;
    }

    byte[] readAndClose(InputStream aInput){
        byte[] bucket = new byte[32*1024];
        ByteArrayOutputStream result = null;
        try  {
            try {
                result = new ByteArrayOutputStream(bucket.length);
                int bytesRead = 0;
                while(bytesRead != -1){
                    bytesRead = aInput.read(bucket);
                    if(bytesRead > 0){
                        result.write(bucket, 0, bytesRead);
                    }
                }
            }
            finally {
                aInput.close();
            }
        }
        catch (IOException e){
            System.err.println(e);
            e.printStackTrace();
        }
        return result.toByteArray();
    };
}