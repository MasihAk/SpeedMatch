package com.example.masih.speedmatch;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class WhichoneGameFragment extends Fragment {

    public final int TOTAL_GAME_TIME = 10000;
    public final int LEFT_BUTTON = 1;
    public final int RIGHT_BUTTON = 2;
    public final int EQUAL_BUTTON = 0;

    private Button leftNumber;
    private Button rightNumber;
    private Button equal;
    private TextView userPoint;
    private TextView remainigTimeView;

    private int leftNumberInt;
    private int rightNumberInt;
    private int userPointInt = 0;
    private int whichPressed;
    private boolean timeRemainig = false;
    private String playerName;

    private CountDownTimer countDownTimer;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        readArgument();
        return inflater.inflate(R.layout.whichone_game_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        configureViews();

        configureLevel();



        countDownTimer = new CountDownTimer(TOTAL_GAME_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemainig = true;
                remainigTimeView.setText("Remainig Time : " + (int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeRemainig = false;
                remainigTimeView.setText(R.string.game_finished);

                updateHighscore();

            }
        };
        countDownTimer.start();

    }

    private void findViews(View view){
        leftNumber = (Button)view.findViewById(R.id.left_number_button);
        rightNumber = (Button)view.findViewById(R.id.right_number_button);
        equal = (Button)view.findViewById(R.id.equal_button);
        userPoint = (TextView)view.findViewById(R.id.user_points_text);
        remainigTimeView = (TextView)view.findViewById(R.id.remainig_time_text);

    }

    private void readArgument(){
        playerName = getArguments().getString("player_name", null);
    }

    private void updateHighscore(){
        User previousUser = MySharePrefrences.getInstance(getActivity()).getBestUser();
        if (previousUser == null || previousUser.getScore() < userPointInt ) {

            User newUser = new User();
            newUser.setName(playerName);
            newUser.setScore(userPointInt);
            MySharePrefrences.getInstance(getActivity()).putBestUser(newUser);
        }

    }

    private void configureViews(){
        leftNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichPressed = LEFT_BUTTON;
                evaluateAndContinue(whichPressed);
            }
        });

        rightNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichPressed = RIGHT_BUTTON;
                evaluateAndContinue(whichPressed);
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichPressed = EQUAL_BUTTON;
                evaluateAndContinue(whichPressed);
            }
        });
    }

    private void configureLevel(){
        leftNumberInt = generateButtonsInt();
        rightNumberInt = generateButtonsInt();
        leftNumber.setText(String.valueOf(leftNumberInt));
        rightNumber.setText(String.valueOf(rightNumberInt));
        userPoint.setText("your points is : " + userPointInt);
    }

    private int generateButtonsInt(){
        Random random = new Random();
        int number = random.nextInt();
        if(number < 0){
            number *= -1;
        }
        number %= 31;
        return number;
    }

    private void evaluateAndContinue(int whichPressed){

        if(whichPressed == LEFT_BUTTON && rightNumberInt < leftNumberInt){
            userPointInt++;
        }else if(whichPressed == RIGHT_BUTTON && rightNumberInt > leftNumberInt){
            userPointInt++;
        }else if(whichPressed == EQUAL_BUTTON && leftNumberInt == rightNumberInt){
            userPointInt++;
        }
        configureLevel();

    }
}
