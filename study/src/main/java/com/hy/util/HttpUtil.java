package com.hy.util;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/5/17
 * Time: 14:39
 */

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @auther haoy
 * @create 2018/5/17
 */
public class HttpUtil {


    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.requestByPostMethod();
    }
    /**
     * POST方式发起http请求
     */
    public void requestByPostMethod(){
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost("http://127.0.0.1:8686/haoy/idempotencyTest");
            post.setHeader("Content-Type","application/json");
            //创建参数列表


            String body = "{\"orderId\":\"88888\"}";
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(body.getBytes());
            post.setEntity(byteArrayEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                org.apache.http.HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }




        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    private CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }

    private void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }

}
