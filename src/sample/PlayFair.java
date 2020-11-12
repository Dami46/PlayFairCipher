package sample;

import java.awt.*;

public class PlayFair {
    private static char[][] charArray;
    private static String  alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String prepareText(String inputText) {
        inputText = inputText.toUpperCase().replaceAll("[^A-Z]", "");
        return inputText.replace("J", "I");
    }

    public static void createTable(String key) {
        charArray = new char[5][5];
        Point[] lettersPositions = new Point[26];


        String lettersInTable = prepareText(key + alphabet);

        int len = lettersInTable.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = lettersInTable.charAt(i);
            if (lettersPositions[c - 'A'] == null) {
                charArray[k / 5][k % 5] = c;
                lettersPositions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }

    public static String encrypt(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i += 2) {
            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");

            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        return playfairCypher(sb, 1);
    }

    public static String decrypt(String s) {
        try {
            String decodedText = playfairCypher(new StringBuilder(s), 4);
            if (decodedText.endsWith("X")) {
                decodedText = decodedText.replace("X", "");
            }
            return decodedText;
        } catch (NullPointerException e) {
            return " ";
        }
    }

    private static String playfairCypher(StringBuilder text, int direction) {
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int row1 = (int) getPoint(a).getX();
            int row2 = (int) getPoint(b).getX();
            int col1 = (int) getPoint(a).getY();
            int col2 = (int) getPoint(b).getY();

            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;

            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;

            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }

            text.setCharAt(i, charArray[row1][col1]);
            text.setCharAt(i + 1, charArray[row2][col2]);
        }
        return text.toString();
    }

    private static Point getPoint(char c){
        Point pt = new Point(0,0);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(c == charArray[i][j])
                    pt = new Point(i,j);
        return pt;
    }
}