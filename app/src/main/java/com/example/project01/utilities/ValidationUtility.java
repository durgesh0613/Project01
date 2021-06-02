package com.example.project01.utilities;

import java.util.Arrays;

//Common Utility Class - Non Business specific functions.
public class ValidationUtility {
    //Checks if any of the provided strings are empty.
    //Return true is empty.
    public static boolean isAnyEmpty(String... fieldValues) {
        return Arrays.stream(fieldValues).anyMatch(fieldValue -> "".equals(fieldValue.trim()));
    }
}
