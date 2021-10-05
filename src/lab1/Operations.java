package lab1;

public interface Operations {
    int length(); //длина строки
    char charAt(int index) throws LSException; // символ в строке в позиции index
    void setCharAt(int index, char ch) throws LSException; //заменить символ в позиции index на ch
    ListString substring(int start, int end) throws LSException; //взятие подстроки, от start до end, не включая
    void append(char ch); //добавить в конец символ
    void append(ListString string) throws LSException; //добавить в конец строку ListString
    void append(String string); //добавить в конец строку String
    void insert(int index, ListString string) throws LSException; //вставить в строку в позицию index строку
    void insert(int index, String string) throws LSException; //вставить в строку в позицию index строку
    public String toString(); //переопределить метод
}