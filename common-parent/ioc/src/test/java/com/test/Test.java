package com.test;

import love.forte.common.ioc.DependCenter;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class Test {

    public static void main(String[] args) {


        final DependCenter center = new DependCenter();

        center.inject(ConfClass.class, B1.class, B2.class, B3.class);

        center.init();

        System.out.println("initialized.");

        // final BeansInt beansInt = center.get(BeansInt.class);
        //
        // System.out.println(beansInt);
        // System.out.println(beansInt.getName());
        //
        // System.out.println(center.get(BeansInt.class));
        // System.out.println(center.get(BeansInt.class));
        // System.out.println(center.get(BeansInt.class));
        // System.out.println(center.get(BeansInt.class));


    }
}