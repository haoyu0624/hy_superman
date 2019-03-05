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

public class HttpServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel,监听端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8529));
        //设置为非阻塞
        ssc.configureBlocking(false);
        //为ssc注册选择器
        Selector selector = Selector.open();
        //selector只注册了serverSocketChannel的OP_ACCEPT事件,可以接收serverSocketChannel的socket
        ssc.register(selector, SelectionKey.OP_ACCEPT);
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
                //启动新线程处理SelectionKey
                new Thread(new HttpHandler(key)).run();
                //处理完后，从待处理的SelectionKey迭代器中移除当前所使用的key
                keyIter.remove();
            }
        }
    }


    /**
     * ============================================================
     */
    private static class HttpHandler implements Runnable{
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";
        private SelectionKey key;

        public HttpHandler(SelectionKey key) {
            this.key = key;
        }

        /**
         * 1.通过最开始使注册的serverSocketChannel，我们能够获得连接serverSocketChannel的所有socketChannel
         * 2.key.channel()获得socketChannel，并为这个socketChannel注册上读的事件
         * @throws Exception
         */
        public void handleAccept()throws Exception{
            SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }

        public void handleRead() throws IOException {
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
                //======>>>>  打印报文头
                String[] split = receiveString.split("\r\n");
                for (String s : split) {
                    System.out.println("报文头 = " + s);
                    //遇到空行说明报文头已经打印完
                    if(s.isEmpty()){
                        break;
                    }
                }
                //======>>>>  打印首行信息
                String[] firstLine = split[0].split(" ");
                System.out.println("Method = " + firstLine[0]);
                System.out.println("url = " + firstLine[1]);
                System.out.println("Http version = " + firstLine[2]);

                //======>>>>  返回客户端
                StringBuilder stringBuilder = new StringBuilder();
                //响应报文首行，200表示处理成功
                stringBuilder.append("HTTP/1.1 200 OK\r\n");
                stringBuilder.append("Content-Type:text/html;charset="+localCharset+"\r\n");
                //报文头结束后加一个空行
                stringBuilder.append("\r\n");
                stringBuilder.append("<html><head><title>显示报文</html></head></title>");
                stringBuilder.append("接收到请求报文是：<br/>");
                for (String s : split) {
                    stringBuilder.append(s +"<br/>");
                }
                stringBuilder.append("</body></html>");
                //返回数据给客户端
                buffer = ByteBuffer.wrap(stringBuilder.toString().getBytes(localCharset));
                sc.write(buffer);
                //关闭Socket
                sc.close();
            }
        }

        @Override
        public void run() {
            try {
                //接收到连接请求时
                if(key.isAcceptable()){
                    handleAccept();
                }
                if(key.isReadable()){
                    handleRead();
                }
                //读数据
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


