package lab2;

public class Main {
    public static void main(String[] args) {

        CyclicQueue queue = new MThreadCyclicQueue(10);

        Producer producer = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        producer.start();
        consumer1.start();
        consumer2.start();

        while (true) {
            System.out.println(queue.toString());
            producer.run();
            System.out.println(queue.toString());
            consumer1.run();
            System.out.println(queue.toString());
            consumer2.run();
        }
    }
}
