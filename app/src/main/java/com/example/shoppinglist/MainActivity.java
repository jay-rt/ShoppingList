package com.example.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private TextView mTextHeaderMain, mListItemOne, mListItemTwo, mListItemThree;
    private Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextHeaderMain = findViewById(R.id.text_header_main);
        mListItemOne = findViewById(R.id.text_list_item_1);
        mListItemTwo = findViewById(R.id.text_list_item_2);
        mListItemThree = findViewById(R.id.text_list_item_3);
        mSend = findViewById(R.id.button_add);

        mSend.setOnClickListener(this);

        //Restore the state
        // See onSaveInstanceState() for what gets saved.
        if(savedInstanceState != null){
            boolean isVisibleMain =
                    savedInstanceState.getBoolean("reply_visible_main");
            boolean isVisibleTwo =
                    savedInstanceState.getBoolean("reply_visible_item_two");
            boolean isVisibleThree =
                    savedInstanceState.getBoolean("reply_visible_item_three");

            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.
            if(isVisibleMain){
                Log.d(LOG_TAG,"Data Retrieved");
                mTextHeaderMain.setVisibility(View.VISIBLE);
                mListItemOne.setText(savedInstanceState.getString("reply_item_one"));
                mListItemOne.setVisibility(View.VISIBLE);
                if(isVisibleTwo){
                    mListItemTwo.setText(savedInstanceState.getString("reply_item_two"));
                    mListItemTwo.setVisibility(View.VISIBLE);
                    if(isVisibleThree){
                        mListItemThree.setText(savedInstanceState.getString("reply_item_three"));
                        mListItemThree.setVisibility(View.VISIBLE);
                    }
                }
            }
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // If the heading is visible, message needs to be saved.
        // Otherwise we're still using default layout.
        if (mTextHeaderMain.getVisibility() == View.VISIBLE) {
            Log.d(LOG_TAG,"Data Saved");
            outState.putBoolean("reply_visible_main", true);
            outState.putString("reply_item_one",mListItemOne.getText().toString());
            if(mListItemTwo.getVisibility() == View.VISIBLE){
                outState.putBoolean("reply_visible_item_two", true);
                outState.putString("reply_item_two",mListItemTwo.getText().toString());
                if(mListItemThree.getVisibility() == View.VISIBLE){
                    outState.putBoolean("reply_visible_item_three", true);
                    outState.putString("reply_item_three",mListItemThree.getText().toString());
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_MESSAGE);
                mTextHeaderMain.setVisibility(View.VISIBLE);

                if(mListItemOne.getVisibility() != View.VISIBLE){
                    mListItemOne.setText(reply);
                    mListItemOne.setVisibility(View.VISIBLE);
                }else if(mListItemTwo.getVisibility() != View.VISIBLE){
                    mListItemTwo.setText(reply);
                    mListItemTwo.setVisibility(View.VISIBLE);
                }else{
                    mListItemThree.setText(reply);
                    mListItemThree.setVisibility(View.VISIBLE);
                }
            }
        }
    }



    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG,"Button Clicked");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent,TEXT_REQUEST);
    }
}