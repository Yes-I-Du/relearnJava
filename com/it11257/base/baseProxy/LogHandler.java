package baseProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler的实现
 *
 * @author dmz xxx@163.com
 * @version 2025/7/8 21:19
 * @since JDK17
 */
public class LogHandler implements InvocationHandler {
    // 真实的对象
    private Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置增强
        System.out.println("[日志] 调用方法: " + method.getName());
        // 调用目标对象的方法
        Object result = method.invoke(target, args);

        // 后置增强
        System.out.println("[日志] 方法执行完成");
        return result;
    }
}

