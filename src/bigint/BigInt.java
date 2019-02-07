/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigint;
import java.math.BigInteger;

/**
 *
 * @author rsanchez
 */
public class BigInt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Print out factorial of each number 2-50
         BigInteger bigFactorial = new BigInteger("1");
        for(int i = 2; i <= 50; i++)
        {
           bigFactorial = bigFactorial.multiply(new BigInteger(i + ""));
          
           System.out.println(i + "! = " +bigFactorial);
           
        }
    }
    
}
