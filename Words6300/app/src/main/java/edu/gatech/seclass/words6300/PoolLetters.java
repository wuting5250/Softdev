package edu.gatech.seclass.words6300;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;

public class PoolLetters implements Serializable {
    public int max_turns;
    public int noTurn;
    public int gameScore;
    public int turnScore;
    public String wordarr;
    Boolean pool_empty;
    public String playGame;
    public String boardLetter;
    public String rackLetter;
    public String swap_rack;
    public String rack_selected;
    public ArrayList selectedRacks;
    public HashMap<Integer, Integer> scores_turn;
    public HashMap<Integer, String> words;
    public HashMap<String, Integer> num_letter_pool = new HashMap<>();
    public HashMap<String, Integer> letter_points = new HashMap<>();
    public HashMap<String, Integer> drawletters = new HashMap<>();
    public  HashMap<String, Integer> swapletters = new HashMap<>();
    public  HashMap<String, Integer> wordsletters = new HashMap<>();



    public PoolLetters(){
        this.num_letter_pool.put("/", 2);
        this.num_letter_pool.put("A", 9);
        this.num_letter_pool.put("B", 2);
        this.num_letter_pool.put("C", 2);
        this.num_letter_pool.put("D", 4);
        this.num_letter_pool.put("E", 12);
        this.num_letter_pool.put("F", 2);
        this.num_letter_pool.put("G", 3);
        this.num_letter_pool.put("H", 2);
        this.num_letter_pool.put("I", 9);
        this.num_letter_pool.put("J", 1);
        this.num_letter_pool.put("K", 1);
        this.num_letter_pool.put("L", 4);
        this.num_letter_pool.put("M", 2);
        this.num_letter_pool.put("N", 6);
        this.num_letter_pool.put("O", 8);
        this.num_letter_pool.put("P", 3);
        this.num_letter_pool.put("Q", 1);
        this.num_letter_pool.put("R", 6);
        this.num_letter_pool.put("S", 4);
        this.num_letter_pool.put("T", 6);
        this.num_letter_pool.put("U", 4);
        this.num_letter_pool.put("V", 2);
        this.num_letter_pool.put("W", 2);
        this.num_letter_pool.put("X", 1);
        this.num_letter_pool.put("Y", 2);
        this.num_letter_pool.put("Z", 1);

        this.letter_points.put("/", 0);
        this.letter_points.put("A", 1);
        this.letter_points.put("B", 3);
        this.letter_points.put("C", 3);
        this.letter_points.put("D", 2);
        this.letter_points.put("E", 1);
        this.letter_points.put("F", 4);
        this.letter_points.put("G", 2);
        this.letter_points.put("H", 4);
        this.letter_points.put("I", 1);
        this.letter_points.put("J", 8);
        this.letter_points.put("K", 5);
        this.letter_points.put("L", 1);
        this.letter_points.put("M", 3);
        this.letter_points.put("N", 1);
        this.letter_points.put("O", 1);
        this.letter_points.put("P", 3);
        this.letter_points.put("Q", 10);
        this.letter_points.put("R", 1);
        this.letter_points.put("S", 1);
        this.letter_points.put("T", 1);
        this.letter_points.put("U", 1);
        this.letter_points.put("V", 4);
        this.letter_points.put("W", 4);
        this.letter_points.put("X", 8);
        this.letter_points.put("Y", 4);
        this.letter_points.put("Z", 10);

        max_turns = 5;

    }

    public void refreshsinglegamedata(){

        noTurn = 0;

        gameScore = 0;

        words = new HashMap<>();

        scores_turn = new HashMap<>();

        turnScore = 0;

        wordarr = "";

        playGame = "";

        boardLetter = "";

        rackLetter = "";

        swap_rack = "";

        rack_selected = "";

        selectedRacks = new ArrayList<>();

        pool_empty = false;
    }

    public void initializeswapletters() {
        this.swapletters.put("/",0);
        this.swapletters.put("A",0);
        this.swapletters.put("B",0);
        this.swapletters.put("C",0);
        this.swapletters.put("D",0);
        this.swapletters.put("E",0);
        this.swapletters.put("F",0);
        this.swapletters.put("G",0);
        this.swapletters.put("H",0);
        this.swapletters.put("I",0);
        this.swapletters.put("J",0);
        this.swapletters.put("K",0);
        this.swapletters.put("L",0);
        this.swapletters.put("M",0);
        this.swapletters.put("N",0);
        this.swapletters.put("O",0);
        this.swapletters.put("P",0);
        this.swapletters.put("Q",0);
        this.swapletters.put("R",0);
        this.swapletters.put("S",0);
        this.swapletters.put("T",0);
        this.swapletters.put("U",0);
        this.swapletters.put("V",0);
        this.swapletters.put("W",0);
        this.swapletters.put("X",0);
        this.swapletters.put("Y",0);
        this.swapletters.put("Z",0);

    }

