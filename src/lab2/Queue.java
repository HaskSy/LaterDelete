package lab2;

public interface Queue {
    void put(int val);
    int get();
    boolean full();
    boolean empty();
}