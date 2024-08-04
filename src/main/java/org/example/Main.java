package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class SharedResource{
   private AtomicInteger  count = new AtomicInteger();

   public void increment(){ count.incrementAndGet();}
    public int getCount(){ return  count.get();}
}


public class Main {
//   static volatile boolean flag = false;
    public static void main(String[] args) {
//        SharedResource sharedResource = new SharedResource();
        SharedRes1 sharedResource =new SharedRes1();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread 1 is running");
            for(int i = 1; i<=10; i++){
                System.out.println("running");
                sharedResource.increment();
            }
            System.out.println("Thread 1 Completed");

        }
    };

    Thread obj1 = new Thread(runnable, "runnable thread");

    obj1.start();



    Runnable runnable1 = ()->{
        for(int i = 1; i<=5; i++){
            System.out.println("running");
            sharedResource.increment();
        }
        System.out.println("Thread 2 Completed");
    };

    Thread obj3 = new Thread(runnable1, "thread 3");
    obj3.start();
        try {
            // Wait for both threads to finish
            obj1.join();
            obj3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main Thread Exit " + sharedResource.getCount());    }
}