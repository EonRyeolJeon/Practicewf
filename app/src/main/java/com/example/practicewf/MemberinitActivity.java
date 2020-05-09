package com.example.practicewf;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberinitActivity extends AppCompatActivity {


    private static String TAG = "MemberinitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);

        findViewById(R.id.button_memincom).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.button_memincom:
                    ProfileUpdate();
                    break;

            }
        }
    };

    private void ProfileUpdate(){

        String height = ((EditText)findViewById(R.id.edit_height)).getText().toString();
        String weight = ((EditText)findViewById(R.id.edit_weight)).getText().toString();
        String age = ((EditText)findViewById(R.id.edit_age)).getText().toString();
        String howmany = ((EditText)findViewById(R.id.editText_howmany)).getText().toString();
        String active = "50";
        String gender = "non";

        CheckBox option1 = (CheckBox) findViewById(R.id.checkBox_nonactive);
        CheckBox option2 = (CheckBox) findViewById(R.id.checkBox_normallyactive);
        CheckBox option3 = (CheckBox) findViewById(R.id.checkBox_veryactive);
        CheckBox male = (CheckBox) findViewById(R.id.checkBox_male);
        CheckBox female = (CheckBox) findViewById(R.id.checkBox_female);

        if(option1.isChecked()){
            active = "1";
        }else if(option2.isChecked()){
            active = "2";
        }else if(option3.isChecked()){
            active = "3";
        }else{
            active = "0";
        }

        if(male.isChecked()){
            gender = "10";
        }else if(female.isChecked()){
            gender = "20";
        }


        if(height.length() > 0 && weight.length() >  0 && age.length() > 0 && howmany.length() > 0 && (option1.isChecked() || option2.isChecked() || option3.isChecked()) && (male.isChecked()|| female.isChecked())) {
            if((option1.isChecked() && option2.isChecked()||(option1.isChecked()&&option3.isChecked())||(option2.isChecked()&&option3.isChecked()))) {
                startToast("목적 중 하나만 선택해주세요.");
            }else if((male.isChecked()&&female.isChecked())){
                startToast("성별 중 하나만 선택해주세요.");
            }else{
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                MemberInfo memeberInfo = new MemberInfo(height, weight, age, howmany, active, gender);

                if (user != null) {
                    db.collection("users").document(user.getUid()).set(memeberInfo)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startToast("회원정보 등록을 성공하였습니다.");
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    startToast("회원정보 등록에 실패하였습니다.");
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });

                }
            }
        }else{
            startToast("회원정보를 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


}