package org.bgprocess.wtf.report;

import java.util.Collection;

public class CollectionPublisher implements Publisher {

    private final Collection<Report> collection;

    public CollectionPublisher(Collection<Report> collection) {
        this.collection = collection;
    }

    @Override
    public void publish(Report report) {
        collection.add(report);
    }

}
