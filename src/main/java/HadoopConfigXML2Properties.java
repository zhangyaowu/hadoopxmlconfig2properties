import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HadoopConfigXML2Properties
{
    public static void main(String[] args)
    {
        String xmlAddress = "G:\\Code\\Persona\\CKM\\SRC_CODE\\ckm-hbase-adapter\\conf\\core-site.xml";
        String toProAddess = "G:\\Conf\\core-site.properties";
        readXML(xmlAddress, toProAddess);

        String xmlAddress2 = "G:\\Code\\Persona\\CKM\\SRC_CODE\\ckm-hbase-adapter\\conf\\hdfs-site.xml";
        String toProAddess2 = "G:\\Conf\\hdfs-site.properties";
        readXML(xmlAddress2, toProAddess2);

        String xmlAddress3 = "G:\\Code\\Persona\\CKM\\SRC_CODE\\ckm-hbase-adapter\\conf\\mapred-site.xml";
        String toProAddess3 = "G:\\Conf\\mapred-site.properties";
        readXML(xmlAddress3, toProAddess3);

        String xmlAddress4 = "G:\\Code\\Persona\\CKM\\SRC_CODE\\ckm-hbase-adapter\\conf\\yarn-site.xml";
        String toProAddess4 = "G:\\Conf\\yarn-site.properties";
        readXML(xmlAddress4, toProAddess4);
        
        String xmlAddress5 = "G:\\Code\\Persona\\CKM\\SRC_CODE\\ckm-hbase-adapter\\conf\\hbase-site.xml";
        String toProAddess5 = "G:\\Conf\\hbase-site.properties";
        readXML(xmlAddress5, toProAddess5);
    }

    @SuppressWarnings("rawtypes")
    static void readXML(String xmlAddress, String toProAddess)
    {
        try
        {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(xmlAddress));

            File toPro = new File(toProAddess);
            BufferedWriter bw = new BufferedWriter(new FileWriter(toPro));

            Element root = document.getRootElement();
            List propertyList = root.elements();
            for (int i = 0; i < propertyList.size(); i++)
            {
                Element propertyElement = (Element) propertyList.get(i);
                List elements = propertyElement.elements();
                String keyValue = null;
                for (int m = 0; m < elements.size(); m++)
                {
                    Element elemBook = (Element) elements.get(m);
                    if (m == 0)
                    {
                        keyValue = elemBook.getText();
                    }
                    else
                    {
                        keyValue += ("=" + elemBook.getText());
                        bw.write(keyValue);
                        bw.newLine();
                    }
                }
            }
            bw.close();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
