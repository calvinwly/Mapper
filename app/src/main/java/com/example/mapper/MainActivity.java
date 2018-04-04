package com.example.mapper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_LOCATION = "com.example.myfirstapp.LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = (EditText) findViewById(R.id.edit_location);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchLocation(v);
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void searchLocation(View view) {
        Intent intent = new Intent(this, DisplayMapActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_location);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_LOCATION, message);
        startActivity(intent);
    }
}
