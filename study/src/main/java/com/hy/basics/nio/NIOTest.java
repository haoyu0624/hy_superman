package com.hy.basics.nio;/**
 * Created by haoy on 2018/4/12.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @auther haoy
 * @create 2018/4/12
 */
public class NIOTest {
    public static void main(String[] args) throws IOException {
        File file = new File("F://data.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String tt = "java nio nbnio";
        buffer.put(tt.getBytes());
        buffer.flip();
        channel.write(buffer);
        channel.close();
        fileOutputStream.close();
    }
}