    public void initializedrawletters() {
        this.drawletters.put("/", 0);
        this.drawletters.put("A", 0);
        this.drawletters.put("B", 0);
        this.drawletters.put("C", 0);
        this.drawletters.put("D", 0);
        this.drawletters.put("E", 0);
        this.drawletters.put("F", 0);
        this.drawletters.put("G", 0);
        this.drawletters.put("H", 0);
        this.drawletters.put("I", 0);
        this.drawletters.put("J", 0);
        this.drawletters.put("K", 0);
        this.drawletters.put("L", 0);
        this.drawletters.put("M", 0);
        this.drawletters.put("N", 0);
        this.drawletters.put("O", 0);
        this.drawletters.put("P", 0);
        this.drawletters.put("Q", 0);
        this.drawletters.put("R", 0);
        this.drawletters.put("S", 0);
        this.drawletters.put("T", 0);
        this.drawletters.put("U", 0);
        this.drawletters.put("V", 0);
        this.drawletters.put("W", 0);
        this.drawletters.put("X", 0);
        this.drawletters.put("Y", 0);
        this.drawletters.put("Z", 0);
//        dmap.put("A", 0);
//        dmap.put("B", 0);
//        dmap.put("C", 0);
//        dmap.put("D", 0);
//        dmap.put("E", 0);
//        dmap.put("F", 0);
//        dmap.put("G", 0);
//        dmap.put("H", 0);
//        dmap.put("I", 0);
//        dmap.put("J", 0);
//        dmap.put("K", 0);
//        dmap.put("L", 0);
//        dmap.put("M", 0);
//        dmap.put("N", 0);
//        dmap.put("O", 0);
//        dmap.put("P", 0);
//        dmap.put("Q", 0);
//        dmap.put("R", 0);
//        dmap.put("S", 0);
//        dmap.put("T", 0);
//        dmap.put("U", 0);
//        dmap.put("V", 0);
//        dmap.put("W", 0);
//        dmap.put("X", 0);
//        dmap.put("Y", 0);
//        dmap.put("Z", 0);
//
//        return dmap;

    }

    public void initializewordletters() {
        this.wordsletters.put("/",0);
        this.wordsletters.put("A",0);
        this.wordsletters.put("B",0);
        this.wordsletters.put("C",0);
        this.wordsletters.put("D",0);
        this.wordsletters.put("E",0);
        this.wordsletters.put("F",0);
        this.wordsletters.put("G",0);
        this.wordsletters.put("H",0);
        this.wordsletters.put("I",0);
        this.wordsletters.put("J",0);
        this.wordsletters.put("K",0);
        this.wordsletters.put("L",0);
        this.wordsletters.put("M",0);
        this.wordsletters.put("N",0);
        this.wordsletters.put("O",0);
        this.wordsletters.put("P",0);
        this.wordsletters.put("Q",0);
        this.wordsletters.put("R",0);
        this.wordsletters.put("S",0);
        this.wordsletters.put("T",0);
        this.wordsletters.put("U",0);
        this.wordsletters.put("V",0);
        this.wordsletters.put("W",0);
        this.wordsletters.put("X",0);
        this.wordsletters.put("Y",0);
        this.wordsletters.put("Z",0);

    }


    // Given a letter, return the letter count
    public Integer get_letter_num(String letter) {
//        final Integer integer = this.num_letter_pool.get(letter);
//
//        System.out.println(integer);
//        System.out.println(letter);
//
//        return integer;
        final Integer integer = this.num_letter_pool.get(letter);
        System.out.println(letter);
        System.out.println(integer);
        if (integer == null) {return 0;}
        else return integer;

    }

    // Given a letter, return the letter points
    public Integer get_letter_points(String letter) {
        final Integer integer = this.letter_points.get(letter);
        System.out.println(letter);
        System.out.println(integer);
        if (integer == null) {return 0;}
        else return integer;
    }

    // Given a letter and int, set the letter count
    public void set_letter_num(String letter, Integer count) {
        this.num_letter_pool.put(letter, count);

    }

    // Given a letter and int, set the letter points
    public void set_letter_points(String letter, Integer points) {
        this.letter_points.put(letter, points);
    }

    public String randomLetterInit()
    {
//in order to get the random one get the pool members whose value greater than 0
        HashMap<String, Integer> temp=new HashMap<String, Integer>();
        Random rand = new Random();
        Object[] hashKeys;
        Object key;
        int i=0;
        for(Map.Entry<String, Integer> entry:this.num_letter_pool.entrySet()){
            if((Integer)entry.getValue() >0 && (String)entry.getKey() != "/") {

                temp.put(entry.getKey(),entry.getValue());
            }
            }
        if(!temp.isEmpty()){

        hashKeys = temp.keySet().toArray();
        if(hashKeys.length>0) {
            key = hashKeys[new Random().nextInt(hashKeys.length)];
            System.out.println("****key***"+key);
            this.num_letter_pool.put((String) key, num_letter_pool.get(key) - 1);
            System.out.println("###Count####"+num_letter_pool.get(key));
            return (String) key;
        }
        else
        {
            this.pool_empty=true;
            return null;
        }
        }
        else {
            this.pool_empty=true;
            return null;

        }
    }


    public String swapLetters()
    {
        Random rand = new Random();
        Object[] hashKeys;
        Object key;
        hashKeys = this.num_letter_pool.keySet().toArray();
        key = hashKeys[new Random().nextInt(hashKeys.length)];
        return (String)key;
    }
    public HashMap<String,Integer> getnum_letter_pools() {
        return this.num_letter_pool;
    }

    public int get_max_turns() {
        return this.max_turns;
    }

    public void set_max_turns(int turns) {
        this.max_turns = turns;
    }
    public void updateKey(String key)
    {
//        System.out.println(key);
        this.num_letter_pool.put(key, num_letter_pool.get(key) + 1);
    }

    public void adddrawletters(String key){
        try {
            this.drawletters.put(key,drawletters.get(key)+1);
        } catch (Exception E) {
            this.drawletters.put(key,1);
        }
    }

    public void addswapletters(String key){
        System.out.println(key);
        try {
            this.swapletters.put(key,swapletters.get(key)+1);
        } catch (Exception E) {
            this.swapletters.put(key,1);
        }

    }

    public void addwordletters(String key){
        try {
            this.wordsletters.put(key,wordsletters.get(key)+1);
        } catch (Exception E) {
            this.wordsletters.put(key,1);
        }

    }


}
