package org.example.Task6;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Setter
public class Subscriber implements Runnable{
    private BlockingQueue<Integer> queue;
    private volatile boolean running;

    public Subscriber(BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.running = true;
    }
    @Override
    public void run() {
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Waiting 4 publisher");
        try {
            while (running) {
                Integer number = queue.take();
                numbers.add(number);

                if (numbers.size() == 1_000_000) {
                    double average = calculateAverage(numbers);
                    System.out.println("Received average: " + average);
                    numbers.clear();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform parallel merge sort with the result of avg(x mod 3)
        // Implement your merge sort logic here
    }

    private synchronized double calculateAverage(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.size();
    }
}
