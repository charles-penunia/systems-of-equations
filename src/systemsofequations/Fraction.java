
package systemsofequations;

/* Charles Penunia. CMPSC 221.
 * This class contains a fraction
 */

public class Fraction{
    private int numerator, denominator;
    public Fraction (int n1, int d1){
        if (d1 == 0)
            throw new IllegalArgumentException("The fraction is invalid.");
        numerator = n1;
        denominator = d1;
        reduceFraction(n1, d1);
    }
    public void add(Fraction f){
        if (denominator == f.getDenominator()){
            numerator += f.getNumerator();
            reduceFraction(numerator, denominator);
        }
        else{
            numerator = numerator * f.getDenominator() 
                    + f.getNumerator()* denominator;
            denominator *= f.getDenominator();
            reduceFraction(numerator, denominator);
        }
    }
    public String getFractionString(){
        if (numerator == 0 || denominator == 1)
            return numerator + "";
        return numerator + "/" + denominator;
    }
    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }
    private void reduceFraction(int tempNumer, int tempDenom){
        int temp, gcd;
        do{
            temp = tempNumer;
            tempNumer = tempDenom;
            tempDenom = temp%tempDenom;
        }while (tempDenom != 0);
        gcd = tempNumer;
        
        numerator = numerator/gcd;
        denominator = denominator/gcd;
        if (denominator < 0){
            numerator = -numerator;
            denominator = -denominator;
        }
    }
}
