package org.bgprocess.wtf.eclipse;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.bgprocess.wtf.eclipse.EditorReporter;
import org.bgprocess.wtf.report.CollectionPublisher;
import org.bgprocess.wtf.report.CommentPrompt;
import org.bgprocess.wtf.report.Publisher;
import org.bgprocess.wtf.report.Report;
import org.bgprocess.wtf.report.Reporter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class EditorReporterTest {
    private final IEditorPart editor = new StubbedEditor("filename", "code");
    private final Reporter reporter = new EditorReporter(editor, new CommentPrompt() {
        @Override
        public String promptFor(String filename, String selectedText) {
            return "";
        }
    });
    private final List<Report> list = new ArrayList<Report>();
    private final Publisher publisher = new CollectionPublisher(list);
    
    @Test public void
    uses_the_tooltip_as_the_filename() {
        reporter.reportTo(publisher);

        assertEquals("filename", list.get(0).filename);
    }
    
    @Test public void
    uses_the_selected_text_as_the_code() {
        reporter.reportTo(publisher);

        assertEquals("code", list.get(0).code);
    }
    
    @Test public void
    publishes_the_new_report() {
        reporter.reportTo(publisher);
        
        assertEquals(1, list.size());
        assertEquals("filename", list.get(0).filename);
        assertEquals("code", list.get(0).code);
    }

    private static final class StubbedEditor implements IEditorPart {

        private final String tooltip;
        private final String selectedText;

        public StubbedEditor(String tooltip, String selectedText) {
            this.tooltip = tooltip;
            this.selectedText = selectedText;
        }

        @Override
        public void addPropertyListener(IPropertyListener arg0) {
            throw new NoSuchMethodError("addPropertyListener");
        }

        @Override
        public void createPartControl(Composite arg0) {
            throw new NoSuchMethodError("createPartControl");
        }

        @Override
        public void dispose() {
            throw new NoSuchMethodError("dispose");
        }

        @Override
        public IWorkbenchPartSite getSite() {
            throw new NoSuchMethodError("getSite");
        }

        @Override
        public String getTitle() {
            throw new NoSuchMethodError("getTitle");
        }

        @Override
        public Image getTitleImage() {
            throw new NoSuchMethodError("getTitleImage");
        }

        @Override
        public String getTitleToolTip() {
            throw new NoSuchMethodError("getTitleToolTip");
        }

        @Override
        public void removePropertyListener(IPropertyListener arg0) {
            throw new NoSuchMethodError("removePropertyListener");
        }

        @Override
        public void setFocus() {
            throw new NoSuchMethodError("setFocus");
        }

        @Override
        public Object getAdapter(@SuppressWarnings("rawtypes") Class arg0) {
            throw new NoSuchMethodError("getAdapter");
        }

        @Override
        public void doSave(IProgressMonitor arg0) {
            throw new NoSuchMethodError("doSave");
        }

        @Override
        public void doSaveAs() {
            throw new NoSuchMethodError("doSaveAs");
        }

        @Override
        public boolean isDirty() {
            throw new NoSuchMethodError("isDirty");
        }

        @Override
        public boolean isSaveAsAllowed() {
            throw new NoSuchMethodError("isSaveAsAllowed");
        }

        @Override
        public boolean isSaveOnCloseNeeded() {
            throw new NoSuchMethodError("isSaveOnCloseNeeded");
        }

        @Override
        public IEditorInput getEditorInput() {
            return new IEditorInput() {
                @Override
                public Object getAdapter(@SuppressWarnings("rawtypes") Class arg0) {
                    throw new NoSuchMethodError("getAdapter");
                }

                @Override
                public String getToolTipText() {
                    return tooltip;
                }

                @Override
                public IPersistableElement getPersistable() {
                    throw new NoSuchMethodError("getPersistable");
                }

                @Override
                public String getName() {
                    throw new NoSuchMethodError("getName");
                }

                @Override
                public ImageDescriptor getImageDescriptor() {
                    throw new NoSuchMethodError("getImageDescriptor");
                }

                @Override
                public boolean exists() {
                    throw new NoSuchMethodError("exists");
                }
            };
        }

        @Override
        public IEditorSite getEditorSite() {
            return new IEditorSite() {
                
                @Override
                public boolean hasService(@SuppressWarnings("rawtypes") Class arg0) {
                    throw new NoSuchMethodError("hasService");
                }
                
                @Override
                public Object getService(@SuppressWarnings("rawtypes") Class arg0) {
                    throw new NoSuchMethodError("getService");
                }
                
                @Override
                public Object getAdapter(@SuppressWarnings("rawtypes") Class arg0) {
                    throw new NoSuchMethodError("getAdapter");
                }
                
                @Override
                public void setSelectionProvider(ISelectionProvider arg0) {
                    throw new NoSuchMethodError("setSelectionProvider");
                }
                
                @Override
                public IWorkbenchWindow getWorkbenchWindow() {
                    throw new NoSuchMethodError("getWorkbenchWindow");
                }
                
                @Override
                public Shell getShell() {
                    throw new NoSuchMethodError("getShell");
                }
                
                @Override
                public ISelectionProvider getSelectionProvider() {
                    return new ISelectionProvider() {
                        
                        @Override
                        public void setSelection(ISelection arg0) {
                            throw new NoSuchMethodError("setSelection");
                        }
                        
                        @Override
                        public void removeSelectionChangedListener(ISelectionChangedListener arg0) {
                            throw new NoSuchMethodError("removeSelectionChangedListener");
                        }
                        
                        @Override
                        public ISelection getSelection() {
                            return new ITextSelection() {
                                
                                @Override
                                public boolean isEmpty() {
                                    throw new NoSuchMethodError("isEmpty");
                                }
                                
                                @Override
                                public String getText() {
                                    return selectedText;
                                }
                                
                                @Override
                                public int getStartLine() {
                                    throw new NoSuchMethodError("getStartLine");
                                }
                                
                                @Override
                                public int getOffset() {
                                    throw new NoSuchMethodError("getOffset");
                                }
                                
                                @Override
                                public int getLength() {
                                    throw new NoSuchMethodError("getLength");
                                }
                                
                                @Override
                                public int getEndLine() {
                                    throw new NoSuchMethodError("getEndLine");
                                }
                            };
                        }
                        
                        @Override
                        public void addSelectionChangedListener(ISelectionChangedListener arg0) {
                            throw new NoSuchMethodError("addSelectionChangedListener");
                        }
                    };
                }
                
                @Override
                public IWorkbenchPage getPage() {
                    throw new NoSuchMethodError("getPage");
                }
                
                @Override
                public void registerContextMenu(String arg0, MenuManager arg1,
                        ISelectionProvider arg2) {
                    throw new NoSuchMethodError("registerContextMenu");
                    
                }
                
                @Override
                public void registerContextMenu(MenuManager arg0, ISelectionProvider arg1) {
                    throw new NoSuchMethodError("registerContextMenu");
                }
                
                @Override
                public String getRegisteredName() {
                    throw new NoSuchMethodError("getRegisteredName");
                }
                
                @Override
                public String getPluginId() {
                    throw new NoSuchMethodError("getPluginId");
                    
                }
                
                @Override
                public IWorkbenchPart getPart() {
                    throw new NoSuchMethodError("getPart");
                }
                
                @Override
                public IKeyBindingService getKeyBindingService() {
                    throw new NoSuchMethodError("getKeyBindingService");
                }
                
                @Override
                public String getId() {
                    throw new NoSuchMethodError("getId");
                }
                
                @Override
                public void registerContextMenu(String arg0, MenuManager arg1,
                        ISelectionProvider arg2, boolean arg3) {
                    throw new NoSuchMethodError("registerContextMenu");
                }
                
                @Override
                public void registerContextMenu(MenuManager arg0, ISelectionProvider arg1,
                        boolean arg2) {
                    throw new NoSuchMethodError("registerContextMenu");
                }
                
                @Override
                public IActionBars getActionBars() {
                    throw new NoSuchMethodError("getActionBars");
                }
                
                @Override
                public IEditorActionBarContributor getActionBarContributor() {
                    throw new NoSuchMethodError("getActionBarContributor");
                }
            };
        }

        @Override
        public void init(IEditorSite arg0, IEditorInput arg1)
                throws PartInitException {
            throw new NoSuchMethodError("init");
        }
    }
}
