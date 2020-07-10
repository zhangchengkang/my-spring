package com.ksf.beans.io;

import java.net.URL;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 15:53
 */
public class ResourceLoader {

    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
