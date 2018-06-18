package com.example.masih.speedmatch;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WhichOneIsBiggerActivity extends AppCompatActivity {

    public Button whichoneStartGameButton;
    public Button whichoneHighScoreButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whichone_is_bigger);

        findViews();

        configure();
    }

    public void findViews(){
        whichoneStartGameButton = (Button)findViewById(R.id.whichone_start_game_button);
        whichoneHighScoreButton = (Button)findViewById(R.id.whichone_highscore_button);
    }

    public void configure(){
        whichoneStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUserNameDialog getUserNameDialog = new GetUserNameDialog(
                        WhichOneIsBiggerActivity.this,
                        new DialogNameListener() {
                            @Override
                            public void DialogNameListener(String playerName) {
                                Log.d("TAG","Player Name : " + playerName );


                                WhichoneGameFragment whichoneGameFragment = new WhichoneGameFragment();

                                Bundle bundle = new Bundle();
                                bundle.putString("player_name", playerName);
                                whichoneGameFragment.setArguments(bundle);

                                getFragmentManager().beginTransaction()
                                        .add(R.id.whichone_fragment_container,whichoneGameFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                );
                getUserNameDialog.show();

            }
        });

        whichoneHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighScoreFragment highScoreFragment = new HighScoreFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.whichone_fragment_container, highScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
