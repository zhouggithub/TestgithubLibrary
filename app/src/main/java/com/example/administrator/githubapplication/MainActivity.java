package com.example.administrator.githubapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhouganglibrary.GithubClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GithubClass.showToast(this);
    }
}
