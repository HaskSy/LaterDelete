package lab2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MThreadCyclicQueue extends CyclicQueue{

    ReentrantLock locker;
    Condition condition;

    public MThreadCyclicQueue(int size) {
        super(size);
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    @Override
    public int get() {
        int value;
        locker.lock();
        try {
            while (super.empty()) {
                condition.await();
            }
            value = super.get();
            condition.signalAll();
        }
        catch (InterruptedException e) {
            value = -1;
            System.out.println(e.getMessage());
        }
        finally {

            locker.unlock();
        }
        return value;
    }

    @Override
    public void put(int val) {
        locker.lock();
        try {
            while (super.full()) {
                condition.await();
            }
            super.put(val);
            condition.signalAll();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        finally {
            locker.unlock();
        }
    }
}
