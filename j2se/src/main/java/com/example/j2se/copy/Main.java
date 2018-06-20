package com.example.j2se.copy;

import com.example.j2se.LogUtil;

/**
 * demo shallow copy、deep copy
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        SchoolClass sc1 = new SchoolClass("3.7", new Teacher("li", "30"));
        LogUtil.sysopl("sc1= " + sc1.toString());

        SchoolClass sc2 = (SchoolClass) sc1.clone();
        sc2.mClassName = "2.8";
        sc2.mTeacher.name = "liu";
        sc2.mTeacher.age = "25";

        LogUtil.sysopl("sc1= " + sc1.toString());
        LogUtil.sysopl("sc2= " + sc2.toString());
    }


//    // 浅复制:
//    /**
//     * 假定每个班级对应唯一的一个班主任，每个班主任对应唯一的一个班级
//     */
//    public static class SchoolClass implements Cloneable {
//
//        /**
//         * 班级名称
//         */
//        public String mClassName;
//
//        /**
//         * 班主任,
//         */
//        public Teacher mTeacher;
//
//        public SchoolClass(String className, Teacher teacher) {
//            mClassName = className;
//            mTeacher = teacher;
//        }
//
//        @Override
//        protected Object clone() throws CloneNotSupportedException {
//            return super.clone();
//        }
//
//        @Override
//        public String toString() {
//            return "SchoolClass{" +
//                    "mClassName='" + mClassName + '\'' +
//                    ", mTeacher=" + mTeacher.toString() +
//                    '}';
//        }
//    }
//
//    public static class Teacher implements Cloneable {
//        public String name;
//        public String age;
//
//        public Teacher(String name, String age) {
//            this.name = name;
//            this.age = age;
//        }
//
//        @Override
//        protected Object clone() throws CloneNotSupportedException {
//            return super.clone();
//        }
//
//        @Override
//        public String toString() {
//            return "Teacher{" +
//                    "name='" + name + '\'' +
//                    ", age='" + age + '\'' +
//                    '}';
//        }
//    }


    // 深复制:
    /**
     * 假定每个班级对应唯一的一个班主任，每个班主任对应唯一的一个班级
     */
    public static class SchoolClass implements Cloneable {

        /**
         * 班级名称
         */
        public String mClassName;

        /**
         * 班主任,
         */
        public Teacher mTeacher;

        public SchoolClass(String className, Teacher teacher) {
            mClassName = className;
            mTeacher = teacher;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            SchoolClass schoolClass = (SchoolClass) super.clone();
            schoolClass.mTeacher = (Teacher) schoolClass.mTeacher.clone();
            return schoolClass;
        }

        @Override
        public String toString() {
            return "SchoolClass{" +
                    "mClassName='" + mClassName + '\'' +
                    ", mTeacher=" + mTeacher +
                    '}';
        }
    }

    public static class Teacher implements Cloneable {
        public String name;
        public String age;

        public Teacher(String name, String age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }

}
