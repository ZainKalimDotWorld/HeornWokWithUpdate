package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.splunk.mint.Mint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static android.R.layout.simple_spinner_item;
//import static com.example.myapplication.Feedback_Menu.swToggle;

//implements AdapterView.OnItemSelectedListener , DroidListener

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DroidListener {

    private ArrayList<String> names7 = new ArrayList<String>();

    String password;
    String spinnervalue;

    ImageView imageView4;

    private ArrayList<String> names8 = new ArrayList<String>();
    private ArrayList<String> names9 = new ArrayList<String>();

    Spinner spLeaveSubject2;
    public ArrayList<Pojo> lstAnime = new ArrayList<Pojo>();

    TelephonyManager mTelephonyManager;
    public String IMEI;


    public ArrayList<Pojo> lstAnime2 = new ArrayList<Pojo>();
    public ArrayList<Pojo> lstAnim3 = new ArrayList<Pojo>();

    SweetAlertDialog pDialogss;
    SweetAlertDialog pdialog;
    ArrayAdapter<String> spinnerArrayAdapter;
    OkHttpClient client;
    JSONObject json;
    static String value2, value7, value24;
    String v1;
    EditText customer_info;
    private DroidNet mDroidNet;
    Button signin;
    int v11;
    static String Portrait = "portrait";
    static String Landscape = "landscape";
    String imeiNumber;
    static int orientation;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orientation = this.getResources().getConfiguration().orientation;


        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //landscape
            setContentView(R.layout.activity_main_portrait);


            Mint.initAndStartSession(this.getApplication(), "8566b133");
            mDroidNet = DroidNet.getInstance();
            mDroidNet.addInternetConnectivityListener(this);

            client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
            lstAnime = new ArrayList<>();

            spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
            customer_info = (EditText) findViewById(R.id.customer_info);
            spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);


            deviceId();
            signin = (Button) findViewById(R.id.signin);
            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    loginapi_landscape();
                }
            });

            retreivebranches2();

        } else {

            //PORTRAIT
            setContentView(R.layout.activity_main);

            Mint.initAndStartSession(this.getApplication(), "8566b133");
            mDroidNet = DroidNet.getInstance();
            mDroidNet.addInternetConnectivityListener(this);

            client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
            lstAnime = new ArrayList<>();


            spLeaveSubject2 = (Spinner) findViewById(R.id.spLeaveSubject2);
            customer_info = (EditText) findViewById(R.id.customer_info);
            spLeaveSubject2.setOnItemSelectedListener(MainActivity.this);

            signin = (Button) findViewById(R.id.signin);
            imageView4 = (ImageView) findViewById(R.id.imageView4);


            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View layout = inflater.inflate(R.layout.custom_dialogss, null);

                    android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(MainActivity.this);

                    Button image_landscape = layout.findViewById(R.id.landscape);
                    Button image_portrait = layout.findViewById(R.id.portrait);
                    aDialog.setView(layout);
                    final android.app.AlertDialog ad = aDialog.create();
                    ad.show();


                    image_landscape.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            setContentView(R.layout.activity_main_portrait);
                            ad.dismiss();
                        }
                    });


                    image_portrait.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ad.dismiss();
                        }
                    });


                }
            });


            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    loginapiportrait();
                }
            });

            retreivebranches2();
        }

        // code for landscape mode
    }

    private void deviceId() {
        mTelephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
            return;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
                        return;
                    }
                    imeiNumber = mTelephonyManager.getDeviceId();
                    Log.d("IMIEE" , imeiNumber);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("IMEI_NUMBER",imeiNumber);
                    editor.apply();

                } else {
                    Toast.makeText(MainActivity.this,"Without permission we check",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



    private void loginapi_landscape() {

        SharedPreferences settings = getApplicationContext().getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        settings.edit().remove("Resumeee").clear().apply();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("IMEI_NUMBER", "");
        if(!name.equalsIgnoreCase(""))
        {
            IMEI = name;  /* Edit the value here*/
            Log.d("SharefIMEI" , IMEI);
        }

        if (IMEI.equals(""))
        {
            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Unable to Get IMEI");
            pDialog.setCancelable(true);
            pDialog.show();
        }

       else if (customer_info.getText().toString().equals(""))
        {
            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Please Fill Form Properly");
            pDialog.setCancelable(true);
            pDialog.show();

        }


        else
        {
            Log.d("DeviceIMEI", IMEI);

            pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
            Log.e("Editext_text", customer_info.getText().toString()           + "            " + v11);


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("BranchName", v1);
                jsonObject.put("BranchPin", customer_info.getText().toString());
                jsonObject.put("IMEI", IMEI);


                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.surveymenu.dwtdemo.com/api/Branch/Login").post(body).build();


                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pdialog);

                                Log.e("HttpService", "onFailure() Request was: " + call);
                                e.printStackTrace();
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {



                        String responses = response.body().string();
                        Log.e("response", "onResponse(): " + responses);

                        try {

                            json = new JSONObject(responses);

                            if (response.code() == 200)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pdialog);
                                    }
                                });

                                JSONObject json2 = json.getJSONObject("data");
                                value2 = json2.getString("accesstoken");
                                value7 = json2.getString("feedback_id");

                                Log.d("Valuesss" , value2);
                                Intent intent = new Intent(MainActivity.this, Value_Feedback.class);
                                startActivity(intent);
                            }

                            else if (response.code()==404)
                            {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Utilss.hideSweetLoader(pdialog);

                                                }
                                            });

                                            JSONObject json2 = json.getJSONObject("data");
                                            value24 = json2.getString("message");
                                            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                            pDialog.setTitleText(value24);
                                            pDialog.setCancelable(true);
                                            pDialog.show();



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }
                                });


                            }


                            else if (response.code()==401)
                            {
                                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        System.exit(0);
                                    }
                                });

                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Session Expired");
                                pDialog.setCancelable(true);
                                pDialog.show();

                            }
                        }

                        catch (JSONException e)
                        {

                        }

                    }
                });




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void loginapiportrait()
    {

                if (customer_info.getText().toString().equals(""))
        {
            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Please Fill Form Properly");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        else
        {
            pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");
            Log.e("Editext_text", customer_info.getText().toString()           + "            " + v11);


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("BranchName", v1);
                jsonObject.put("BranchPin", customer_info.getText().toString());

                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                okhttp3.Request request = new okhttp3.Request.Builder() .url("http://api.surveymenu.dwtdemo.com/api/Branch/Login").post(body).build();


                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Utilss.hideSweetLoader(pdialog);

                                Log.e("HttpService", "onFailure() Request was: " + call);
                                e.printStackTrace();
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {



                        String responses = response.body().string();
                        Log.e("response", "onResponse(): " + responses);

                        try {

                            json = new JSONObject(responses);

                            if (response.code() == 200)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Utilss.hideSweetLoader(pdialog);
                                    }
                                });

                                JSONObject json2 = json.getJSONObject("data");
                                value2 = json2.getString("accesstoken");
                                value7 = json2.getString("feedback_id");

                                Log.d("Valuesss" , value2);

                                Intent intent = new Intent(MainActivity.this, Value_Feedback.class);
                                intent.putExtra("Portrait_mode" , "portrait");
                                startActivity(intent);



                            }

                            else if (response.code()==404)
                            {


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Utilss.hideSweetLoader(pdialog);

                                                }
                                            });

                                            JSONObject json2 = json.getJSONObject("data");
                                            value24 = json2.getString("message");
                                            SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
                                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                            pDialog.setTitleText(value24);
                                            pDialog.setCancelable(true);
                                            pDialog.show();



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }
                                });


                            }


                            else if (response.code()==401)
                            {
                                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        System.exit(0);
                                    }
                                });

                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Session Expired");
                                pDialog.setCancelable(true);
                                pDialog.show();

                            }
                        }

                        catch (JSONException e)
                        {

                        }






                    }
                });




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    private void retreivebranches2()
    {

        pdialog = Utilss.showSweetLoader(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.surveymenu.dwtdemo.com/api/Branch/GetBranches", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("ResponseIs" , response);

                Log.d("strrrrr", ">>" + response);

                try {

                    JSONArray obj = new JSONArray(response);

                    lstAnime2 = new ArrayList<>();

                    for (int i = 0; i < obj.length(); i++) {

                        lstAnime2.clear();
                        Pojo playerModel7 = new Pojo();
                        JSONObject dataobj = obj.getJSONObject(i);

                        playerModel7.setValue(dataobj.getInt("value"));
                        playerModel7.setText(dataobj.getString("text"));
                        lstAnime2.add(playerModel7);

                    }

                    for (int i = 0; i < lstAnime2.size(); i++) {
                        names8.add(lstAnime2.get(i).getText());
                    }

                    spinnerArrayAdapter = new ArrayAdapter<>(MainActivity.this, simple_spinner_item, names8);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spLeaveSubject2.setAdapter(new NothingSelectedSpinnerAdapter(spinnerArrayAdapter, R.layout.contact_spinner_row_nothing_selected,MainActivity.this));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Utilss.hideSweetLoader(pdialog);
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        //displaying the error in toast if occurrs

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                Log.d("ErrorIs" , error.toString());

                                Utilss.hideSweetLoader(pdialog);
                            }
                        });
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("password", customer_info.getText().toString());
        outState.putString("mySpinner", v1);
        Log.d("SPinsss" , ""+ v1);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId()==R.id.spLeaveSubject2)
        {
            v1 = String.valueOf(spLeaveSubject2.getSelectedItem());
            Log.d("spinnervalue1" , ""+v1);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }

    @Override
    public void onInternetConnectivityChanged(final boolean isConnected) {


        if (isConnected) {
            //do Stuff with internet



        } else {

             pDialogss = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {


                    finishAffinity();
                }
            });

            pDialogss.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialogss.setTitleText("Internet Not Found");
            pDialogss.setCancelable(true);
            pDialogss.show();


        }
    }

}
