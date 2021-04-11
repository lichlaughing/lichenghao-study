package cn.com.lichenghao.sync.threadlocal;

/**
 * @author chenghao.li
 * 演示演示 ThreadLocal 初始化默认值
 */
public class ThreadLocalTest02 {
    public static void main(String[] args) {
        SubThreadLocal subThreadLocal = new SubThreadLocal();
        String s = subThreadLocal.get();
        System.out.println(s);
    }

    static class SubThreadLocal extends ThreadLocal<String> {
        @Override
        protected String initialValue() {
            return "Hello";
        }
    }
}
