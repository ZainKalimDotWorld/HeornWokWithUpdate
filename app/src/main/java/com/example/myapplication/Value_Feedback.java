package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.preference.PreferenceManager;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Value_Feedback extends AppCompatActivity implements DroidListener {


    ImageView imageView1;
    ImageView imageView2;
    DroidNet mDroidNet;

    boolean zzz,zzz2,zzz3,zzz4,zzz5;
TextView time_in_is, time_in_is2;
    static   LabeledSwitch swToggle;


    boolean value,valusss;
    boolean shouldExecuteOnResume;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


if (MainActivity.orientation==Configuration.ORIENTATION_LANDSCAPE)
{
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    lockActivityOrientation(Value_Feedback.this);

    setContentView(R.layout.activity_value__feedback_landscape);
    shouldExecuteOnResume = false;
     zzz= getIntent().getBooleanExtra("boolvalues2" , false);

            mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);


    time_in_is = (TextView) findViewById(R.id.time_in_is);
    time_in_is2 = (TextView) findViewById(R.id.time_in_is2);

    swToggle = findViewById(R.id.iv_toggle);
    imageView1 = (ImageView) findViewById(R.id.imageView1);

    imageView1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
            boolean xyz = swToggle.isEnabled();
            intent.putExtra("bool_value" , xyz);
            startActivity(intent);
        }
    });


    imageView2 = (ImageView) findViewById(R.id.imageView2);

    swToggle.setOnToggledListener(new OnToggledListener() {
        @Override
        public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
            // Implement your switching logic here
            if (isOn)
            {
              time_in_is.setText("قائمة الطعام");
              time_in_is2.setText("ردود الفعل");

            } else {

                time_in_is.setText("FOOD MENU");
                time_in_is2.setText("FEEDBACK");

            }
        }

    });

    imageView2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
            boolean xyz3 = swToggle.isEnabled();
            intent.putExtra("bool_value3" , xyz3);
            startActivity(intent);
        }
    });
}



else
{
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    lockActivityOrientation(Value_Feedback.this);

    setContentView(R.layout.activity_value__feedback);
    imageView1 = (ImageView) findViewById(R.id.imageView1);

            mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);


    imageView1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
            intent.putExtra("PortraitMode" , "Portrait");
            startActivity(intent);
        }
    });

    imageView2 = (ImageView) findViewById(R.id.imageView2);


    imageView2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
            startActivity(intent);
        }
    });
}
    }

    @Override
    protected void onStop() {

        value= swToggle.isOn();
        Log.d("Resumeee" , ""+value);
        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putBoolean("Resumeee",value);
        editor.apply();

        super.onStop();

    }

    @Override
    protected void onResume()
    {

        if(shouldExecuteOnResume){

        }

        else
            {

                SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                boolean name = userDetails.getBoolean("Resumeee", false);

                if (name)
                {
                    Log.d("Resume22" , ""+name);

                    time_in_is.setText("قائمة الطعام");
                    time_in_is2.setText("ردود الفعل");

                    swToggle.setOn(name);
                }
                else
                {
                    Log.d("Resume223" , ""+name);

                    time_in_is.setText("FOOD MENU");
                    time_in_is2.setText("FEEDBACK");
                    swToggle.setOn(false);

                }
            shouldExecuteOnResume = true;

        }

        super.onResume();
    }
















    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
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
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {
//            netIsOn();
        } else {
            //no internet
            SweetAlertDialog pDialog = new SweetAlertDialog(Value_Feedback.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {

                    finishAffinity();
                }
            });

            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Internet Not Found");
            pDialog.setCancelable(true);
            pDialog.show();


        }
    }
}
