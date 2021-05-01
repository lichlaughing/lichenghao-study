package cn.com.lichenghao.hook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author chenghao.li
 * 演示：hook 钩子线程
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        // 注入Hook 线程
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("程序退出，删除.lock 文件！");
            getLockFile().toFile().delete();
        }));

        if (getLockFile().toFile().exists()) {
            throw new RuntimeException("程序已经启动！");
        } else {
            try {
                getLockFile().toFile().createNewFile();
                System.out.println("程序启动，创建.lock 文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 运行
        for (int i = 0; i < 5; i++) {
            TimeUnit.SECONDS.sleep(i);
        }
    }

    // 读取.lock 文件
    private static Path getLockFile() {
        Path path = Paths.get("", "a.lock");
        System.out.println(path.toFile().getAbsolutePath());
        return path;
    }
}
