package com.sinux.parse;

import com.alibaba.fastjson.JSONObject;
import com.sinux.parse.base.BaseParse;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 * @date 2019-08-21 09:58
 */
public class ParseJson extends BaseParse {
    Map<String,Object> parseMap = new HashMap<String, Object>();

    /**
     * 解析json文件中所有键值对的方法
     * @param file
     * @return
     */
    public Map<String,Object> parse(File file){
        try {
            String s = IOUtils.toString(new FileReader(file));
            parseMap = JSONObject.parseObject(s);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return parseMap;
    }
}
