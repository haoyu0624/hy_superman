package com.hy.rpc;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/7/24
 * Time: 16:14
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
