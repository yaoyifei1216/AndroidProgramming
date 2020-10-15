package com.example.hookdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test {
    public static void Tests() {
        try {
            //通过Java反射机制得到类的包名和类名
            Test1();
            System.out.println("===============================================");

            //验证所有的类都是Class类的实例对象
            Test2();
            System.out.println("===============================================");

            //通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]，无参构造
            Test3();
            System.out.println("===============================================");

            //通过Java反射机制得到一个类的构造函数，并实现构造带参实例对象
            Test4();
            System.out.println("===============================================");

            //通过Java反射机制操作成员变量, set 和 get
            Test5();
            System.out.println("===============================================");

            //通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
            Test6();
            System.out.println("===============================================");

            //通过Java反射机制调用类中方法
            Test7();
            System.out.println("===============================================");

            //通过Java反射机制获得类加载器
            Test8();
            System.out.println("===============================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Demo1: 通过Java反射机制得到类的包名和类名
     */
    public static void Test1() {
        Person person = new Person();
        System.out.println("Test1: 包名: " + person.getClass().getPackage().getName() + "，" + "完整类名: " + person.getClass().getName());
    }

    /**
     * Demo2: 验证所有的类都是Class类的实例对象
     */
    public static void Test2() throws ClassNotFoundException {
        //定义两个类型都未知的Class , 设置初值为null, 看看如何给它们赋值成Person类
        Class<?> class1 = null;
        Class<?> class2 = null;

        //写法1, 可能抛出 ClassNotFoundException [多用这个写法]
        class1 = Class.forName("com.example.hookdemo.Person");
        System.out.println("Test2:(写法1) 包名: " + class1.getPackage().getName() + "，" + "完整类名: " + class1.getName());

        //写法2
        class2 = Person.class;
        System.out.println("Test2:(写法2) 包名: " + class2.getPackage().getName() + "，" + "完整类名: " + class2.getName());
    }

    /**
     * Demo3: 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]
     */
    public static void Test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> class1 = null;
        class1 = Class.forName("com.example.hookdemo.Person");
        //由于这里不能带参数，所以你要实例化的这个类Person，一定要有无参构造函数
        Person person = (Person) class1.newInstance();
        person.setAge(26);
        person.setName("kaiven");
        System.out.println("Test3: " + person.getName() + " : " + person.getAge());
    }

    /**
     * Demo4: 通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
     */
    public static void Test4() throws ClassNotFoundException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> class1 = null;
        Person person1 = null;
        Person person2 = null;

        class1 = Class.forName("com.example.hookdemo.Person");
        //得到一系列构造函数集合
        Constructor<?>[] constructors = class1.getConstructors();

        try {
            person1 = (Person) constructors[0].newInstance();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        person1.setAge(28);
        person1.setName("zhuk");

        person2 = (Person) constructors[1].newInstance(29, "zhuk");

        System.out.println("Test4: " + person1.getName() + " : " + person1.getAge() + "  ,   " + person2.getName() + " : " + person2.getAge());

    }

    /**
     * Demo5: 通过Java反射机制操作成员变量, set 和 get
     */
    public static void Test5() throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InstantiationException, ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.example.hookdemo.Person");
        Object obj = class1.newInstance();

        Field nameField = class1.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(obj, "cyy");

        System.out.println("Test5: 修改属性之后得到属性变量的值：" + nameField.get(obj));

    }


    /**
     * Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
     */
    public static void Test6() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.example.hookdemo.SuperPerson");

        //取得父类名称
        Class<?> superClass = class1.getSuperclass();
        System.out.println("Test6:  SuperMan类的父类名: " + superClass.getName());

        System.out.println("===============================================");


        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("类中的成员: " + fields[i]);
        }
        System.out.println("===============================================");


        //取得类方法
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("Test6,取得SuperMan类的方法：");
            System.out.println("函数名：" + methods[i].getName());
            System.out.println("函数返回类型：" + methods[i].getReturnType());
            System.out.println("函数访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("函数代码写法： " + methods[i]);
        }

        System.out.println("===============================================");

        Class<?> interfaces[] = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名: " + interfaces[i].getName());
        }

    }

    /**
     * Demo7: 通过Java反射机制调用类方法
     */
    public static void Test7() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> class1 = null;
        class1 = Class.forName("com.example.hookdemo.SuperPerson");

        System.out.println("Test7: \n调用无参方法fly()：");
        Method method = class1.getMethod("fly");
        method.invoke(class1.newInstance());

        System.out.println("调用有参方法smoke(int m)：");
        method = class1.getMethod("smoke", int.class);
        method.invoke(class1.newInstance(), 100);
    }

    /**
     * Demo8: 通过Java反射机制得到类加载器信息
     * <p/>
     * 在java中有三种类类加载器。
     * <p/>
     * 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
     * <p/>
     * 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
     * <p/>
     * 3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
     *
     * @throws ClassNotFoundException
     */
    public static void Test8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.example.hookdemo.SuperPerson");
        String name = class1.getClassLoader().getClass().getName();

        System.out.println("Test8: 类加载器类名: " + name);
    }
}
