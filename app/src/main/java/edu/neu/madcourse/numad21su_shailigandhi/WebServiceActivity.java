package edu.neu.madcourse.numad21su_shailigandhi;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class WebServiceActivity extends AppCompatActivity {

    private EditText city;
    private EditText zipcode;
    private Button submit;
    private TextView temperature;
    private TextView feels_like;
    private TextView wind_speed;
    private TextView humidity;
    private ImageView loadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        city = (EditText)findViewById(R.id.city);
        zipcode = (EditText)findViewById(R.id.zipcode);
        submit = (Button)findViewById(R.id.submit);
        loadImage = (ImageView) findViewById(R.id.loadImg);
        loadImage.setVisibility(View.INVISIBLE);
        temperature = (TextView)findViewById(R.id.temperature);
        feels_like = (TextView)findViewById(R.id.feels_like);
        wind_speed = (TextView)findViewById(R.id.wind_speed);
        humidity = (TextView)findViewById(R.id.humidity);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage.setVisibility(View.VISIBLE);
                WebService webService = new WebService();

                String url = "";
                if (!city.getText().toString().isEmpty() && !zipcode.getText().toString().isEmpty()) {
                    url = "https://api.openweathermap.org/data/2.5/weather?q="+city.getText().toString()+"&zip="+zipcode.getText().toString()+"&units=metric&appid=83ca9f9376c527c8734248c866fdc494";
                }
                else{
                    url = "https://api.openweathermap.org/data/2.5/weather?q=Boston&units=metric&appid=83ca9f9376c527c8734248c866fdc494";
                }
                url = NetworkUtil.checkValid(url);
                if (url.equals("Invalid Input")) {
                    Toast.makeText(WebServiceActivity.this,"Invalid URL",Toast.LENGTH_SHORT).show();
                }
                else {
                    webService.execute(url);
                }
            }
        });

    }

    private class WebService extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            loadImage.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonObject = new JSONObject();
            URL url;
            String response;
            try {
                url = new URL(params[0]);
                response = NetworkUtil.getResponseFromURL(url);
                if (response.equals("No Result")) {
                    throw new IOException("No Result");
                }

                return new JSONObject(response);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);


                try {
                    if (!jsonObject.getString("cod").equals("200")) {
                        Toast.makeText(WebServiceActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                    }
                    else {
                        temperature.setText("Temperature: " + jsonObject.getJSONObject("main").getString("temp") + "C");
                        feels_like.setText("Feels Like Temperature: " + jsonObject.getJSONObject("main").getString("feels_like") + "C");
                        wind_speed.setText("Wind Speed: " + jsonObject.getJSONObject("wind").getString("speed") + "m/s");
                        humidity.setText("Humidity: " + jsonObject.getJSONObject("main").getString("humidity") + "%");
                    }
                } catch (JSONException e) {
                    Toast.makeText(WebServiceActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                }

            loadImage.setVisibility(View.INVISIBLE);
        }
    }

}
