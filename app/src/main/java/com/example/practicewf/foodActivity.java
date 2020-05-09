package com.example.practicewf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;


public class foodActivity extends AppCompatActivity {

    private static String TAG = "foodActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                TextView textView = (TextView) findViewById(R.id.textViewfoodfor);
                                TextView textView2 = (TextView) findViewById(R.id.textViewfoodkcal);
                                TextView textView3 = (TextView) findViewById(R.id.textViewfoodtansoo);
                                TextView textView4 = (TextView) findViewById(R.id.textViewfooddanback);
                                TextView textView5 = (TextView) findViewById(R.id.textViewfoodfat);
                                TextView textView6 = (TextView) findViewById(R.id.textViewfoodchicken);
                                TextView textView7 = (TextView) findViewById(R.id.textViewfoodbeef);

                                double heightdouble;
                                String height = document.getData().get("height").toString();
                                String outputheight;
                                heightdouble = Double.parseDouble(height);
                                outputheight = Double.toString(heightdouble);

                                double weightdouble;
                                String weight = document.getData().get("weight").toString();
                                String outputweight;
                                weightdouble = Double.parseDouble(weight);
                                outputweight = Double.toString(weightdouble);

                                int agedouble;
                                String age = document.getData().get("age").toString();
                                String outputage;
                                agedouble = Integer.parseInt(age);
                                outputage = Integer.toString(agedouble);

                                int howmanydouble;
                                String howmany = document.getData().get("howmany").toString();
                                String outputhowmany;
                                howmanydouble = Integer.parseInt(howmany);
                                outputhowmany = Integer.toString(howmanydouble);

                                int activedouble;
                                String active = document.getData().get("active").toString();
                                String outputactive;
                                activedouble = Integer.parseInt(active);
                                outputactive = Integer.toString(activedouble);

                                double Caloriedouble;
                                int genderint;
                                String gender = document.getData().get("gender").toString();
                                String outputcalorie;
                                genderint = Integer.parseInt(gender);

                                if(genderint == 10){
                                    Caloriedouble = (66 + (13.7*weightdouble) + (5*heightdouble) - (6.5*agedouble))*1.2;
                                } else if(genderint == 20){
                                    Caloriedouble = 655 + (9.6*weightdouble) + (1.8*heightdouble) - (4.7*agedouble);
                                } else{
                                    Caloriedouble = 0;
                                }

                                if(howmanydouble == 0){
                                    Caloriedouble = Caloriedouble*1.2;
                                }else if(1<= howmanydouble && howmanydouble <= 2){
                                    Caloriedouble = Caloriedouble*1.3;
                                }else if(3<=howmanydouble && howmanydouble<=5){
                                    Caloriedouble = Caloriedouble*1.5;
                                }else if(howmanydouble == 6 || howmanydouble == 7){
                                    Caloriedouble = Caloriedouble*1.7;
                                }else{
                                    Caloriedouble = 0;
                                }

                                if(activedouble == 1){
                                    Caloriedouble = Caloriedouble*0.7;
                                }else if(activedouble == 3){
                                    Caloriedouble = Caloriedouble *1.5;
                                }

                                double tansoo = 0;
                                double danback = 0;
                                double fat = 0;
                                double chicken = 0;
                                double beef = 0;
                                String outputtansoo = "0";
                                String outputdanback = "0";
                                String outputfat = "0";
                                String outputchicken = "0";
                                String outputbeef = "0";
                                int intcalorie=0;
                                int inttansoo=0;
                                int intdanback=0;
                                int intfat=0;
                                int intchicken=0;
                                int intbeef=0;


                                if(activedouble == 1){
                                    textView.setText("체중 감소");
                                    tansoo = (0.3*Caloriedouble)/4;
                                    danback = (0.4*Caloriedouble)/4;
                                    fat = (0.3*Caloriedouble)/9;
                                }else if(activedouble == 2){
                                    textView.setText("체중 유지");
                                    tansoo = (0.5*Caloriedouble)/4;
                                    danback = (0.3*Caloriedouble)/4;
                                    fat = (0.2*Caloriedouble)/9;
                                }else if(activedouble == 3){
                                    textView.setText("체중 증가");
                                    tansoo = (0.4*Caloriedouble)/4;
                                    danback = (0.4*Caloriedouble)/4;
                                    fat = (0.2*Caloriedouble)/9;
                                }


                                chicken = danback/31*100;
                                beef = danback/24*100;

                                intcalorie = (int)Caloriedouble;
                                inttansoo = (int)tansoo;
                                intdanback=(int)danback;
                                intfat = (int)fat;
                                intchicken = (int)chicken;
                                intbeef = (int)beef;

                                outputcalorie = Integer.toString(intcalorie);
                                outputtansoo = Integer.toString(inttansoo);
                                outputdanback = Integer.toString(intdanback);
                                outputfat = Integer.toString(intfat);
                                outputchicken = Integer.toString(intchicken);
                                outputbeef = Integer.toString(intbeef);



                                textView2.setText(outputcalorie);
                                textView3.setText(outputtansoo);
                                textView4.setText(outputdanback);
                                textView5.setText(outputfat);
                                textView6.setText(outputchicken);
                                textView7.setText(outputbeef);


                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                }
            });
        }


    }


    private void myStartActivity(Class c){
        Intent intent = new Intent (this,c);
        startActivity(intent);
    }
}
