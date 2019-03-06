package com.jeiker.weixin.utils;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Description: weixin-java-mp-demo-springboot
 * User: jeikerxiao
 * Date: 2019/3/4 4:17 PM
 */
public class XmlUtils {
    public static String format(String unformattedXml) {
        try {
            final Document document = parseXmlFile(unformattedXml);
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        // 未格式化前的xml
        String s = "<xml><URL><![CDATA[http://wx.jeiker.cn/wx/portal/wx732e201a20897ea3]]></URL><ToUserName><![CDATA[gh_b2e6c8d3af64]]></ToUserName><FromUserName><![CDATA[ojUgjt6tkI08QzSfqgzZLKgsVR24]]></FromUserName><CreateTime>1551686064</CreateTime><MsgType><![CDATA[device_text]]></MsgType><DeviceType><![CDATA[gh_b2e6c8d3af64]]></DeviceType><DeviceID><![CDATA[gh_b2e6c8d3af64_3efa69cbb7eb0e35]]></DeviceID><Content><![CDATA[hello]]></Content><SessionID>12</SessionID><MsgId>12</MsgId><OpenID><![CDATA[ojUgjt6tkI08QzSfqgzZLKgsVR24]]></OpenID></xml>";
        System.out.println(XmlUtils.format(s));
    }
}
