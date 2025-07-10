package baseProxy;/**
 * Cglib方法增强
 *
 * @author Dmz Email:  * @since 2025/07/08 21:53
 */

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author dmz xxx@163.com
 * @version 2025/7/8 21:53
 * @since JDK17
 */
public class UserServiceInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 前置增强：在目标方法调用前执行
        System.out.println("[CGLIB代理] 开始执行方法: " + method.getName());

        // 调用原始目标方法 (父类方法)
        Object result = methodProxy.invokeSuper(o, objects);

        // 后置增强：在目标方法调用后执行
        System.out.println("[CGLIB代理] 方法执行完成: " + method.getName());
        return result; // 返回方法执行结果 (可选择修改)
    }

}

