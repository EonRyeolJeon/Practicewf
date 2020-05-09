package com.example.practicewf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class UserInfo extends AppCompatActivity {

    private static String TAG = "UserInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        findViewById(R.id.button_editinfo).setOnClickListener(onClickListener);
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
                                TextView textView = (TextView) findViewById(R.id.heighttextView);
                                TextView textView2 = (TextView) findViewById(R.id.weighttextView);
                                TextView textView3 = (TextView) findViewById(R.id.agetextView);
                                TextView textView4 = (TextView) findViewById(R.id.howmanytextView);
                                TextView textView5 = (TextView) findViewById(R.id.textView_active);
                                TextView textView6 = (TextView) findViewById(R.id.textView_gender);
                                ImageView imageView = (ImageView) findViewById(R.id.imageView_user);

                                double heightdouble;
                                String height = document.getData().get("height").toString();
                                String outputheight;
                                heightdouble = Double.parseDouble(height);
                                outputheight = Double.toString(heightdouble);
                                textView.setText("신장 : "+ outputheight);

                                double weightdouble;
                                String weight = document.getData().get("weight").toString();
                                String outputweight;
                                weightdouble = Double.parseDouble(weight);
                                outputweight = Double.toString(weightdouble);
                                textView2.setText("체중 : "+ outputweight);

                                int agedouble;
                                String age = document.getData().get("age").toString();
                                String outputage;
                                agedouble = Integer.parseInt(age);
                                outputage = Integer.toString(agedouble);
                                textView3.setText("나이 : "+ outputage);

                                int howmanydouble;
                                String howmany = document.getData().get("howmany").toString();
                                String outputhowmany;
                                howmanydouble = Integer.parseInt(howmany);
                                outputhowmany = Integer.toString(howmanydouble);
                                textView4.setText("주간 운동 횟수 : "+ outputhowmany);

                                int intwhy;
                                String active = document.getData().get("active").toString();
                                intwhy = Integer.parseInt(active);

                                if(intwhy == 1){
                                    textView5.setText("운동 목적 : 체중 감소");
                                }else if(intwhy == 2){
                                    textView5.setText("운동 목적 : 체중 유지");
                                }else if(intwhy == 3){
                                    textView5.setText("운동 목적 : 체중 증가");
                                }else{
                                    textView5.setText("잘못선택했음");
                                }

                                int genderint;
                                String gender = document.getData().get("gender").toString();
                                genderint = Integer.parseInt(gender);


                                if(genderint == 10){
                                    textView6.setText("성별 : 남성");
                                    imageView.setImageResource(R.drawable.man);
                                }else if(genderint == 20){
                                    textView6.setText("성별 : 여성");
                                    imageView.setImageResource(R.drawable.women);
                                }else{
                                    textView6.setText("성별이 잘못 선택되었습니다.");
                                }


                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                }
            });
        }


    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.button_editinfo:
                    myStartActivity(MemberinitActivity.class);
                    finish();
                    break;

            }
        }
    };

    private void myStartActivity(Class c){
        Intent intent = new Intent (this,c);
        startActivity(intent);
    }

}



