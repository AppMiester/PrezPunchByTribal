package tribalway.prezpunchby.com.prezpunchbytribal;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Utility {





    public static List<Integer> loadScores(Context context) {









        SharedPreferences sharedPreferences = context.getSharedPreferences("KNOCKOUTS",MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sharedPreferences.getString("SCORES", null);

        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();



        ArrayList <Integer> temp = gson.fromJson(json, type);



        if(temp != null){
            Log.e("score", "!= null ran");
            return temp;

        }

        Log.e("score","null ran" );
        Integer trump = 0;
        Integer obama = 0;
        ArrayList<Integer> temp2 = new ArrayList<Integer>();
        temp2.add(trump);
        temp2.add(obama);




        return temp2;





    }





    public static void saveScores(Context context, List<Integer> scoreArray){





        Gson gson = new Gson();

        String json = gson.toJson(scoreArray);



        SharedPreferences sharedPreferences = context.getSharedPreferences("KNOCKOUTS", MODE_PRIVATE);



        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("SCORES",json);

        editor.apply();




        }

}

