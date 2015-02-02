Plugins Wizard for jMonkeyEngine 3 SDK
======================================

[Development Thread](http://hub.jmonkeyengine.org/t/plugin-wizard-improved-plugin-publishing-procedure/28888)

An implementation of a plugin wizard - reducing the steps required to create a jmonkey plugin by means of automation.

Useage: 
The instantiate() method in file pluginWizardIterator.java is where the logic lies when the finish button is pressed.
The boolean valid(WizardDescriptor wizardDescriptor) method in pluginWizardPanelVisial.java is where the validation logic lies.

The wizard checks if a plugin suite exists in the given directory. If one does not exist, it unzips the file 'ModuleSuiteTemplate.zip' in a sub-folder called 'jme-plugins'. The wizard then checks if a plugin of the same name exists within the module suite. If  valid(WizardDescriptor wizardDescriptor) returns true the wizard unzips the file 'ModuleTemplate.zip' in ./jme-plugins/pluginName

The WizardUtils.java class provides helper methods to edit files such as 'build.xml' and 'project.properties'. Modifications to these files are required for plugin renaming, module integration into the suite and other such tasks.

TODO:
- Provide a better method for module-suite existence. Currently only checking if directory exists.
- Provide a better method for plugin existence. Currently only checking if directory exists.
