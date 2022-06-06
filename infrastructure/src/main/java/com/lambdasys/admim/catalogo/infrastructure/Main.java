package com.lambdasys.admim.catalogo.infrastructure;

import com.lambdasys.admim.catalogo.application.UseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(new UseCase().execute());
    }
}