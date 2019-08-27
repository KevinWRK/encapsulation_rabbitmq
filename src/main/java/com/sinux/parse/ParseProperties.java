package com.sinux.parse;

import com.sinux.parse.base.BaseParse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author kevin
 * @date 2019-08-21 09:58
 */
public class ParseProperties extends BaseParse {
    Map<String,Object> parseMap = new HashMap<String, Object>();
    /**
     * 解析Properties文件的方法
     * @param file
     * @return
     */
    public Map<String, Object> parse(File file) {

        try {
            Properties prop = new Properties();
            //加载Properties文件
            prop.load(new FileInputStream(file));
            //获取properties里所有的键值对
            Set<Map.Entry<Object, Object>> entries = prop.entrySet();
            //遍历所有的键值对
            for (Map.Entry<Object, Object> entry : entries) {
                //将遍历出来的键值对放到map集合里面
                parseMap.put((String) entry.getKey(),entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return parseMap;
    }
}
