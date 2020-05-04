package com.gp.cms_manager;

import java.sql.PreparedStatement;

/**
 * @author 码农界的小学生
 * @description:
 * @title: test
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/28 7:28
 */
public class test {
    static int x= 10;
    static {
        x+=5;
    }
    int a = 1+2;
    Short c= 1;
    String s =null;
    public test(){

    }
    public void test(){

    }
    public static void main(String[] args) {
        test test = new test();
        test.s.concat("abc");
        System.out.println(x);
    }
    static {
        x/=3;
    }
}
