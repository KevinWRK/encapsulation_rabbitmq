package com.sinux.parse;

import com.sinux.parse.base.BaseParse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author kevin
 * @date 2019-08-21 09:57
 */
public class ParseXml extends BaseParse {
    Map<String,Object> XMLMap = new HashMap<String, Object>();


    public Map<String, Object> parse(File file) {

        try {
            //解析xml中的数据
            SAXReader reader = new SAXReader();
            //通过加载classpath下的xml文件来获取document对象
            Document document = reader.read(file);
            //将文件下的第一节点元素交给parseElement解析
            parseElement(document.getRootElement());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return XMLMap;
    }

    /**
     * 解析每一个结点元素
     * @param rootElement
     */
    public void parseElement(Element rootElement){
        Element element;
        //获取节点元素里的所有的子节点元素，并放到迭代器里面迭代,判断条件为迭代器里面是否还有下一个元素
        Iterator<?> iterator = rootElement.elementIterator();
        while (iterator.hasNext()){
            //获取子节点元素的对象
            element = (Element) iterator.next();
            XMLMap.put(element.getName(),element.getTextTrim());
            //判断是否为节点
            if (element.getNodeType() == Node.ELEMENT_NODE){
                //判断节点里是否还有内容
                if (element.hasContent()){
                    parseElement(element);
                }
            }
        }
    }
}
