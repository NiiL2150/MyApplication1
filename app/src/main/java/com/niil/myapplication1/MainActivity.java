package com.niil.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Cat cat = new Cat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_2).setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            cat.setAge(5);
            cat.setName("Mew");
            intent.putExtra("cat", cat);
            startActivity(intent);
        });
    }

    public void onMyClick(View view){
        Toast toast = Toast.makeText(this, R.string.hello_world, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("cat", cat);
    }
}