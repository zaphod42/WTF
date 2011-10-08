package org.bgprocess.wtf.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class Parameters {
    private final StringBuilder parameters = new StringBuilder();
    
    public void write(OutputStream out) throws IOException {
        out.write(parameters.toString().getBytes());
    }

    public Parameters parameter(String name, String value) throws UnsupportedEncodingException {
        if (this.parameters.length() > 0) {
            parameters.append('&');
        }
        
        parameters.append(format(name, value));
        
        return this;
    }
    
    private static String format(String name, String value) throws UnsupportedEncodingException {
        return String.format(name + "=%s", encode(value));
    }
    
    private static String encode(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, "UTF-8");
    }
}