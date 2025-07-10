package baseCopy;

import java.io.*;

/**
 * 重学Java：Day03-深拷贝与浅拷贝之深拷贝
 *
 * @author dmz xxx@163.com
 * @version 2025/7/10 22:08
 * @since JDK17
 */
public class BaseDeepCopy {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        /*
        * 创建一个新对象，并递归复制原始对象及其所有引用对象(基本数据类型字段：直接复制值；引用类型字段：创建新的对象实例，新旧对象完全独立)。
         * 重写clone()方法，对每个引用类型递归调用clone()方法。
         * 序列化/反序列化：通过 ObjectOutputStream 和 ObjectInputStream（需实现 Serializable）。
         * 第三方库。
         */

        // 重写clone()方法，对每个引用类型递归调用clone()方法。
        School school = new School("北京大学", 100, new Classes("北京大学计算机学院"));
        System.out.println(
            "school name is [" + school.name + "], age is [" + school.age + "]" + ", city is [" + school.classes.getName() + "]");
        School schoolCopy = (School)school.clone();
        System.out.println(
            "schoolCopy name is [" + schoolCopy.name + "], age is [" + schoolCopy.age + "]" + ", city is [" + schoolCopy.classes.getName() + "]");
        schoolCopy.name = "清华大学";
        schoolCopy.age = 200;
        schoolCopy.classes.setName("清华大学计算机学院");
        System.out.println(
            "schoolCopy name is [" + schoolCopy.name + "], age is [" + schoolCopy.age + "]" + ", city is [" + schoolCopy.classes.getName() + "]");
        System.out.println(
            "school name is [" + school.name + "], age is [" + school.age + "]" + ", city is [" + school.classes.getName() + "]");

        // 序列化实现
        Department department = new Department("创新部", 100, new Employee("澳航三"));
        System.out.println(
            "department name is [" + department.name + "], quantity is [" + department.quantity + "]" + ", employee is [" + department.employee.getName() + "]");
        Department departmentCopy = DeepCopyUtil.deepCopy(department);
        System.out.println(
            "departmentCopy name is [" + departmentCopy.name + "], quantity is [" + departmentCopy.quantity + "]" + ", employee is [" + departmentCopy.employee.getName() + "]");
        departmentCopy.name = "研发部";
        departmentCopy.quantity = 200;
        departmentCopy.employee.setName("wangwu");
        System.out.println(
            "departmentCopy name is [" + departmentCopy.name + "], quantity is [" + departmentCopy.quantity + "]" + ", employee is [" + departmentCopy.employee.getName() + "]");
        System.out.println(
            "department name is [" + department.name + "], quantity is [" + department.quantity + "]" + ", employee is [" + department.employee.getName() + "]");

    }
}

class Classes implements Cloneable{
    public String name;

    public Classes(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class School implements Cloneable {
    public String name;
    public int age;

    public Classes classes;

    public School(String name, int age, Classes classes) {
        this.name = name;
        this.age = age;
        this.classes = classes;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        School school = (School) super.clone();
        school.classes = (Classes) this.classes.clone();
        return school;
    }
}

class Employee implements Serializable{
    private static final long serialVersionUID = 1L;

    public String name;

    public Employee(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public int quantity;

    public Employee employee;

    public Department(String name, int quantity, Employee employee) {
        this.name = name;
        this.quantity = quantity;
        this.employee = employee;
    }
}

class DeepCopyUtil {
    public static <T extends Serializable> T deepCopy(T obj)
        throws IOException, ClassNotFoundException {

        // 1. 序列化对象到字节流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        }

        // 2. 从字节流反序列化新对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (T) ois.readObject();
        }
    }
}
