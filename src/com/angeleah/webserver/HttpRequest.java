package com.angeleah.webserver;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

final class HttpRequest implements Runnable {

    private final Socket client;
    private final Router router;

    public HttpRequest(Socket client, Router router){
        this.client = client;
        this.router = router;
    }

    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processRequest() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        Conductor conductor = new Conductor(in, router);
        DataOutputStream out= new DataOutputStream(client.getOutputStream());

        byte[] response = conductor.handleTheRequest();
        int length = response.length;
        out.write(response, 0, length);

        out.close();
        in.close();
        client.close();
    }
}
