package com.hy.basics.socket.noblock;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel,监听端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8528));
        //设置为非阻塞
        ssc.configureBlocking(false);
        //为ssc注册选择器
        Selector selector = Selector.open();
        //??SelectionKey.OP_ACCEPT
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //创建处理器
//        new Handler(1024);
        while (true){
            //等待请求，每次等待阻塞3s，超过3s后线程继续向下运行，如果传入0或者不传参数将一直阻塞
            if(selector.select(3000) == 0){

            }
            //获取待处理的selectionKey

        }
    }


    private static class Handler{
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";
    }
}


