package com.changan.kafka.zookeeper;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                    DistributedLock lock = new DistributedLock();
                    lock.lock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread---"+i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }

}
