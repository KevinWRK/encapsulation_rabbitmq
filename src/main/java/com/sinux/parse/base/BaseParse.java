package com.sinux.parse.base;

import java.io.File;
import java.util.Map;

/**
 * @author kevin
 * @date 2019-08-21 09:59
 */
public abstract class BaseParse {
    public abstract Map<String,Object> parse(File file);
}
