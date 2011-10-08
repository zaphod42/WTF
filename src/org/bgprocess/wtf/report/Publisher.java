package org.bgprocess.wtf.report;

public interface Publisher {
    void publish(Report report) throws PublishException;
}
