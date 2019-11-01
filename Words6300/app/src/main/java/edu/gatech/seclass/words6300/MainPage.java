package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainPage extends AppCompatActivity {
    private PoolLetters poolletters;
    public boolean resume_game;
    private Button game_settings_button;
    private Button start_game_button;
    private Button resume_game_button;
    private Button view_statistics_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        game_settings_button = (Button) findViewById(R.id.game_settings_button);

        start_game_button = (Button) findViewById(R.id.start_game_button);

        resume_game_button = (Button) findViewById(R.id.resume_game_button);

        view_statistics_button = (Button) findViewById(R.id.view_statistics_button);


        game_settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengamesettings();
            }
        });

        start_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resume_game = false;
                SingleGameData.save_resumegame(getApplicationContext(), resume_game);
                opengameboard();
            }
        });

        resume_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resume_game = true;
                SingleGameData.save_resumegame(getApplicationContext(), resume_game);
                opengameboard();
            }
        });

        view_statistics_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openviewstat();
            }
        });
        this.poolletters = (PoolLetters) SingleGameData.get_pool(this);
//        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
//        db.putScore(100, 9, 15, get_fake_letter_map(), get_fake_letter_map());
//        db.putLetters(get_fake_letter_map(), get_fake_letter_map(), get_fake_letter_map());
//        db.putWord("test1");
//        db.putWord("test1");
//        db.putWord("test2");
//        db.putWord("test3");
//        db.putWord("test4");
//        db.putWord("test5");
//        db.putWord("test6");
//        db.putWord("test7");
//        db.putWord("test8");
//        db.putWord("test9");
//        db.close();
    }

    public void opengamesettings() {
        Intent intent = new Intent(this, GameSettings.class);
        intent.putExtra("PoolLetters", poolletters);
        startActivity(intent);
    }

    public void opengameboard() {
        Intent intent = new Intent(this, GameBoard.class);
        // First you need to check whether any turns have been played.
        // If no turns have been played, start a new game
        // Otherwise, resume the game
        // In either case, you just start the gameboard.
        startActivity(intent);
    }

    public void openviewstat() {
        Intent intent = new Intent(this, GameStatistics.class);
        startActivity(intent);
    }

    public HashMap<String, Integer> get_fake_letter_map() {
        HashMap<String, Integer> fake_map = new HashMap<>();
        for (int i = 65; i <= 90; i++) {
            fake_map.put(Character.toString((char) i), 2);
        }
        fake_map.put("_", 3);
        return fake_map;
    }
}
