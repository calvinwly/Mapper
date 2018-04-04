package com.example.mapper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_LOCATION = "com.example.mapper.LOCATION";
    private static List<String> history = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.edit_location);
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
        String[] suggestions = getResources().getStringArray(R.array.suggestions);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestions);
        editText.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (history.size() > 0) {
            TextView history_list = (TextView) findViewById(R.id.history_list);
            String display = TextUtils.join("\n", history);
            history_list.setText(display);
        }
    }

    public void searchLocation(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_location);
        String message = editText.getText().toString();
        history.add(message);
        if (history.size() > 10) {
            history.remove(0);
        }
        intent.putExtra(EXTRA_LOCATION, message);
        startActivity(intent);
    }
}
