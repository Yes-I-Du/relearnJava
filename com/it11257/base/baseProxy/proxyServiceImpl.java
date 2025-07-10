package baseProxy;/**
 * 接口实现
 *
 * @author Dmz Email:  * @since 2025/07/08 21:08
 */

/**
 * @author dmz xxx@163.com
 * @version 2025/7/8 21:08
 * @since JDK17
 */
public class proxyServiceImpl implements proxyService {
    @Override
    public String show() {
        return null;
    }

    @Override
    public void study() {

    }

    @Override
    public void show1() {

    }

    @Override
    public void createUser(String name) {
        System.out.println("创建用户: " + name);
    }

    @Override
    public void deleteUser(int id) {
        System.out.println("删除用户ID: " + id);
    }
}

