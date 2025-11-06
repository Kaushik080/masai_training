package com.example;

public class StringUtils {
    public boolean isPalindrome(String input){
        if(input == null){
         return false;
        }
    String cleaned = input.toLowerCase().replaceAll("\\s+", "");

        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public String reverse(String input){
        if(input == null){
            return null;
        }
        StringBuilder sb = new StringBuilder(input);
        return  sb.reverse().toString();
    }

    public boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }
}

