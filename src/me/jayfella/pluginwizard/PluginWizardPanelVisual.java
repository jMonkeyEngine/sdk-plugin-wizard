/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jayfella.pluginwizard;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import org.netbeans.spi.project.ui.support.ProjectChooser;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.filesystems.FileUtil;

public class PluginWizardPanelVisual extends JPanel implements DocumentListener, ActionListener {

    public static final String PROP_PROJECT_NAME = "projectName";
    private PluginWizardWizardPanel panel;

    public PluginWizardPanelVisual(PluginWizardWizardPanel panel) {
        initComponents();
        this.panel = panel;

        // Register listener on the textFields to make the automatic updates
        pluginNameTextField.getDocument().addDocumentListener(this);
        suiteLocationTextField.getDocument().addDocumentListener(this);
        pluginBasePackageTextField.getDocument().addDocumentListener(this);

        wrapProjectCheckBox.addActionListener(this);
    }

    public String getProjectName() {
        return this.pluginNameTextField.getText();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pluginNameLabel = new javax.swing.JLabel();
        pluginNameTextField = new javax.swing.JTextField();
        suiteLocationLabel = new javax.swing.JLabel();
        suiteLocationTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        suiteFolderLabel = new javax.swing.JLabel();
        suiteFolderTextField = new javax.swing.JTextField();
        suiteDescriptionLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pluginBasePackageLabel = new javax.swing.JLabel();
        pluginBasePackageTextField = new javax.swing.JTextField();
        pluginFolderLabel = new javax.swing.JLabel();
        pluginFolderTextField = new javax.swing.JTextField();
        projectWrappingDescriptionLabel = new javax.swing.JLabel();
        wrapProjectCheckBox = new javax.swing.JCheckBox();
        wrappedProjectLocationTextField = new javax.swing.JTextField();
        browseWrappedProjectButton = new javax.swing.JButton();

        pluginNameLabel.setLabelFor(pluginNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(pluginNameLabel, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.pluginNameLabel.text")); // NOI18N

        suiteLocationLabel.setLabelFor(suiteLocationTextField);
        org.openide.awt.Mnemonics.setLocalizedText(suiteLocationLabel, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.suiteLocationLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(browseButton, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.browseButton.text")); // NOI18N
        browseButton.setActionCommand(org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.browseButton.actionCommand")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                browseButtonActionPerformed(evt);
            }
        });

        suiteFolderLabel.setLabelFor(suiteFolderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(suiteFolderLabel, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.suiteFolderLabel.text")); // NOI18N

        suiteFolderTextField.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(suiteDescriptionLabel, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.suiteDescriptionLabel.text")); // NOI18N

        pluginBasePackageLabel.setLabelFor(pluginBasePackageTextField);
        org.openide.awt.Mnemonics.setLocalizedText(pluginBasePackageLabel, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.pluginBasePackageLabel.text")); // NOI18N

        pluginBasePackageTextField.setText(org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.pluginBasePackageTextField.text")); // NOI18N
        pluginBasePackageTextField.setToolTipText(org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.pluginBasePackageTextField.toolTipText")); // NOI18N

        pluginFolderLabel.setLabelFor(pluginFolderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(pluginFolderLabel, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.pluginFolderLabel.text")); // NOI18N

        pluginFolderTextField.setEditable(false);
        pluginFolderTextField.setText(org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.pluginFolderTextField.text")); // NOI18N
        pluginFolderTextField.setToolTipText(org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.pluginFolderTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(projectWrappingDescriptionLabel, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.projectWrappingDescriptionLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(wrapProjectCheckBox, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.wrapProjectCheckBox.text")); // NOI18N

        wrappedProjectLocationTextField.setText(org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.wrappedProjectLocationTextField.text")); // NOI18N
        wrappedProjectLocationTextField.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(browseWrappedProjectButton, org.openide.util.NbBundle.getMessage(PluginWizardPanelVisual.class, "PluginWizardPanelVisual.browseWrappedProjectButton.text")); // NOI18N
        browseWrappedProjectButton.setEnabled(false);
        browseWrappedProjectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                browseWrappedProjectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projectWrappingDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(suiteDescriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suiteFolderLabel)
                        .addGap(14, 14, 14)
                        .addComponent(suiteFolderTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suiteLocationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suiteLocationTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pluginNameLabel)
                        .addGap(13, 13, 13)
                        .addComponent(pluginNameTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pluginBasePackageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pluginBasePackageTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pluginFolderLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pluginFolderTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wrapProjectCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wrappedProjectLocationTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseWrappedProjectButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(suiteDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suiteLocationLabel)
                    .addComponent(suiteLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suiteFolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suiteFolderLabel))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pluginNameLabel)
                    .addComponent(pluginNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pluginFolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pluginFolderLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pluginBasePackageLabel)
                    .addComponent(pluginBasePackageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(projectWrappingDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(wrapProjectCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wrappedProjectLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseWrappedProjectButton))
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        String command = evt.getActionCommand();
        if ("BROWSE".equals(command))
        {
            JFileChooser chooser = new JFileChooser();
            FileUtil.preventFileChooserSymlinkTraversal(chooser, null);
            chooser.setDialogTitle("Select Project Location");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String path = this.suiteLocationTextField.getText();
            if (path.length() > 0)
            {
                File f = new File(path);
                if (f.exists())
                {
                    chooser.setSelectedFile(f);
                }
            }
            if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this))
            {
                File projectDir = chooser.getSelectedFile();
                suiteLocationTextField.setText(FileUtil.normalizeFile(projectDir).getAbsolutePath());
            }
            panel.fireChangeEvent();
        }

    }//GEN-LAST:event_browseButtonActionPerformed

    private void browseWrappedProjectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_browseWrappedProjectButtonActionPerformed
    {//GEN-HEADEREND:event_browseWrappedProjectButtonActionPerformed
        FileFilter fileFilter = new FileNameExtensionFilter("Jar File (*.jar)", new String[]{ "jar" });

        JFileChooser chooser = new JFileChooser();
        FileUtil.preventFileChooserSymlinkTraversal(chooser, null);
        chooser.setDialogTitle("Select JAR File");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(fileFilter);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        String path = this.wrappedProjectLocationTextField.getText();

        if (path.length() > 0)
        {
            File f = new File(path);
            if (f.exists())
            {
                chooser.setSelectedFile(f);
            }
        }
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this))
        {
            File projectDir = chooser.getSelectedFile();
            wrappedProjectLocationTextField.setText(FileUtil.normalizeFile(projectDir).getAbsolutePath());
        }

        panel.fireChangeEvent();

    }//GEN-LAST:event_browseWrappedProjectButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton browseWrappedProjectButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel pluginBasePackageLabel;
    private javax.swing.JTextField pluginBasePackageTextField;
    private javax.swing.JLabel pluginFolderLabel;
    private javax.swing.JTextField pluginFolderTextField;
    private javax.swing.JLabel pluginNameLabel;
    private javax.swing.JTextField pluginNameTextField;
    private javax.swing.JLabel projectWrappingDescriptionLabel;
    private javax.swing.JLabel suiteDescriptionLabel;
    private javax.swing.JLabel suiteFolderLabel;
    private javax.swing.JTextField suiteFolderTextField;
    private javax.swing.JLabel suiteLocationLabel;
    private javax.swing.JTextField suiteLocationTextField;
    private javax.swing.JCheckBox wrapProjectCheckBox;
    private javax.swing.JTextField wrappedProjectLocationTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addNotify()
    {
        super.addNotify();

        //same problem as in 31086, initial focus on Cancel button
        pluginNameTextField.requestFocus();
    }

    boolean valid(WizardDescriptor wizardDescriptor)
    {

        if (pluginNameTextField.getText().trim().length() == 0)
        {
            wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "You must specify a Plugin Name.");
            return false;
        }

        File f = FileUtil.normalizeFile(new File(suiteLocationTextField.getText()).getAbsoluteFile());
        if (!f.isDirectory())
        {
            wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "Suite Location Folder is not a valid path.");
            return false;
        }

        if (pluginBasePackageTextField.getText().trim().length() == 0)
        {
            wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "You must specify a base package.");
            return false;
        }

        final File destFolder = FileUtil.normalizeFile(new File(suiteFolderTextField.getText()).getAbsoluteFile());

        File projLoc = destFolder;
        while (projLoc != null && !projLoc.exists())
        {
            projLoc = projLoc.getParentFile();
        }
        if (projLoc == null || !projLoc.canWrite())
        {
            wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "Plugin Suite Folder cannot be created.");
            return false;
        }

        if (FileUtil.toFileObject(projLoc) == null)
        {
            wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "Plugin Suite Folder is not a valid path.");
            return false;
        }

        String suitePath = new StringBuilder()
                .append(suiteLocationTextField.getText().trim())
                .append(File.separatorChar)
                .append("jme-plugins")
                .toString();

        String modulePath = new StringBuilder()
                .append(suitePath)
                .append(File.separatorChar)
                .append(pluginNameTextField.getText().trim())
                .toString();

        if (new File(modulePath).exists())
        {
            wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "This suite already contains a plugin with this name.");
            return false;
        }

        File suiteProjPropertiesFile = new File(suitePath + File.separatorChar + "nbproject" + File.separatorChar + "project.properties");
        if (suiteProjPropertiesFile.exists())
        {
            if (new WizardUtils().basePackageAlreadyExistsInModuleSuite(FileUtil.toFileObject(suiteProjPropertiesFile), this.pluginBasePackageTextField.getText().trim()))
            {
                wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "Base Package has already been defined in another plugin.");
                return false;
            }
        }

        wizardDescriptor.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, "");
        return true;
    }

    void store(WizardDescriptor d)
    {
        String folder = suiteFolderTextField.getText().trim();
        String pluginName = pluginNameTextField.getText().trim().toLowerCase();
        String basePackage = pluginBasePackageTextField.getText().trim().toLowerCase();
        String wrappedJar = this.wrapProjectCheckBox.isSelected() ?  this.wrappedProjectLocationTextField.getText().trim() : "";

        d.putProperty("projdir", new File(folder));
        d.putProperty("pluginName", pluginName);
        d.putProperty("pluginBasePackage", basePackage);
        d.putProperty("wrappedJar", wrappedJar);
    }

    void read(WizardDescriptor settings)
    {
        File projectLocation = (File) settings.getProperty("projdir");
        if (projectLocation == null || projectLocation.getParentFile() == null || !projectLocation.getParentFile().isDirectory())
        {
            projectLocation = ProjectChooser.getProjectsFolder();
        } else
        {
            projectLocation = projectLocation.getParentFile();
        }
        this.suiteLocationTextField.setText(projectLocation.getAbsolutePath());

        String pluginName = (String) settings.getProperty("pluginName");
        if (pluginName == null)
        {
            pluginName = "my-library";
        }
        this.pluginNameTextField.setText(pluginName);
        this.pluginNameTextField.selectAll();

        String pluginBasePackage = (String) settings.getProperty("pluginBasePackage");
        if (pluginBasePackage == null)
        {
            pluginBasePackage = "com.mycompany.plugins.mylibrary";
        }
        this.pluginBasePackageTextField.setText(pluginBasePackage);
    }

    void validate(WizardDescriptor d) throws WizardValidationException
    {
        // nothing to validate
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
        updateTexts(e);

        if (this.pluginNameTextField.getDocument() == e.getDocument())
            firePropertyChange(PROP_PROJECT_NAME, null, this.pluginNameTextField.getText());

        else if (this.pluginBasePackageTextField.getDocument() == e.getDocument())
            firePropertyChange(PROP_PROJECT_NAME, null, this.pluginBasePackageTextField.getText());
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        updateTexts(e);

        if (this.pluginNameTextField.getDocument() == e.getDocument())
            firePropertyChange(PROP_PROJECT_NAME, null, this.pluginNameTextField.getText());

        else if (this.pluginBasePackageTextField.getDocument() == e.getDocument())
            firePropertyChange(PROP_PROJECT_NAME, null, this.pluginBasePackageTextField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        updateTexts(e);

        if (this.pluginNameTextField.getDocument() == e.getDocument())
            firePropertyChange(PROP_PROJECT_NAME, null, this.pluginNameTextField.getText());

        else if (this.pluginBasePackageTextField.getDocument() == e.getDocument())
            firePropertyChange(PROP_PROJECT_NAME, null, this.pluginBasePackageTextField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (this.wrapProjectCheckBox == e.getSource())
        {
            this.wrappedProjectLocationTextField.setEnabled(this.wrapProjectCheckBox.isSelected());
            this.browseWrappedProjectButton.setEnabled(this.wrapProjectCheckBox.isSelected());
        }
    }

    private void updateTexts(DocumentEvent e)
    {
        Document doc = e.getDocument();

        if (doc == pluginNameTextField.getDocument() || doc == suiteLocationTextField.getDocument())
        {
            String pluginName = pluginNameTextField.getText().trim();
            String projectFolder = suiteLocationTextField.getText().trim();

            String suitePath = new StringBuilder()
                    .append(projectFolder)
                    .append(File.separatorChar)
                    .append("jme-plugins")
                    .toString();
            suiteFolderTextField.setText(suitePath);

            String modulePath = new StringBuilder()
                    .append(suitePath)
                    .append(File.separatorChar)
                    .append(pluginName)
                    .toString();
            pluginFolderTextField.setText(modulePath);

            if (!new File(suitePath).exists())
            {
                suiteFolderTextField.setBackground(new Color(1f, .98f, 0.6f));
                suiteFolderTextField.setToolTipText("A 'jme-plugins' suite does not exist in this directory. One will be created.");
            }
            else
            {
                suiteFolderTextField.setBackground(new Color(.525f, .976f, .607f));
                suiteFolderTextField.setToolTipText("A 'jme-plugins' suite has been found in this directory.");
            }

        }

        panel.fireChangeEvent();
    }


}
