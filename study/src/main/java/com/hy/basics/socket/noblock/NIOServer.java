package com.hy.basics.socket.noblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel,监听端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8528));
        //设置为非阻塞
        ssc.configureBlocking(false);
        //为ssc注册选择器
        Selector selector = Selector.open();
        //selector只注册了serverSocketChannel的OP_ACCEPT事件,可以接收serverSocketChannel的socket
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //创建处理器
        Handler handler = new Handler(1024);
        while (true){
            //等待请求，每次等待阻塞3s，超过3s后线程继续向下运行，如果传入0或者不传参数将一直阻塞
            if(selector.select(3000) == 0){
                System.out.println("等待请求超时......");
                continue;
            }
            System.out.println("处理请求...");
            //获取待处理的selectionKey
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

            while(keyIter.hasNext()){
                SelectionKey key = keyIter.next();
                try {
                    //接收到连接请求时,不再接收请求放置
                    if(key.isAcceptable()){
                        handler.handleAccept(key);
                    }
                    //读数据
                    if(key.isReadable()){
                        handler.handleRead(key);
                    }
                }catch (Exception e){
                    keyIter.remove();
                    continue;
                }
                //处理完后，从待处理的SelectionKey迭代器中移除当前所使用的key
                keyIter.remove();
            }
        }
    }


    /**
     * ============================================================
     */
    private static class Handler{
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";

        public Handler() {
        }

        public Handler(int bufferSize) {
            this.bufferSize = bufferSize;
        }

        public Handler(String localCharset) {
            this.localCharset = localCharset;
        }

        public Handler(int bufferSize, String localCharset) {
            this.bufferSize = bufferSize;
            this.localCharset = localCharset;
        }

        /**
         * 1.通过最开始使注册的serverSocketChannel，我们能够获得连接serverSocketChannel的所有socketChannel
         * 2.key.channel()获得socketChannel，并为这个socketChannel注册上读的事件
         * @param key
         * @throws Exception
         */
        public void handleAccept(SelectionKey key)throws Exception{
            SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead(SelectionKey key) throws IOException {
            //获取channel
            SocketChannel sc = (SocketChannel) key.channel();
            //获取buffer并重置
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            //没有读到内容则关闭
            if(sc.read(buffer) == -1){
                sc.close();
            }else{
                //将buffer转换为读状态
                buffer.flip();
                //将buffer中接受到的值按localCharset格式编码后保存到receiveString
                String receiveString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                System.out.println("receive from client:" + receiveString);
                //返回数据给客户端
                String sendString = "received data: " + receiveString;
                buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
                sc.write(buffer);
                //关闭Socket
                sc.close();
            }
        }
    }
}


