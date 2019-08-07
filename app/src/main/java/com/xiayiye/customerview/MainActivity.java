package com.xiayiye.customerview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xiayiye.customerview.view.MyAnimView;

public class MainActivity extends Activity {

    private MyAnimView mavView;
    private EditText etInput;
    private boolean isCanvas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mavView = findViewById(R.id.my_view);
        etInput = findViewById(R.id.et_input);
    }

    public void start(View view) {
        mavView.setDrawText(etInput.getText().toString().trim());
        mavView.setCanvas(isCanvas);
        isCanvas = !isCanvas;
    }
}
