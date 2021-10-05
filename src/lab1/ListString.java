package lab1;

import java.util.ArrayList;

public class ListString implements Operations {


    private StringItem head;
    private int length;
    private boolean changed;

    public ListString() {
        this.head = new StringItem();
        this.length = 0;
        this.changed = true;
    }

    @Override
    public int length() {
        if (this.changed) {

            int len = 0;
            StringItem pointer = this.head;

            while (pointer != null && pointer.getNext() != null) {

                if (pointer.getSize() + pointer.getNext().getSize() <= 16) {

                    char[] slow = pointer.getSymbols();
                    char[] fast = pointer.getNext().getSymbols();
                    char[] updated = new char[16];

                    byte newSize = 0;

                    for (int i = 0; i < pointer.getSize(); ++i) {

                        updated[newSize] = slow[i];
                        newSize++;
                    }

                    for (int i = 0; i < pointer.getNext().getSize(); ++i) {

                        updated[newSize] = fast[i];
                        newSize++;
                    }

                    pointer.setSymbols(updated);
                    pointer.setSize(newSize);
                    pointer.setNext(pointer.getNext().getNext());

                } else {

                    len += pointer.getSize();
                    pointer = pointer.getNext();
                }
            }

            this.changed = false;
            this.length = len;
            return this.length;
        }
        return this.length;
    }

    @Override
    public char charAt(int index) throws LSException {

        if (index >= this.length() || index < 0) {
            throw new LSException("Wrong index typed");
        }

        int pointerIndex = 0;
        StringItem pointer = this.head;

        while (index < pointerIndex + pointer.getSize() - 1) {
            pointerIndex += pointer.getSize();
            pointer = pointer.getNext();
        }

        return pointer.getSymbols()[index - pointerIndex];
    }

    @Override
    public void setCharAt(int index, char ch) throws LSException {

        if (index >= this.length() || index < 0) {
            throw new LSException("Wrong index typed");
        }

        int pointerIndex = 0;
        StringItem pointer = this.head;

        while (index < pointerIndex + pointer.getSize() - 1) {
            pointerIndex += pointer.getSize();
            pointer = pointer.getNext();
        }

        pointer.setSymbol(index - pointerIndex, ch);
    }

    @Override
    public ListString substring(int start, int end) throws LSException {

        int len = this.length();

        if (start >= end) {
            throw new LSException("Start index cannot be greater that end index");
        }

        if (start >= len) {
            throw new LSException("Start index cannot be greater that ListString size");
        }

        if (start < 0) {
            throw new LSException("Index cannot be negative");
        }

        if (end >= len) {
            end = len;
        }

        ListString result = new ListString();
        int pointerIndex = 0;
        StringItem pointer = this.head;

        while (start < end) {

            while (start < pointerIndex || start >= pointerIndex + pointer.getSize() - 1) {
                pointerIndex += pointer.getSize();
                pointer = pointer.getNext();
            }

            int i = 0;

            for (;start + i < end && start - pointerIndex + i < pointer.getSize(); i++) {
                result.append(pointer.getSymbols()[start - pointerIndex + i]);
            }

            start += i;
            pointerIndex += pointer.getSize();
            pointer = pointer.getNext();

        }

        return result;

    }

    @Override
    public void append(char ch) {

        StringItem pointer = this.head;

        while (pointer.getNext() != null) {

            pointer = pointer.getNext();
        }

        if (pointer.getSize() < 16) {

            pointer.setSymbol(pointer.getSize(), ch);

        } else {

            StringItem newItem = new StringItem();
            newItem.setSymbol(0, ch);
            pointer.setNext(newItem);
        }
    }

    @Override
    public void append(ListString string) throws LSException {

        for (int i = 0; i < string.length(); i++) {

            this.append(string.charAt(i));

        }

    }

    @Override
    public void append(String string) {

        for (int i = 0; i < string.length(); i++) {

            this.append(string.charAt(i));
        }
    }

    private void insertHelper(int index, char ch) {

        int pointerIndex = 0;
        StringItem pointer = this.head;

        while (index < pointerIndex || index >= pointerIndex + pointer.getSize() - 1) {

            pointerIndex += pointer.getSize();
            pointer = pointer.getNext();
        }

        StringItem newItem = new StringItem();
        newItem.setSymbol(0, ch);

        if (index - pointerIndex == 0) {

            StringItem previousBlock = this.head;

            if (previousBlock == pointer) {

                newItem.setNext(this.head);
                this.head = newItem;

            } else {

                while (previousBlock.getNext() != pointer) {

                    previousBlock = previousBlock.getNext();
                }

                newItem.setNext(pointer);
                previousBlock.setNext(newItem);
            }

        } else if (index - pointerIndex == 16) {

            if (pointer.getNext() != null) {

                newItem.setNext(pointer.getNext());
            }
            pointer.setNext(newItem);

        } else {

            char[] left = new char[16];
            char[] right = new char[16];

            int ind = index - pointerIndex;

            for (int i = 0; i < ind; i++) {

                left[i] = pointer.getSymbols()[i];
            }

            for (int i = ind; i < pointer.getSize(); i++) {

                right[i - ind] = pointer.getSymbols()[i];
            }

            pointer.setSymbols(left);
            pointer.setSize((byte)ind);

            StringItem newItem2 = new StringItem();
            newItem2.setSymbols(right);
            newItem2.setSize((byte)(pointer.getSize() - ind));

            newItem2.setNext(pointer.getNext());
            newItem.setNext(newItem2);
            pointer.setNext(newItem);

        }
    }

    @Override
    public void insert(int index, ListString string) throws LSException {

        if (index < 0 || index > this.length()) {
            throw new LSException("Wrong index typed");
        }

        for (int i = 0; i < string.length(); ++i) {
            this.insertHelper(index + i, string.charAt(i));
        }
    }

    @Override
    public void insert(int index, String string) throws LSException {

        if (index < 0 || index > this.length()) {
            throw new LSException("Wrong index typed");
        }

        for (int i = 0; i < string.length(); ++i) {
            this.insertHelper(index + i, string.charAt(i));
        }
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        StringItem pointer = this.head;

        while (pointer != null) {

            for (int i = 0; i < pointer.getSize(); ++i) {
                result.append(pointer.getSymbols()[i]);
            }
            pointer = pointer.getNext();
        }

        return result.toString();
    }


}