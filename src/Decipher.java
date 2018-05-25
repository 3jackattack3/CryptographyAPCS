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
//        int firstIndex = message.indexOf(super.getEncipheredValue('/')) + 2;
//        int lastIndex = message.indexOf(super.getEncipheredValue('.'));

        char item;

//        System.out.println("first index = " + firstIndex + ", and last index = " + lastIndex);
        System.out.println("\n" + message);

        for(int i = 0; i < message.length(); i++){
//            System.out.println("i = " + i + ", index = " + ((int) message.charAt(i) - '0' + 1));

            int column = super.getStringCipher().substring(0,11).indexOf(message.charAt(i));
            int nextColumn = super.getStringCipher().substring(0,11).indexOf(message.charAt(i+1));

//            System.out.println("column = " + column + ", and nextColumn = " + nextColumn);
//            System.out.println(super.getStringCipher().substring(0,11).indexOf('0'));

            if(super.getCipher()[column][1] == 0){  //if there is a null value in the first row space, then looks at two digit combos
                item = (super.getCipher()[nextColumn][super.getStringCipher().substring(11).indexOf(message.charAt(i))/10 + 1]);
                if(item == '/'){  //escape character
                    i+=3;
                    while(item != '.'){  //loop until other escape character
                        deciphered += message.charAt(i-1);
                        item = (super.getCipher()[super.getStringCipher().substring(0,11).indexOf(message.charAt(i+1))][super.getStringCipher().substring(11).indexOf(message.charAt(i))/10 + 1]);
                        i++;
                    }
//                    i-=2;  //then increment i past escape character
                }
                else{
                    deciphered += item;
                    i++;
                }
            }
            else deciphered += super.getCipher()[column][1];
        }

        return deciphered;
    }
}
