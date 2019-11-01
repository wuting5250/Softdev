package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameBoard extends AppCompatActivity {

    int turnScore;
    int gameScore;
    String boardLetter;
    String rackLetter;
    String letterBoard;
    String playGame;
    boolean endgame;
    boolean resumegame;
    boolean duplicateword;

    private Button return_menu_button;
    private Button return_menu_button2;
    private Button next_turn;
    private Button swap_rack_button;
    private Button board_1;
    private Button board_2;
    private Button board_3;
    private Button board_4;
    private Button rack_1;
    private Button rack_2;
    private Button rack_3;
    private Button rack_4;
    private Button rack_5;
    private Button rack_6;
    private Button rack_7;
    private Button play;
    private Button cancel;
    private Button select_rack_button;
    private TextView gamescore;
    private TextView score;
    private TextView turn;
    private TextView error;
    private TextView max_game_cycles;
    private TextView word;
    private String wordstr;
    public PoolLetters poolletters;
    public HashMap<String, Integer> original_num_letter_pool;
    int noTurn;
//    private HashMap<Integer, Integer> scores_turn;
//    private HashMap<Integer, String> words;

    public String rack_selected;
    public String swap_rack;
    public ArrayList selectedRacks;
    public boolean pool_not_refill;
    private HashMap<String, Integer> testpoolletter = new HashMap<>();
    public String[] boards = new String[4];
    public String[] racks = new String[7];
    DatabaseHelper db;

    // SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);
        db = new DatabaseHelper(getApplicationContext());

        // add all UI IDs
        board_1 = (Button) findViewById(R.id.board_1);
        board_2 = (Button) findViewById(R.id.board_2);
        board_3 = (Button) findViewById(R.id.board_3);
        board_4 = (Button) findViewById(R.id.board_4);
        rack_1 = (Button) findViewById(R.id.rack_1);
        rack_2 = (Button) findViewById(R.id.rack_2);
        rack_3 = (Button) findViewById(R.id.rack_3);
        rack_4 = (Button) findViewById(R.id.rack_4);
        rack_5 = (Button) findViewById(R.id.rack_5);
        rack_6 = (Button) findViewById(R.id.rack_6);
        rack_7 = (Button) findViewById(R.id.rack_7);
        cancel = (Button) findViewById(R.id.cancel);
        play = (Button) findViewById(R.id.play);
        swap_rack_button = (Button) findViewById(R.id.swap_rack_button);
        //select_rack_button = (Button) findViewById(R.id.select_rack_button);
        next_turn = (Button) findViewById(R.id.next_turn);
        score = (TextView) findViewById(R.id.score);
//        error = (TextView) findViewById(R.id.error);
        max_game_cycles = (TextView) findViewById(R.id.max_game_cycles);
        turn = (TextView) findViewById(R.id.turn);

        gamescore = (TextView) findViewById(R.id.gamescore);
        word = (TextView) findViewById(R.id.word);

        return_menu_button = (Button) findViewById(R.id.return_menu_button);
        return_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmainactivity();
            }
        });

        // Load parameters from Single Game Data, if no data, will create default ones.

//        poolletters = new PoolLetters();
        endgame = SingleGameData.check_endgame(this);
        poolletters = SingleGameData.get_pool(getApplicationContext());
        //        noTurn = SingleGameData.get_turnNo(this);
        noTurn = SingleGameData.get_pool(this).noTurn;

        System.out.println("game starts! numbers of letters in pool" + poolletters.num_letter_pool);

        this.resumegame = SingleGameData.check_resumegame(this);
        max_game_cycles.setText(String.valueOf(poolletters.max_turns));

        if (!resumegame){
            Toast.makeText(getApplicationContext(), "Start a new game", Toast.LENGTH_SHORT).show();
            InitializeGameSettings();
            System.out.println("A new game started");
            System.out.println();
            System.out.println("print out original numbers of letters in pool before new game starts");
            System.out.println(original_num_letter_pool.toString());
            System.out.println("Initialize game settings succeeded2");
            System.out.println("print out numbers of letters in pool after initializing board and rack");
            System.out.println(poolletters.num_letter_pool.toString());
        }
        else if (noTurn > 0 && resumegame) {
//        else if (resumegame) {
            Toast.makeText(getApplicationContext(), "Resume previous game", Toast.LENGTH_SHORT).show();
            System.out.println("resuming previous game");
            resumeGameSettings();
            System.out.println("resumed previous game");

        }
        else if (noTurn == 0 && resumegame) {


            Toast.makeText(getApplicationContext(), "Start a new game since previous game ended", Toast.LENGTH_SHORT).show();
            InitializeGameSettings();
            System.out.println("Start a new game since previous game ended");

        }

            board_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Board 1 issue");
                    System.out.println("1" + endgame); // false good
                    System.out.println("2" + boardLetter.equals("")); // true good
                    System.out.println(swap_rack);
                    System.out.println("swap 3" + swap_rack == "");  //
                    System.out.println("4" +playGame.equals(""));


                    if (!board_1.getText().toString().isEmpty() && !endgame  && boardLetter.equals("") && swap_rack == ""&& playGame.equals("")) {
                        System.out.println("test0");
                        System.out.println("board 1 is working");
                        letterBoard = board_1.getText().toString();
                        word.append(board_1.getText());
                        System.out.println("test 1");
                        poolletters.wordarr = poolletters.wordarr + letterBoard ;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        System.out.println("test2");
                        calculateScore(letterBoard);
                        System.out.println(score.getText().toString());
                        System.out.println("test3");
                        boardLetter = "board_1";
                        poolletters.boardLetter = boardLetter;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        board_1.setText("");
                        boards[0]= "";
                        SingleGameData.save_boards(getApplicationContext(), boards);

                    } else {

                        System.out.println("Check issue 2");
                        System.out.println(poolletters.boardLetter);
                        System.out.println(endgame);
                        //|| board_1.getText().toString().isEmpty()//--check--Ting
                        if (!boardLetter.equals("") && !endgame && rackLetter.equals("")) {
                            System.out.println(endgame);
                            Toast.makeText(getApplicationContext(), "You can only select from rack now.", Toast.LENGTH_SHORT).show();
                        }

                        if (!boardLetter.equals("") && !endgame && !rackLetter.equals("")) {
                            System.out.println(endgame);
                            Toast.makeText(getApplicationContext(), "You can only select from rack or confirm the word now.", Toast.LENGTH_SHORT).show();
                        }
//                        else if (endgame == true) {
//                            endGame();
//                        }
                        else if (!swap_rack.equals("") && !endgame ) {
                            Toast.makeText(getApplicationContext(), "You can only swap rack letters.", Toast.LENGTH_SHORT).show();
                        }
                        else if (endgame != true && !playGame.equals("") && !word.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();
                        }
                        else if (endgame == true)
                        {endGame();
                            System.out.println("Game ends when pressing button 1");
                            System.out.println(endgame);}
                    }
                }
            });

            System.out.println("board1 works");

            board_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!board_2.getText().toString().isEmpty() && !endgame  && boardLetter.equals("") && swap_rack.equals("") && playGame.equals("")) {

                        letterBoard = board_2.getText().toString();
                        word.append(board_2.getText());
                        poolletters.wordarr = poolletters.wordarr + letterBoard ;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        calculateScore(letterBoard);
                        board_2.setText("");
                        boardLetter = "board_2";
                        poolletters.boardLetter = boardLetter;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        boards[1] = "";
                        SingleGameData.save_boards(getApplicationContext(), boards);

                    } else {
                        if (!boardLetter.equals("") && !endgame ) {
                            Toast.makeText(getApplicationContext(), "You can only select from rack now.", Toast.LENGTH_SHORT).show();
                        }
//                        else if ( endgame == true) {
//                            endGame();
//                        }
                        else if (!swap_rack.equals("")&& !endgame) {
                            Toast.makeText(getApplicationContext(), "You can only swap rack letters.", Toast.LENGTH_SHORT).show();
                        }
                        else if (!endgame && !playGame.equals("") && !word.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();
                        }
                        else if (!endgame)
                        {endGame();}
                    }
                }

            });
            System.out.println("board2 works");

            board_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!board_3.getText().toString().isEmpty() && endgame !=true  && boardLetter.equals("") && swap_rack.equals("") && playGame.equals("")) {

                        letterBoard = board_3.getText().toString();
                        word.append(board_3.getText());
                        poolletters.wordarr = poolletters.wordarr + letterBoard ;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        calculateScore(letterBoard);
                        board_3.setText("");
                        boardLetter = "board_3";
                        poolletters.boardLetter = boardLetter;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        boards[2] = "";
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    } else {
                        if (!boardLetter.equals("") && !endgame ) {
                            Toast.makeText(getApplicationContext(), "You can only select from rack now.", Toast.LENGTH_SHORT).show();
                        }
//                        else if (endgame == true) {
//                            endGame();
//                        }
                        else if (!swap_rack.equals("") && !endgame ) {
                            Toast.makeText(getApplicationContext(), "You can only swap rack letters.", Toast.LENGTH_SHORT).show();
                        }
                        else if (endgame != true && !playGame.equals("") && !word.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();
                        }
                        else if (endgame == true)
                        {endGame();}
                    }

                }
            });
            System.out.println("board3 works");
            board_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!board_4.getText().toString().isEmpty() && endgame != true && boardLetter.equals("") && swap_rack.equals("") && playGame.equals("")) {
                        System.out.println("button1 works");
                        letterBoard = board_4.getText().toString();
                        word.append(board_4.getText());
                        poolletters.wordarr = poolletters.wordarr + letterBoard ;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        calculateScore(letterBoard);
                        board_4.setText("");
                        boardLetter = "board_4";
                        poolletters.boardLetter = boardLetter;
                        SingleGameData.save_pool(getApplicationContext(), poolletters);
                        boards[3] = "";
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    } else {
                        if (!boardLetter.equals("") && !endgame ) {
                            Toast.makeText(getApplicationContext(), "You can only select from rack now.", Toast.LENGTH_SHORT).show();
                        }
//                        else if ( endgame == true) {
//                            endGame();
//                        }
                        else if (swap_rack.equals("") && !endgame) {
                            Toast.makeText(getApplicationContext(), "You can only swap rack letters.", Toast.LENGTH_SHORT).show();
                        }
                        else if (!endgame && !playGame.equals("") && !word.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();
                        }
                        else if (!endgame)
                        {
                            endGame();
                        }
                    }
                }
            });
            System.out.println("board4 works");

        rack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !endgame) {
                    if (playGame.equals("") ) {
                        if (swap_rack.equals("") && !rack_1.getText().toString().isEmpty()) {
                            if (!rack_1.getText().toString().isEmpty()) {
                                word.append(rack_1.getText());
                                poolletters.wordarr = poolletters.wordarr + rack_1.getText().toString();
                                calculateScore(rack_1.getText().toString());
                                rack_1.setText("");
                                rackLetter = "rack_1";
                                poolletters.rackLetter = rackLetter;
                                racks[1] = "";
                                SingleGameData.save_racks(getApplicationContext(), racks);
                                SingleGameData.save_pool(getApplicationContext(), poolletters);
                            }
                        } else {
                            if (!swap_rack.equals("") && !rack_1.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
                                rack_selected = "selected";
                                poolletters.rack_selected = rack_selected;
                                System.out.println("Choose rack1 for swap");
                                System.out.println(rack_1.getText().toString());
                                rack_1.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
                                System.out.println("Added rack1 to selectedRacks");
                                System.lineSeparator();
                                selectedRacks.add("rack_1");
                                poolletters.selectedRacks = selectedRacks;
                                SingleGameData.save_pool(getApplicationContext(),poolletters);
                            }
                        }
                    }  else if (!word.getText().toString().isEmpty())
                    {Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();}

                }
                else endGame();
