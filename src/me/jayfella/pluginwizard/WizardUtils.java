package me.jayfella.pluginwizard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.EditableProperties;
import org.openide.util.Exceptions;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author jayfella
 */
public class WizardUtils
{
    public void addWrappedJarToModuleProjectXmlFile(FileObject xmlFile, String jarName)
    {
        try
        {
            Document doc = XMLUtil.parse(new InputSource(new ByteArrayInputStream(xmlFile.asBytes())), false, false, null, null);

            Element runtimeRelativePathElement = doc.createElement("runtime-relative-path");
            runtimeRelativePathElement.appendChild(doc.createTextNode("ext/" + jarName));

            Element binaryOriginElement = doc.createElement("binary-origin");
            binaryOriginElement.appendChild(doc.createTextNode("release/modules/ext/" + jarName));

            Element classPathExtensionElement = doc.createElement("class-path-extension");
            classPathExtensionElement.appendChild(runtimeRelativePathElement);
            classPathExtensionElement.appendChild(binaryOriginElement);

            NodeList nl = doc.getDocumentElement().getElementsByTagName("data");

            Element dataElement = (Element)nl.item(0);
            dataElement.appendChild(classPathExtensionElement);


            OutputStream out = xmlFile.getOutputStream();

            try
            {
                XMLUtil.write(doc, out, "UTF-8");
            }
            finally
            {
                out.close();
            }
        }
        catch (Exception ex)
        {
            Exceptions.printStackTrace(ex);
        }
    }

    public boolean basePackageAlreadyExistsInModuleSuite(FileObject fo, String basePackage)
    {
        try
        {
            EditableProperties ep = loadProperties(fo);
            return (!(ep.getProperty("project." + basePackage) == null));
        }
        catch (IOException ex)
        {
            Exceptions.printStackTrace(ex);
            return false;
        }
    }

    public void setModuleBasePackageInProjectXmlFile(FileObject xmlFile, String basePackage)
    {
        try
        {
            Document doc = XMLUtil.parse(new InputSource(new ByteArrayInputStream(xmlFile.asBytes())), false, false, null, null);
            NodeList nl = doc.getDocumentElement().getElementsByTagName("code-name-base");

            if (nl != null)
            {
                for (int i = 0; i < nl.getLength(); i++)
                {
                    Element el = (Element) nl.item(i);

                    if (el.getParentNode() != null && "data".equals(el.getParentNode().getNodeName()))
                    {
                        NodeList nl2 = el.getChildNodes();

                        if (nl2.getLength() > 0)
                        {
                            nl2.item(0).setNodeValue(basePackage);
                        }

                        break;
                    }
                }
            }

            OutputStream out = xmlFile.getOutputStream();

            try
            {
                XMLUtil.write(doc, out, "UTF-8");
            }
            finally
            {
                out.close();
            }
        }
        catch (Exception ex)
        {
            Exceptions.printStackTrace(ex);
        }
    }

    public void setLicenseInProjectProperties(String projPropPath, String licenseLoc) throws IOException
    {
        File projPropFile = new File(projPropPath);
        FileObject fo = FileUtil.toFileObject(projPropFile);

        String licensePath = "src/" + licenseLoc.replace("\\", "/");

        EditableProperties ep = loadProperties(fo);
        ep.setProperty("license.file", licensePath);
        storeProperties(fo, ep);
    }

    public void copyJmeLicenseToModule(File basePackageDir) throws IOException
    {
        InputStream licenseFileIs = WizardUtils.class.getClassLoader().getResourceAsStream("me/jayfella/pluginwizard/modulefiles/jme-license.txt");
        BufferedReader br = null;

        StringBuilder sb = new StringBuilder();
        String line;

        try
        {
            br = new BufferedReader(new InputStreamReader(licenseFileIs));

            while ((line = br.readLine()) != null)
            {
                sb.append(line).append(System.getProperty("line.separator"));
            }

        }
        catch (IOException ex)
        {
            Exceptions.printStackTrace(ex);
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException ex)
                {
                    Exceptions.printStackTrace(ex);
                }
            }
        }

        File newLicenseFile = new File(basePackageDir.getAbsolutePath() + File.separatorChar + "jme-license.txt");
        newLicenseFile.createNewFile();

        FileWriter fw = new FileWriter(newLicenseFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString());

        bw.close();
    }

    public void createBundlePropertiesFile(FileObject fo, String pluginName) throws IOException
    {
        EditableProperties ep = loadProperties(fo);
        ep.setProperty("OpenIDE-Module-Name", pluginName);
        ep.setProperty("OpenIDE-Module-Short-Description", "A short description...");
        ep.setProperty("OpenIDE-Module-Long-Description", "A long description...");
        ep.setProperty("OpenIDE-Module-Display-Category", "jMonkeyEngine");
        storeProperties(fo, ep);
    }

    public void createModuleManifestFile(File manifestFile, String basePackage) throws IOException
    {
        String basePackagePath = basePackage.replace(".", "/");

        String content = new StringBuilder()
                .append("Manifest-Version: 1.0").append("\n")
                .append("OpenIDE-Module: ").append(basePackage).append("\n")
                .append("OpenIDE-Module-Implementation-Version: 0").append("\n")
                // .append("OpenIDE-Module-Layer: ").append(basePackagePath).append("/layer.xml").append("\n")
                .append("OpenIDE-Module-Localizing-Bundle: ").append(basePackagePath).append("/Bundle.properties").append("\n")
                .toString();

        FileWriter fw = new FileWriter(manifestFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);

        bw.close();
    }

    public void addModuleToSuiteProperties(String propertiesFilePath, String pluginBasePackage, String pluginName) throws IOException
    {
        FileObject propFileObject = FileUtil.toFileObject(new File(propertiesFilePath));

        EditableProperties ep = loadProperties(propFileObject);
        String prop = ep.getProperty("modules");

        if (prop.trim().isEmpty())
        {
            prop = "${project." + pluginBasePackage + "}";
        }
        else
        {
            prop += ":${project." + pluginBasePackage + "}";
        }

        ep.setProperty("modules", prop);
        ep.setProperty("project." + pluginBasePackage, pluginName);

        storeProperties(propFileObject, ep);
    }

    private EditableProperties loadProperties(FileObject propsFO) throws IOException
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

    private void storeProperties(FileObject propsFO, EditableProperties props) throws IOException
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
