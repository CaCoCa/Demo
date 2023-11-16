package com.example.demo.xml;

import cn.hutool.core.util.XmlUtil;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

/**
 * xml工具类
 * Created by haoj on 2017/9/7.
 */
public class XmlUtils {
    public static Document getDocument(InputStream file) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true); // 没有这句会出问题
        return documentBuilderFactory.newDocumentBuilder().parse(file);
    }

    /**
     * 根据输入了String转换为Document
     * @param string
     * @return
     * @throws Exception
     */
    public static Document getDocument(String string) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        StringReader stringReader = new StringReader(string);
        // InputSource xml的单一输入源
        InputSource inputSource = new InputSource(stringReader);
        return  documentBuilderFactory.newDocumentBuilder().parse(inputSource);
    }

    public static String documentToString(Document document) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("encoding", "UTF-8");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        transformer.transform(new DOMSource(document), new StreamResult(outputStream));
        return outputStream.toString();
    }

    public static void documentToFile(Document document, String filePath) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filePath)));
    }

    public static void main(String[] args) throws Exception {

        String xmlPath = "/Users/lishaojun/IdeaProjects/Demo/src/test/java/com/example/demo/xsl/banks.xml";
        String xml = FileUtils.readFileToString(new File(xmlPath), StandardCharsets.UTF_8);


        Document document = getDocument(xml);

        NodeList banks = document.getElementsByTagName("banks");




        String a = XmlUtil.toStr(banks.item(0),false);



        System.out.println(new String(compress(a), StandardCharsets.UTF_8));



        //System.out.println(documentToString(document));


    }


    public static byte[] compress(String string) {
        byte[] compressed = null;
        try {/*from   w  w w .j av a2s  .c o m*/
            ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
            GZIPOutputStream gos = new GZIPOutputStream(os);
            gos.write(string.getBytes());
            gos.close();
            compressed = os.toByteArray();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compressed;
    }

    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
}