package com.viewer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viewer.R;


public class MainActivity extends Activity {

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setTitle("Picture Viewer Login");
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.main_login_button);
        addActionListeners();
    }

    private void addActionListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AlbumsActivity.class));
            }
        });
    }
}
