package org.bgprocess.wtf.report;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;


public class CompositePublisherTest {
    @Test public void
    publishes_to_all_contained_publishers() throws PublishException {
        Collection<Report> first = new ArrayList<Report>();
        Collection<Report> second = new ArrayList<Report>();
        Publisher composite = new CompositePublisher(new CollectionPublisher(first), new CollectionPublisher(second));
        
        Report report = new Report("", "", "");
        composite.publish(report);
        
        assertTrue(first.contains(report));
        assertTrue(second.contains(report));
    }
}
