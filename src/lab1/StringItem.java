package lab1;

public class StringItem {

    private char [] symbols;
    private StringItem next;
    private byte size;

    public StringItem(){
        this.next = null;
        this.size = 0;
        this.symbols = new char[16];
    }

    public char[] getSymbols() {
        return symbols;
    }

    public void setSymbols(char[] symbols) {
        this.symbols = symbols;
    }

    public StringItem getNext() {
        return next;
    }

    public void setNext(StringItem next) {
        this.next = next;
    }

    public byte getSize() {
        return size;
    }

    public void setSize(byte size) {
        this.size = size;
    }

    public void setSymbol(int index, char ch) {
        if (index < this.size) {
            this.symbols[index] = ch;
        }
        else if (index == this.size && this.size < 16) {
            this.symbols[index] = ch;
            this.size += 1;
        }
    }
}
