package com.zengzixi.insidesync;

import org.openjdk.jol.info.ClassLayout;

public class Java_ClassLayout {
    public static void main(String[] args) {
        User o =new User();
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
    }
    static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
