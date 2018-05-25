/*
    Jack Defay
    Main.java
    5/23/2018
*/

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
//        Cipher encipherTest = new Encipher();
//
//        printMatrix(encipherTest.getCipher());
//
//        encipherTest.writeToFile("cipher.txt");
//
////        System.out.println("\n" + encipherTest.cipher("eee"));
//
//        Cipher decipherTest = new Decipher("cipher.txt");
//
//        System.out.println("\n" + decipherTest.cipher(encipherTest.cipher("hello my na1235me is jack")));  //encipherTest.cipher("eee")"6400049"

        int choice = 0;
        Scanner in = new Scanner(System.in);
        Cipher encipher = null;
        Cipher decipher = null;
        String fileName;
        String inputText;


        while(choice >= 0 ){
            System.out.println("1. create a random encipher\n2. create a standard encipher\n3. create an encipher from a text file\n4. enter text to encipher\n5. load a file with text to encipher\n6. create a random decipher\n7. create a standard decipher\n8. create a decipher from a text file\n9. enter text to decipher\n10. load a file with text to decipher\n-1. exit");
            choice = Integer.parseInt(in.nextLine());

            switch(choice){
                case 1: encipher = new Encipher(true);
                        break;
                case 2: encipher = new Encipher();
                        break;
                case 3: System.out.print("please enter the name of the file you want to load from: ");
                        fileName = in.nextLine();
                        encipher = new Encipher(fileName);
                        break;
                case 4: System.out.print("please enter text to encipher: ");
                        inputText = in.nextLine();
                        System.out.print(encipher.cipher(inputText));
                default: break;
                }
        }
    }

    public static void printMatrix(char[][] matrix){
        for(int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length; j++){
                System.out.print(matrix[j][i] + " ");
                if(matrix[j][i] == 0) System.out.print(" ");
            }
            System.out.println();
        }
    }
}


