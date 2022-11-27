package com.niil.myapplication1;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    Cat cat = new Cat();
    Random random = new Random();
    int MAX = 20;
    int count = 0;
    int front_color = 0;
    int back_color = 0;
    String myFont = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        cat = (Cat) getIntent().getSerializableExtra("cat");
        setTitle(String.format(getString(R.string.cat_tostring), cat.getName(), cat.getAge()));
        Button button = findViewById(R.id.btn_random);
        findViewById(R.id.btn_return).setOnClickListener(view -> finish());
        button.setOnClickListener(view -> {
            setButton();
            count++;
            setTitle(String.format(getString(R.string.clicked_out_max), count, MAX));
            if(count >= MAX){
                button.setEnabled(false);
            }
        });
    }

    int randomColor(){
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    void setTitle(String title){
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        Button button = findViewById(R.id.btn_random);
        outState.putInt("back_color", back_color);
        outState.putInt("front_color", front_color);
        outState.putFloat("text_size", button.getTextSize());
        outState.putString("font", myFont);
    }

    void setButton(Button button, int color1, int color2, float fontSize, Typeface font){
        back_color = color1;
        button.setBackgroundColor(color1);
        front_color = color2;
        button.setTextColor(color2);
        button.setTextSize(fontSize);
        button.setTypeface(font);
    }

    void setButton(int color1, int color2, float fontSize, Typeface font){
        Button button = findViewById(R.id.btn_random);
        setButton(button, color1, color2, fontSize, font);
    }

    void setButton(){
        Typeface typeface = getTypeface();
        setButton(randomColor(), randomColor(), random.nextFloat()*24f+8f, typeface);
    }

    Typeface getTypeface(){
        AssetManager assetManager = getAssets();
        String[] files = new String[0];
        try {
            files = assetManager.list("fonts");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String font = files[random.nextInt(files.length)];
        this.myFont = font;
        return Typeface.createFromAsset(assetManager, "fonts/" + font);
    }

    Typeface getTypeface(String fontName){
        AssetManager assetManager = getAssets();
        myFont = fontName;
        return Typeface.createFromAsset(assetManager, "fonts/" + fontName);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count", 0);
        Button button = findViewById(R.id.btn_random);

        int backColor = savedInstanceState.getInt("back_color");
        int frontColor = savedInstanceState.getInt("front_color");
        int textSize = savedInstanceState.getInt("text_size");
        String font1 = savedInstanceState.getString("font");

        if(count > 0){
            setTitle(String.format(getString(R.string.clicked_out_max), count, MAX));
        }

        setButton(button, backColor, frontColor, textSize, getTypeface(font1));
    }
}