package me.jayfella.pluginwizard;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.ZipInputStream;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.EditableProperties;

/**
 *
 * @author jayfella
 */
public class WizardUtils
{
    public static void setModuleName(FileObject fo, ZipInputStream str, String name) throws IOException
    {
        /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileUtil.copy(str, baos);

        String data = baos.toString("UTF-8");
        data = data.replace("OpenIDE-Module-Name=JmonkeyPluginModuleTemplate", "OpenIDE-Module-Name=" + name);

        OutputStream out = fo.getOutputStream();

        PrintStream printStream = new PrintStream(out);
        printStream.print(data);
        printStream.close(); */
        
        EditableProperties ep = loadProperties(fo);
        ep.setProperty("OpenIDE-Module-Name", name);
        storeProperties(fo, ep);
    }

    public static void addModuleToSuiteProperties(String propertiesFilePath, String pluginBasePackage, String pluginName) throws IOException
    {
        FileObject propFileObject = FileUtil.toFileObject(new File(propertiesFilePath));

        EditableProperties ep = loadProperties(propFileObject);
        String prop = ep.getProperty("modules");

        String additional = new StringBuilder()
                .append('\\')
                .append(String.format("%n"))
                .append("${project.")
                .append(pluginBasePackage)
                .append("}")
                .toString();

        ep.setProperty("modules", prop + additional);
        ep.setProperty("project." + pluginBasePackage, pluginName);

        storeProperties(propFileObject, ep);
    }

    private static EditableProperties loadProperties(FileObject propsFO) throws IOException
    {
        InputStream propsIS = propsFO.getInputStream();
        EditableProperties props = new EditableProperties(true);
        try
        {
            props.load(propsIS);
        }
        finally
        {
            propsIS.close();
        }
        return props;
    }

    public static void storeProperties(FileObject propsFO, EditableProperties props) throws IOException
    {
        FileLock lock = propsFO.lock();

        try
        {
            OutputStream os = propsFO.getOutputStream(lock);

            try
            {
                props.store(os);
            }
            finally
            {
                os.close();
            }
        }
        finally
        {
            lock.releaseLock();
        }
    }


}
