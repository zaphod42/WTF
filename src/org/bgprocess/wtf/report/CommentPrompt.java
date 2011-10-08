package org.bgprocess.wtf.report;


public interface CommentPrompt {
    String promptFor(String filename, String selectedText) throws CancelledException;
}
