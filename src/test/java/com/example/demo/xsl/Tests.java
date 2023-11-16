package com.example.demo.xsl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.io.FileUtils;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class Tests {

    public static void transform(String xmlFile, String xslFile, String targetFile) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
            transformer.transform(new StreamSource(xmlFile), new StreamResult(targetFile));
        } catch (TransformerConfigurationException e) {
            System.err.println(e.getMessage());
        } catch (TransformerException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        String path = "/Users/lishaojun/IdeaProjects/Demo/src/test/java/com/example/demo/xsl/";

        XStream xStream = new XStream(new StaxDriver());
        XStream xstream = new XStream(new StaxDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.ignoreUnknownElements();
        String xml = FileUtils.readFileToString(new File(path + "banks.xml"),
                StandardCharsets.UTF_8);
        System.out.println(xml);


        XmlTest o = (XmlTest) xStream.fromXML(xml,XmlTest.class);

        System.out.println(o);

        /*Processor processor = new Processor(false);
        XsltCompiler compiler = processor.newXsltCompiler();
        XsltExecutable stylesheet = compiler.compile(new StreamSource(new File(path + "banks.xsl")));
        Serializer out = processor.newSerializer(new File(path + "11.html"));
        out.setOutputProperty(Serializer.Property.METHOD, "html");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        Xslt30Transformer transformer = stylesheet.load30();
        transformer.transform(new StreamSource(new File(path + "banks.xml")), out);

        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        System.out.println(out.getXMLStreamWriter().getReceiver().getPipelineConfiguration());*/
    }
}