//                    else
//                        Toast.makeText(getApplicationContext(), "You can't play game since game ended!", Toast.LENGTH_SHORT).show();

            }

        });

            rack_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( !endgame) {
                        if (playGame.equals("") ) {
                            if (swap_rack.equals("") && !rack_2.getText().toString().isEmpty()) {
                                if (!rack_2.getText().toString().isEmpty()) {
                                    word.append(rack_2.getText());
                                    poolletters.wordarr = poolletters.wordarr + rack_2.getText().toString();
                                    calculateScore(rack_2.getText().toString());
                                    rack_2.setText("");
                                    rackLetter = "rack_2";
                                    poolletters.rackLetter = rackLetter;
                                    racks[1] = "";
                                    SingleGameData.save_racks(getApplicationContext(), racks);
                                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                                }
                            } else {
                                if (!swap_rack.equals("") && !rack_2.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
                                    rack_selected = "selected";
                                    poolletters.rack_selected = rack_selected;
                                    System.out.println("Choose rack2 for swap");
                                    System.out.println(rack_2.getText().toString());
                                    rack_2.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
                                    System.out.println("Added rack2 to selectedRacks");
                                    System.lineSeparator();
                                    selectedRacks.add("rack_2");
                                    poolletters.selectedRacks = selectedRacks;
                                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                                }
                            }
                        }  else if (!word.getText().toString().isEmpty())
                        {Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();}

                    }
                    else endGame();
//                    else
//                        Toast.makeText(getApplicationContext(), "You can't play game since game ended!", Toast.LENGTH_SHORT).show();

                }

            });

            rack_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( !endgame ) {
                        if (playGame.equals("") ) {
                            if (swap_rack.equals("")&& !rack_3.getText().toString().isEmpty()) {
                                if (!rack_3.getText().toString().isEmpty()) {
                                    word.append(rack_3.getText());
                                    poolletters.wordarr = poolletters.wordarr + rack_3.getText().toString();
                                    calculateScore(rack_3.getText().toString());
                                    rack_3.setText("");
                                    rackLetter = "rack_3";
                                    poolletters.rackLetter = rackLetter;
                                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                                    racks[2] = "";
                                    SingleGameData.save_racks(getApplicationContext(), racks);
                                }
                            } else {
                                if (!swap_rack.equals("") && !rack_3.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
                                    rack_selected = "selected";
                                    poolletters.rack_selected = rack_selected;
                                    System.out.println("Choose rack3 for swap");
                                    System.out.println(rack_3.getText().toString());
                                    rack_3.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
                                    System.out.println("Added rack3 to selectedRacks");
                                    System.lineSeparator();
                                    selectedRacks.add("rack_3");
                                    poolletters.selectedRacks = selectedRacks;
                                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                                }
                            }
                        } else if (!word.getText().toString().isEmpty())
                        {Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();}

                    }
                    else endGame();
//                    else
//                        Toast.makeText(getApplicationContext(), "You can't play game since game ended!", Toast.LENGTH_SHORT).show();

                }

            });
            rack_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( !endgame ) {
                        if (playGame.equals("")) {
                            if (swap_rack.equals("") && !rack_4.getText().toString().isEmpty()) {
                                if (!rack_4.getText().toString().isEmpty()) {
                                    word.append(rack_4.getText());
                                    poolletters.wordarr = poolletters.wordarr + rack_4.getText().toString();
                                    rack_4.setText("");
                                    rackLetter = "rack_4";
                                    poolletters.rackLetter = rackLetter;
                                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                                    racks[3] = "";
                                    SingleGameData.save_racks(getApplicationContext(), racks);
                                }
                            } else {
                                if (!swap_rack.equals("") && !rack_4.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
                                    rack_selected = "selected";
                                    poolletters.rack_selected = rack_selected;
                                    System.out.println("Choose rack4 for swap");
                                    System.out.println(rack_4.getText().toString());
                                    rack_4.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
                                    System.out.println("Added rack4 to selectedRacks");
                                    System.lineSeparator();
                                    selectedRacks.add("rack_4");
                                    poolletters.selectedRacks = selectedRacks;
                                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                                }
                            }
                        } else if (!word.getText().toString().isEmpty())
                            {Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();}

                    }
                    else endGame();
