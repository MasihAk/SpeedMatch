package com.example.masih.speedmatch;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetUserNameDialog extends Dialog {

    private Button confirmButton;
    private EditText playerName;
    private DialogNameListener listener;

    public GetUserNameDialog(@NonNull Context context, DialogNameListener listener) {
        super(context);

        this.listener =  listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_get_user_name);

        findViews();

        configureViews();

        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);

    }

    private void findViews(){

        confirmButton = (Button)findViewById(R.id.confirm_player_name_button);
        playerName = (EditText)findViewById(R.id.player_name);

    }

    private void configureViews(){
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DialogNameListener(playerName.getText().toString());
                dismiss();
            }
        });
    }

}
