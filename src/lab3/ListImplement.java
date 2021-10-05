package lab3;


import java.util.Collection;

public class ListImplement<T> implements MarkerCollection<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size;
    private int capacity;

    public ListImplement(int n) {
        this.capacity = Math.max(DEFAULT_CAPACITY, n);
        this.array = new Object[capacity];
        size = 0;
    }

    public ListImplement() {
        this.array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public T end() {
        return (T) this.array[this.size];
    }

    @Override
    public void insert(T obj, int pos) {
        if (pos > this.size || pos < 0) {
            return;
        }
        if (this.size == this.capacity) {
            this.capacity = 3*this.capacity/2 + 1;
            Object[] newArray = new Object[this.capacity];
            for (int i = 0; i < this.size; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        if (pos == this.size) {
            this.size++;
        }
        this.array[pos] = obj;
    }

    @Override
    public void insert(T obj) {
        insert(obj, this.size);
    }

    @Override
    public int locate(T obj) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(obj)) {
                return i;
            }
        }
        return this.size;
    }

    @Override
    public T retrieve(int pos) {
        if (pos >= this.size || pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.array[pos];
    }

    @Override
    public void delete(int pos, int to) {
        // [a; b)
        if (pos >= this.size || pos < 0 || to < pos || to > this.size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = pos; i <= this.size - (to - pos + 1); i++) {
            this.array[i] = this.array[i + (to - pos)];
        }

        int newSize = this.size;
        for (int i = this.size - (to - pos); i < this.size ; i++) {
            this.array[i] = null;
            newSize--;
        }
        this.size = newSize;


    }

    public T next(int pos) {
        if (pos > this.size || pos < -1) {
            throw new IndexOutOfBoundsException();
        }
        if (pos + 1 == this.size) {
            return this.end();
        }
        return (T) this.array[pos + 1];
    }

    public T previous(int pos) {
        if (pos > this.size || pos < 1) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.array[pos + 1];
    }

    public void makeNull() {
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
        this.size = 0;
    }

    public T first() {
        if (size == 0) {
            return end();
        }
        return (T) this.array[0];
    }

    public String printList(String sep) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < this.size; ) {
            ans.append(this.array[i].toString());
            i++;
            if (i != this.size) {
                ans.append(sep);
            }
        }
        return ans.toString();
    }
}
