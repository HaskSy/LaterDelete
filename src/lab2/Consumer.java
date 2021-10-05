package lab2;

import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {

    private CyclicQueue queue;
    private Thread thread;

    public Consumer(CyclicQueue queue) {
        this.queue = queue;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        consume();
    }

    private void consume() {
        if (!this.queue.empty()) {
            this.queue.get();
        }
    }

    public void start(){
        this.thread.start();
    }

    public boolean isAlive() {
        return this.thread.isAlive();
    }

}
