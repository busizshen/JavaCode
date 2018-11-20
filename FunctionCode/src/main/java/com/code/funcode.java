package com.code;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class funcode {

    public static void main(String[] args) {
//        new Thread(new Runnable(){// 接口名
//            @Override
//            public void run(){// 方法名
//                System.out.println("Thread run()");
//            }
//        }).start();

//        new Thread(
//                () -> System.out.println("Thread run()")// 省略接口名和方法名
//        ).start();
//
//        new Thread(
//                () -> {
//                    System.out.print("Hello");
//                    System.out.println(" Hoolee");
//                }
//        ).start();

        List<String> list = Arrays.asList("I", "love", "you", "too");
        Collections.sort(list, new Comparator<String>(){// 接口名
            @Override
            public int compare(String s1, String s2){// 方法名
                if(s1 == null)
                    return -1;
                if(s2 == null)
                    return 1;
                System.out.println("s1 = [" + s1 + "], s2 = [" + s2 + "]");
                return s1.length()-s2.length();
            }
        });
        for (String ss : list){
            System.out.println("args = [" + ss + "]");
        }


    }


}
