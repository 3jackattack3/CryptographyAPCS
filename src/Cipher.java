/*
    Jack Defay
    Cipher.java
    5/23/2018
*/

import java.util.ArrayList;
import java.util.Random;

public abstract class Cipher {

    private char[][] cipher;

    public Cipher(){
        cipher = new char[11][4];

        Random rand = new Random();  //***use "secureRandom" instead later?

        //***scramble the order later to add additional security
        for(byte i = 0; i < cipher.length-1; i++){  //initializes the first row to the values 0-9, starting from the second cell
            cipher[i+1][0] = (char) (i+48);
        }

        //***randomize which cells are empty to add additional security
        //set common characters:
            cipher[0+1][1] = 'E';
            cipher[1+1][1] = 'T';
            cipher[2+1][1] = 'A';
            cipher[3+1][1] = 'O';

            cipher[5+1][1] = 'N';

            cipher[7+1][1] = 'R';
            cipher[8+1][1] = 'I';
            cipher[9+1][1] = 'S';

        //sets the less common characters in the next two rows
        //***randomize the "label" cell values to add additional security
            int location = rand.nextInt(20);  //is this bound inclusive?

            cipher[0][2] = 48+4;
            cipher[0][3] = 48+6;

            for(byte j = 0; j <= 90-48; j++){
                while(cipher[1 + location%10][1 + location/10] != 0){
                    cipher[1 + location%10][1 + location/10] = (char) (j+48);
                    location = rand.nextInt(20);
                }
            }

    }

    public Cipher(String fileName){
        //initialize the cipher from the contents of a file instead, this allows for the encipher and decipher objects to be paired.
    }

    public char[][] getCipher(){
        return cipher;
    }

}
