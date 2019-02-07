/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaencryptdecrypt;

/**
 *
 * @author Raul Bazan Sanchez
 */

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;


public class RSAEncryptDecrypt 
{

    /**
     * @param args the command line arguments
     */
     
    //Private BigInts
    private static BigInteger p;
    private static BigInteger q;
    private static BigInteger N;
    private static BigInteger phi;
    private static BigInteger e;
    private static BigInteger d;
    
    //main method
    public static void main(String[] args)
    {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
        //Take in Two Messages
        System.out.println("Enter message 1");
        String plainText = sc.nextLine();
        System.out.println("Enter message 2");
        String plainTextTwo = sc.nextLine();
        
        
        RSA(plainText, plainTextTwo);
    }
    
    public static void RSA(String message, String secMessage)
    {
        
        //local variables
        int bitLength = 25;
       
        Random rnd = new Random();
        p = BigInteger.probablePrime(bitLength, rnd);
        q = BigInteger.probablePrime(bitLength, rnd);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLength, rnd);
        
        while (phi.gcd(e).compareTo(BigInteger.ONE) == 1 && e.compareTo(phi) == -1)
        {
            e.add(BigInteger.ONE);
        }
        
        d = e.modInverse(phi);
        
        //encryption
        byte[] encryptedBytes = encrypt(message.getBytes()); 
        byte[] secEncryptedBytes = encrypt(secMessage.getBytes());
      
        //decryption
        byte[] decryptedMessBytes = decrypt(encryptedBytes);
        byte[] decryptedMessBytes2 = decrypt(secEncryptedBytes);
        
        //signature
        byte[] signatureBytes = signature(message.getBytes());
        byte[] signatureBytes2 = signature(secMessage.getBytes());
        
        //verification
        byte[] veriBytes = verifcation(signatureBytes);
        byte[] veriBytesTwo = verifcation(signatureBytes2);
        
        //bigInt of encrytpion
        BigInteger encrypMess = new BigInteger(encryptedBytes);
        BigInteger encrypMess2 = new BigInteger(secEncryptedBytes);
        
        //String of decryption
        String decryptMess = new String(decryptedMessBytes);
        String decryptMess2 = new String(decryptedMessBytes2);
        
        //bigInt of Signature
        BigInteger sig = new BigInteger(signatureBytes);
        BigInteger sigTwo = new BigInteger(signatureBytes2);
        
        //String for Verification
        String verifcation = new String(veriBytes);
        String verficiationTwo = new String(veriBytesTwo); 
        
        //Homomorphic Proof
        BigInteger messBytes = new BigInteger(message.getBytes());
        BigInteger messBytesTwo = new BigInteger(secMessage.getBytes());
        byte[] multiMessageBytes = encrypt(messBytes.multiply(messBytesTwo).toByteArray());
        BigInteger multiMessage = new BigInteger(multiMessageBytes); 
        
        //E(Message) * E (Message 2) mod n 
        BigInteger multipliedMessage  = encrypMess.multiply(encrypMess2);
        BigInteger multipliedMessageMod = multipliedMessage.mod(N);
        
        //Computations 
        BigInteger eMultiplyD = e.multiply(d);
        BigInteger eDmodPhi = eMultiplyD.mod(phi);
        BigInteger GCD = e.gcd(phi);
        
        System.out.println("================================= ");
        System.out.println("Computations");
        System.out.println("n: " + N);
        System.out.println("e: " + e);
        System.out.println("d: " + d);
        System.out.println("e * d mod Phi" + eDmodPhi);
        System.out.println("GCD(e, phi)" + GCD);
       
        System.out.println("================================= ");
        System.out.println("Message 1");
        System.out.println("First Original Message: " + message);
        System.out.println("Encrypted Message: " + encrypMess );
        System.out.println("Decrypted Message: " + decryptMess);
        System.out.println("Signature: " + sig);
        System.out.println("Verfication: " + verifcation);
        System.out.println("=================================");
        System.out.println("Message Two");
        System.out.println("Second Original Message: " + secMessage);
        System.out.println("Encyrpted Message: " + encrypMess2);
        System.out.println("Decrypted String: " + decryptMess2);
        System.out.println("Signature: " + sigTwo);
        System.out.println("Verification: " + verficiationTwo );
        System.out.println("");
        System.out.println("=================================");
        System.out.println("Proving Homomorphic Properties");
        System.out.println("E(message 1) * E(message 2) =  " + multiMessage );
        System.out.println("E(message1 * mmessage2) mod N = " + multipliedMessageMod );
        
        
    
    }
    //Encrypt Method
    public static byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
    
    //Decrypt Method
    public static byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
    
    //signature method
    public static byte[] signature(byte[] message)
    {
        byte[] signature = new BigInteger(message).modPow(d,N).toByteArray();
        return signature;
    }
    //Verification Method
    public static byte[] verifcation(byte[] message)
    {
        byte[] verification = new BigInteger(message).modPow(e, N).toByteArray();
        return verification; 
    }
    
}
