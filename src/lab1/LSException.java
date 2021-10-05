package lab1;

public class LSException extends Exception {

    private String message;

    public LSException(String message){
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
