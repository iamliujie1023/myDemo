package com.example.j2se.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by liuj on 2017/11/27.
 */
@Main.TestAnnotation(msg = "hello")
public class Main {

    //注解 demo
//    public static void main(String[] args) {
//
//    }


//    public @interface Persons {
//        Person[] value();
//    }
//    @Repeatable(Persons.class)
//    public @interface Person {
//        String role() default "";
//    }
//    @Person(role = "artist")
//    @Person(role = "coder")
//    @Person(role = "PM")
//    public class SuperMan {
//    }


    //注解属性 无形参的方法”形式来声明 方法名对应名字，返回类型对应类型
    //在注解中定义属性时它的类型必须是:8种基本数据类型、类、接口、注解、及它们的数组
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {
        int id() default 1;

        String msg();
    }

    //属性赋值
    @TestAnnotation(id = 3, msg = "helloAnnotation")
    public class Text {
    }

    //设置注解属性默认值
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation2 {
        public int id() default -1;

        public String msg() default "Hi";
    }


    @Retention(RetentionPolicy.RUNTIME)
    public @interface Check {
        String value();
    }

    //设置注解属性特殊情况
    //有且只有一个属性 && 名字为 value, 可以这么使用
    @Check("hi")
    public class CheckText {
    }

    @Check(value = "hi")
    public class CheckText2 {
    }
    //以上2中设置属性的效果一样


    //特殊情况2
    //一个注解没有任何属性
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Perform {
    }

    //那么在应用这个注解的时候，括号都可以省略。
    @Perform
    public void testMethod() {
    }


//    @TestAnnotation(id = 3, msg = "helloWorld")
//    public static class Test {
//        public static void main(String[] args) {
//            boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
//            if (hasAnnotation) {
//                TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
//                System.out.println("id:" + testAnnotation.id());
//                System.out.println("msg:" + testAnnotation.msg());
//            }
//        }
//    }

//    public static class Test {
//        @Check(value = "hi")
//        public int a;
//
//        @Perform
//        public void testMethod() {
//        }
//
//        @SuppressWarnings("deprecation")
//        public void test1() {
//            Hero hero = new Hero();
//            hero.say();
//            hero.speak();
//        }
//
//        public static void main(String[] args) {
//            boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
//            if (hasAnnotation) {
//                TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);//获取类的注解System.out.println("id:"+testAnnotation.id()); 
//                System.out.println("msg:" + testAnnotation.msg());
//            }
//            try {
//                Field a = Test.class.getDeclaredField("a");
//                a.setAccessible(true);//获取一个成员变量上的注解
//                Check check = a.getAnnotation(Check.class);
//                if (check != null) {
//                    System.out.println("check value:" + check.value());
//                }
//                Method testMethod = Test.class.getDeclaredMethod("testMethod");
//                if (testMethod != null) {// 获取方法中的注解
//                    Annotation[] ans = testMethod.getAnnotations();
//                    for (int i = 0; i < ans.length; i++) {
//                        System.out.println("method testMethod annotation:" + ans[i].annotationType().getSimpleName());
//                    }
//                }
//            } catch (NoSuchFieldException e) {// TODO Auto-generated catch blocke.printStackTrace();           
//                System.out.println(e.getMessage());
//            } catch (SecurityException e) {// TODO Auto-generated catch blocke.printStackTrace();           
//                System.out.println(e.getMessage());
//            } catch (NoSuchMethodException e) {// TODO Auto-generated catch blocke.printStackTrace();         
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    public static class Hero {
//        public void say() {
//            LogUtil.sysopl("hero say");
//        }
//
//        public void speak() {
//            LogUtil.sysopl("hero speak");
//        }
//    }


    @Retention(RetentionPolicy.RUNTIME)
    public @interface Jiecha {
    }

    public static class NoBug {
        @Jiecha
        public void suanShu() {
            System.out.println("1234567890");
        }

        @Jiecha
        public void jiafa() {
            System.out.println("1+1=" + 1 + 1);
        }

        @Jiecha
        public void jiefa() {
            System.out.println("1-1=" + (1 - 1));
        }

        @Jiecha
        public void chengfa() {
            System.out.println("3 x 5=" + 3 * 5);
        }

        @Jiecha
        public void chufa() {
            System.out.println("6 / 0=" + 6 / 0);
        }

        public void ziwojieshao() {
            System.out.println("我写的程序没有 bug!");
        }
    }

    public static class TestTool {
        public static void main(String[] args) {
            NoBug testobj = new NoBug();

            Class clazz = testobj.getClass();

            Method[] method = clazz.getDeclaredMethods();
            //用来记录测试产生的 log 信息
            StringBuilder log = new StringBuilder();
            // 记录异常的次数
            int errornum = 0;

            for (Method m : method) {
                // 只有被 @Jiecha 标注过的方法才进行测试
                if (m.isAnnotationPresent(Jiecha.class)) {
                    try {
                        m.setAccessible(true);
                        m.invoke(testobj, null);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        //e.printStackTrace();
                        errornum++;
                        log.append(m.getName());
                        log.append(" ");
                        log.append("has error:");
                        log.append("\n\r  caused by ");
                        //记录测试过程中，发生的异常的名称
                        log.append(e.getCause().getClass().getSimpleName());
                        log.append("\n\r");
                        //记录测试过程中，发生的异常的具体信息
                        log.append(e.getCause().getMessage());
                        log.append("\n\r");
                    }
                }
            }
            log.append(clazz.getSimpleName());
            log.append(" has  ");
            log.append(errornum);
            log.append(" error.");
            // 生成测试报告
            System.out.println(log.toString());
        }

    }


}