//                    else
//                        Toast.makeText(getApplicationContext(), "You can't play game since game ended!", Toast.LENGTH_SHORT).show();

                }

            });
            rack_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( !endgame ) {
                        if (playGame.equals("") ) {
                            if (swap_rack.equals("") && !rack_5.getText().toString().isEmpty()) {
                                if (!rack_5.getText().toString().isEmpty()) {
                                    word.append(rack_5.getText());
                                    poolletters.wordarr = poolletters.wordarr + rack_5.getText().toString();
                                    rack_5.setText("");
                                    rackLetter = "rack_5";
                                    poolletters.rackLetter = rackLetter;
                                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                                    racks[4] = "";
                                    SingleGameData.save_racks(getApplicationContext(), racks);
                                }
                            } else {
                                if (!swap_rack.equals("") && !rack_5.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
                                    rack_selected = "selected";
                                    poolletters.rack_selected = rack_selected;
                                    System.out.println("Choose rack1 for swap");
                                    System.out.println(rack_5.getText().toString());
                                    rack_5.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
                                    System.out.println("Added rack5 to selectedRacks");
                                    System.lineSeparator();
                                    selectedRacks.add("rack_5");
                                    poolletters.selectedRacks = selectedRacks;
                                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                                }
                            }
                        }
                        else if (!word.getText().toString().isEmpty())
                            {Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();}

                    }
                    else endGame();
//                    else
//                        Toast.makeText(getApplicationContext(), "You can't play game since game ended!", Toast.LENGTH_SHORT).show();

                }

            });
            rack_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( !endgame ) {
                        if (playGame.equals("")) {
                            if (swap_rack.equals("") && !rack_6.getText().toString().isEmpty()) {
                                if (!rack_6.getText().toString().isEmpty()) {
                                    word.append(rack_6.getText());
                                    poolletters.wordarr = poolletters.wordarr + rack_6.getText().toString();
                                    calculateScore(rack_6.getText().toString());
                                    rack_6.setText("");
                                    rackLetter = "rack_6";
                                    poolletters.rackLetter = rackLetter;
                                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                                    racks[5] = "";
                                    SingleGameData.save_racks(getApplicationContext(), racks);
                                }
                            } else {
                                if (!swap_rack.equals("") && !rack_6.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
                                    rack_selected = "selected";
                                    poolletters.rack_selected = rack_selected;
                                    System.out.println("Choose rack1 for swap");
                                    System.out.println(rack_6.getText().toString());
                                    rack_6.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
                                    System.out.println("Added rack1 to selectedRacks");
                                    System.lineSeparator();
                                    selectedRacks.add("rack_6");
                                    poolletters.selectedRacks = selectedRacks;
                                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                                }
                            }
                        }
                        else if (!word.getText().toString().isEmpty())
                        {Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();}
                    }
                    else endGame();
//                    else
//                        Toast.makeText(getApplicationContext(), "You can't play game since game ended!", Toast.LENGTH_SHORT).show();
                }

            });
            rack_7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( !endgame ) {
                        if (playGame.equals("")) {
                            if (swap_rack.equals("")&& !rack_7.getText().toString().isEmpty()) {
                                if (!rack_7.getText().toString().isEmpty()) {
                                    word.append(rack_7.getText());
                                    poolletters.wordarr = poolletters.wordarr + rack_7.getText().toString();
                                    calculateScore(rack_7.getText().toString());
                                    rack_7.setText("");
                                    rackLetter = "rack_7";
                                    poolletters.rackLetter = rackLetter;
                                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                                    racks[6] = "";
                                    SingleGameData.save_racks(getApplicationContext(), racks);
                                }
                            } else {
                                if (!swap_rack.equals("")&& !rack_7.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
                                    rack_selected = "selected";
                                    poolletters.rack_selected = rack_selected;
                                    System.out.println("Choose rack7 for swap");
                                    System.out.println(rack_7.getText().toString());
                                    rack_7.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
                                    System.out.println("Added rack7 to selectedRacks");
                                    System.lineSeparator();
                                    selectedRacks.add("rack_7");
                                    poolletters.selectedRacks = selectedRacks;
                                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                                }
                            }
                        } else if (!word.getText().toString().isEmpty())
                            {Toast.makeText(getApplicationContext(), "Click next turn", Toast.LENGTH_SHORT).show();}

                    }
                    else endGame();
//                    else
//                        Toast.makeText(getApplicationContext(), "You can't play game since game ended!", Toast.LENGTH_SHORT).show();
                }
            });

            // add cancel button functions

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println();
                    System.out.println("Play button is" + playGame);
                    System.out.println(playGame == "");
                    if (playGame.equals("")) { //&& swap_rack == "" check--Ting

                        if (!word.getText().toString().isEmpty()) {
                            wordstr = word.getText().toString();
                            System.out.println("Play button is" + playGame);
                            System.out.println("Play button is" + wordstr);
                            clearword(wordstr);
                            word.setText("");

                            poolletters.wordarr = poolletters.wordarr.replace(poolletters.wordarr,"");
                            SingleGameData.save_pool(getApplicationContext(), poolletters);

                            score.setText("0");
                            turnScore= 0;
                            poolletters.turnScore = turnScore;
                            SingleGameData.save_pool(getApplicationContext(),poolletters);
                        }

//                       gameScore = gameScore - turnScore;

//                        gamescore.setText(String.valueOf(gameScore));

                    } else if (!swap_rack.equals("")) { //todo add cancle swap function if having extra time
                    }
                }
            });
            //add swap button functions

            //swap_rack_button
            swap_rack_button = (Button) findViewById(R.id.swap_rack_button);
            swap_rack_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //check game_Board.end_game -- todo--Ting
                    //
                    System.out.print(noTurn);
                    System.out.print(noTurn > poolletters.get_max_turns());
                    if (poolletters.pool_empty || noTurn > poolletters.max_turns){
//                    if (maxTurn || pool_not_refill) { --todo check--Ting
                        endGame();
                    }
                    else
                        { swaprack(); }
                }
            });

            //add play button functions
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Play button");
                    //check if exceeded max turns, if play clicked, if swapped
