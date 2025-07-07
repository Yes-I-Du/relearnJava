package baseProxy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *重学Java：Day01-动态代理
 *
 * @author dmz xxx@163.com
 * @version 2025/7/7 23:51
 * @since JDK17
 */
public class baseProxy {
    public static void main(String[] args) throws Exception {
        // 读取配置文件
        BufferedReader br = new BufferedReader(new FileReader("com/it11257/Resources/baseProxyConfig.txt"));
        // 获取className
        String className = br.readLine();
        // 获取methodName
        String methodName = br.readLine();
        // 获取内容
        String content = br.readLine();
        br.close();

        // 根据类路径，获取Class对象
        Class<?> clazzProxy = Class.forName(className);

//        // 动态代理实现，创建动态代理
//       proxyService proxyService =(proxyService) Proxy.newProxyInstance(
//           // 第一个参数：类加载器，用哪儿个类加载器动态加载该类
//           clazzProxy.getClassLoader()
//           ,
//           // 第二个参数：接口数组(0个或多个 Class[])，该类实现了哪些接口
//           new Class[]{clazzProxy}
//           ,
//           // 第三个参数：InvocationHandler，代理类要做什么事情
//           new InvocationHandler() {
//               // proxy：代理对象,代表子类对象目标本身
//               // method：被调用的方法
//               // args：被调用的方法参数
//               // return：被调用的方法的返回值
//               public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                   if (method.getName().equals(methodName)){
//                       System.out.println(content);
//                   }
//                   return null;
//               }
//           });
//
//        proxyService.show1();
//        proxyService.study();
//        proxyService.show();

        // 动态代理实现，创建动态代理
        Object obj = Proxy.newProxyInstance(
            // 第一个参数：类加载器，用哪儿个类加载器动态加载该类
            clazzProxy.getClassLoader()
            ,
            // 第二个参数：接口数组(0个或多个 Class[])，该类实现了哪些接口
            new Class[]{clazzProxy}
            ,
            // 第三个参数：InvocationHandler，代理类要做什么事情
            new InvocationHandler() {
                // proxy：代理对象,代表子类对象目标本身
                // method：被调用的方法
                // args：被调用的方法参数
                // return：被调用的方法的返回值
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals(methodName)){
                        System.out.println(content);
                    }
                    return null;
                }
            });

        Method method = clazzProxy.getMethod(methodName);
        method.invoke(obj);
    }
}

