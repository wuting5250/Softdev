package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameSettings extends AppCompatActivity {
    private Button return_menu_button;
    private Button num_letter_pool_button;
    private Button letter_points_button;
    private Button max_game_cycles_button;
    private Button confirm_button;
    private EditText A_NO;
    private EditText B_NO;
    private EditText C_NO;
    private EditText D_NO;
    private EditText E_NO;
    private EditText F_NO;
    private EditText G_NO;
    private EditText H_NO;
    private EditText I_NO;
    private EditText J_NO;
    private EditText K_NO;
    private EditText L_NO;
    private EditText M_NO;
    private EditText N_NO;
    private EditText O_NO;
    private EditText P_NO;
    private EditText Q_NO;
    private EditText R_NO;
    private EditText S_NO;
    private EditText T_NO;
    private EditText U_NO;
    private EditText V_NO;
    private EditText W_NO;
    private EditText X_NO;
    private EditText Y_NO;
    private EditText Z_NO;
    private EditText blank_NO;

    private EditText A_p;
    private EditText B_p;
    private EditText C_p;
    private EditText D_p;
    private EditText E_p;
    private EditText F_p;
    private EditText G_p;
    private EditText H_p;
    private EditText I_p;
    private EditText J_p;
    private EditText K_p;
    private EditText L_p;
    private EditText M_p;
    private EditText N_p;
    private EditText O_p;
    private EditText P_p;
    private EditText Q_p;
    private EditText R_p;
    private EditText S_p;
    private EditText T_p;
    private EditText U_p;
    private EditText V_p;
    private EditText W_p;
    private EditText X_p;
    private EditText Y_p;
    private EditText Z_p;
    private EditText blank_p;

    private EditText max_turns;


    private PoolLetters poolletters;
    private LinearLayout letter_counts_layout;
    private LinearLayout letter_points_layout;
    private LinearLayout max_game_cycles_layout;
    private TextView check_max_game_cycles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamesettings);

        this.A_NO = findViewById(R.id.A_NO);
        this.B_NO = findViewById(R.id.B_NO);
        this.C_NO = findViewById(R.id.C_NO);
        this.D_NO = findViewById(R.id.D_NO);
        this.E_NO = findViewById(R.id.E_NO);
        this.F_NO = findViewById(R.id.F_NO);
        this.G_NO = findViewById(R.id.G_NO);
        this.H_NO = findViewById(R.id.H_NO);
        this.I_NO = findViewById(R.id.I_NO);
        this.J_NO = findViewById(R.id.J_NO);
        this.K_NO = findViewById(R.id.K_NO);
        this.L_NO = findViewById(R.id.L_NO);
        this.M_NO = findViewById(R.id.M_NO);
        this.N_NO = findViewById(R.id.N_NO);
        this.O_NO = findViewById(R.id.O_NO);
        this.P_NO = findViewById(R.id.P_NO);
        this.Q_NO = findViewById(R.id.Q_NO);
        this.R_NO = findViewById(R.id.R_NO);
        this.S_NO = findViewById(R.id.S_NO);
        this.T_NO = findViewById(R.id.T_NO);
        this.U_NO = findViewById(R.id.U_NO);
        this.V_NO = findViewById(R.id.V_NO);
        this.W_NO = findViewById(R.id.W_NO);
        this.X_NO = findViewById(R.id.X_NO);
        this.Y_NO = findViewById(R.id.Y_NO);
        this.Z_NO = findViewById(R.id.Z_NO);
        this.blank_NO = findViewById(R.id.blank_NO);


        this.A_p = findViewById(R.id.A_p);
        this.B_p = findViewById(R.id.B_p);
        this.C_p = findViewById(R.id.C_p);
        this.D_p = findViewById(R.id.D_p);
        this.E_p = findViewById(R.id.E_p);
        this.F_p = findViewById(R.id.F_p);
        this.G_p = findViewById(R.id.G_p);
        this.H_p = findViewById(R.id.H_p);
        this.I_p = findViewById(R.id.I_p);
        this.J_p = findViewById(R.id.J_p);
        this.K_p = findViewById(R.id.K_p);
        this.L_p = findViewById(R.id.L_p);
        this.M_p = findViewById(R.id.M_p);
        this.N_p = findViewById(R.id.N_p);
        this.O_p = findViewById(R.id.O_p);
        this.P_p = findViewById(R.id.P_p);
        this.Q_p = findViewById(R.id.Q_p);
        this.R_p = findViewById(R.id.R_p);
        this.S_p = findViewById(R.id.S_p);
        this.T_p = findViewById(R.id.T_p);
        this.U_p = findViewById(R.id.U_p);
        this.V_p = findViewById(R.id.V_p);
        this.W_p = findViewById(R.id.W_p);
        this.X_p = findViewById(R.id.X_p);
        this.Y_p = findViewById(R.id.Y_p);
        this.Z_p = findViewById(R.id.Z_p);
        this.blank_p = findViewById(R.id.blank_p);

        this.poolletters = (PoolLetters) SingleGameData.get_pool(this);

        num_letter_pool_button = (Button) findViewById(R.id.num_letter_pool_button);
        letter_points_button = (Button) findViewById(R.id.letter_points_button);
        max_game_cycles_button = (Button) findViewById(R.id.max_game_cycles_button);
        return_menu_button = (Button) findViewById(R.id.return_menu_button);
        letter_counts_layout = (LinearLayout) findViewById(R.id.letter_counts_layout);
        letter_points_layout = (LinearLayout) findViewById(R.id.letter_points_layout);
        max_game_cycles_layout = (LinearLayout) findViewById(R.id.max_game_cycles_layout);
        max_turns = (EditText) findViewById(R.id.max_turns);
        check_max_game_cycles = (TextView) findViewById(R.id.check_max_game_cycles);

        max_turns.setText(Integer.toString(poolletters.max_turns));

        num_letter_pool_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populate_count_table();
                letter_points_layout.setVisibility(View.INVISIBLE);
                max_game_cycles_layout.setVisibility(View.INVISIBLE);

            }
        });

        letter_points_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populate_points_table();
                letter_counts_layout.setVisibility(View.INVISIBLE);
                max_game_cycles_layout.setVisibility(View.INVISIBLE);
            }
        });

        max_game_cycles_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letter_counts_layout.setVisibility(View.INVISIBLE);
                letter_points_layout.setVisibility(View.INVISIBLE);
                max_game_cycles_layout.setVisibility(View.VISIBLE);
            }
        });

        max_turns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populate_max_turn();
            }
        });

        return_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmainactivity();
            }
        });

        A_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(A_NO, "A");
            }
        });
        B_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(B_NO, "B");
            }
        });
        C_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(C_NO, "C");
            }
        });
        D_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(D_NO, "D");
            }
        });
        E_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(E_NO, "E");
            }
        });
        F_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(F_NO, "F");
            }
        });
        G_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(G_NO, "G");
            }
        });
        H_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(H_NO, "H");
            }
        });
        I_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(I_NO, "I");
            }
        });
        J_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(J_NO, "J");
            }
        });
        K_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(K_NO, "K");
            }
        });
        L_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(L_NO, "L");
            }
        });
        M_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(M_NO, "M");
            }
        });
        N_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(N_NO, "N");
            }
        });
        O_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(O_NO, "O");
            }
        });
        P_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(P_NO, "P");
            }
        });
        Q_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(Q_NO, "Q");
            }
        });
        R_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(R_NO, "R");
            }
        });
        S_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(S_NO, "S");
            }
        });
        T_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(T_NO, "T");
            }
        });
        U_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(U_NO, "U");
            }
        });
        V_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(V_NO, "V");
            }
        });
        W_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(W_NO, "W");
            }
        });
        X_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(X_NO, "X");
            }
        });
        Y_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(Y_NO, "Y");
            }
        });
        Z_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(Z_NO, "Z");
            }
        });
        blank_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_num(blank_NO, "/");
            }
        });



        A_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(A_p, "A");
            }
        });
        B_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(B_p, "B");
            }
        });
        C_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(C_p, "C");
            }
        });
        D_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(D_p, "D");
            }
        });
        E_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(E_p, "E");
            }
        });
        F_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(F_p, "F");
            }
        });
        G_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(G_p, "G");
            }
        });
        H_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(H_p, "H");
            }
        });
        I_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(I_p, "I");
            }
        });
        J_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(J_p, "J");
            }
        });
        K_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(K_p, "K");
            }
        });
        L_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(L_p, "L");
            }
        });
        M_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(M_p, "M");
            }
        });
        N_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(N_p, "N");
            }
        });
        O_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(O_p, "O");
            }
        });
        P_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(P_p, "P");
            }
        });
        Q_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(Q_p, "Q");
            }
        });
        R_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(R_p, "R");
            }
        });
        S_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(S_p, "S");
            }
        });
        T_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(T_p, "T");
            }
        });
        U_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(U_p, "U");
            }
        });
        V_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(V_p, "V");
            }
        });
        W_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(W_p, "W");
            }
        });
        X_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(X_p, "X");
            }
        });
        Y_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(Y_p, "Y");
            }
        });
        Z_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(Z_p, "Z");
            }
        });
        blank_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_letter_points(blank_p, "/");
            }
        });
    }

    public void openmainactivity() {
        Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("PoolLetters", poolletters);
        startActivity(intent);
    }

    private void populate_count_table() {
        letter_counts_layout.setVisibility(View.VISIBLE);

        this.A_NO.setText(Integer.toString(poolletters.get_letter_num("A")));
        B_NO.setText(Integer.toString(poolletters.get_letter_num("B")));
        C_NO.setText(Integer.toString(poolletters.get_letter_num("C")));
        D_NO.setText(Integer.toString(poolletters.get_letter_num("D")));
        E_NO.setText(Integer.toString(poolletters.get_letter_num("E")));
        F_NO.setText(Integer.toString(poolletters.get_letter_num("F")));
        G_NO.setText(Integer.toString(poolletters.get_letter_num("G")));
        H_NO.setText(Integer.toString(poolletters.get_letter_num("H")));
        I_NO.setText(Integer.toString(poolletters.get_letter_num("I")));
        J_NO.setText(Integer.toString(poolletters.get_letter_num("J")));
        K_NO.setText(Integer.toString(poolletters.get_letter_num("K")));
        L_NO.setText(Integer.toString(poolletters.get_letter_num("L")));
        M_NO.setText(Integer.toString(poolletters.get_letter_num("M")));
        N_NO.setText(Integer.toString(poolletters.get_letter_num("N")));
        O_NO.setText(Integer.toString(poolletters.get_letter_num("O")));
        P_NO.setText(Integer.toString(poolletters.get_letter_num("P")));
        Q_NO.setText(Integer.toString(poolletters.get_letter_num("Q")));
        R_NO.setText(Integer.toString(poolletters.get_letter_num("R")));
        S_NO.setText(Integer.toString(poolletters.get_letter_num("S")));
        T_NO.setText(Integer.toString(poolletters.get_letter_num("T")));
        U_NO.setText(Integer.toString(poolletters.get_letter_num("U")));
        V_NO.setText(Integer.toString(poolletters.get_letter_num("V")));
        W_NO.setText(Integer.toString(poolletters.get_letter_num("W")));
        X_NO.setText(Integer.toString(poolletters.get_letter_num("X")));
        Y_NO.setText(Integer.toString(poolletters.get_letter_num("Y")));
        Z_NO.setText(Integer.toString(poolletters.get_letter_num("Z")));
        blank_NO.setText(Integer.toString(poolletters.get_letter_num("/")));



    }

    private void populate_points_table() {
        letter_points_layout.setVisibility(View.VISIBLE);
        this.A_p.setText(Integer.toString(poolletters.get_letter_points("A")));
        B_p.setText(Integer.toString(poolletters.get_letter_points("B")));
        C_p.setText(Integer.toString(poolletters.get_letter_points("C")));
        D_p.setText(Integer.toString(poolletters.get_letter_points("D")));
        E_p.setText(Integer.toString(poolletters.get_letter_points("E")));
        F_p.setText(Integer.toString(poolletters.get_letter_points("F")));
        G_p.setText(Integer.toString(poolletters.get_letter_points("G")));
        H_p.setText(Integer.toString(poolletters.get_letter_points("H")));
        I_p.setText(Integer.toString(poolletters.get_letter_points("I")));
        J_p.setText(Integer.toString(poolletters.get_letter_points("J")));
        K_p.setText(Integer.toString(poolletters.get_letter_points("K")));
        L_p.setText(Integer.toString(poolletters.get_letter_points("L")));
        M_p.setText(Integer.toString(poolletters.get_letter_points("M")));
        N_p.setText(Integer.toString(poolletters.get_letter_points("N")));
        O_p.setText(Integer.toString(poolletters.get_letter_points("O")));
        P_p.setText(Integer.toString(poolletters.get_letter_points("P")));
        Q_p.setText(Integer.toString(poolletters.get_letter_points("Q")));
        R_p.setText(Integer.toString(poolletters.get_letter_points("R")));
        S_p.setText(Integer.toString(poolletters.get_letter_points("S")));
        T_p.setText(Integer.toString(poolletters.get_letter_points("T")));
        U_p.setText(Integer.toString(poolletters.get_letter_points("U")));
        V_p.setText(Integer.toString(poolletters.get_letter_points("V")));
        W_p.setText(Integer.toString(poolletters.get_letter_points("W")));
        X_p.setText(Integer.toString(poolletters.get_letter_points("X")));
        Y_p.setText(Integer.toString(poolletters.get_letter_points("Y")));
        Z_p.setText(Integer.toString(poolletters.get_letter_points("Z")));
        blank_p.setText(Integer.toString(poolletters.get_letter_points("/")));
    }


    private void set_letter_num(EditText ET, String letter) {
        if (check_valid_integer(ET.getText().toString())) {
            String counts = ET.getText().toString();
            poolletters.set_letter_num(letter, Integer.parseInt(counts));
            SingleGameData.save_pool(this, poolletters);
        } else {
            ET.setText(Integer.toString(poolletters.get_letter_num(letter)));
            Toast.makeText(getApplicationContext(),"Invalid Number",Toast.LENGTH_SHORT).show();
        }
    }

    private void set_letter_points(EditText ET, String letter) {
        if (check_valid_integer(ET.getText().toString())) {
            String points = ET.getText().toString();
            poolletters.set_letter_points(letter, Integer.parseInt(points));
            SingleGameData.save_pool(this, poolletters);
        } else {
            ET.setText(Integer.toString(poolletters.get_letter_points(letter)));
            Toast.makeText(getApplicationContext(),"Invalid Number",Toast.LENGTH_SHORT).show();
        }
    }

    public void populate_max_turn(){
        if (Integer.parseInt(max_turns.getText().toString()) > 0 && Integer.parseInt(max_turns.getText().toString())<=50 ) {
            int max_game_cycles = Integer.parseInt(max_turns.getText().toString());
            poolletters.max_turns = max_game_cycles;
            SingleGameData.save_pool(this,poolletters);

        } else {
            Toast.makeText(getApplicationContext(),"Enter valid number between 1 and 50.",Toast.LENGTH_SHORT).show();
            max_turns.setText(Integer.toString(poolletters.max_turns));
        }
        System.out.println(poolletters.get_max_turns());
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        SingleGameData.save_pool(getApplicationContext(), poolletters);
        super.onBackPressed();  // optional depending on your needs
    }

    private boolean check_valid_integer(String value) {
        try {
            Integer.parseInt(value);
        }
        catch(Exception e) {
            return false;
        }
        int num = Integer.parseInt(value);
        if (num >= 0 && num <=20) { return true; }
        else { return false; }
    }

}
