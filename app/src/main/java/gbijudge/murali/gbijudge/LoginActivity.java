package gbijudge.murali.gbijudge;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


import org.json.JSONArray;
import org.json.JSONObject;

import gbijudge.murali.gbijudge.Services.AlertDialogManager;
import gbijudge.murali.gbijudge.Services.ConnectionDetector;
import gbijudge.murali.gbijudge.Services.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView closebutton;
    Button login, Getpin;
    private ProgressDialog pDialog;
    public static TextInputLayout phoneNumber,pinNumber,forgotPin;
    private String phone,pin;
    private AlertDialogManager alert = new AlertDialogManager();
    private ConnectionDetector cd;
    private SharedPreferences.Editor editor;
    private SessionManager session;
    public static String registerationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Session Manager
        session = new SessionManager(getApplicationContext());

        cd = new ConnectionDetector(getApplicationContext());
        closebutton = (ImageView)findViewById(R.id.closeForgot);
        phoneNumber = (TextInputLayout)findViewById(R.id.PhoneNum);
        pinNumber = (TextInputLayout)findViewById(R.id.PinNum);
        forgotPin = (TextInputLayout)findViewById(R.id.forgotpin);
        login = (Button)findViewById(R.id.signin);
        login.setOnClickListener(this);
        Getpin = (Button)findViewById(R.id.request);
        Getpin.setOnClickListener(this);
        //After Clicking Register Button Navigating Register Activity....
        try{
            //This IS For FofgotLayout....
            findViewById(R.id.forgotpassword).setOnClickListener(this);
            findViewById(R.id.closeForgot).setOnClickListener(this);
        }catch (NullPointerException e){

        }
    }
    public void timerDelayRemoveDialog(long time, final ProgressDialog d){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                d.dismiss();
            }
        }, time);

    }
    private void login(final String phone, final String pin) {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Attempting Login");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(getApplicationContext())
                .load("POST", "http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/judgelogin")
                .setBodyParameter("mobile",phone)
                .setBodyParameter("pin",pin)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        if (e != null) {

                        } else {
                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                    if(pDialog.isShowing())
                                        pDialog.dismiss();
                                    session.createLoginSession(phone,pin);
                                JSONArray array = jSONObject.getJSONArray("gbis");
                                for(int i=0;i<array.length();i++){
                                    JSONObject object = array.getJSONObject(i);
                                    if(object.has("_id")){
                                        registerationId = object.getString("_id");
                                    }

                                }

                                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    editor = settings.edit();
                                    editor.putString("adminid", registerationId);
                                    editor.commit();
                                    Toast.makeText(getApplicationContext(), "Welcome To GBI Connect", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    // Add new Flag to start new Activity
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                    finish();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                })
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!cd.isNetworkOn(getApplicationContext())) {
            // Internet Connection is Present
            // make HTTP requests
            alert.showAlertDialog1(LoginActivity.this, "No Internet Connection","You don't have internet connection.", false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin:
                phone =  phoneNumber.getEditText().getText().toString();
                pin = pinNumber.getEditText().getText().toString();
                View view = LoginActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                login(phone,pin);
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

                break;
//            case R.id.request:
//                String Forgot = forgotPin.getEditText().getText().toString();
//                forgotPin(Forgot);
//                break;
//            case R.id.forgotpassword:
//                View forgotLayout = findViewById(R.id.forgotLayout);
//                //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
//                forgotLayout.setVisibility(View.VISIBLE);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        findViewById(R.id.SignInLayout).setVisibility(View.GONE);
//                    }
//                }, 500);
//                break;
//            case R.id.closeForgot:
//                View forgotLayoutt = findViewById(R.id.forgotLayout);
//                //forgotLayoutt.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
//                forgotLayoutt.setVisibility(View.GONE);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        findViewById(R.id.SignInLayout).setVisibility(View.VISIBLE);
//                    }
//                }, 200);
//                break;
        }
    }
}
