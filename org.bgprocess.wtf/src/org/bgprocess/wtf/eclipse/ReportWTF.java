package org.bgprocess.wtf.eclipse;

import java.net.MalformedURLException;
import java.net.URL;

import org.bgprocess.wtf.http.HttpPublisher;
import org.bgprocess.wtf.preferences.PreferenceConstants;
import org.bgprocess.wtf.report.Reporter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ReportWTF implements IObjectActionDelegate {
	private Shell shell;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	public void run(IAction action) {
	    try {
	        Reporter reporter = new EditorReporter(activeEditor(), new DialogCommentPrompt(shell));
	        reporter.reportTo(new HttpPublisher(serverUrl()));
	    } catch (MalformedURLException e) {
	        MessageDialog.openError(shell, "Invalid WTF server URL", "Error parsing the server URL: " + e.getMessage());
	    }
	}
	
	private URL serverUrl() throws MalformedURLException {
	    return new URL(activator().getPreferenceStore().getString(PreferenceConstants.P_SERVER));
	}

	private IEditorPart activeEditor() {
		return activator().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}
	
	private Activator activator() {
	    return Activator.getDefault();
	}

	public void selectionChanged(IAction action, ISelection selection) {}
}
