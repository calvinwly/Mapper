package com.example.mapper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_LOCATION = "com.example.myfirstapp.LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchLocation(View view) {
        Intent intent = new Intent(this, DisplayMapActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_location);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_LOCATION, message);
        startActivity(intent);
    }
}
