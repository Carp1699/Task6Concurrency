package org.example.Task6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Create and start the subscriber
        Subscriber subscriber = new Subscriber(queue);
        Thread subscriberThread = new Thread(subscriber);
        executorService.submit(subscriberThread);

        // Create and start the publishers
        Publisher publisher1 = new Publisher(queue);
        Publisher publisher2 = new Publisher(queue);
        Publisher publisher3 = new Publisher(queue);
        Publisher publisher4 = new Publisher(queue);


        Thread publisherThread1 = new Thread(publisher1);
        Thread publisherThread2 = new Thread(publisher2);
        Thread publisherThread3 = new Thread(publisher3);
        Thread publisherThread4 = new Thread(publisher4);


        executorService.submit(publisherThread1);
        executorService.submit(publisherThread2);
        executorService.submit(publisherThread3);


        // Wait for the publishers to finish
        try {
            publisherThread1.join();
            publisherThread2.join();
            publisherThread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(publisherThread4);
        // Wait for the subscriber to finish
        try {
            subscriberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
