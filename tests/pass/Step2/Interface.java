package pass;

import java.lang.System;

interface Friend{
    public void friend();
}

interface Cute{
    public void cute();
}

interface Animal extends Friend, Cute{
    public void eat();
    public void travel();
}

public class Dog implements Animal{
    public void eat(){
        System.out.println("Dogs can eat.");
    }

    public void travel(){
        System.out.println("Dogs can run.");
    }

    public void cute(){
        System.out.println("Dogs are cute.");
    }

    public void friend(){
        System.out.println("Dogs are our friends.");
    }

    public int numoflegs(){
        int x;
        x = 4;
        System.out.println("Dogs have" + x + "legs.");
    }

    public static void main(String[] args) {
        Dog doggy = new Dog();
        doggy.eat();
        doggy.travel();
        doggy.numoflegs();
        doggy.friend();
    }
}
