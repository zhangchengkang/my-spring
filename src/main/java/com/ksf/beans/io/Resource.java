package com.ksf.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 15:35
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
