package tribalway.prezpunchby.com.prezpunchbytribal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class Punch extends AppCompatActivity {


    static int victimHeight = 0;
    static int amount = 0;
    int punchCount= 0;
    boolean hasSavedRan = false;
    MediaPlayer punchSound;
    MediaPlayer pain;
    MediaPlayer make;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;

        stopService(MainActivity.music);

        setVictim(Selection.prez);
        setVisibilty(false);

    }
    public void setVictim(int prez) {

        ImageView victim = (ImageView) findViewById(R.id.victim);

        switch (prez) {

            case R.id.dump:
                victim.setImageResource(R.drawable.dump);
                 make = MediaPlayer.create(this, R.raw.makeamericagreat);
                make.start();
                if (!make.isPlaying())
                    make.release();

                break;

            case R.id.bama:
                victim.setImageResource(R.drawable.bama);
                break;
        }
    }


    public void punch(View view) {



        if(punchSound != null){
            punchSound.release();
        }


        if(!make.isPlaying() && punchCount <35) {

            ImageView victim = (ImageView) findViewById(R.id.victim);

            victimHeight = victim.getHeight();

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int screenHeight = size.y;

            ViewGroup.MarginLayoutParams viewParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

            amount = screenHeight - (viewParams.bottomMargin + victimHeight);
            viewParams.setMargins(viewParams.leftMargin, viewParams.topMargin, viewParams.rightMargin, viewParams.bottomMargin + amount / 2);
            view.setLayoutParams(viewParams);

            PunchDown halfStep = new PunchDown(view, viewParams) {
                @Override
                public void run() {
                    params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin + amount / 2);
                    gloveView.setLayoutParams(params);
                }

            };

            new Handler().postDelayed(halfStep, 80);


            punchSound = MediaPlayer.create(this, R.raw.punchsound);
            punchSound.start();

            punchCount++;
            showDamage();


            new Handler().postDelayed(new PunchDown(view, viewParams), 150);
        }

        if(punchCount == 35){
            setVisibilty(true);
            saveScore();
        }

    }


    public void showDamage(){
        ImageView victim = (ImageView) findViewById(R.id.victim);

        switch(punchCount){

            case 10:
                if(Selection.prez == R.id.dump){
                    victim.setImageResource(R.drawable.dump1);
                    pain = MediaPlayer.create(this, R.raw.dumppain);
                    pain.start();
                    break;
                }

            case 20:
                if(Selection.prez == R.id.dump){
                    pain.start();
                    victim.setImageResource(R.drawable.dump2);

                    break;
                }

            case 35:
                if(Selection.prez == R.id.dump){

                    victim.setImageResource(R.drawable.dump3);
                    pain.start();
                    pain = MediaPlayer.create(this,R.raw.dumppess );
                    pain.start();

                    break;
                    }


                }
        }


        public void setVisibilty(boolean ViewVisible){

        View view = (View) findViewById(R.id.returnToMenu);

        if(!ViewVisible){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
            }
        }

        public void returnToMenu(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        }


        public void saveScore(){


             if(!hasSavedRan) {
                 List<Integer> scores = Utility.loadScores(context);
                 Log.e("score", String.valueOf(scores.get(0)));

                 switch (Selection.prez) {

                     case R.id.dump:
                         Integer temp = scores.get(0);
                         temp++;
                         Log.e("score", "temp value " + String.valueOf(temp));
                         scores.set(0, temp);

                         Log.e("score", " before saving " + String.valueOf(scores.get(0)));
                         Utility.saveScores(context, scores);
                         hasSavedRan = true;
                 }
             }
             Selection.prez = 0;
        }




    // ---- inner Punch Down Class
    public class PunchDown implements Runnable {


        View gloveView = null;
        ViewGroup.MarginLayoutParams params = null;


        public PunchDown(View gloveView, ViewGroup.MarginLayoutParams params) {

            this.gloveView = gloveView;
            this.params = params;

        }

        @Override
        public void run() {

            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin - amount);
            gloveView.setLayoutParams(params);

        }

    }
}

