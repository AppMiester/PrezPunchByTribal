package tribalway.prezpunchby.com.prezpunchbytribal;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Punch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
        setVictim(Selection.prez);
    }

    public void setVictim(int prez){

        ImageView victim = (ImageView) findViewById(R.id.victim);

        switch(prez){

            case R.id.dump:
                victim.setImageResource(R.drawable.dump);
                break;

            case R.id.bama:
                victim.setImageResource(R.drawable.bama);
                break;
        }
    }
}
