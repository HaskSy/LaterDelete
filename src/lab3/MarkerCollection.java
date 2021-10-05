package lab3;

public interface MarkerCollection<K> {

    K end();
    void insert(K obj, int pos);
    void insert(K obj);
    int locate(K obj);
    K retrieve(int pos);
    void delete(int pos, int to);
    K next(int pos);
    K previous(int pos);
    void makeNull();
    K first();
    String printList(String sep);
    int size();
}
