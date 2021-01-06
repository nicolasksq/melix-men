package com.melix.men.Utils;

public class Utils {
    public static char[][] convertToMatrix(String[] array){
        char[][] matrix = new char[array.length][array.length];
        for (int index = 0; index < array.length; index++) {
            System.arraycopy(array[index].toCharArray(), 0, matrix[index], 0, array[0].length());
        }
        return matrix;
    }
}
