package br.com.rfasioli.java8whatsnews;

public class DefaultMethodsDemo {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }

    private interface Vehicle {
        default void print() {
            System.out.println("I am a vehicle!");
        }

        static void blowHorn() {
            System.out.println("Blowing horn!!!");
        }
    }

    private interface FourWheeler {
        default void print() {
            System.out.println("I am a four wheeler!");
        }
    }

    private static class Car implements Vehicle, FourWheeler {
        public void print() {
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("I am a car!");
        }
    }
}
