package com.curso.androidt.dialogos;

/**
 * Created by androidt on 18/05/2015.
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    public Singleton getInstance() {
        return instance;
    }

}
