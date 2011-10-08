package org.bgprocess.wtf.preferences;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.bgprocess.wtf.eclipse.Activator;

public class WTFPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public WTFPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	public void createFieldEditors() {
		addField(
			new UrlFieldEditor(PreferenceConstants.P_SERVER, "&Server URL:", getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
	}
	
	private static class UrlFieldEditor extends StringFieldEditor {

        public UrlFieldEditor(String key, String label, Composite fieldEditorParent) {
            super(key, label, fieldEditorParent);
            setValidateStrategy(VALIDATE_ON_FOCUS_LOST);
            setErrorMessage("Invalid URL");
        }
	    
        @Override
        public boolean isValid() {
            try {
                URL url = new URL(getStringValue());
                String protocol = url.getProtocol();
                return (protocol.equals("http") || protocol.equals("https"))
                    && !url.getHost().isEmpty();
            } catch (MalformedURLException e) {
                return false;
            }
        }
	}
}