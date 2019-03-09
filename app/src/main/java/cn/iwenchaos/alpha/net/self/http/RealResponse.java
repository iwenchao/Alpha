package cn.iwenchaos.alpha.net.self.http;

import java.io.InputStream;

/**
 * Created by chaos
 * on 2019/3/9. 19:59
 * 文件描述：
 */
public class RealResponse {
    public InputStream inputStream;
    public InputStream errorStream;
    public int code;
    public long contentLength;
    public Exception exception;
}
