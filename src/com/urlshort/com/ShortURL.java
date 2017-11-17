package com.urlshort.com;

public class ShortURL {

    public String idToShortURL(int n) {
        // Map to store 62 possible characters
        char chars[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuilder shortUrl = new StringBuilder();

        // Convert given integer id to a base 62 number
        while (n > 0) {
            // use above map to store actual character
            // in short url
            shortUrl.append(chars[n%62]);
            n = n/62;
        }

        // Reverse shortURL to complete base conversion
        return shortUrl.reverse().toString();
    }

    public int shortURLtoID(String shortURL) {
        int id = 0; // initialize result

        // A simple base conversion logic
        for (int i=0; i < shortURL.length(); i++) {
            if ('a' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'z')
                id = id*62 + shortURL.charAt(i) - 'a';
            if ('A' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'Z')
                id = id*62 + shortURL.charAt(i) - 'A' + 26;
            if ('0' <= shortURL.charAt(i) && shortURL.charAt(i) <= '9')
                id = id*62 + shortURL.charAt(i) - '0' + 52;
        }
        return id;
    }

}