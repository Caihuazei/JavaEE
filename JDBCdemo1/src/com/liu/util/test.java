package com.liu.util;


import java.util.List;

public class test {
    public static void main(String[] args) {
      List<t2> t2List = DButils.querAll(t2.class);
        for (t2 t2 : t2List) {
            System.out.println(t2);
        }

    }
}
