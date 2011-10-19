package org.bgprocess.wtf.http;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketAddress;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bgprocess.wtf.report.PublishException;
import org.bgprocess.wtf.report.Report;
import org.junit.Test;
import org.simpleframework.http.Form;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class HttpPublisherTest {
    private final BlockingQueue<String> filenames = new LinkedBlockingQueue<String>();
    private final BlockingQueue<String> codes = new LinkedBlockingQueue<String>();
    private final BlockingQueue<String> comments = new LinkedBlockingQueue<String>();
    private final SimpleServer server = new SimpleServer();
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

    @Test public void
    posts_the_report_to_the_server() throws MalformedURLException, InterruptedException, PublishException {
        executor.execute(server);
        Thread.sleep(1000); //Bad hack to wait for the server to start
        
        HttpPublisher publisher = new HttpPublisher(new URL("http://localhost:8080"));
        publisher.publish(new Report("the file", "the code", "the comment"));
        
        assertEquals("the file", filenames.poll(1, TimeUnit.SECONDS));
        assertEquals("the code", codes.poll(1, TimeUnit.SECONDS));
        assertEquals("the comment", comments.poll(1, TimeUnit.SECONDS));
    }
    
    public class SimpleServer implements Container, Runnable {
        @Override
        public void run() {
            try {
                Connection connection = new SocketConnection(this);
                SocketAddress address = new InetSocketAddress(8080);

                connection.connect(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void handle(Request request, Response response) {
            try {
                Form form = request.getForm();
                if(form.containsKey("file")) {
                    filenames.add(form.get("file"));
                }

                if(form.containsKey("code")) {
                    codes.add(form.get("code"));
                }
                
                if(form.containsKey("comment")) {
                    comments.add(form.get("comment"));
                }
            
                response.setCode(201);
                response.setDate("Date", System.currentTimeMillis());
                response.commit();
                response.close();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
            
        }
        
    }
}
