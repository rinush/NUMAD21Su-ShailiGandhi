package edu.neu.madcourse.numad21su_shailigandhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        Button start = findViewById(R.id.start);
        Button lclt = findViewById(R.id.lclt);
        Button locator = findViewById(R.id.locator);
        TextView nameL = findViewById(R.id.nameL);
        TextView name = findViewById(R.id.name);
        TextView emailL = findViewById(R.id.emailL);
        TextView email = findViewById(R.id.email);

        nameL.setVisibility(View.INVISIBLE);
        name.setVisibility(View.INVISIBLE);
        emailL.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameL.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                emailL.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Clicky.class);
                startActivity(intent);
            }
        });

        lclt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinkCollector.class);
                startActivity(intent);
            }
        });

        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Locator.class);
                startActivity(intent);
            }
        });
    }
}