package com.example.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText cityEditText;
    private Button getWeatherButton;
    private TextView weatherText;
    private ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = findViewById(R.id.cityEditText);
        getWeatherButton = findViewById(R.id.getWeatherButton);
        weatherText = findViewById(R.id.weatherText);
        weatherIcon = findViewById(R.id.weatherIcon);

        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityEditText.getText().toString();
                new GetWeatherTask().execute(city);
            }
        });
    }

    private class GetWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String city = strings[0];
            String apiKey = "YOUR_API_KEY";
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                return jsonObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                JsonObject jsonObject = JsonParser.parseString(s).getAsJsonObject();
                JsonObject main = jsonObject.getAsJsonObject("main");
                JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();

                String temperature = main.get("temp").getAsString();
                String description = weather.get("description").getAsString();
                String iconCode = weather.get("icon").getAsString();

                weatherText.setText("Temperature: " + temperature + "\nDescription: " + description);
                Picasso.get().load("http://openweathermap.org/img/w/" + iconCode + ".png").into(weatherIcon);
            }
        }
    }
}
