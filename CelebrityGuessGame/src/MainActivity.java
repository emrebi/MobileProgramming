package com.example.celebrityguessgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView celebrityImage;
    private EditText guessEditText;
    private Button submitGuessButton;
    private TextView scoreText;
    private List<Celebrity> celebrityList;
    private int currentScore = 0;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityImage = findViewById(R.id.celebrityImage);
        guessEditText = findViewById(R.id.guessEditText);
        submitGuessButton = findViewById(R.id.submitGuessButton);
        scoreText = findViewById(R.id.scoreText);

        String json = loadJSONFromAsset();
        celebrityList = new Gson().fromJson(json, CelebrityList.class).celebrities;

        showNextCelebrity();

        submitGuessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = guessEditText.getText().toString().trim();
                if (guess.equalsIgnoreCase(celebrityList.get(currentIndex).name)) {
                    currentScore++;
                    scoreText.setText("Score: " + currentScore);
                }
                showNextCelebrity();
            }
        });
    }

    private void showNextCelebrity() {
        if (currentIndex < celebrityList.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
        Picasso.get().load(celebrityList.get(currentIndex).imageUrl).into(celebrityImage);
        guessEditText.setText("");
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("celebrities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private class Celebrity {
        String name;
        String imageUrl;
    }

    private class CelebrityList {
        List<Celebrity> celebrities = new ArrayList<>();
    }
}
