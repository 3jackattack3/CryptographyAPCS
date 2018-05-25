/*
    Jack Defay
    Decipher.java
    5/23/2018
*/

import java.sql.SQLSyntaxErrorException;

public class Decipher extends Cipher{

    public Decipher(boolean random){
        super(random);
    }

    public Decipher(String fileName){
        super(fileName);
    }

    @Override
    public String cipher(String message) {
        String deciphered = "";
        int firstIndex = message.indexOf(super.getEncipheredValue('/')) + 2;
        int lastIndex = message.indexOf(super.getEncipheredValue('.'));

//        System.out.println("first index = " + firstIndex + ", and last index = " + lastIndex);
//        System.out.println(message);

        for(int i = firstIndex; i < lastIndex; i++){
//            System.out.println("i = " + i + ", index = " + ((int) message.charAt(i) - '0' + 1));
            if(super.getCipher()[((int) message.charAt(i) - '0' + 1)][1] == 0){
                deciphered += super.getCipher()[((int) message.charAt(i+1) - '0' + 1)][super.getStringCipher().substring(11).indexOf(message.charAt(i))/10 + 1];
                i++;
            }
            else deciphered += super.getCipher()[((int) message.charAt(i) - '0' + 1)][1];
        }

        return deciphered;
    }
}
