package com.hy.basics.nio;/**
 * Created by haoy on 2018/4/12.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @auther haoy
 * @create 2018/4/12
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
//        File file = new File("F://data.txt");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        byte[] bytes = new byte[1024];
//        int read = fileInputStream.read(bytes);
//        if(read != -1){
//            System.out.println(read);
//        }
//        fileInputStream.close();
        File file = new File("F://data.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String tt = "java nio nbio";
        fileOutputStream.write(tt.getBytes());
        fileOutputStream.close();
    }
}
