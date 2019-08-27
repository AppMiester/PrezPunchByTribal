package tribalway.prezpunchby.com.prezpunchbytribal;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class KnockoutScore extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knockout_score);
        context = this;
        setScoreText();
    }



    public void setScoreText(){

        List<Integer> scores = new ArrayList<Integer>();
        scores = Utility.loadScores(context);

        TextView scoreTextView = (TextView)findViewById(R.id.knock_out_score);

        String scoreText = "Trump Knockouts = " + String.valueOf(scores.get(0) + "\n" +
                           "Obama Knockouts = " + String.valueOf(scores.get(1)));

        scoreTextView.setText(scoreText);
    }



    public void returnToMenu(View view){
       Intent intent = new Intent(this,MainActivity.class);
       startActivity(intent);
    }
}
