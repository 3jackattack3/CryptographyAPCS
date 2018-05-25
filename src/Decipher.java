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

    public Decipher(){ super();}

    @Override
    public String cipher(String message) {
        String deciphered = "";

        char item;
        int nextColumn;
        int column;

//        System.out.println("\n" + message);

        for(int i = 0; i < message.length(); i++){
            column = super.getStringCipher().substring(0,11).indexOf(message.charAt(i));
            if(super.getCipher()[column][1] != 0){  //if there is a null value in the first row space, then looks at two digit combos
                deciphered += super.getCipher()[column][1];
            }
            else{
                nextColumn = super.getStringCipher().substring(0,11).indexOf(message.charAt(i+1));
                item = (super.getCipher()[nextColumn][super.getStringCipher().substring(11).indexOf(message.charAt(i))/10 + 1]);  //getCipher[nextColumn][row]
                if(item == '/'){  //escape character
                    i+=2;
//                    while(item != '.'){  //loop until other escape character
//                        deciphered += message.charAt(i-1);
//                        item = (super.getCipher()[super.getStringCipher().substring(0,11).indexOf(message.charAt(i+1))][super.getStringCipher().substring(11).indexOf(message.charAt(i))/10 + 1]);
//                        i++;
//                    }
                    deciphered += message.charAt(i);
                    i+=2;
                }
                else{
                    deciphered += item;
                    i++;
                }
            }
        }

        return deciphered;
    }
}
