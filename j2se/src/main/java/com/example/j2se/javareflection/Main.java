package com.example.j2se.javareflection;

import com.example.j2se.LogUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //反射 demo
    //参考文章 : http://www.jianshu.com/p/f67182a482eb   http://www.jianshu.com/p/779b3e27b26d
    public static void main(String[] args) {
        //*****获取class的途径
        //1.
        Class clz = Person.class;
        LogUtil.sysopl(clz.getName());
        //2.
        Person person = new Person();
        clz = person.getClass();
        LogUtil.sysopl(clz.getName());
        //3.
        try {
            clz = Class.forName("com.example.j2se.javareflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LogUtil.sysopl(clz.getName());

        //*****获取Metch操作
//        public Method getDeclaredMethod(String name, Class<?>... parameterTypes) // 得到该类所有的方法，不包括父类的
//        public Method getMethod(String name, Class<?>... parameterTypes) // 得到该类所有的public方法，包括父类的
        try {
            //1.调用public 方法
            clz = Class.forName("com.example.j2se.javareflection.Person");//生产class //throwClassNotFoundException
            Object obj = clz.newInstance(); // new一个对象 //throw InstantiationException、IllegalAccessException
            //注意int的值
            Method method = clz.getMethod("printInfo", String.class, int.class); //throw NoSuchMethodException
            method.invoke(obj, "liuj", 100); // throw InvocationTargetException

            //2.调用private方法
            Method method2 = clz.getDeclaredMethod("printMsg", String.class);
            //设置是否允许访问，因为该变量是private的，所以要手动设置允许访问
            method2.setAccessible(true);
            method2.invoke(obj, "调用private方法");

            //3.获取所有的方法集合. 得到该类所有的public方法，包括父类的
            Method[] mtd1 = clz.getMethods();
            LogUtil.sysopl("========clz.getMethods========");
            for (Method m : mtd1) {
                LogUtil.sysopl(m.getName());
            }
            LogUtil.sysopl("========clz.getMethods========");

            //4.获取所有的方法集合 得到该类所有的方法，不包括父类的
            Method[] mtd2 = clz.getDeclaredMethods();
            LogUtil.sysopl("========clz.getDeclaredMethods========");
            for (Method m : mtd2) {
                LogUtil.sysopl(m.getName());
            }
            LogUtil.sysopl("========clz.getDeclaredMethods========");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //*****获取field,同上
//        public Field getDeclaredField(String name) // 获得该类自身声明的所有变量，不包括其父类的变量
//        public Field getField(String name) // 获得该类自所有的public成员变量，包括其父类变量
        try {
            clz = Person.class;
            Object obj = clz.newInstance();
            Field field = clz.getDeclaredField("msg");
            field.setAccessible(true);
            String fieldMsg = (String) field.get(obj);
            LogUtil.sysopl(fieldMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取构造方法
//        public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) //  获得该类所有的构造器，不包括其父类的构造器
//        public Constructor<T> getConstructor(Class<?>... parameterTypes) // 获得该类所以public构造器，包括父类
        LogUtil.sysopl("======构造方法");
        try {
            clz = Person.class;
            Constructor constructor = clz.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            constructor.newInstance("liujie", 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //通过反射了解集合泛型的本质
        List<String> list = new ArrayList<>();
        list.add("1");
//        list.add(2); //这个方法会报错 直接编译不通过
        LogUtil.sysopl("1,list.size()=" + list.size());
         /*
         * 2.然后通过反射添加元素方式，在运行期动态加载类，首先得到list1和list2
         * 的类类型相同，然后再通过方法反射绕过编译器来调用add方法，看能否插入int
         * 型的元素
         */
        clz = list.getClass();
        try {
            Method method = clz.getMethod("add", Object.class);
            method.invoke(list, 2);
            LogUtil.sysopl("2,list.size()=" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < list.size(); i++) {
//            //这句话还是会报错， 虽然编译通过了，所有通过反射去对泛型集合操作是有风险的
//            com.example.j2se.LogUtil.sysopl("list.i = " + i + ", value = " + list.get(i));
//        }


        //*****其他
//        Annotation[] annotations = (Annotation[]) class1.getAnnotations();//获取class对象的所有注解
//        Annotation annotation = (Annotation) class1.getAnnotation(Deprecated.class);//获取class对象指定注解
//        Type genericSuperclass = class1.getGenericSuperclass();//获取class对象的直接超类的 Type
//        Type[] interfaceTypes = class1.getGenericInterfaces();//获取class对象的所有接口的type集合

//        boolean isPrimitive = class1.isPrimitive();//判断是否是基础类型
//        boolean isArray = class1.isArray();//判断是否是集合类
//        boolean isAnnotation = class1.isAnnotation();//判断是否是注解类
//        boolean isInterface = class1.isInterface();//判断是否是接口类
//        boolean isEnum = class1.isEnum();//判断是否是枚举类
//        boolean isAnonymousClass = class1.isAnonymousClass();//判断是否是匿名内部类
//        boolean isAnnotationPresent = class1.isAnnotationPresent(Deprecated.class);//判断是否被某个注解类修饰
//        String className = class1.getName();//获取class名字 包含包名路径
//        Package aPackage = class1.getPackage();//获取class的包信息
//        String simpleName = class1.getSimpleName();//获取class类名
//        int modifiers = class1.getModifiers();//获取class访问权限
//        Class<?>[] declaredClasses = class1.getDeclaredClasses();//内部类
//        Class<?> declaringClass = class1.getDeclaringClass();//外部类



    }

}
