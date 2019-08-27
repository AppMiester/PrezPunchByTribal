package tribalway.prezpunchby.com.prezpunchbytribal;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Selection extends AppCompatActivity {


    static int prez = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }


    public void selectPrez(View view) {

        int radioButton = view.getId();

        switch (radioButton) {

            case R.id.dump:
                prez = radioButton;
                break;

            case R.id.bama:
                prez = radioButton;
                break;

        }
    }

    public void goToPunch(View view){

        Intent intent = new Intent(this,Punch.class);

        if(prez != 0) {
            startActivity(intent);
        } else{
           Toast toast = Toast.makeText(this, "Select A Victim", Toast.LENGTH_LONG);
           toast.show();

        }

        }




}
