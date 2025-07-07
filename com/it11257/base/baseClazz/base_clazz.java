package baseClazz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;

/**
 * 重学Java：Day01-反射
 *
 * @author dmz xxx@163.com
 * @version 2025/7/7 23:15
 * @since JDK17
 */
public class base_clazz {
    public static void main(String[] args) throws Exception {
        // 读取配置文件
        BufferedReader br = new BufferedReader(new FileReader("com/it11257/Resources/baseClazzConfig.txt"));
        // 获取className
        String className = br.readLine();
        // 获取methodName
        String methodName = br.readLine();
        br.close();

        // 根据类路径，获取Class对象
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        // 根据方法名，获取Method对象
        Method method = clazz.getMethod(methodName);
        // 调用方法
        method.invoke(obj);
    }
}

