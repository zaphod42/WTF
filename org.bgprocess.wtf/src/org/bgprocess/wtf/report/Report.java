package org.bgprocess.wtf.report;

public class Report {
	public final String filename;
	public final String code;
    public final String comment;

	public Report(String filename, String wtfCode, String comment) {
		this.filename = filename;
		this.code = wtfCode;
        this.comment = comment;
	}
}
