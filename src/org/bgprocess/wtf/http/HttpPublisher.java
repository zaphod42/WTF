package org.bgprocess.wtf.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bgprocess.wtf.report.PublishException;
import org.bgprocess.wtf.report.Publisher;
import org.bgprocess.wtf.report.Report;

public class HttpPublisher implements Publisher {

    private final URL url;

    public HttpPublisher(URL url) {
        this.url = url;
    }

    @Override
    public void publish(Report report) throws PublishException {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStream output = connection.getOutputStream();
            new Parameters()
                .parameter("file", report.filename)
                .parameter("code", report.code)
                .parameter("comment", report.comment)
                .write(output);
            output.flush();
            output.close();
            
            if(connection.getResponseCode() != 201) {
                throw new RuntimeException("Error when posting report");
            }
            
            connection.disconnect();
        } catch (IOException e) {
            throw new PublishException("Error communicating with server: " + e.getMessage(), e);
        }
    }
}
