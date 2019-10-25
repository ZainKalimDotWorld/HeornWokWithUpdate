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
//     Log.d("LogValue" , ""+zzz);




            mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);


    time_in_is = (TextView) findViewById(R.id.time_in_is);
    time_in_is2 = (TextView) findViewById(R.id.time_in_is2);

    swToggle = findViewById(R.id.iv_toggle);


    if (savedInstanceState != null) {

        Toast.makeText(this, "fff", Toast.LENGTH_SHORT).show();
    }


//    if (zzz)
//    {
//        swToggle.setOn(zzz);
//    }
//
//    else
//    {
//        swToggle.setOn(false);
//    }



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















//        int currentOrientation = this.getResources().getConfiguration().orientation;
//        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT)
//        {
//
//        }
//
//
//                else
//        {
//
//
//        }




//        int orientation = this.getResources().getConfiguration().orientation;
//        if (orientation==Configuration.ORIENTATION_PORTRAIT)
//        {
//            setContentView(R.layout.activity_value__feedback);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    intent.putExtra("PortraitMode" , "Portrait");
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//        }
//
//        else
//        {
//            setContentView(R.layout.activity_value__feedback_landscape);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    intent.putExtra("LandscapeMode" , "Landscape");
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//
//        }
//
//
//        mDroidNet = DroidNet.getInstance();
//        mDroidNet.addInternetConnectivityListener(this);

    }


//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
////        setContentView(R.layout.activity_main__menu_screens2);
//        super.onConfigurationChanged(newConfig);
//    }



//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//
////        savedInstanceState.putBoolean("titleText", swToggle.isOn());
//
//        value= swToggle.isOn();
//        Log.d("Resumeee" , ""+value);
//
////        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//        // etc.
//    }
//


//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        isFinishing();
//         value = swToggle.isEnabled();
//        Log.d("Vluesss" , ""+value);
////        Toast.makeText(this, "Hi2", Toast.LENGTH_SHORT).show();
//
//    }
//
//

//
//



    @Override
    protected void onStop() {

        value= swToggle.isOn();
        Log.d("Resumeee" , ""+value);

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putBoolean("Resumeee",value);
        editor.apply();

//
//        Intent va = getIntent().putExtra("vals" , value);
//        Toast.makeText(this, "Saved..!!", Toast.LENGTH_SHORT).show();

        super.onStop();

    }

    @Override
    protected void onResume()
    {

//        prefs.edit().putString("titleText", titleEditText.getText().toString()).apply();
//        prefs.edit().putString("notesText", notesEditText.getText().toString()).apply();


        if(shouldExecuteOnResume){
            // Your onResume Code Here
//            Toast.makeText(this, "Resumed", Toast.LENGTH_SHORT).show();
        }

        else
            {

                SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                boolean name = userDetails.getBoolean("Resumeee", false);

                if (name)
                {
//                    Toast.makeText(this, "ssss111", Toast.LENGTH_SHORT).show();
                    Log.d("Resume22" , ""+name);

                    time_in_is.setText("قائمة الطعام");
                    time_in_is2.setText("ردود الفعل");

                    swToggle.setOn(name);
                }
                else
                {
//                    Toast.makeText(this, "ssss11122", Toast.LENGTH_SHORT).show();
                    Log.d("Resume223" , ""+name);

                    time_in_is.setText("FOOD MENU");
                    time_in_is2.setText("FEEDBACK");
                    swToggle.setOn(false);

                }


////                savedInstanceState.getString("titleText")
//            Toast.makeText(this, "ResumedNo", Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "ssss", Toast.LENGTH_SHORT).show();
//                if (value)
//                {
//
//
//                }
//
//                else
//                {
//
//
//                }
            shouldExecuteOnResume = true;

        }




//        zzz= getIntent().getBooleanExtra("boolvalues2" , false);
//        zzz2= getIntent().getBooleanExtra("boolvalues4" , false);
//
//        zzz3= getIntent().getBooleanExtra("boolvalues5" , false);
//
//        zzz4= getIntent().getBooleanExtra("toggle_value7" , false);
//        zzz5= getIntent().getBooleanExtra("toggle_value78" , false);
//
//
//
//        if (zzz)
//        {
//            swToggle.setOn(zzz);
//            Log.d("LogValue111" , ""+zzz);
//
//            time_in_is.setText("قائمة الطعام");
//            time_in_is2.setText("ردود الفعل");
//
//        }
//
//        else if (zzz2)
//        {
//            swToggle.setOn(zzz2);
//            Log.d("LogValue111" , ""+zzz2);
//
//            time_in_is.setText("قائمة الطعام");
//            time_in_is2.setText("ردود الفعل");
//        }
//
//        else if (zzz3)
//        {
//            swToggle.setOn(zzz3);
//            Log.d("LogValue111" , ""+zzz3);
//            time_in_is.setText("قائمة الطعام");
//            time_in_is2.setText("ردود الفعل");
//        }
//
//        else if (zzz4)
//        {
//            swToggle.setOn(zzz4);
//            Log.d("LogValue111" , ""+zzz4);
//            time_in_is.setText("قائمة الطعام");
//            time_in_is2.setText("ردود الفعل");
//        }
//
//        else if (zzz5)
//        {
//            swToggle.setOn(zzz5);
//            Log.d("LogValue111" , ""+zzz5);
//            time_in_is.setText("قائمة الطعام");
//            time_in_is2.setText("ردود الفعل");
//        }
//
//
//        else
//        {
//            swToggle.setOn(false);
//            Log.d("LogValue222" , ""+zzz);
//            time_in_is.setText("FOOD MENU");
//            time_in_is2.setText("FEEDBACK");
//        }

        super.onResume();
    }
















    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }

//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
////        setContentView(R.layout.activity_main);
//
//
//
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//
////            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_value__feedback_landscape);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//
//
//
//
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
////            createVerticalLayout();
//
//            //            Toast.makeText(this, "2288", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_value__feedback);
//
//            imageView1 = (ImageView) findViewById(R.id.imageView1);
//
//            imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(Value_Feedback.this , Main_MenuScreen.class);
//                    startActivity(intent);
//                }
//            });
//
//            imageView2 = (ImageView) findViewById(R.id.imageView2);
//
//
//            imageView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    Intent intent = new Intent(Value_Feedback.this , Feedback_Menu.class);
//                    startActivity(intent);
//                }
//            });
//
//
//
//
//        }
//
//    }

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
            //do Stuff with internet
//            netIsOn();
        } else {
            //no internet

//            Toast.makeText(this, "Internet Off..!!", Toast.LENGTH_SHORT).show();

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
