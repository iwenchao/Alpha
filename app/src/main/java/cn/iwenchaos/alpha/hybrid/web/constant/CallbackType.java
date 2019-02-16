package cn.iwenchaos.alpha.hybrid.web.constant;

/**
 * Created by chaos
 * on 2019/2/16. 17:00
 * 文件描述：
 */
public enum CallbackType {

    SUCCESS("SUCCESS"),FAIL("FAIL"),CANCEL("CANCEL"),COMPLETED("COMPLETED");

    private String value;

    CallbackType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
