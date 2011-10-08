package org.bgprocess.wtf.eclipse;

import org.bgprocess.wtf.report.CancelledException;
import org.bgprocess.wtf.report.CommentPrompt;
import org.bgprocess.wtf.report.PublishException;
import org.bgprocess.wtf.report.Publisher;
import org.bgprocess.wtf.report.Report;
import org.bgprocess.wtf.report.Reporter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;

public class EditorReporter implements Reporter {

    private final IEditorPart editor;
    private final CommentPrompt commentPrompt;

    public EditorReporter(IEditorPart editor, CommentPrompt commentPrompt) {
        this.editor = editor;
        this.commentPrompt = commentPrompt;
    }

    @Override
    public void reportTo(Publisher publisher) {
        String filename = filename(editor);
        String selectedText = selectedText(editor);

        try {
            String comment = commentPrompt.promptFor(filename, selectedText);
            Report report = new Report(filename, selectedText, comment);
            publisher.publish(report);
        } catch (CancelledException e) {
        } catch (PublishException e) {
            MessageDialog.openError(editor.getEditorSite().getShell(), "Error While Publishing", e.getMessage());
        }
    }

    private String filename(IEditorPart editor) {
        return editor.getEditorInput().getToolTipText();
    }

    private String selectedText(IEditorPart editor) {
        return ((ITextSelection) editor.getEditorSite().getSelectionProvider()
                .getSelection()).getText();
    }
}
