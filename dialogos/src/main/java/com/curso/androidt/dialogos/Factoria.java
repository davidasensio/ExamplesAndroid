package com.curso.androidt.dialogos;

/**
 * Created by androidt on 18/05/2015.
 */
public class Factoria {
    public interface Coche {
        public Double getVelocidadMaxima();

    }

    private class Megane implements Coche {
        public Double getVelocidadMaxima() {
            return 200d;
        }

    }

    private class Clio implements Coche {
        @Override
        public Double getVelocidadMaxima() {
            return 140d;
        }
    }

    public Coche create(String modelo) {
        if (modelo.equals("Megane")) {
            return new Megane();
        }
        return null;
    }
}
