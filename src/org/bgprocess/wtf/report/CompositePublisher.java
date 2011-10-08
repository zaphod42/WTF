package org.bgprocess.wtf.report;

public class CompositePublisher implements Publisher {
    private final Publisher[] publishers;

    public CompositePublisher(Publisher... publishers) {
        this.publishers = publishers;
    }

    @Override
    public void publish(Report report) throws PublishException {
        for(Publisher publisher : publishers) {
            publisher.publish(report);
        }
    }
}
