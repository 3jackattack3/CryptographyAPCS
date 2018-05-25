/*
    Jack Defay
    Cipher.java
    5/23/2018
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public abstract class Cipher {

    private char[][] cipher;

    public Cipher(){  //standard cipher
        //place all of the values and then pick random locations to shift all of the values for the . and /
    }

    public Cipher(boolean random){  //fully randomized cipher
        cipher = new char[11][4];

        Random rand = new Random();  //***use "secureRandom" instead later?

        //***scramble the order later to add additional security
        for(byte i = 0; i < cipher.length-1; i++){  //initializes the first row to the values 0-9, starting from the second cell
            cipher[i+1][0] = (char) (i+48);
        }
        
        //set common characters:
            int[] spaces = new int[2];
            spaces[0] = rand.nextInt(9);
            do{
                spaces[1] = rand.nextInt(9);
            }while(spaces[1] == spaces[0]);

            Arrays.sort(spaces);

            cipher[0+1][1] = 'E';
            cipher[1+1][1] = 'T';
            cipher[2+1][1] = 'A';
            cipher[3+1][1] = 'O';
            cipher[4+1][1] = 'N';
            cipher[5+1][1] = 'R';
            cipher[6+1][1] = 'I';
            cipher[7+1][1] = 'S';

            for(int a = 8; a > spaces[0]; a--){
                cipher[a + 1][1] = cipher[a + 1 - 1][1];
            }

            cipher[spaces[0] + 1][1] = 0;

        for(int b = 9; b > spaces[1]; b--){
            cipher[b + 1][1] = cipher[b + 1 - 1][1];
        }

        cipher[spaces[1] + 1][1] = 0;

        //sets the less common characters in the next two rows
        //***randomize the "label" cell values to add additional security
            int location = rand.nextInt(20);  //is this bound inclusive?

            cipher[0][2] = (char) (48 + spaces[0]);
            cipher[0][3] = (char) (48 + spaces[1]);

            for(byte j = 65; j <= 90; j++){
                if(j != 'E' && j != 'T' && j != 'A' && j != 'O' && j != 'N' && j != 'R' && j != 'I' && j != 'S') {
                    while (cipher[1 + location % 10][2 + location / 10] != 0) {
                        location = rand.nextInt(20);
                    }
                    cipher[1 + location % 10][2 + location / 10] = (char) j;
                }
            }

        //sets the location of the '.' and the '/'
            location = 0;

            while (cipher[1 + location % 10][2 + location / 10] != 0) {
                location++;
            }
            cipher[1 + location % 10][2 + location / 10] = '.';
            while (cipher[1 + location % 10][2 + location / 10] != 0) {
                location++;
            }
            cipher[1 + location % 10][2 + location / 10] = '/';
    }

    public void writeToFile(){
        File file = new File("cipher.txt");

        PrintWriter output = null;

        try{
            output = new PrintWriter("cipher.txt");
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot create file");
            System.exit(1);
        }

        for(int i = 0; i < this.cipher[0].length; i++){
            for(int j = 0; j < this.cipher.length; j++){
                if(this.cipher[j][i] == 0) output.print('*');  //bytecode 42
                output.print(this.cipher[j][i] + " ");
            }
            output.println();
        }

        output.close();


    }

    public Cipher(String fileName){
        //initialize the cipher from the contents of a file instead, this allows for the encipher and decipher objects to be paired.
        cipher = new char[11][4];
        String[] line;
        Scanner in = null;

        try{
            in = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e){
            System.out.println("can't read the file: " + fileName);
        }

        for(int i = 0; i < cipher[0].length; i++){
            line = in.nextLine().split(" ");
            for(int j = 0; j < cipher.length; j++){  //cipher[j][i]
                if(line[j].charAt(0) != '*') cipher[j][i] = line[j].charAt(0);
            }
        }
    }

    public char[][] getCipher(){
        return cipher;
    }

    public String getStringCipher(){
        String stringCipher = "";

        for(int i = 0; i < cipher[0].length; i++){
            for(int j = 0; j < cipher.length; j++){  //cipher[j][i]
                if(this.cipher[j][i] == 0) stringCipher += '*';  //bytecode 42
                else stringCipher += cipher[j][i];
            }
        }

        return stringCipher;
    }

    protected String getEncipheredValue(char item){
        int[] index = new int[2];
        String temp = "";

        index[0] = getStringCipher().indexOf(item) / 11;
        index[1] = getStringCipher().indexOf(item) % 11;

        if((char) getCipher()[0][index[0]] != 0) temp += (char) getCipher()[0][index[0]];  //double check that these indeces are in the right order and stuff, this is a likely area of error
        temp += (char) getCipher()[index[1]][0];

        return temp;
    }

    public abstract String cipher(String message);
}
