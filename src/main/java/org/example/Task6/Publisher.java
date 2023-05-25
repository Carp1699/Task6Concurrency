package org.example.Task6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Publisher implements Runnable{
    private BlockingQueue<Integer> queue;
    private Random random;
    public Publisher(BlockingQueue<Integer> queue){
        this.queue = queue;
        this.random = new Random();
    }
    @Override
    public void run() {

        try{
            for (int i = 0; i< 1_000_000; i++){
                int randomNumber = generateRandomNumber();
                queue.put(generateRandomNumber());
            }
        }catch (InterruptedException e){
            throw  new RuntimeException();
        }
    }
    private int generateRandomNumber() {
        return random.nextInt() % 3;
    }
}
