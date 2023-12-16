import java.math.BigInteger;

public class DAA_1

{

 public static BigInteger square(BigInteger x) 

 {

 //If the input is a single-digit number, return the square.

 if (x.compareTo(BigInteger.TEN) < 0) 

 {

 return x.multiply(x);

 }

 // Split the input into two equal parts.

 int n = x.toString().length();

 int halfN = n / 2;

 BigInteger a = x.divide(BigInteger.TEN.pow(halfN));

 BigInteger b = x.mod(BigInteger.TEN.pow(halfN));

 // Recursive calls to compute squares of the two halves.

 BigInteger aSquare = square(a);

 BigInteger bSquare = square(b);

 // Cross-term computation.

 BigInteger crossTerm = square(a.add(b)).subtract(aSquare).subtract(bSquare);

 // Combine results using Karatsuba's formula.

 BigInteger result = 

aSquare.multiply(BigInteger.TEN.pow(2*halfN)).add(crossTer.multiply(BigInteger.TEN.pow

(halfN))).add(bSquare);

 return result;

 }

 public static void main(String[] args) 

 {

 String inputNumber = "12345678901234567999";

 BigInteger x = new BigInteger(inputNumber);

 BigInteger result = square(x);

 System.out.println("Square of " + x + " is: " + result);

 }

}