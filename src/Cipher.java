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

        char[] letters = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'P', 'Q','U', 'V', 'W', 'X', 'Y', 'Z',0,0};

        spaces[0] = rand.nextInt(18);
        do{
            spaces[1] = rand.nextInt(18);
        }while(spaces[1] == spaces[0]);

        Arrays.sort(spaces);

        for(int c = 18; c > spaces[0]; c--){
            letters[c + 1] = letters[c + 1 - 1];
        }

        letters[spaces[0] + 1] = '/';

        for(int d = 18; d > spaces[1]; d--){
            letters[d + 1] = letters[d + 1 - 1];
        }

        letters[spaces[1] + 1] = '.';

//        System.out.print(letters);

        for(int l = 0; l < 10; l++){
            cipher[l+1][2] = letters[l];
            cipher[l+1][3] = letters[l+9];
        }

        writeToFile("cipher.txt");
    }

    public Cipher(boolean random){  //fully randomized cipher
        cipher = new char[11][4];

        Random rand = new Random();  //***use "secureRandom" instead later?

        int location = rand.nextInt(10);

        for(byte j = 0; j <= 9; j++){
            while (cipher[1 + location % 10][0] != 0) {
                location = rand.nextInt(10);
            }
            cipher[1 + location % 10][0] = (char) (j+48);
        }

        //set common characters:
            int[] spaces = new int[2];
            spaces[0] = rand.nextInt(9);
            do{
                spaces[1] = rand.nextInt(9);
            }while(spaces[1] == spaces[0]);

            Arrays.sort(spaces);

            char[] commonLetters = {'E', 'T', 'A', 'O', 'N', 'R', 'I', 'S'};
            location = rand.nextInt(8);

            for(byte k = 0; k < 8; k++){
                while (cipher[1 + location % 10][1] != 0){
                    location = rand.nextInt(8);
                }
                cipher[1 + location % 10][1] = commonLetters[k];
            }

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
            location = rand.nextInt(20);  //is this bound inclusive?

            cipher[0][2] = cipher[spaces[0] + 1][0];  //(char) (48 + cipher[spaces[0]][0]);
            cipher[0][3] = cipher[spaces[1] + 1][0];  //(char) (48 + cipher[spaces[1]][0]);

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

        writeToFile("cipher.txt");
    }

    public void writeToFile(String fileName){
        File file = new File(fileName);

        PrintWriter output = null;

        try{
            output = new PrintWriter(file);
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

        if(index[0] == 1) temp += getCipher()[index[1]][0];
        else {
            temp += (char) getCipher()[0][index[0]];  //double check that these indeces are in the right order and stuff, this is a likely area of error
            temp += (char) getCipher()[index[1]][0];
        }

        return temp;
    }

    public abstract String cipher(String message);
}
