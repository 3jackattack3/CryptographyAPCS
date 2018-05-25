/*
    Jack Defay
    Main.java
    5/23/2018
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        File encipheredOutput;


        while(choice >= 0 ){
            System.out.println("\n1. create a random encipher\n2. create a standard encipher\n3. create an encipher from a text file\n4. enter text to encipher\n5. load a file with text to encipher\n6. create a random decipher\n7. create a standard decipher\n8. create a decipher from a text file\n9. enter text to decipher\n10. load a file with text to decipher\n11. print grid\n12. check for an encipher grid\n13. check for a decipher grid\n-1. exit");
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
                        System.out.println("that phrase enciphered = " + encipher.cipher(inputText));
                        writeStringToFile(encipher, inputText, "encipheredOutput.txt");
                        break;
                case 5: System.out.print("please enter the name of a file to encipher: ");
                        fileName = in.nextLine();
                        writeToFile(encipher, fileName, "encipheredOutput.txt");
                        break;
                case 6: decipher = new Decipher(true);
                        break;
                case 7: decipher = new Decipher();
                        break;
                case 8: System.out.print("please enter the name of the file you want to load from: ");
                        fileName = in.nextLine();
                        decipher = new Decipher(fileName);
                        break;
                case 9: System.out.print("please enter text to decipher: ");
                        inputText = in.nextLine();
                        System.out.println("that phrase deciphered = " + decipher.cipher(inputText));
                        writeStringToFile(decipher, inputText, "decipheredOutput.txt");
                        break;
                case 10: System.out.print("please enter the name of the file you want to load from: ");
                         fileName = in.nextLine();
                         writeToFile(decipher, fileName, "decipheredOutput.txt");
                         break;
                case 11: printMatrix(encipher.getCipher());
                         break;
                case 12: System.out.println(encipher != null);
                         break;
                case 13: System.out.println(decipher != null);
                         break;
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

    public static void writeToFile(Cipher cipher, String readFrom, String writeTo){
        File encipherFile = new File(readFrom);
        Scanner fileReader = null;
        PrintWriter output = null;

        try{
            fileReader = new Scanner(encipherFile);
        }
        catch(FileNotFoundException e){
            System.out.print("could not load file: " + readFrom);
        }

        try{
            output = new PrintWriter(writeTo);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot create file");
            System.exit(1);
        }

        String line;

        while(fileReader.hasNextLine()) {
            line = fileReader.nextLine();
            line = cipher.cipher(line);
            output.print(line);
            output.println();
        }

        output.close();
    }

    public static void writeStringToFile(Cipher cipher, String message, String writeTo){
        PrintWriter output = null;
        String line = message;

        try{
            output = new PrintWriter(writeTo);
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot create file");
            System.exit(1);
        }

//        System.out.println(cipher instanceof  Encipher);

        if(cipher instanceof Encipher) line = ((Encipher) cipher).cipher(message);
        else if(cipher instanceof Decipher) line = ((Decipher) cipher).cipher(message);

        output.print(line);
        output.println();

        output.close();
    }
}



