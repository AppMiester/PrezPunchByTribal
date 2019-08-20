package tribalway.prezpunchby.com.prezpunchbytribal;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Punch extends AppCompatActivity {


    static int victimHeight = 0;
    static int amount = 0;
    int punchCount= 0;
    MediaPlayer punchSound;
    MediaPlayer trumpPain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
        setVictim(Selection.prez);

    }

    public void setVictim(int prez) {

        ImageView victim = (ImageView) findViewById(R.id.victim);

        switch (prez) {

            case R.id.dump:
                victim.setImageResource(R.drawable.dump);
                MediaPlayer make = MediaPlayer.create(this, R.raw.makeamericagreat);
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
        ImageView victim = (ImageView) findViewById(R.id.victim);

        victimHeight = victim.getHeight();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int screenHeight = size.y;

        ViewGroup.MarginLayoutParams viewParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

        amount = screenHeight - (viewParams.bottomMargin + victimHeight);
        viewParams.setMargins(viewParams.leftMargin, viewParams.topMargin, viewParams.rightMargin, viewParams.bottomMargin + amount/2);
        view.setLayoutParams(viewParams);

        viewParams.setMargins(viewParams.leftMargin, viewParams.topMargin, viewParams.rightMargin, viewParams.bottomMargin + amount/2);
        view.setLayoutParams(viewParams);

        punchSound = MediaPlayer.create(this, R.raw.punchsound);
        punchSound.start();

        punchCount++;
        showDamage();



        new Handler().postDelayed(new PunchDown(view, viewParams), 150);

    }


    public void showDamage(){
        ImageView victim = (ImageView) findViewById(R.id.victim);

        switch(punchCount){

            case 10:
                if(Selection.prez == R.id.dump){
                    victim.setImageResource(R.drawable.dump1);
                    trumpPain = MediaPlayer.create(this, R.raw.dumppain);
                    trumpPain.start();
                    break;
                }

            case 20:
                if(Selection.prez == R.id.dump){
                    trumpPain.start();
                    victim.setImageResource(R.drawable.dump2);
                    
                    break;
                }

            case 35:
                if(Selection.prez == R.id.dump){

                    victim.setImageResource(R.drawable.dump3);
                    trumpPain.start();
                    trumpPain = MediaPlayer.create(this,R.raw.dumppess );
                    trumpPain.start();

                    break;
                    }


                }
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