//                    if(noTurn <= poolletters.max_turns && !poolletters.pool_empty && !endgame)
                    if(!endgame) {
                        System.out.println(!word.getText().toString().isEmpty() );//true
//                        System.out.println(!poolletters.pool_empty );
                        System.out.println(!boardLetter.equals("") ); //true
                        System.out.println(!rackLetter.equals("")); //ture
                        System.out.println(playGame.equals("")); //ture
                        System.out.println(swap_rack.equals("") ); //true
                        System.out.println(!word.getText().toString().isEmpty() && !boardLetter.equals("") && !rackLetter.equals("") && swap_rack.equals("") && playGame.equals(""));
                        //check if word is empty, if one board is selected and if at least one rack is selected
                        if (!word.getText().toString().isEmpty() && !boardLetter.equals("") && !rackLetter.equals("") && swap_rack.equals("") && playGame.equals("") ) {
                            duplicateword = check_duplicate_word(word.getText().toString());
                            System.out.println("play button works");
                            // check if duplicateword
                            if (!duplicateword) {

                                //add and save word to words
                                poolletters.words.put(noTurn,word.getText().toString());
                                SingleGameData.save_pool(getApplicationContext(),poolletters);

                                poolletters.wordarr = poolletters.wordarr.replace(poolletters.wordarr,word.getText().toString());
                                SingleGameData.save_pool(getApplicationContext(), poolletters);

                                System.out.println();
                                System.out.println("Print out saved words when play button is played: " + poolletters.words.toString());

                                // add word to word letters and save that to sing game data
                                char[] charWord = word.getText().toString().toCharArray();
                                for(int i=0; i < charWord.length; i++) {

                                    poolletters.addwordletters(Character.toString(charWord[i]));

                                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                                }
                                System.out.println();
                                System.out.println("Print out words letters if word is not duplicate");
                                System.out.println(SingleGameData.get_pool(getApplicationContext()).wordsletters);
                                System.out.println();
                                System.out.println("turn score without extra credits ");
                                System.out.println(turnScore);
                                // update rack and board since the word is played and not duplicate
                                String playedword = word.getText().toString();
                                System.out.println();
                                System.out.println("Print out played word");
                                System.out.println(playedword);
                                System.out.println("Going to update board and rack");
                                playGame = "Played";
                                poolletters.playGame = playGame;
                                updateBoardRack(playedword);

                                //add extra 10 points after updating rack and found that the pool is empty
                                if (pool_not_refill){

                                    turnScore += 10 ;
                                    Toast.makeText(getApplicationContext(), "Earned extra 10 points when pool is empty and rack can't be refilled", Toast.LENGTH_SHORT).show();
                                    System.out.println();
                                    System.out.println("turn score with extra credits ");
                                    System.out.println(turnScore);
                                }
                                // show score & gameScore on UI, add scores_turn and gameScore to SingleGameData
                                System.out.println("Print out turn score " + turnScore);
                                score.setText(String.valueOf(turnScore));
                                poolletters.turnScore = turnScore;

                                poolletters.scores_turn.put(noTurn,turnScore);
//                                scores_turn.put(noTurn, turnScore);
                                SingleGameData.save_pool(getApplicationContext(),poolletters);
                                System.out.println("Print out saved scores for all turns after pressing play button: " + poolletters.scores_turn.toString());
                                gameScore = gameScore + turnScore;

                                poolletters.gameScore = gameScore;

                                gamescore.setText(String.valueOf(gameScore));
                                boardLetter = "";
                                poolletters.boardLetter = boardLetter;
                                rackLetter = "";
                                poolletters.rackLetter = rackLetter;
                                SingleGameData.save_pool(getApplicationContext(), poolletters);

                            } else {
                                // return letter to board and rack, clear word
                                Toast.makeText(getApplicationContext(), "Duplicate word! Play a new word", Toast.LENGTH_SHORT).show();
                                //todo return letter to board and rack, clear word
                                wordstr = word.getText().toString();
                                clearword(wordstr);
                                word.setText("");

                                score.setText("0");
                                turnScore= 0;
                                poolletters.turnScore = turnScore;

                                playGame ="";
                                poolletters.playGame = playGame;
                                SingleGameData.save_pool(getApplicationContext(), poolletters);
                            }

                        }
                        else {
                            if (boardLetter.equals("") && swap_rack.equals("") && !rackLetter.equals(""))
                                Toast.makeText(getApplicationContext(), "You need to select one letter from board.", Toast.LENGTH_SHORT).show();

                            if (rackLetter.equals("") && swap_rack.equals("") && !boardLetter.equals(""))
                                Toast.makeText(getApplicationContext(), "You need to select one letter from rack.", Toast.LENGTH_SHORT).show();

                            if (rackLetter.equals("") && boardLetter.equals("") && !swap_rack.equals(""))
                                Toast.makeText(getApplicationContext(), "You need to finish swapping.", Toast.LENGTH_SHORT).show();

                            if (word.getText().toString().isEmpty() && swap_rack.equals(""))
                                Toast.makeText(getApplicationContext(), "You need to select letters from rack and board to play game!", Toast.LENGTH_SHORT).show();
                        }
                    }
