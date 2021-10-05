package lab3;


public class Rational implements Comparable {

    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException();
        }
        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;

    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) {
            throw new IllegalArgumentException();
        }
        Rational rational = (Rational) o;

        float a = (float) this.numerator / this.denominator;
        float b = (float) rational.getNumerator() / rational.getDenominator();

        return a == b ? 0 : (a > b ? 1 : -1);
    }

    @Override
    public String toString() {
        int IntegerPart = this.numerator / this.denominator;
        if (this.numerator == 0) {
            return "0";
        }
        if (IntegerPart != 0){
            return String.valueOf(IntegerPart) +
                    ' ' +
                    (this.numerator - IntegerPart * this.denominator) +
                    '/' +
                    this.denominator;
        }
        return String.valueOf(this.numerator) +
                '/' +
                this.denominator;
    }

}
