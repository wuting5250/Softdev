package edu.gatech.seclass.words6300;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

public class SingleGameData {

    public static PoolLetters get_pool(Context context) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
        PoolLetters poolLetters;
        String text_pool_letters = preferences.getString("poolLetters", null);
        poolLetters = gson_converter.fromJson(text_pool_letters, PoolLetters.class);
        if (poolLetters == null) {

            return new PoolLetters();
        }
        return poolLetters;
    }


//    public static HashMap get_words(Context context) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
//        HashMap<Integer, String> words;
//        String text_words = preferences.getString("words", null);
//        words = gson_converter.fromJson(text_words, HashMap.class);
//        if (words == null) {
//            return new HashMap();
//        }
//        return words;
//    }


    public static boolean check_endgame(Context context) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
        Boolean checkendgame;
        String json_text = preferences.getString("endgame", null);
        checkendgame = gson_converter.fromJson(json_text, Boolean.class);
        if (checkendgame == null) {
            return false;
        }
        return checkendgame;
    }

    public static boolean check_resumegame(Context context) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
        Boolean checkresume;
        String json_text = preferences.getString("resumegame", null);
        checkresume = gson_converter.fromJson(json_text, Boolean.class);
        if (checkresume == null) {
            return false;
        }
        return checkresume;
    }

    public static String [] get_racks(Context context) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
        String[] savedracks;
        String json_text = preferences.getString("racks", null);
        savedracks = gson_converter.fromJson(json_text, String[].class);
        if (savedracks== null) {
            return new String[7];
        }
        return savedracks;
    }

    public static String [] get_boards(Context context) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
        String[] savedboards;
        String json_text = preferences.getString("boards", null);
        savedboards = gson_converter.fromJson(json_text, String[].class);
        if (savedboards == null) {
            return new String[4];
        }
        return savedboards;
    }

//
//    public static int get_turnNo(Context context) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
//        int noTurn;
//        String text_turnNo = preferences.getString("poolLetters", null);
//        noTurn = Integer.parseInt(preferences.getString(text_turnNo, "0"));
//        return noTurn;
//    }
//
//    public static String get_word(Context context) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
//        String wordtext;
//        String json_text = preferences.getString("word", null);
//        wordtext = gson_converter.fromJson(json_text, String.class);
//        if (wordtext== null) {
//            return new String();
//        }
//        return wordtext;
//    }

//    public static int get_gameScore(Context context) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
//        int gameScore;
//        String text_gameScore = preferences.getString("poolLetters", null);
//        gameScore = Integer.parseInt(preferences.getString(text_gameScore, "0"));
//        return gameScore;
//    }

//    public static HashMap get_scores_turn (Context context) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences preferences = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE);
//        HashMap<Integer, Integer> scores_turn;
//        String text_scores_turn = preferences.getString("scores_turn", null);
//        scores_turn = gson_converter.fromJson(text_scores_turn, HashMap.class);
//        if (scores_turn == null) {
//            return new HashMap();
//        }
//        return scores_turn;
//    }


    public static void save_pool(Context context, PoolLetters poolLetters) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
        String text_pool_letters = gson_converter.toJson(poolLetters);
        save_tool.putString("poolLetters", text_pool_letters);
        save_tool.commit();
    }


//    public static void save_words(Context context, HashMap<Integer, String> words) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
//        String text_words = gson_converter.toJson(words);
//        save_tool.putString("words", text_words);
//        save_tool.commit();
//    }


//    public static void save_word(Context context, String wordarr) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
//        String text_word = gson_converter.toJson(wordarr);
//        save_tool.putString("words", text_word);
//        save_tool.commit();
//    }

//    public static void save_turnNo(Context context, int turnNo) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
//        String text_turnNo = gson_converter.toJson(turnNo);
//        save_tool.putString("turnNo", text_turnNo);
//        save_tool.commit();
//    }

    public static void save_racks (Context context, String[] racks) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
        String text_racks = gson_converter.toJson(racks);
        save_tool.putString("racks", text_racks);
        save_tool.commit();
    }

    public static void save_boards (Context context, String[] boards) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
        String text_boards = gson_converter.toJson(boards);
        save_tool.putString("boards", text_boards);
        save_tool.commit();
    }


    public static void save_endgame(Context context, boolean endgame) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
        String text_endgame = gson_converter.toJson(endgame);
        save_tool.putString("endgame", text_endgame);
        save_tool.commit();
    }

    public static void save_resumegame(Context context, boolean resumegame) {
        Gson gson_converter = new Gson();
        Context getapp = context.getApplicationContext();
        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
        String text_resume = gson_converter.toJson(resumegame);
        save_tool.putString("resumegame", text_resume);
        save_tool.commit();
    }

//    public static void save_gameScore(Context context, int gameScore) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("Words6300", Context.MODE_PRIVATE).edit();
//        String text_gamescore = gson_converter.toJson(gameScore);
//        save_tool.putString("gameScore", text_gamescore);
//        save_tool.commit();
//    }

//    public static void save_scores_turn(Context context, HashMap<Integer, Integer> scores_turn) {
//        Gson gson_converter = new Gson();
//        Context getapp = context.getApplicationContext();
//        SharedPreferences.Editor save_tool = getapp.getSharedPreferences("scores_turn", Context.MODE_PRIVATE).edit();
//        String text_scores_turn = gson_converter.toJson(scores_turn);
//        save_tool.putString("scores_turn", text_scores_turn);
//        save_tool.commit();
//    }



}
