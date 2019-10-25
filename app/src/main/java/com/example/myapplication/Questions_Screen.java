package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Questions_Screen extends AppCompatActivity implements View.OnClickListener {

    TextView question;
    EditText mComments;
    int count = 0;
    MyData[] myData;
    String emails_intent;
    Button imageView2;
    private ImageView rate1, rate2, rate3, rate4, rate5, sendBtn;
    int[] ratings;
int contact_intent;
Boolean bool_value;
    String data, fbid, token, comments;
    private ImageView imageView22, imageView23, imageView24,imageView26;
    TextView textview44,textview445;
boolean toggle_home_values;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (MainActivity.orientation==Configuration.ORIENTATION_PORTRAIT)
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            lockActivityOrientation(Questions_Screen.this);
            //portrait
            setContentView(R.layout.activity_questions__screen);

            data = getIntent().getStringExtra("mylist");
            emails_intent = getIntent().getStringExtra("email");

            Log.e("My Json String", data);

            Gson gson = new Gson();
            myData = gson.fromJson(data, MyData[].class);
            ratings = new int[myData.length + 1];

            question = (TextView) findViewById(R.id.textview);
            question.setText(myData[count].getQuestion());

            imageView2 = findViewById(R.id.home_btn);
            imageView22 = (ImageView) findViewById(R.id.imageView22);
            imageView23 = (ImageView) findViewById(R.id.imageView24);
            imageView24 = (ImageView) findViewById(R.id.imageView26);
            imageView26 = (ImageView) findViewById(R.id.imageView27);

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(Questions_Screen.this , Value_Feedback.class);
                    startActivity(intent);
//                    finish();
                }
            });






            rate1 = (ImageView) findViewById(R.id.imageView1);
            rate2 = (ImageView) findViewById(R.id.imageView25);
            rate3 = (ImageView) findViewById(R.id.imageView3);
            rate4 = (ImageView) findViewById(R.id.imageView4);

            rate1.setOnClickListener(this);
            rate2.setOnClickListener(this);
            rate3.setOnClickListener(this);
            rate4.setOnClickListener(this);
        }

        else
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            lockActivityOrientation(Questions_Screen.this);

            setContentView(R.layout.activity_questions__screen_porttrait);

            String count_value = getIntent().getStringExtra("count");

            data = getIntent().getStringExtra("mylist");

            emails_intent = getIntent().getStringExtra("email");

            Log.e("My Json String", data);

            Gson gson = new Gson();
            myData = gson.fromJson(data, MyData[].class);
            ratings = new int[myData.length + 1];

            question = (TextView) findViewById(R.id.textview);

            textview445 = (TextView) findViewById(R.id.textview445);
            textview44= (TextView) findViewById(R.id.textview44);
            textview445.setText(""+i);

            question.setText(myData[count].getQuestion());

            imageView2 = findViewById(R.id.home_btn);
            imageView22 = (ImageView) findViewById(R.id.imageView22);
            imageView23 = (ImageView) findViewById(R.id.imageView24);
            imageView24 = (ImageView) findViewById(R.id.imageView26);
            imageView26 = (ImageView) findViewById(R.id.imageView28);


            if (Value_Feedback.swToggle.isOn())
            {
                imageView2.setText("الصفحة الرئيسية");
                imageView22.setImageResource(R.mipmap.very_gud_arabic);
                imageView23.setImageResource(R.mipmap.gud_arabic);
                imageView24.setImageResource(R.mipmap.fair_arabic);
                imageView26.setImageResource(R.mipmap.poor_arabic);
            }

            else
            {
                imageView2.setText("HOME");
                imageView22.setImageResource(R.mipmap.very_gud);
                imageView23.setImageResource(R.mipmap.gud);
                imageView24.setImageResource(R.mipmap.fair_btn);
                imageView26.setImageResource(R.mipmap.poor_btn);
            }


            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(Questions_Screen.this , Value_Feedback.class);
                    startActivity(intent);
                }
            });






            rate1 = (ImageView) findViewById(R.id.imageView1);
            rate2 = (ImageView) findViewById(R.id.imageView25);
            rate3 = (ImageView) findViewById(R.id.imageView3);
            rate4 = (ImageView) findViewById(R.id.imageView4);

            rate1.setOnClickListener(this);
            rate2.setOnClickListener(this);
            rate3.setOnClickListener(this);
            rate4.setOnClickListener(this);
        }

    }

    public void loadQuestion(int c) {
        int Length = myData.length;
        count++;
        if (count < Length) {
            question.setText(myData[count].getQuestion());
            i++;
            textview445.setText(""+i);


        } else {

            Intent intent = new Intent(Questions_Screen.this, Feedback_Submit.class);
            intent.putExtra("Total_Things", myData.length);
            intent.putExtra("stock_list", data);
            intent.putExtra("Total_Things_Data", ratings);
            intent.putExtra("Email_Value", emails_intent);
            intent.putExtra("Contact_Value", 0);
            intent.putExtra("Again_Bool_Value", bool_value);

            Log.d("Array", "" + ratings);
            startActivity(intent);


        }


    }


    public static void lockActivityOrientation(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        int rotation = display.getRotation();
        int height;
        int width;
        Point size = new Point();
        display.getSize(size);
        height = size.y;
        width = size.x;
        switch (rotation) {
            case Surface.ROTATION_90:
                if (width > height)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                break;
            case Surface.ROTATION_180:
                if (height > width)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                break;
            case Surface.ROTATION_270:
                if (width > height)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            default:
                if (height > width)
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                else
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageView1:
                ratings[count] = 1;
                break;
            case R.id.imageView25:
                ratings[count] = 2;
                break;
            case R.id.imageView3:
                ratings[count] = 3;
                break;
            case R.id.imageView4:
                ratings[count] = 4;
                break;

        }
        loadQuestion(count);
    }


    public void onBackPressed() {

    }
}
