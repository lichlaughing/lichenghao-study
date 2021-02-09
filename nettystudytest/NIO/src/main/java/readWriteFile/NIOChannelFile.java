package readWriteFile;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOChannelFile {
    public static void main(String[] args) throws Exception {
        String msg = "hello world";
        FileOutputStream fileOutputStream =
                new FileOutputStream("/Users/lichenghao/Desktop/hello.txt");
        // 得到对应的Channel通道
        FileChannel channel = fileOutputStream.getChannel();
        // 得到Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // Buffer读取数据
        byteBuffer.put(msg.getBytes("UTF-8"));
        // Buffer 由读变成写,写入到Channel
        byteBuffer.flip();
        channel.write(byteBuffer);
        // 关闭流
        fileOutputStream.close();
    }
}
