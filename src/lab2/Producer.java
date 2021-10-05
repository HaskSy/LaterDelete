package lab2;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable {

    private CyclicQueue queue;
    private Thread thread;
    private int value;

    public Producer(CyclicQueue queue) {
        this.queue = queue;
        this.thread = new Thread(this);
        this.value = 0;
    }
    @Override
    public void run() {
        produce();
    }

    private void produce() {
        if (!queue.full()) {
            if (!queue.isStatus()) {
                value += 1;
            }
            queue.put(value);
        }
    }

    public void start(){
        this.thread.start();
    }

    public boolean isAlive() {
        return this.thread.isAlive();
    }
}
