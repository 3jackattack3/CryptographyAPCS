/*
    Jack Defay
    Main.java
    5/23/2018
*/

public class Main {
    public static void main(String args[]) {
        Cipher encipherTest = new Encipher();

        printMatrix(encipherTest.getCipher());

        encipherTest.writeToFile("cipher.txt");

//        System.out.println("\n" + encipherTest.cipher("eee"));

        Cipher decipherTest = new Decipher("cipher.txt");

        System.out.println("\n" + decipherTest.cipher(encipherTest.cipher("hello my na1235me is jack")));  //encipherTest.cipher("eee")"6400049"
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


