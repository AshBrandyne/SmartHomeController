package com.algonquin.cst2335.smarthomecontroller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.algonquin.cst2335.smarthomecontroller.R.id.homeJustRightButton;

public class Weather extends AppCompatActivity {
    ProgressBar progBar;

    Bitmap pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Button done =  (Button) findViewById(homeJustRightButton);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        progBar.setVisibility(View.VISIBLE);
        ForecastQuery fq = new ForecastQuery();
        fq.execute();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Weather.this, HomeSubMenu.class);
                startActivity(intent);
            }

        });


    }

    public class ForecastQuery extends AsyncTask<String, Integer, String> {

        String min;
        String max;
        String temp;
        String iconName;

        String urlString = (String) "http://api.openweathermap.org/data/2.5/weather?q=ottawa%2Cca&APPID=205978c99c4c8f8643000a424c09d0f6&mode=xml&units=metric";


        public String doInBackground(String... args) {


            try {

                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.connect();

                InputStream istream = urlConnection.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(istream, "UTF8");
                boolean finished = false;
                int type = XmlPullParser.START_DOCUMENT;

                while (type != XmlPullParser.END_DOCUMENT) {

                    switch (type) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            finished = true;
                            break;
                        case XmlPullParser.START_TAG:
                            String name = xpp.getName();
                            if (name.equals("temperature")) {
                                min = xpp.getAttributeValue(null, "min");

                                publishProgress(25);
                                max = xpp.getAttributeValue(null, "max");

                                publishProgress(50);

                                temp = xpp.getAttributeValue(null, "value");

                                publishProgress(75);
                            }

                            if (name.equals("weather")) {

                                iconName = xpp.getAttributeValue(null, "icon");
                            }

                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            break;
                    }
                    type = xpp.next(); //advances to next xml event
                }
                type = xpp.next();
            } catch (Exception e) {
                Log.e("XML PARSING", e.getMessage());
            }
            try {
                if (fileExistance(String.valueOf(iconName + ".png"))) {

                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(Weather.this.getFilesDir() + "/" + iconName + ".png");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    pic = BitmapFactory.decodeStream(fis);
                } else {
                    URL url2 = new URL("http://openweathermap.org/img/w/" + iconName + ".png");
                    HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
                    urlConnection2.setReadTimeout(10000);
                    urlConnection2.setConnectTimeout(15000);
                    urlConnection2.setRequestMethod("GET");
                    urlConnection2.setDoInput(true);
                    urlConnection2.connect();

                    InputStream instream = urlConnection2.getInputStream();
                    pic = BitmapFactory.decodeStream(instream);
                    FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                    pic.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();


                }

            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
            publishProgress(100);
            return null;
        }

        //catch(Exception e)

        public boolean fileExistance(String fname) {

            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        public void onPostExecute(String result) {
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
            imageView2.setImageBitmap(pic);

            TextView currentTemp = (TextView) findViewById(R.id.currentTemp);
            currentTemp.setText("Current Temperature: " + temp);
            TextView minTemp = (TextView) findViewById(R.id.minTemp);
            minTemp.setText("Low: " + min);
            TextView maxTemp = (TextView) findViewById(R.id.maxTemp);
            maxTemp.setText("High: " + max);


            progBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //if it's help, show the help screen!
        if (menuItem.getItemId() ==  R.id.action_help) {
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

            builder.setTitle("Weather Activity");
            builder.setMessage("Activity made by Tyler Woyiwada" +
                    " : Check the weather outside");
            builder .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            final android.app.AlertDialog alert = builder.create();
            alert.show();

        } else {
            Intent intent = new Intent();
            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    intent = new Intent(this, HomeSubMenu.class);
                    startActivity(intent);
                    break;
                case R.id.action_sofa:
                    intent = new Intent(this, LRHome.class);
                    startActivity(intent);
                    break;
                case R.id.action_fridge:
                    intent = new Intent(this, KitchenListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.action_car:
                    intent = new Intent(this, AutomobileListActivity.class);
                    startActivity(intent);
                    break;
            }

        }
        return true;
    }
}