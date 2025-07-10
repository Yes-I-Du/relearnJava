package baseProxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * 重学Java：Day02-使用Cglib实现动态代理
 *
 * @author dmz xxx@163.com
 * @version 2025/7/8 21:33
 * @since JDK17
 */
public class baseCglib {
    public static void main(String[] args) {

        // 方法一：
        // 1. 创建增强器
        Enhancer enhancer = new Enhancer();

        // 2. 设置父类 (目标类)
        enhancer.setSuperclass(UserService.class);

        // 3. 设置回调 (方法拦截器)
        enhancer.setCallback(new UserServiceInterceptor());

        // 4. 创建代理对象 (注意：这里创建的是 UserService 的子类实例)
        UserService proxy = (UserService) enhancer.create();

        // 5. 通过代理对象调用方法
        proxy.addUser("Alice");
        proxy.deleteUser("Bob");

        // 方法二：
        Object obj = Enhancer.create(UserService.class, new UserServiceInterceptor());


        // 方法二：
        // 真实对象
        proxyService proxyServiceImpl = new proxyServiceImpl();
        // 代理对象
        proxyService proxyServiceProxy = (proxyService)Proxy.newProxyInstance(
            // 第一个参数：类加载器，用哪儿个类加载器动态加载该类
            proxyServiceImpl.getClass().getClassLoader(),
            // 第二个参数：接口数组(0个或多个 Class[])，该类实现了哪些接口
            proxyServiceImpl.getClass().getInterfaces(),
            // 第三个参数：InvocationHandler，代理类要做什么事情
            new LogHandler(proxyServiceImpl));
        proxyServiceProxy.createUser("dmz");
        proxyServiceProxy.deleteUser(11);
    }
}

