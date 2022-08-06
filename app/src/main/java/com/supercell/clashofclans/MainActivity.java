package com.supercell.clashofclans;
import android.app.Activity ;
import android.app.admin.DevicePolicyManager ;
import android.content.ComponentName ;
import android.content.Context ;
import android.content.Intent ;
import android.media.MediaPlayer;
import android.os.Bundle ;
import android.os.Handler;
import android.view.View ;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button ;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast ;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    static final int RESULT_ENABLE = 1 ;
    DevicePolicyManager deviceManger ;
    ComponentName compName ;
    Handler handler;
    ImageView lg ;
    ImageButton cancel,ok,login;
    ConstraintLayout main;
    RelativeLayout dlg;
    MediaPlayer mp;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super .onCreate(savedInstanceState) ;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mp=MediaPlayer.create(MainActivity.this,R.raw.sound);
        ins();
        deviceManger = (DevicePolicyManager)
        getSystemService(Context.DEVICE_POLICY_SERVICE ) ;
        compName = new ComponentName( this, DeviceAdmin.class ) ;
        boolean active = deviceManger .isAdminActive( compName ) ;
    }
    public void ins(){
        setContentView(R.layout.activity_spalsh);
        lg=findViewById(R.id.lg);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mp.start();
                lg.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lg.setVisibility(View.GONE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setContentView(R.layout.main);
                                main=findViewById(R.id.main);
                                login=findViewById(R.id.login);
                                cancel=findViewById(R.id.cancel);
                                dlg=findViewById(R.id.dlg);
                                ok=findViewById(R.id.ok);
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dlg.setVisibility(View.GONE);
                                        login.setVisibility(View.VISIBLE);
                                        main.setBackgroundResource(R.drawable.bk);
                                    }
                                });
                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                    }
                                });
                                login.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ins();
                                    }
                                });


                            }
                        },200);

                    }
                },2000);

            }
        },900);

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent
            data) {
        super .onActivityResult(requestCode , resultCode , data) ;
        switch (requestCode) {
            case RESULT_ENABLE :
                if (resultCode == Activity.RESULT_OK ) {
                } else {
                    Toast.makeText (getApplicationContext() , "Failed!" ,
                            Toast.LENGTH_SHORT ).show() ;
                }
                return;
        }
    }
}