package lab2;

public class CyclicQueue implements Queue {

    int head;
    int tail;
    int[] QueueArray;
    boolean status;

    public boolean isStatus() {
        return status;
    }

    public CyclicQueue(int size) {
        QueueArray = new int[size + 1];
        head = 0;
        tail = 0;
        status = false; // get - false | put - true
    }

    @Override
    public void put(int val) {
        tail = (tail + 1) % QueueArray.length;
        if (tail == head) { // Queue is full
            if (tail == 0) {
                tail = QueueArray.length - 1;
            } else {
                tail -= 1;
            }
            return;
        }
        QueueArray[tail] = val;
        status = true;
    }

    @Override
    public int get() {
        if (tail == head) { // Queue is empty
            return -1;
        }
        int tmp = QueueArray[head];
        head = (head + 1) % QueueArray.length;
        status = false;
        return tmp;
    }

    @Override
    public boolean full() {
        return (this.tail + 1) % this.QueueArray.length == this.head;
    }

    @Override
    public boolean empty() {
        return this.tail == this.head;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder("Queue state: ");
        int curr = head;
        if (curr == tail) {
            ans.append("EMPTY");
        } else while (curr != tail) {
            curr = (curr + 1) % QueueArray.length;
            ans.append(QueueArray[curr]).append(" ");
        }
        return ans.toString();
    }


}
