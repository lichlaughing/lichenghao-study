package readWriteFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOChannelReadFile {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream =
                new FileInputStream(new File("/Users/lichenghao/Desktop/hello.txt"));
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
