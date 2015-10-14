package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger ZERO = BigInteger.ZERO;


    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int zn1, zn2;
        System.out.println("Введите натуральное число, которое необходимо разбить на слагаемые");
        while (true) {
            try {
                zn1 = Integer.valueOf(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели неверное значение, пожалуйста, повторите ввод");
            }
        }

        while (true) {
            System.out.println("Введите количество натуральных слагаемых, на которое нужно разбить число");
            try {
                zn2 = Integer.valueOf(reader.readLine());
                break;
            } catch (IOException e) {
                System.out.println("Вы ввели неверное значение, пожалуйста, повторите ввод");
            }
        }
        System.out.println("Количество возможных разбиений равно " + getRazbienie(zn1, zn2));
    }


    private static BigInteger getRazbienie(int n, int k) {
        if (k == 1)
            return ONE;
        else if (n == k)
            return ONE;
        else if (k > n)
            return ZERO;

        return getRazbienie(n - 1, k - 1).add(getRazbienie(n - k, k));

    }
}