//                        else if (noTurn > poolletters.max_turns) {
//                            endGame();
//                        }
//                        else if (poolletters.pool_empty) {
//                            endGame();
//                        }
                }
            });


            //add next turn button functions
            next_turn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noTurn = incrementTurn(noTurn);
                    poolletters.noTurn = noTurn;
                    System.out.println("Next turn is clicked");
                    System.out.println(String.valueOf(noTurn));
                    System.out.println(pool_not_refill);
                    System.out.println(playGame);

                    if (noTurn > poolletters.max_turns || pool_not_refill) {
                        endgame = true;
                        SingleGameData.save_endgame(getApplicationContext(),endgame);
                        endGame();
                    }

                    else if (!playGame.equals("") ){
                        System.out.println("next turn is working");
                        turn.setText(String.valueOf(noTurn));
                        playGame = "";
                        poolletters.playGame = playGame;
                        word.setText("");
                        poolletters.wordarr = poolletters.wordarr.replace(poolletters.wordarr,"");

                        score.setText("0");
                        turnScore = 0;
                        poolletters.turnScore = turnScore;

                        SingleGameData.save_pool(getApplicationContext(), poolletters);

                    }

                    else if (word.getText().toString().isEmpty() && swap_rack.equals("") && noTurn <= poolletters.max_turns && !pool_not_refill && !endgame) {
                        Toast.makeText(getApplicationContext(), "You didn't play a word or confirm swap!", Toast.LENGTH_SHORT).show();

                    }
                    else if (playGame.equals("") && !word.getText().toString().isEmpty() && swap_rack.equals("") && noTurn <= poolletters.max_turns && !pool_not_refill && !endgame){
                        Toast.makeText(getApplicationContext(), "You need to confirm the word by pressing play button!", Toast.LENGTH_SHORT).show();
                    }
                    else if (word.getText().toString().isEmpty() && swap_rack.equals("") && noTurn <= poolletters.max_turns && !pool_not_refill && !endgame)
                        Toast.makeText(getApplicationContext(), "Select letters from board and rack or swap racks to play a game.", Toast.LENGTH_SHORT).show();

                }
            });

        }

        public void openmainactivity () {
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }

        public void initializeBoardandRack () {
            String k = "";
            System.out.println("drawn 1 letter for board from pool letters");

            poolletters.pool_empty = false;
            this.pool_not_refill = false;
            k = poolletters.randomLetterInit();
            System.out.println("");
            System.out.println(k);
            if (k != null) {
                System.out.println("Print out first letter draw for board 1");
                System.out.println(k);
                System.out.println();
                board_1.setText(k);
                poolletters.adddrawletters(k);
                boards[0] = k;
                SingleGameData.save_boards(getApplicationContext(), boards);

            }

            System.out.println("drawn 2 letter for board from pool letters");
            k = poolletters.randomLetterInit();
            if (k != null) {
                board_2.setText(k);
                poolletters.adddrawletters(k);
                boards[1] = k;
                SingleGameData.save_boards(getApplicationContext(), boards);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 3 letter for board from pool letters");
            k = poolletters.randomLetterInit();
            if (k != null) {
                board_3.setText(k);
                poolletters.adddrawletters(k);
                boards[2] = k;
                SingleGameData.save_boards(getApplicationContext(), boards);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 4 letter for board from pool letters");
            k = poolletters.randomLetterInit();
            if (k != null) {
                board_4.setText(k);
                poolletters.adddrawletters(k);
                boards[3] = k;
                System.out.println("drawn 4 letter for board from pool letters " + k);
                System.out.println(board_4.getText().toString());
                SingleGameData.save_boards(getApplicationContext(), boards);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 1 letter for rack from pool letters");
            k = poolletters.randomLetterInit();
            if (k != null) {
                rack_1.setText(k);
                poolletters.adddrawletters(k);
                racks[0] = k;
                SingleGameData.save_racks(getApplicationContext(), racks);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 2 letter for rack from pool letters");
            k = poolletters.randomLetterInit();
            System.out.println(k);
            if (k != null) {
                rack_2.setText(k);
                poolletters.adddrawletters(k);
                racks[1] = k;
                SingleGameData.save_racks(getApplicationContext(), racks);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 3 letter for rack from pool letters");
            k = poolletters.randomLetterInit();
            System.out.println(poolletters.drawletters.toString());
            if (k != null) {
                rack_3.setText(k);
                poolletters.adddrawletters(k);
                racks[2] = k;
                SingleGameData.save_racks(getApplicationContext(), racks);
                System.out.println(k);
                System.out.println();
            }

            k = poolletters.randomLetterInit();
            System.out.println("drawn 4 letter for rack from pool letters");
            if (k != null) {
                rack_4.setText(k);
                poolletters.adddrawletters(k);
                racks[3] = k;
                SingleGameData.save_racks(getApplicationContext(), racks);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 5 letter for rack from pool letters");
            k = poolletters.randomLetterInit();
            if (k != null) {
                rack_5.setText(k);
                poolletters.adddrawletters(k);
                racks[4] = k;
                SingleGameData.save_racks(getApplicationContext(), racks);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 6 letter for rack from pool letters");
            k = poolletters.randomLetterInit();
            if (k != null) {
                rack_6.setText(k);
                poolletters.adddrawletters(k);
                racks[5] = k;
                SingleGameData.save_racks(getApplicationContext(), racks);
                System.out.println(k);
                System.out.println();
            }

            System.out.println("drawn 7 letter for rack from pool letters");
            k = poolletters.randomLetterInit();
            if (k != null) {
                rack_7.setText(k);
                poolletters.adddrawletters(k);
                racks[6] = k;
                SingleGameData.save_racks(getApplicationContext(), racks);
                System.out.println(k);
                System.out.println();
            }
            else {
                System.out.println("Pool is empty from the beginning");
                Toast.makeText(getApplicationContext(), "Need more letters to initialize board and rack.", Toast.LENGTH_SHORT).show();
            }

            System.out.println("Initialize board and rack functions work");
            System.out.println("Print out draw letters in pool after initializing board and rack: ");
            System.out.println(poolletters.drawletters);
            System.out.println();
//            System.out.println("Print out draw letters in sing game data before saving: ");
//            System.out.println(SingleGameData.get_pool(this).drawletters);
            SingleGameData.save_pool(this,poolletters);
            System.out.println("Print out draw letters in single game data after saving: ");
            System.out.println(SingleGameData.get_pool(this).drawletters);
            System.out.println();
        }

        private void updateBoardRack (String wordstr){
            System.out.println("updating board and rack");
            System.out.println();
            System.out.println("rackLetter is " + rackLetter);
            System.out.println("1 boardLetter is " + boardLetter  );
            System.out.println("Play button is " + playGame );
            System.out.println("Played word is " + wordstr  );
            System.out.println(wordstr != null && !playGame.equals(""));
            // if the pool is not empty, a word is played, board and rack have not been updated, update board and rack.

            if (wordstr != null && !playGame.equals("")) {
                System.out.println("2 boardLetter is " + boardLetter  );
                // update board if it is not updated.
                if (!boardLetter.equals("")) {
                    System.out.println("update board");
                    System.out.println();

                    char[] charWord = wordstr.toCharArray();
                    Random rand = new Random();
                    char p = charWord[new Random().nextInt(charWord.length)];
//                        System.out.println("#####" + p + boardLetter);
                    if (boardLetter.equals("board_1")) {
                        board_1.setText(String.valueOf(p));
                        boards[0] = String.valueOf(p);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    } else if (boardLetter.equals("board_2")) {
                        board_2.setText(String.valueOf(p));
                        boards[1] = String.valueOf(p);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    } else if (boardLetter.equals("board_3")) {
                        board_3.setText(String.valueOf(p));
                        boards[2] = String.valueOf(p);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    } else if (boardLetter.equals("board_4")) {
                        board_4.setText(String.valueOf(p));
                        boards[3] = String.valueOf(p);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    }
                    boardLetter = "";
                    poolletters.boardLetter = boardLetter;
                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                }
                // update rack if it is not updated
                if (!rackLetter.equals("")) {
                    System.out.println("print out num letter in pool before updating rack");
                    System.lineSeparator();
                    System.out.println(poolletters.num_letter_pool.values());
                    System.lineSeparator();
                    String k = "";
                    if (rack_1.getText().toString().isEmpty()) {
                        k = poolletters.randomLetterInit();
                        if (k != null){
                            rack_1.setText(k);
                            racks[0] = k;
                            poolletters.adddrawletters(k);
                            SingleGameData.save_racks(getApplicationContext(), racks);
                        }
                        else {
                            poolletters.pool_empty = true;
                            pool_not_refill = true;
                            endgame = true;
//                            endGame();
                        }
                    }
                    if (rack_2.getText().toString().isEmpty()) {
                        k = poolletters.randomLetterInit();
                        if (k != null){
                            rack_2.setText(k);
                            racks[1] = k;
                            poolletters.adddrawletters(k);
                            SingleGameData.save_racks(getApplicationContext(), racks);
                        }
                        else {
                            poolletters.pool_empty = true;
                            pool_not_refill = true;
                            endgame = true;
//                            endGame();
                        }
                    }
                    if (rack_3.getText().toString().isEmpty()) {
                        k = poolletters.randomLetterInit();
                        if (k != null){
                            rack_3.setText(k);
                            racks[2] = k;
                            poolletters.adddrawletters(k);
                            SingleGameData.save_racks(getApplicationContext(), racks);
                        }
                        else {
                            poolletters.pool_empty = true;
                            pool_not_refill = true;
                            endgame = true;
//                            endGame();
                        }
                    }
                    if (rack_4.getText().toString().isEmpty()) {
                        k = poolletters.randomLetterInit();
                        if (k != null)
                            if (k != null){
                                rack_4.setText(k);
                                racks[3] = k;
                                poolletters.adddrawletters(k);
                                SingleGameData.save_racks(getApplicationContext(), racks);
                            }
                        else {
                            poolletters.pool_empty = true;
                            pool_not_refill = true;
                            endgame = true;
//                            endGame();
                        }
                    }
                    if (rack_5.getText().toString().isEmpty()) {
                        k = poolletters.randomLetterInit();
                        if (k != null){
                            rack_5.setText(k);
                            racks[4] = k;
                            poolletters.adddrawletters(k);
                            SingleGameData.save_racks(getApplicationContext(), racks);
                        }
                        else {
                            poolletters.pool_empty = true;
                            pool_not_refill = true;
                            endgame = true;
//                            endGame();
                        }
                    }
                    if (rack_6.getText().toString().isEmpty()) {
                        k = poolletters.randomLetterInit();
                        if (k != null){
                            rack_6.setText(k);
                            racks[5] = k;
                            poolletters.adddrawletters(k);
                            SingleGameData.save_racks(getApplicationContext(), racks);
                        }
                        else {
                            poolletters.pool_empty = true;
                            pool_not_refill = true;
                            endgame = true;
//                            endGame();
                        }
                    }
                    if (rack_7.getText().toString().isEmpty()) {
                        k = poolletters.randomLetterInit();
                        if (k != null){
                            rack_7.setText(k);
                            racks[6] = k;
                            poolletters.adddrawletters(k);
                            SingleGameData.save_racks(getApplicationContext(), racks);
                        }
                        else {
                            poolletters.pool_empty = true;
                            pool_not_refill = true;
                            endgame = true;
//                            endGame();
                        }
                    }
                    System.out.println("print out numbers letter in pool after updating rack");
                    System.lineSeparator();
                    System.out.println(poolletters.num_letter_pool.values());
                    System.lineSeparator();
                    SingleGameData.save_pool(this,this.poolletters);
                    SingleGameData.save_endgame(this,endgame);
                    rackLetter = "";
                    poolletters.rackLetter = rackLetter;
                    SingleGameData.save_pool(getApplicationContext(), poolletters);
                }

            }

        }

        private void clearword (String wordst){


            char wordLetters[] = wordst.toCharArray();

            for (char w : wordLetters) {
                System.out.println(letterBoard);
                System.out.println(Character.toString(w));
                if (letterBoard.charAt(0) == w && (board_1.getText().toString().isEmpty() || board_2.getText().toString().isEmpty() || board_3.getText().toString().isEmpty() || board_4.getText().toString().isEmpty())) {

                    if (board_1.getText().toString().isEmpty()) {
                        board_1.setText(Character.toString(w));
                        boards[0] = Character.toString(w);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                        boardLetter = "";

                    } else if (board_2.getText().toString().isEmpty()) {
                        board_2.setText(Character.toString(w));
                        boards[1] = Character.toString(w);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                        boardLetter = "";
                    } else if (board_3.getText().toString().isEmpty()) {
                        board_3.setText(Character.toString(w));
                        boardLetter = "";
                        boards[2] = Character.toString(w);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    } else if (board_4.getText().toString().isEmpty()) {
                        board_4.setText(Character.toString(w));
                        boardLetter = "";
                        boards[3] = Character.toString(w);
                        SingleGameData.save_boards(getApplicationContext(), boards);
                    }

                } else {

                    if (rack_1.getText().toString().isEmpty()) {
                        rack_1.setText(Character.toString(w));
                        racks[0] = Character.toString(w);
                        SingleGameData.save_racks(getApplicationContext(), racks);
                    } else if (rack_2.getText().toString().isEmpty()) {
                        rack_2.setText(Character.toString(w));
                        racks[1] = Character.toString(w);
                        SingleGameData.save_racks(getApplicationContext(), racks);
                    } else if (rack_3.getText().toString().isEmpty()) {
                        rack_3.setText(Character.toString(w));
                        racks[2] = Character.toString(w);
                        SingleGameData.save_racks(getApplicationContext(), racks);
                    } else if (rack_4.getText().toString().isEmpty()) {
                        rack_4.setText(Character.toString(w));
                        racks[3] = Character.toString(w);
                        SingleGameData.save_racks(getApplicationContext(), racks);
                    } else if (rack_5.getText().toString().isEmpty()) {
                        rack_5.setText(Character.toString(w));
                        racks[4] = Character.toString(w);
                        SingleGameData.save_racks(getApplicationContext(), racks);
                    } else if (rack_6.getText().toString().isEmpty()) {
                        rack_6.setText(Character.toString(w));
                        racks[5] = Character.toString(w);
                        SingleGameData.save_racks(getApplicationContext(), racks);
                    } else if (rack_7.getText().toString().isEmpty()) {
                        rack_7.setText(Character.toString(w));
                        racks[6] = Character.toString(w);
                        SingleGameData.save_racks(getApplicationContext(), racks);
                    }

                }
                rackLetter = "";
                poolletters.rackLetter = rackLetter;
                poolletters.boardLetter = boardLetter;
                SingleGameData.save_pool(getApplicationContext(), poolletters);
            }

        }

        public void swaprack () { //add that letter to num_letter_pool

            if (word.getText().toString().isEmpty() ) {

                if (rack_selected != "selected" && noTurn <= poolletters.max_turns) {
                    swap_rack = "swap";
                    poolletters.swap_rack = swap_rack;
                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                    Toast.makeText(getApplicationContext(), "You need to select racks and click on Swap button to swap it.", Toast.LENGTH_SHORT).show();
                } else {
                    // go through arraylist to swap it

                    String s = "";

                    for (int i = 0; i < selectedRacks.size(); i++) {

                        s = (String) selectedRacks.get(i);

                        String k = "";
                        // Button btn=(Button)s;
                        //  System.out.println(getResources().getResourceEntryName(rack_1.getId()));

                        if (getResources().getResourceEntryName(rack_1.getId()).toString().compareTo(s.toString()) == 0) {
                            rack_1.getBackground().clearColorFilter();
                            if (!rack_1.getText().toString().isEmpty()) {
                                // add swapped letter back to pool letters
                                poolletters.updateKey(rack_1.getText().toString());
                                System.out.println("Swap " + rack_1.getText().toString() + " back to pool");
                                System.out.println();
                                poolletters.addswapletters(rack_1.getText().toString());
                                System.out.println("Updated swap letters with swapped letter 1:");
                                System.out.println(poolletters.swapletters);
                                System.out.println();
                            }
                            // draw new letter from pool for and add it to draw letters
                            k = poolletters.randomLetterInit();
                            if (k != null) {
                                rack_1.setText(k);
                                System.out.println("Draw new " + k + " from pool for rack");
                                System.out.println();
                                poolletters.adddrawletters(k);
                                racks[0] = k;
                                SingleGameData.save_racks(getApplicationContext(), racks);
                                System.out.println("Updated draw letters with new drawn letter 1:");
                                System.out.println(poolletters.drawletters);
                                System.lineSeparator();
                                System.lineSeparator();
                            }
                            continue;
                        }

                        if (getResources().getResourceEntryName(rack_2.getId()).toString().compareTo(s.toString()) == 0) {
                            rack_2.getBackground().clearColorFilter();
                            if (!rack_2.getText().toString().isEmpty()) {
                                // add swapped letter back to pool letters
                                poolletters.updateKey(rack_2.getText().toString());
                                System.out.println("Swap " + rack_2.getText().toString() + " back to pool");
                                System.out.println();
                                poolletters.addswapletters(rack_2.getText().toString());
                                System.out.println("Updated swap letters with swapped letter 2:");
                                System.out.println(poolletters.swapletters);
                                System.out.println();
                            }
                            // draw new letter from pool for and add it to draw letters
                            k = poolletters.randomLetterInit();
                            if (k != null) {
                                rack_2.setText(k);
                                System.out.println("Draw new " + k + " from pool for rack");
                                System.out.println();
                                poolletters.adddrawletters(k);
                                racks[1] = k;
                                SingleGameData.save_racks(getApplicationContext(), racks);
                                System.out.println("Updated draw letters with new drawn letter 2:");
                                System.out.println(poolletters.drawletters);
                                System.lineSeparator();
                                System.lineSeparator();
                            }
                            continue;
                        }
                            System.out.print(getResources().getResourceEntryName(rack_3.getId()).toString().compareTo(s.toString()));

                        if (getResources().getResourceEntryName(rack_3.getId()).toString().compareTo(s.toString()) == 0) {
                            rack_3.getBackground().clearColorFilter();
                            if (!rack_3.getText().toString().isEmpty()) {
                                // add swapped letter back to pool letters
                                poolletters.updateKey(rack_3.getText().toString());
                                System.out.println("Swap " + rack_3.getText().toString() + " back to pool");
                                System.out.println();
                                poolletters.addswapletters(rack_3.getText().toString());
                                System.out.println("Updated swap letters with swapped letter 3:");
                                System.out.println(poolletters.swapletters);
                                System.out.println();
                            }
                            // draw new letter from pool for and add it to draw letters
                            k = poolletters.randomLetterInit();
                            if (k != null) {
                                rack_3.setText(k);
                                System.out.println("Draw new " + k + " from pool for rack");
                                System.out.println();
                                poolletters.adddrawletters(k);
                                racks[2] = k;
                                SingleGameData.save_racks(getApplicationContext(), racks);
                                System.out.println("Updated draw letters with new drawn letter 3:");
                                System.out.println(poolletters.drawletters);
                                System.lineSeparator();
                                System.lineSeparator();
                            }
                            continue;
                        }
                        if (getResources().getResourceEntryName(rack_4.getId()).toString().compareTo(s.toString()) == 0) {
                                rack_4.getBackground().clearColorFilter();
                                if (!rack_4.getText().toString().isEmpty()) {
                                    // add swapped letter back to pool letters
                                    poolletters.updateKey(rack_4.getText().toString());
                                    System.out.println("Swap " + rack_4.getText().toString() + " back to pool");
                                    System.out.println();
                                    poolletters.addswapletters(rack_4.getText().toString());
                                    System.out.println("Updated swap letters with swapped letter 4:");
                                    System.out.println(poolletters.swapletters);
                                    System.out.println();
                                }
                                // draw new letter from pool for and add it to draw letters
                                k = poolletters.randomLetterInit();
                                if (k != null) {
                                    rack_4.setText(k);
                                    System.out.println("Draw new " + k + " from pool for rack");
                                    System.out.println();
                                    poolletters.adddrawletters(k);
                                    racks[3] = k;
                                    SingleGameData.save_racks(getApplicationContext(), racks);
                                    System.out.println("Updated draw letters with new drawn letter 4:");
                                    System.out.println(poolletters.drawletters);
                                    System.lineSeparator();
                                    System.lineSeparator();
                                }
//                                }
                                continue;
                            }
                        if (getResources().getResourceEntryName(rack_5.getId()).toString().compareTo(s.toString()) == 0) {
                            rack_5.getBackground().clearColorFilter();
                            if (!rack_5.getText().toString().isEmpty()) {
                                // add swapped letter back to pool letters
                                poolletters.updateKey(rack_5.getText().toString());
                                System.out.println("Swap " + rack_5.getText().toString() + " back to pool");
                                System.out.println();
                                poolletters.addswapletters(rack_5.getText().toString());
                                System.out.println("Updated swap letters with swapped letter 5:");
                                System.out.println(poolletters.swapletters);
                                System.out.println();
                            }
                            // draw new letter from pool for and add it to draw letters
                            k = poolletters.randomLetterInit();
                            if (k != null) {
                                rack_5.setText(k);
                                System.out.println("Draw new " + k + " from pool for rack");
                                System.out.println();
                                poolletters.adddrawletters(k);
                                racks[4] = k;
                                SingleGameData.save_racks(getApplicationContext(), racks);
                                System.out.println("Updated draw letters with new drawn letter 5:");
                                System.out.println(poolletters.drawletters);
                                System.lineSeparator();
                                System.lineSeparator();
                            }
                            continue;
                        }
                        if (getResources().getResourceEntryName(rack_6.getId()).toString().compareTo(s.toString()) == 0) {
                            rack_6.getBackground().clearColorFilter();
                            if (!rack_6.getText().toString().isEmpty()) {
                                // add swapped letter back to pool letters
                                poolletters.updateKey(rack_6.getText().toString());
                                System.out.println("Swap " + rack_6.getText().toString() + " back to pool");
                                System.out.println();
                                poolletters.addswapletters(rack_6.getText().toString());
                                System.out.println("Updated swap letters with swapped letter 6:");
                                System.out.println(poolletters.swapletters);
                                System.out.println();
                            }
                            // draw new letter from pool for and add it to draw letters
                            k = poolletters.randomLetterInit();
                            if (k != null) {
                                rack_6.setText(k);
                                System.out.println("Draw new " + k + " from pool for rack");
                                System.out.println();
                                poolletters.adddrawletters(k);
                                racks[5] = k;
                                SingleGameData.save_racks(getApplicationContext(), racks);
                                System.out.println("Updated draw letters with new drawn letter 6:");
                                System.out.println(poolletters.drawletters);
                                System.lineSeparator();
                                System.lineSeparator();
                            }
                            continue;
                        }
                        if (getResources().getResourceEntryName(rack_7.getId()).toString().compareTo(s.toString()) == 0) {
                            rack_7.getBackground().clearColorFilter();
                            if (!rack_7.getText().toString().isEmpty()) {
                                // add swapped letter back to pool letters
                                poolletters.updateKey(rack_7.getText().toString());
                                System.out.println("Swap " + rack_7.getText().toString() + " back to pool");
                                System.out.println();
                                poolletters.addswapletters(rack_7.getText().toString());
                                System.out.println("Updated swap letters with swapped letter 7:");
                                System.out.println(poolletters.swapletters);
                                System.out.println();
                            }
                            // draw new letter from pool for and add it to draw letters
                            k = poolletters.randomLetterInit();
                            if (k != null) {
                                rack_7.setText(k);
                                System.out.println("Draw new " + k + " from pool for rack");
                                System.out.println();
                                poolletters.adddrawletters(k);
                                racks[6] = k;
                                SingleGameData.save_racks(getApplicationContext(), racks);
                                System.out.println("Updated draw letters with new drawn letter 7:");
                                System.out.println(poolletters.drawletters);
                                System.out.println();
                            }
                            continue;
                        }
                        }

                    testpoolletter = poolletters.num_letter_pool;
                    System.out.println("After confirming swap, print out pool letter numbers in pool");
                    System.out.println(testpoolletter.toString());
                    System.out.println();
                    // saved updated draw letters and swap letters
                    testpoolletter = SingleGameData.get_pool(this).num_letter_pool;
                    System.out.println("After confirming swap, print out pool letter numbers in single game data after saving");
                    System.out.println(testpoolletter.toString());
                    System.out.println();
                    testpoolletter = SingleGameData.get_pool(this).swapletters;
                    System.out.println("After confirming swap, print out swap letters in single game data after saving");
                    System.out.println(testpoolletter.toString());
                    System.out.println();
                    testpoolletter = SingleGameData.get_pool(this).drawletters;
                    System.out.println("After confirming swap, print out draw letters in single game data after saving");
                    System.out.println(testpoolletter.toString());
                    System.out.println();

                    turnScore = 0;
                    score.setText(String.valueOf(turnScore));
                    poolletters.turnScore = turnScore;
                    poolletters.scores_turn.put(noTurn,0);

                    noTurn = incrementTurn(noTurn);
                    poolletters.noTurn = noTurn;

                    SingleGameData.save_pool(this, poolletters);


//                    SingleGameData.save_turnNo(getApplicationContext(), noTurn);

                    System.out.println("print out noTurn after adding 1:");
                    System.out.println(noTurn);

                    if (noTurn > poolletters.max_turns){
                        Toast.makeText(getApplicationContext(), "You reached maximum turns!", Toast.LENGTH_SHORT).show();
                        System.out.println();
                        System.out.print("Game ended after swapping the last turn");
                        endGame();
                    }
                    else {turn.setText(String.valueOf(noTurn));}
                    rack_selected = "";
                    poolletters.rack_selected = rack_selected;
                    swap_rack = "";
                    poolletters.swap_rack = swap_rack;
                    selectedRacks = new ArrayList();
                    poolletters.selectedRacks = selectedRacks;

                    SingleGameData.save_pool(getApplicationContext(),poolletters);
                    }
                }
            else{
                if (!word.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), "You can't swap while you are playing game!", Toast.LENGTH_SHORT).show();
//                if (noTurn > poolletters.max_turns) {
//                        Toast.makeText(getApplicationContext(), "You reached maximum turns!", Toast.LENGTH_SHORT).show();
//                        endGame();
//                }
            }
        }

        private boolean check_duplicate_word (String word){
            if (poolletters.words != null) {
                boolean dup = poolletters.words.containsValue(word);
                return dup;
            }
            return false;
        }

        public void calculateScore (String str){
            Integer value = (Integer) poolletters.get_letter_points(str);
            turnScore = turnScore + value;
            score.setText(String.valueOf(turnScore));
            poolletters.turnScore = turnScore;
            SingleGameData.save_pool(this, poolletters);
        }

        private int incrementTurn ( int noturn){
            noturn += 1;

            poolletters.noTurn = noturn;
            SingleGameData.save_pool(this,poolletters);
//            SingleGameData.save_turnNo(this,noturn);
            System.out.println("Print out turn no when adding 1 to it");
//            System.out.println(SingleGameData.get_turnNo(this));
            return noturn;
        }

//        private HashMap<Integer, String> addwords ( int turnno, String wordstr){
//            words.put(turnno, wordstr);
//            SingleGameData.save_words(this, words);
//            return words;
//        }

//        private HashMap<Integer, String> addscores_turn ( int turnno, int turnScore){
//            scores_turn.put(turnno, turnScore);
//            SingleGameData.save_scores_turn(this, scores_turn);
//            return words;
//        }


//        private int setTurnToZero () {
//            SingleGameData.save_turnNo(this, 0);
//            turn.setText("0");
//            return 0;
//        }

//        private HashMap<Integer, Integer> setScores_turnToNull () {
//            SingleGameData.save_scores_turn(this, new HashMap<Integer, Integer>());
//            return new HashMap<Integer, Integer>();
//        }

//        private HashMap<Integer, String> setWordsToEmpty () {
//            SingleGameData.save_words(this, new HashMap<Integer, String>());
//            return new HashMap<Integer, String>();
//        }



        private void InitializeGameSettings () {

            poolletters.refreshsinglegamedata();
            rackLetter = poolletters.rackLetter;
            boardLetter = poolletters.boardLetter;
            selectedRacks = poolletters.selectedRacks;
            rack_selected = poolletters.rack_selected;
            playGame = poolletters.playGame;
            swap_rack = poolletters.swap_rack;
            turnScore = poolletters.turnScore;


        // Add game board parameters and display
            System.out.println("Initialize game settings for new games");
            System.out.println("Print out game settings 1 : " + poolletters.num_letter_pool);
            System.out.println("Print out game settings 2 : " + poolletters.num_letter_pool);

            System.out.println("Print out game settings: " + poolletters.num_letter_pool);
            original_num_letter_pool = SingleGameData.get_pool(this).num_letter_pool;
            System.out.println("Print out game settings: " + poolletters.num_letter_pool);

            System.out.println("using default pool when game starts and game setting have not been changed, otherwise using changed settings");

            endgame = false;
            SingleGameData.save_endgame(this, endgame);
            pool_not_refill = false;
            poolletters.pool_empty = false;

            noTurn = 1;
            poolletters.noTurn = 1;

            swap_rack = "";
            System.out.println("Print out swap_rack status" + poolletters.swap_rack);
//            poolletters.swap_rack = swap_rack;

            SingleGameData.save_pool(this,poolletters);
            // display turn no
            turn.setText(String.valueOf(poolletters.noTurn));

            // display score
            score.setText("0");

            // display game score
            gamescore.setText("");

            // word
            word.setText("");

//            this.poolletters = SingleGameData.get_pool(this); //have done
//            this.poolletters.initializedrawletters(); //todo delete later
//            this.poolletters.initializeswapletters();

            System.out.println("print out swap letters class when game starts");
            System.out.println(poolletters.swapletters);
            System.out.println("print out draw letters class when game starts");
            System.out.println(poolletters.drawletters);

            initializeBoardandRack();
            System.out.println("print out numbers of letters in pool after initializing board and rack1");
            System.out.println(poolletters.num_letter_pool.toString());
            System.out.println("print out original numbers of letters in pool after initializing board and rack");
            System.out.println(original_num_letter_pool.toString());
            System.out.println(poolletters.drawletters);

        }
        // initializeListeners();


        private void resumeGameSettings () {

            // Add game board parameters and display

            poolletters = SingleGameData.get_pool(getApplicationContext());

            rackLetter = poolletters.rackLetter;
            boardLetter = poolletters.boardLetter;
            selectedRacks = poolletters.selectedRacks;
            rack_selected = poolletters.rack_selected;
            playGame = poolletters.playGame;
            swap_rack = poolletters.swap_rack;
            turnScore = poolletters.turnScore;
            System.out.println();
            System.out.println("Resuming game settings");

            System.out.println("Print out swap status: " + swap_rack);
            System.out.println("Print out boardLetter: " + boardLetter);
            System.out.println("Print out rackLetter: " + rackLetter);
            System.out.println("Print out play status: " + playGame);
            System.out.println("Print out rack_selected status: " + rack_selected);
            System.out.println("Print out selected racks: " + selectedRacks);

            // display turn no
            turn.setText(String.valueOf(poolletters.noTurn));

            // display score
            score.setText(String.valueOf(poolletters.turnScore));
            System.out.println("Print out previously saved turn score: " + score.getText().toString());

            // display game score
            gamescore.setText(String.valueOf(poolletters.gameScore));
            System.out.println("Print out previously saved game score: " + gamescore.getText().toString());

            // word
            word.setText(poolletters.wordarr);
            System.out.println("Print out previously saved words: " + poolletters.wordarr.toString());

            boards = SingleGameData.get_boards(this);
            racks = SingleGameData.get_racks(this);
            System.out.println();
            System.out.println("Print out saved boards from previous game");
            System.out.println(boards.toString());
            System.out.println();
            System.out.println("Print out saved racks from previous game");
            System.out.println(racks.toString());
            System.out.println(playGame);


            board_1.setText(boards[0]);
            board_2.setText(boards[1]);
            board_3.setText(boards[2]);
            board_4.setText(boards[3]);
            rack_1.setText(racks[0]);
            rack_2.setText(racks[1]);
            rack_3.setText(racks[2]);
            rack_4.setText(racks[3]);
            rack_5.setText(racks[4]);
            rack_6.setText(racks[5]);
            rack_7.setText(racks[6]);

//                if (!swap_rack.equals("") && !rack_4.getText().toString().isEmpty() && word.getText().toString().isEmpty()) {
//                    rack_selected = "selected";
//                    poolletters.rack_selected = rack_selected;
//                    System.out.println("Choose rack4 for swap");
//                    System.out.println(rack_4.getText().toString());
//                    rack_4.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY);
//                    System.out.println("Added rack4 to selectedRacks");
//                    System.lineSeparator();
//                    selectedRacks.add("rack_4");
//                    poolletters.selectedRacks = selectedRacks;
//                    SingleGameData.save_pool(getApplicationContext(),poolletters)


        }

        private void process_statistics() {
            PoolLetters pool = SingleGameData.get_pool(getApplicationContext());
            System.out.println(pool.wordsletters.toString());
            System.out.println(pool.swapletters.toString());
            System.out.println(pool.drawletters.toString());

            db.putScore(pool.gameScore, pool.noTurn, pool.max_turns, original_num_letter_pool, pool.letter_points);
            for (int turn: pool.words.keySet()) {
                db.putWord(pool.words.get(turn));
            }
            db.putLetters(pool.wordsletters, pool.swapletters, pool.drawletters);
            db.close();
        }

        private void endGame () {
            process_statistics();

            Toast.makeText(getApplicationContext(), "Game ended! Final score is " + poolletters.gameScore , Toast.LENGTH_SHORT).show();

            System.out.println("GAME IS ENDING1");
//            this.poolletters.initializeswapletters();
//            this.poolletters.initializedrawletters();
            //add new pool and save it

            System.out.println();
            System.out.println("print out saved game data");
            System.out.println("Print out original numbers letters in pool");
            System.out.println(original_num_letter_pool);
//            this.poolletters = new PoolLetters(); //todo check the logic

            poolletters.num_letter_pool = original_num_letter_pool;

            poolletters.initializedrawletters();
            poolletters.initializeswapletters();
            poolletters.initializewordletters();

            System.out.println(poolletters.num_letter_pool);
            SingleGameData.save_pool(this, poolletters);
            System.out.println("print out emptied game data");
            System.out.println("print out reset defaulted number letter in single game data");
            System.out.println(SingleGameData.get_pool(this).num_letter_pool.toString());
            System.out.println();
            System.out.println("print out reset defaulted letter points in single game data");
            System.out.println(SingleGameData.get_pool(this).letter_points.toString());
            System.out.println();
            System.out.println("print out turnNo in single game data");
            System.out.println(SingleGameData.get_pool(this).noTurn);
            System.out.println();
            System.out.println("print out words in single game data");
            System.out.println(SingleGameData.get_pool(this).words);
            System.out.println();
            System.out.println("print out scores_turn in single game data");
            System.out.println(SingleGameData.get_pool(this).scores_turn);
            System.out.println();
            System.out.println("print out swap letters for all games");
            System.out.println(SingleGameData.get_pool(this).swapletters);
            System.out.println();
            System.out.println("print out word letters for all letters");
            System.out.println(SingleGameData.get_pool(this).wordsletters);
            System.out.println();
            System.out.println("print out draw letters for all letters");
            System.out.println(SingleGameData.get_pool(this).drawletters);
            System.out.println();
            System.out.println("print out saved racks");
            System.out.println(SingleGameData.get_racks(this));
            System.out.println();
            System.out.println("print out saved boards");
            System.out.println(SingleGameData.get_boards(this));
            System.out.println();
            openmainactivity();

//            poolletters.noTurn = 0;
//            poolletters.words = new HashMap<Integer, String>();
//            poolletters.turnScore = 0;
//            poolletters.gameScore = 0;
//            poolletters.scores_turn = new HashMap<Integer, Integer>();
//            poolletters.wordarr = new String();

            endgame = false;
            SingleGameData.save_endgame(this,endgame);
            resumegame = true;
            SingleGameData.save_resumegame(this, resumegame);

        }
    }

//            private void processGameStatistics () {
//            }
