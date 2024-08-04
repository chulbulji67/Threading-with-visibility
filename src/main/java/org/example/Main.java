package org.example;

class SharedResource{
    private volatile  boolean flag = false;

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }

    public synchronized   boolean getFlag(){
        return flag;
    }
}


public class Main {
//   static volatile boolean flag = false;
    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();
        Thread obj = new Thread(()->{
        System.out.println("Thread 1 is running");
    });
    obj.start();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread 2 is running");
            sharedResource.setFlag(true);
            System.out.println("Flagged changed");
            try {
                Thread.sleep(10999);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getState());
            System.out.println("AFter sleeping");
        }
    };

    Thread obj1 = new Thread(runnable, "runnable thread");

    obj1.start();



    Runnable runnable1 = ()->{
        while (sharedResource.getFlag() != true){

        }
        System.out.println("Thread 3 detected flag change");
    };

    Thread obj3 = new Thread(runnable1, "thread 3");
    obj3.start();
        System.out.println("Main Thread Exit");
    }
}