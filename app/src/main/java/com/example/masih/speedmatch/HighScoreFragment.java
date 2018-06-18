package com.example.masih.speedmatch;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HighScoreFragment extends Fragment {

    private TextView highScore;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.high_score_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        User bestUser = MySharePrefrences.getInstance(getActivity()).getBestUser();

        highScore.setText(getString(R.string.high_score,bestUser.getScore(),bestUser.getName()));

    }

    private void findViews(View view){
        highScore = (TextView)view.findViewById(R.id.high_score_textview);
    }
}
