package com.example.projectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupHyperlink();
    }

    private void setupHyperlink(){
        TextView linkTextView = findViewById(R.id.textView12);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}