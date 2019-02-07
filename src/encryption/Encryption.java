/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Written by Raul Bazan Sanchez
package encryption;

/**
 *
 * @author rsanchez
 */
public class Encryption {

    /**
     * @param args the command line arguments
     */
    public static String encrypt(String plainText, int shiftKey)
    {
         
        
        String cipherText = "";                                            //empty string to put cipher text in
         
         for(int i = 0; i < plainText.length(); i++)
         {
             int charPosition = plainText.indexOf(plainText.charAt(i));     //position of encryption
             int keyVal = (shiftKey + charPosition) % 26;                   //value of encrypted letter
             
             char replaceVal = plainText.charAt(keyVal);                    //value at new encrypted value
             cipherText += replaceVal;                                      //add letters to empty string.
             
          
             
         }
         
         return cipherText;                                     //return encrypted message
       
    
    }
     
    public static String decrypt(String cipherText, int shiftKey)
    {
        
        String plainText = "";                                              //empty text
        
        for(int i = 0; i < cipherText.length(); i++)                        //for loop 
        {
            int charPosition = cipherText.indexOf(cipherText.charAt(i));   // position of letter 
            int keyVal = (charPosition - shiftKey) % 26;                   // decryption
            
            
            
            
            if(keyVal < 0)                                                  // to ensure decyption vlaue is positive
            {
                keyVal = cipherText.length() + keyVal;
            }
            char replaceValue = cipherText.charAt(keyVal);                  //letter with new value
          
            plainText += replaceValue;                                      //add letters to the string. 
        }
        return plainText;                                                   //return decrypted message
    }
    public static void main(String[] args) 
    {
        
        // TODO code application logic here
        String message1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println(encrypt (message1, 3));
        System.out.println(decrypt (encrypt(message1,3) ,3));
        System.out.println(encrypt(message1.toLowerCase(),5));
        
        System.out.println(decrypt(encrypt(message1.toLowerCase() ,5 ) ,5));
    }
    
}
