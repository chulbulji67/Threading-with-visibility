package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class SharedRes1{
    private AtomicInteger count = new AtomicInteger();

    public void increment(){ count.incrementAndGet();}
    public int getCount(){ return  count.get();}
}
