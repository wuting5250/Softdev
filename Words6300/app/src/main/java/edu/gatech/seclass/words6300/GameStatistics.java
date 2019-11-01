package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameStatistics extends AppCompatActivity {
    public ArrayList<Integer> allScores;
    public ArrayList<Integer> allAverageTurnScores;
    public HashMap<String, Integer> allLetterWordNumber;
    public HashMap<String, String> allLettersBackPool;
    public HashMap<String, String> allDrawLetterWordPerc;
    ArrayList<String> allGameScores;
    ArrayList<String> valueOfGameScoreStatistics;
    HashMap<String, ArrayList<String>> gameScoreStatistics;
    ArrayList<Integer> numberOfTimesPlayed;
    ArrayList<Integer> numberOfTimesTracked;
    ArrayList<Integer> percentageOfTimes;
    HashMap<String, String> timesPlayedLetters;
    public GameBoard gameBoard;
    public SingleGameStatistics singleGameStatistics;

    public DatabaseHelper db;

    private Button return_menu_button;
    private Button check_gamesetting_button;
    private Button gameScoreStatisticsButton;
    private Button LetterStatisticsButton;
    private Button wordBankButton;
    private ScrollView statistics_scroll_view;
    private TableLayout table_layout;
    TextView displayTextView;
    ScrollView displayScrollView;

    public GameStatistics() {
        allScores = new ArrayList<Integer>();
        allAverageTurnScores = new ArrayList<Integer>();
        allGameScores = new ArrayList<>();
        valueOfGameScoreStatistics = new ArrayList<>();
        gameScoreStatistics = new HashMap<>();
        numberOfTimesPlayed = new ArrayList<>();
        numberOfTimesTracked = new ArrayList<>();
        percentageOfTimes = new ArrayList<>();
        timesPlayedLetters = new HashMap<>();
        allLetterWordNumber = new HashMap<>();
        allLettersBackPool = new HashMap<>();
        allDrawLetterWordPerc = new HashMap<>();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamestatistics);
        singleGameStatistics = new SingleGameStatistics();
        gameBoard = new GameBoard();
        statistics_scroll_view = findViewById(R.id.statistics_scroll_view);
        statistics_scroll_view.setVisibility(View.INVISIBLE);
        table_layout = findViewById(R.id.statistics_table_layout);

        db = new DatabaseHelper(getApplicationContext());

        displayTextView = new TextView(this);
        displayScrollView = (ScrollView) findViewById(R.id.statistics_scroll_view);

        return_menu_button = (Button) findViewById(R.id.return_menu_button);
        return_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmainactivity();
            }
        });

        check_gamesetting_button = (Button) findViewById(R.id.check_gamesetting_button);
        check_gamesetting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkgamesettings();
            }
        });

        gameScoreStatisticsButton = (Button) findViewById(R.id.gameScoreStatisticsButton);
        gameScoreStatisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statistics_scroll_view.setVisibility(View.VISIBLE);
                build_game_statistics_table();
            }
        });

        LetterStatisticsButton = (Button) findViewById(R.id.LetterStatisticsButton);
        LetterStatisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build_letter_statistics_table();
            }
        });

        wordBankButton = (Button) findViewById(R.id.wordBankButton);
        wordBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build_word_table();
            }
        });
    }

    private void build_game_statistics_table() {
        statistics_scroll_view.setVisibility(View.VISIBLE);
        table_layout.removeAllViews();
        Cursor cursor = db.getGameScoreData();
        TableRow r = new TableRow(getApplicationContext());
        TableRow.LayoutParams table_params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        r.setLayoutParams(table_params);
        TextView score_header = new TextView(this);
        score_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        score_header.setText("score");
        score_header.setTextSize(24);
        r.addView(score_header);
        TextView average_score_header = new TextView(this);
        average_score_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        average_score_header.setText("turns");
        average_score_header.setTextSize(24);
        r.addView(average_score_header);
        TextView turns_header = new TextView(this);
        turns_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        turns_header.setText("avg score");
        turns_header.setTextSize(24);
        r.addView(turns_header);
        table_layout.addView(r);

        try {
            while (cursor.moveToNext()) {
                String score = cursor.getString(0);
                Integer turns = cursor.getInt(1);
                Double average_score = cursor.getDouble(2);
                final Integer max_turns = cursor.getInt(3);
                String letter_count_string = cursor.getString(4);
                String letter_points_string = cursor.getString(5);
                final HashMap<String, Double> letter_count = DatabaseHelper.get_letter_map_from_json(letter_count_string);
                final HashMap<String, Double> letter_points = DatabaseHelper.get_letter_map_from_json(letter_points_string);
                TableRow row = new TableRow(getApplicationContext());
                row.setLayoutParams(table_params);
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        build_game_data(max_turns, letter_count, letter_points);
                    }
                });
                TextView score_column = new TextView(this);
                score_column.setText(score);
                score_column.setTextSize(20);
                score_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(score_column);
                TextView turns_column = new TextView(this);
                turns_column.setText(Integer.toString(turns));
                turns_column.setTextSize(20);
                turns_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(turns_column);
                TextView average_score_column = new TextView(this);
                average_score_column.setText(Double.toString(average_score));
                average_score_column.setTextSize(20);
                average_score_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(average_score_column);

                table_layout.addView(row);
            }
        } finally {
            cursor.close();
        }
    }

    public void build_game_data(Integer max_turns, HashMap<String, Double> count_map, HashMap<String, Double> points_map) {
        table_layout.removeAllViews();
        TableRow r = new TableRow(getApplicationContext());
        TableRow.LayoutParams table_params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        r.setLayoutParams(table_params);
        TextView max_header = new TextView(this);
        max_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        max_header.setText("MAX");
        max_header.setTextSize(36);
        r.addView(max_header);
        TextView turns_header = new TextView(this);
        turns_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        turns_header.setText("TURNS:");
        turns_header.setTextSize(36);
        r.addView(turns_header);
        TextView max_turns_header = new TextView(this);
        max_turns_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        max_turns_header.setText(Long.toString(Math.round(max_turns)));
        max_turns_header.setTextSize(36);
        r.addView(max_turns_header);
        table_layout.addView(r);
        r = new TableRow(getApplicationContext());
        TextView letter_header = new TextView(this);
        letter_header.setTextSize(24);
        letter_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        letter_header.setText("letter");
        r.addView(letter_header);
        TextView count_header = new TextView(this);
        count_header.setTextSize(24);
        count_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        count_header.setText("count");
        r.addView(count_header);
        TextView points_header = new TextView(this);
        points_header.setTextSize(24);
        points_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        points_header.setText("points");
        r.addView(points_header);
        table_layout.addView(r);
        for (String letter : count_map.keySet()) {
            TableRow row = new TableRow(getApplicationContext());
            row.setLayoutParams(table_params);
            TextView letter_column = new TextView(this);
            letter_column.setText(letter);
            letter_column.setTextSize(20);
            letter_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
            row.addView(letter_column);
            TextView count_column = new TextView(this);
            count_column.setText(Long.toString(Math.round(count_map.get(letter))));
            count_column.setTextSize(20);
            count_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
            row.addView(count_column);
            TextView points_column = new TextView(this);
            points_column.setText(Long.toString(Math.round(points_map.get(letter))));
            points_column.setTextSize(20);
            points_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
            row.addView(points_column);
            table_layout.addView(row);
        }
    }

    private void build_letter_statistics_table() {
        Cursor cursor = db.getLettersData();
        statistics_scroll_view.setVisibility(View.VISIBLE);
        table_layout.removeAllViews();
        TableRow r = new TableRow(getApplicationContext());
        TableRow.LayoutParams table_params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        r.setLayoutParams(table_params);
        TextView letter_header = new TextView(this);
        letter_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        letter_header.setText("letter");
        letter_header.setTextSize(24);
        r.addView(letter_header);
        TextView played_header = new TextView(this);
        played_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        played_header.setText("played");
        played_header.setTextSize(24);
        r.addView(played_header);
        TextView swapped_header = new TextView(this);
        swapped_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        swapped_header.setText("swapped");
        swapped_header.setTextSize(24);
        r.addView(swapped_header);
        TextView percent_header = new TextView(this);
        percent_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        percent_header.setText("played/drawn");
        percent_header.setTextSize(24);
        r.addView(percent_header);
        table_layout.addView(r);

        try {
            while (cursor.moveToNext()) {
                String letter = cursor.getString(1);
                String played_in_word = cursor.getString(2);
                String swapped = cursor.getString(3);
                String drawn = cursor.getString(4);
                String played_drawn;
                try {
                    played_drawn = Double.toString(Math.round(1.0 * Integer.parseInt(played_in_word) / Integer.parseInt(drawn) * 10000) / 100.0);
                } catch (Exception e) {
                    played_drawn = "-1";
                }
                TableRow row = new TableRow(getApplicationContext());
                row.setLayoutParams(table_params);
                TextView letter_column = new TextView(this);
                letter_column.setText(letter);
                letter_column.setTextSize(20);
                letter_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(letter_column);
                TextView played_column = new TextView(this);
                played_column.setText(played_in_word);
                played_column.setTextSize(20);
                played_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(played_column);
                TextView swapped_column = new TextView(this);
                swapped_column.setText(swapped);
                swapped_column.setTextSize(20);
                swapped_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(swapped_column);
                TextView percent_column = new TextView(this);
                percent_column.setText(played_drawn + "%");
                percent_column.setTextSize(20);
                percent_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(percent_column);
                table_layout.addView(row);
            }
        } finally {
            cursor.close();
        }
    }

    private void build_word_table() {
        Cursor cursor = db.getWordData();
        statistics_scroll_view.setVisibility(View.VISIBLE);
        table_layout.removeAllViews();
        TableRow r = new TableRow(getApplicationContext());
        TableRow.LayoutParams table_params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        r.setLayoutParams(table_params);
        TextView word_header = new TextView(this);
        word_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        word_header.setText("word");
        word_header.setTextSize(30);
        r.addView(word_header);
        TextView count_header = new TextView(this);
        count_header.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
        count_header.setText("played");
        count_header.setTextSize(30);
        r.addView(count_header);
        table_layout.addView(r);

        try {
            while (cursor.moveToNext()) {
                String word = cursor.getString(1);
                String count = cursor.getString(2);
                TableRow row = new TableRow(getApplicationContext());
                row.setLayoutParams(table_params);
                TextView word_column = new TextView(this);
                word_column.setText(word);
                word_column.setTextSize(24);
                word_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(word_column);
                TextView played_column = new TextView(this);
                played_column.setText(count);
                played_column.setTextSize(24);
                played_column.setTextAlignment(TableRow.TEXT_ALIGNMENT_CENTER);
                row.addView(played_column);
                table_layout.addView(row);
            }
        } finally {
            cursor.close();
        }
    }

    public void openmainactivity() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void checkgamesettings() {
        Intent intent = new Intent(this, GameSettings.class);
        startActivity(intent);
    }
}

