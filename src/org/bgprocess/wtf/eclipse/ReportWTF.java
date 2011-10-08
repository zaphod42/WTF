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
    private HttpPublisher httpPublisher;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		try {
		    httpPublisher = new HttpPublisher(new URL(Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_SERVER)));
		} catch (MalformedURLException e) {
		    MessageDialog.openError(shell, "Invalid WTF server URL", "Error parsing the server URL: " + e.getMessage());
		}
	}

	public void run(IAction action) {
	    Reporter reporter = new EditorReporter(activeEditor(), new DialogCommentPrompt(shell));
        reporter.reportTo(httpPublisher);
	}

	private IEditorPart activeEditor() {
		return Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
