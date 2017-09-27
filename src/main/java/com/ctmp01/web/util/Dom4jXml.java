package com.ctmp01.web.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class Dom4jXml {
    /**
     * 将String的xml转换为HashMap
     * @param xml 只能有读取一级：<xml><one>这一级</one></xml>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> parserXmlToHashMap(String xml){
        try {
            HashMap<String, Object> xmlMap = null;
            if(!"".equals(xml)){
                xmlMap = new HashMap<String, Object>();
                InputSource in = new InputSource(new StringReader(xml));
                in.setEncoding("UTF-8");
                SAXReader reader = new SAXReader();
                Document document = reader.read(in);
                Element root = document.getRootElement();
                List<Element> elements = root.elements();
                for(Iterator<Element> it = elements.iterator();it.hasNext();){
                    Element element = it.next();
                    xmlMap.put(element.getName(), element.getTextTrim());
                }
            }
            return xmlMap;
        } catch (Exception e) {
            return null;
        }

    }
}
