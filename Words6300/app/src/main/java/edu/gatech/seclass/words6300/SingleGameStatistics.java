package edu.gatech.seclass.words6300;

import java.util.ArrayList;
import java.util.HashMap;

public class SingleGameStatistics {
    public int averageTurnScore;
    public HashMap<String,Integer> letterWordNumber;
    public HashMap<String,Integer> lettersBackPool;
    public HashMap<String,Integer> drawLetterWordPerc;

    public SingleGameStatistics() {
        averageTurnScore = 0;
        letterWordNumber = new HashMap<String,Integer>();
        lettersBackPool = new HashMap<String,Integer>();
        drawLetterWordPerc = new HashMap<String,Integer>();
    }

    public int calAverageTurnScore(Integer[] scores_turn, int turn){
        int averageTurnScore = 0;
        for (int i : scores_turn) {
            averageTurnScore += i;
        }
        averageTurnScore = averageTurnScore / turn;
        return averageTurnScore;
    }

    public HashMap<String,Integer> calLetterWordNumber(String word){
        char arr[] = word.toCharArray();
        for (char c : arr){
            if (letterWordNumber.get(Character.toString(c)) == null) {
                letterWordNumber.put(Character.toString(c), 1);
            } else {
                letterWordNumber.put(Character.toString(c), letterWordNumber.get(Character.toString(c)) + 1);
            }
        }
        return letterWordNumber;
    }

    public HashMap<String,Integer> calLettersBackPool(ArrayList<String> letters){
        for (String s : letters){
            if (lettersBackPool.get(s) == null) {
                lettersBackPool.put(s, 1);
            } else {
                lettersBackPool.put(s, lettersBackPool.get(s) + 1);
            }
        }
        return lettersBackPool;
    }

    public HashMap<String,Integer> calDrawLetterWordPerc(ArrayList<String> letters, String word){
        char arr[] = word.toCharArray();
        int length = arr.length;
        HashMap<String,Integer> letterWordNumberMap = calLetterWordNumber(word);
        for (char c : arr){
            int count = 0;
            if (letterWordNumberMap.get(Character.toString(c)) != null) {
                count = letterWordNumberMap.get(Character.toString(c));
            }
            drawLetterWordPerc.put(Character.toString(c), count / length);
        }
        return drawLetterWordPerc;
    }
}
