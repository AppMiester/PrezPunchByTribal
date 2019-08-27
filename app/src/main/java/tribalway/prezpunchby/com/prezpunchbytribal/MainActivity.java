package tribalway.prezpunchby.com.prezpunchbytribal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean leavingApp = true;
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

     static Intent music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

         music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
    }


    public void goToSelectiom(View view){
        leavingApp = false;
        Intent intent = new Intent(this,Selection.class);
        startActivity(intent);
    }

    public void goToKnockOutScore(View view){
        leavingApp = false;
        Intent intent = new Intent(this,KnockoutScore.class);
        startActivity(intent);
    }



    @Override
    public void onPause(){


        super.onPause();
        if(leavingApp) {
            Log.e("music", "onPause leaving app ran");
            stopService(music);
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        startService(music);
        leavingApp = true;

    }
}
