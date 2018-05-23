/*
    Jack Defay
    Main.java
    5/23/2018
*/

public class Main {
    public static void main(String args[]) {
        Cipher test = new Encipher(true);

        printMatrix(test.getCipher());

        test.writeToFile();
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


