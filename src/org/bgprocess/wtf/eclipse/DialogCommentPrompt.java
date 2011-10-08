package org.bgprocess.wtf.eclipse;

import org.bgprocess.wtf.report.CancelledException;
import org.bgprocess.wtf.report.CommentPrompt;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DialogCommentPrompt implements CommentPrompt {
    private final Shell parentShell;
    
    public DialogCommentPrompt(Shell parentShell) {
        this.parentShell = parentShell;
    }

    @Override
    public String promptFor(String filename, String selectedText) throws CancelledException {
        CommentDialog dialog = new CommentDialog(parentShell, filename, selectedText);
        int clicked = dialog.open();
        if (clicked == Window.OK) {
            return dialog.getValue();
        }
        throw new CancelledException();
    }
    
    private static class CommentDialog extends Dialog {
        private final String selectedText;
        private Text commentArea;
        private final String filename;
        private String comment;

        protected CommentDialog(Shell parentShell, String filename, String selectedText) {
            super(parentShell);
            setShellStyle(getShellStyle() | SWT.RESIZE);
            this.filename = filename;
            this.selectedText = selectedText;
        }
        
        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText(filename);
            newShell.setSize(250, 300);
        }
        
        @Override
        protected Control createDialogArea(Composite parent) {
            Composite composite = (Composite)super.createDialogArea(parent);
            FormLayout layout = new FormLayout();
            composite.setLayout(layout);
            
            Text textUnderReview = textArea(composite);
            textUnderReview.setEditable(false);
            textUnderReview.setText(selectedText);
            FormData reviewLayout = new FormData();
            reviewLayout.top = new FormAttachment(0, 2);
            reviewLayout.left = new FormAttachment(0, 2);
            reviewLayout.bottom = new FormAttachment(1, 2, -2);
            reviewLayout.right = new FormAttachment(1, 1, -2);
            textUnderReview.setLayoutData(reviewLayout);
            
            commentArea = textArea(composite);
            FormData commentLayout = new FormData();
            commentLayout.top = new FormAttachment(textUnderReview, 5);
            commentLayout.left = reviewLayout.left;
            commentLayout.bottom = new FormAttachment(2, 2, -2);
            commentLayout.right = reviewLayout.right;
            commentArea.setLayoutData(commentLayout);
            return composite;
        }

        @Override
        protected void okPressed() {
            super.okPressed();
            comment = commentArea.getText();
        }
        
        public String getValue() {
            return comment;
        }
        
        private Text textArea(Composite composite) {
            return new Text(composite, SWT.MULTI |
                    SWT.V_SCROLL | SWT.H_SCROLL |
                    SWT.BORDER);
        }
    }
}
