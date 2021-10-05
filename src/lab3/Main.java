package lab3;

public class Main {



    public static void main(String[] args) throws CloneNotSupportedException {
        ListImplement<Rational> myList = new ListImplement<>();

        myList.insert(new Rational(3,2));
        myList.insert(new Rational(6, 4));
        myList.insert(new Rational(1, 3));
        myList.insert(new Rational(5, 2));
        myList.insert(new Rational(10, 4));
        myList.insert(new Rational(2, 7));
        myList.insert(new Rational(12, 36));
        myList.insert(new Rational(16, 24));
        myList.insert(new Rational(20, 13));
        myList.insert(new Rational(23, 5));
        myList.insert(new Rational(23, 5));
        myList.insert(new Rational(23, 5));

        System.out.println(myList.printList("; "));

        System.out.println(myList.printList("; "));


        DuplicatesDeleter<Rational> deleter = new DuplicatesDeleter<>();

        deleter.deleteDuplicates(myList);

        System.out.println(myList.printList("; "));


    }
}
