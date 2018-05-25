/*
    Jack Defay
    Encipher.java
    5/23/2018
*/

import java.util.Scanner;

public class Encipher extends Cipher{

    public Encipher(boolean random){
        super(random);
    }

    public Encipher(String fileName){
        super(fileName);
    }

    public Encipher(){
        super();
    }

    @Override
    public String cipher(String message) {
        String enciphered = "";

        message = message.toUpperCase().replace(" ", "").replace(",", "").replace(".", "").replace("\'", "").replace("!", "").replace("?", "");
        //^ read in line, make all caps, and remove all common punctuation.

        for(int i = 0; i < message.length(); i++){
            if(Character.isDigit(message.charAt(i))){
                enciphered+=super.getEncipheredValue('/');
//                i++;
                while(i<message.length() && Character.isDigit(message.charAt(i))){
                    enciphered+=message.charAt(i);
                    i++;
                }
                enciphered+=super.getEncipheredValue('.');
                i--;
            }
            else enciphered += super.getEncipheredValue(message.charAt(i));
        }
        return enciphered;
    }


}
