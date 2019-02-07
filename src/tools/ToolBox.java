package tools;

//

public class ToolBox {
    
    public static int getRanInt(int min, int max) {//inclusive
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static int arrayMax(int[] array) {
        if (array.length == 0) {
                throw new IllegalArgumentException("array must have a length greater then 0!");
        }
        else if (array.length == 1) {return array[0];}

        int result = array[0];
        for (int i = 1; i < array.length;i++) {
                if (result < array[i]) result = array[i];
        }
        return result;
    }
}