package baseCopy;

/**
 * 重学Java：Day03-深拷贝与浅拷贝之浅拷贝
 *
 * @author dmz xxx@163.com
 * @version 2025/7/10 22:08
 * @since JDK17
 */
public class BaseShallowCopy {

    public static void main(String[] args) throws CloneNotSupportedException {
        /*
         * 浅拷贝：创建一个新对象，然后将原对象的所有字段复制到新对象中。如果字段是基本数据类型，值复制，完全独立；不可变引用（String）引用复制，但因不可变性表现似深拷贝;如果字段是引用类型，则复制引用地址，即两个对象指向同一个内存地址。
         * 实现方式:
         * 1.使用默认的Object.clone()方法,实现Cloneable接口.
         * 2.拷贝构造方法或工具类（如 BeanUtils.copyProperties）。
         */
        Person person = new Person("张三", 18, new Address("北京"));
        System.out.println(
            "person name is [" + person.name + "], age is [" + person.age + "]" + ", city is [" + person.address.getCity() + "]");
        Person person1 = (Person)person.clone();
        System.out.println(
            "person1 name is [" + person1.name + "], age is [" + person1.age + "]" + ", city is [" + person1.address.getCity() + "]");
        person1.name = "李四";
        person1.age = 20;
        person1.address.setCity("上海");
        System.out.println(
            "person1 name is [" + person1.name + "], age is [" + person1.age + "]" + ", city is [" + person1.address.getCity() + "]");
        System.out.println(
            "person name is [" + person.name + "], age is [" + person.age + "]" + ", city is [" + person.address.getCity() + "]");
    }
}

class Address {
    public String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

class Person implements Cloneable {
    public String name;
    public int age;

    public Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

