package me.hottutorials.utils;

import java.util.Arrays;

public class StringUtils {

    public static String upperCaseFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String upperCaseFirstLetterEachWord(String str) {
        char[] array = str.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) array[i] = Character.toUpperCase(array[i]);
        }
        return new String(array);
    }

    public static String format(String str, Object... object) {
        String returnString = str;
        for(int i = 0; i < object.length; i++) {
            Object obj = object[i];
            returnString = returnString.replace("${" + i + "}", String.valueOf(obj));
        }
        return returnString;
    }

}
