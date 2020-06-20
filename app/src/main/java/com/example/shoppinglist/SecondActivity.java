package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.android.shoppinglist.extra.MESSAGE";
    private TextView mTextHeaderSec;
    private Button mButtonOne, mButtonTwo, mButtonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextHeaderSec = findViewById(R.id.text_header_sec);
        mButtonOne = findViewById(R.id.button_one);
        mButtonTwo = findViewById(R.id.button_two);
        mButtonThree = findViewById(R.id.button_three);

        mButtonOne.setOnClickListener(this);
        mButtonTwo.setOnClickListener(this);
        mButtonThree.setOnClickListener(this);

        Intent intent = getIntent();
    }

    @Override
    public void onClick(View v) {

        Intent replyIntent = new Intent(this, MainActivity.class);
        String message = "";

        switch (v.getId()){
            case R.id.button_one:
                message = getResources().getString(R.string.item_1);
                break;

            case R.id.button_two:
                message = getResources().getString(R.string.item_2);
                break;

            case R.id.button_three:
                message = getResources().getString(R.string.item_3);
                break;

        }

        replyIntent.putExtra(EXTRA_MESSAGE, message);
        setResult(RESULT_OK,replyIntent);
        Log.d(LOG_TAG, "End SecondActivity");
        finish();
    }
}