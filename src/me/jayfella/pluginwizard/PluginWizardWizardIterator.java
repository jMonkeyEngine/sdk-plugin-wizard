/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jayfella.pluginwizard;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.spi.project.ui.support.ProjectChooser;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;

@TemplateRegistration(folder = "Project/JME3", displayName = "#PluginWizard_displayName", description = "PluginWizardDescription.html", iconBase = "me/jayfella/pluginwizard/jmonkeyplugin.png")
@Messages("PluginWizard_displayName=JME Plugin")
public class PluginWizardWizardIterator implements WizardDescriptor./*Progress*/InstantiatingIterator
{
    private int index;
    private WizardDescriptor.Panel[] panels;
    private WizardDescriptor wiz;

    public PluginWizardWizardIterator()
    {
    }

    public static PluginWizardWizardIterator createIterator()
    {
        return new PluginWizardWizardIterator();
    }

    private WizardDescriptor.Panel[] createPanels()
    {
        return new WizardDescriptor.Panel[]
        {
            new PluginWizardWizardPanel(),
        };
    }

    private String[] createSteps()
    {
        return new String[]
        {
            NbBundle.getMessage(PluginWizardWizardIterator.class, "LBL_CreateProjectStep")
        };
    }

    private void createModuleSuite(FileObject suiteDir) throws IOException
    {
        File file = FileUtil.normalizeFile(new File("src/me/jayfella/pluginwizard/ModuleSuiteTemplate.zip"));
        FileObject suiteTemplate = FileUtil.toFileObject(file);

        ZipInputStream str = new ZipInputStream(suiteTemplate.getInputStream());
        ZipEntry entry;

        try
        {
            while ((entry = str.getNextEntry()) != null)
            {
                if (entry.isDirectory())
                {
                    FileUtil.createFolder(suiteDir, entry.getName());
                }
                else
                {
                    FileObject fo = FileUtil.createData(suiteDir, entry.getName());

                    if (entry.getName().equals("src/myplugin/Bundle.properties"))
                    {
                        // setModuleName(fo, str, suiteDir.getName());
                    }
                    else
                    {
                        writeFile(str, fo);
                    }
                }
            }
        }
        finally
        {
            str.close();
        }
    }

    private void createModule(FileObject moduleDir) throws IOException
    {
        File file = FileUtil.normalizeFile(new File("src/me/jayfella/pluginwizard/ModuleTemplate.zip"));
        FileObject moduleTemplate = FileUtil.toFileObject(file);

        ZipInputStream str = new ZipInputStream(moduleTemplate.getInputStream());
        ZipEntry entry;

        try
        {
            while ((entry = str.getNextEntry()) != null)
            {
                if (entry.isDirectory())
                {
                    FileUtil.createFolder(moduleDir, entry.getName());
                }
                else
                {
                    FileObject fo = FileUtil.createData(moduleDir, entry.getName());

                    if (entry.getName().equals("src/myplugin/Bundle.properties"))
                    {
                        WizardUtils.setModuleName(fo, str, moduleDir.getName());
                    }
                    else
                    {
                        writeFile(str, fo);
                    }
                }
            }
        }
        finally
        {
            str.close();
        }
    }

    @Override
    public Set<FileObject> instantiate() throws IOException
    {
        String pluginName = (String)wiz.getProperty("pluginName");

        // File baseDir = FileUtil.normalizeFile((File) wiz.getProperty("projdir"));
        // File suiteDir = new File(baseDir.getAbsolutePath() + File.separatorChar + "jme-plugins");
        File suiteDir = FileUtil.normalizeFile((File) wiz.getProperty("projdir"));
        File moduleDir = new File(suiteDir.getAbsolutePath() + File.separatorChar + pluginName);

        // check if module suite exists.
        if (!suiteDir.exists())
        {
            suiteDir.mkdirs();
            createModuleSuite(FileUtil.toFileObject(suiteDir));
        }

        moduleDir.mkdirs();

        // add module to module suite.
        createModule(FileUtil.toFileObject(moduleDir));

        // create the base package in the module.
        String basePackage = wiz.getProperty("pluginBasePackage").toString().replace(".", "" + File.separatorChar);
        File basePackageDir = new File(moduleDir.getAbsolutePath() + File.separatorChar + "src" + File.separatorChar + basePackage);
        basePackageDir.mkdirs();

        // add the new module to the suite
        String suiteProjectPropertiesFile = new StringBuilder()
                .append(suiteDir.getAbsolutePath())
                .append(File.separatorChar)
                .append("nbproject")
                .append(File.separatorChar)
                .append("project.properties")
                .toString();

        WizardUtils.addModuleToSuiteProperties(suiteProjectPropertiesFile, wiz.getProperty("pluginBasePackage").toString(), pluginName);

        Set<FileObject> resultSet = new LinkedHashSet<FileObject>();

        // Always open top dir as a project:
        FileObject suiteFileObject = FileUtil.toFileObject(suiteDir);
        resultSet.add(suiteFileObject);

        // Look for nested projects to open as well:
        Enumeration<? extends FileObject> e = suiteFileObject.getFolders(true);
        while (e.hasMoreElements())
        {
            FileObject subfolder = e.nextElement();
            if (ProjectManager.getDefault().isProject(subfolder))
            {
                resultSet.add(subfolder);
            }
        }

        File parent = suiteDir.getParentFile();
        if (parent != null && parent.exists())
        {
            ProjectChooser.setProjectsFolder(parent);
        }

        return resultSet;
    }

    @Override
    public void initialize(WizardDescriptor wiz)
    {
        this.wiz = wiz;
        index = 0;
        panels = createPanels();

        String[] steps = createSteps();

        for (int i = 0; i < panels.length; i++)
        {
            Component c = panels[i].getComponent();

            if (steps[i] == null)
            {
                steps[i] = c.getName();
            }

            if (c instanceof JComponent)
            {
                JComponent jc = (JComponent) c;

                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, new Integer(i));
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
            }
        }
    }

    @Override
    public void uninitialize(WizardDescriptor wiz)
    {
        this.wiz.putProperty("projdir", null);
        this.wiz.putProperty("name", null);
        this.wiz.putProperty("pluginBasePackage", null);

        this.wiz = null;
        panels = null;
    }

    @Override
    public String name()
    {
        return MessageFormat.format("{0} of {1}", new Object[] { new Integer(index + 1), new Integer(panels.length) });
    }

    @Override
    public boolean hasNext()
    {
        return index < panels.length - 1;
    }

    @Override
    public boolean hasPrevious()
    {
        return index > 0;
    }

    @Override
    public void nextPanel()
    {
        if (!hasNext())
            throw new NoSuchElementException();

        index++;
    }

    @Override
    public void previousPanel()
    {
        if (!hasPrevious())
            throw new NoSuchElementException();

        index--;
    }

    @Override
    public WizardDescriptor.Panel current()
    {
        return panels[index];
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    @Override public final void addChangeListener(ChangeListener l) { }
    @Override public final void removeChangeListener(ChangeListener l) { }

    private static void writeFile(ZipInputStream str, FileObject fo) throws IOException
    {
        OutputStream out = fo.getOutputStream();

        try
        {
            FileUtil.copy(str, out);
        }
        finally
        {
            out.close();
        }
    }



}
