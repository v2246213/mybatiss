package com.ctmp01.web.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.net.URL;
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
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            File file = new File("C:\\Users\\Administrator\\Desktop\\test.xml");
            SAXReader reader=new SAXReader();
            //读取xml文件到Document中
            Document doc=reader.read(file);
            //获取xml文件的根节点
            Element rootElement=doc.getRootElement();
            //定义一个Element用于遍历
            Element fooElement;
            //遍历所有名叫“VALUE”的节点
            for(Iterator i=rootElement.elementIterator("VALUE");i.hasNext();){
                fooElement=(Element)i.next();
                System.out.println("车牌号："+fooElement.elementText("NO"));
                System.out.println("地区："+fooElement.elementText("ADDR"));
            }
            System.out.println(rootElement.toString());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

       // Dom4jXml.parserXmlToHashMap(" C:/Users/Administrator/Desktop/test.xml");

    }
