package com.example.practicewf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Member;
import java.util.HashMap;

public class calendarActivity extends Activity {

    private static String TAG = "calendarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);
        findViewById(R.id.button_add).setOnClickListener(onClickListener);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String yearstring = Integer.toString(year);
                String monthstring = Integer.toString(month+1);
                String dayofmonthstring = Integer.toString(dayOfMonth);

                Memberdate.getInstance().setYear(yearstring);
                Memberdate.getInstance().setMonth(monthstring);
                Memberdate.getInstance().setDayofmonth(dayofmonthstring);

                Toast.makeText(calendarActivity.this, ""+year+"/"+(month+1)+"/"+dayOfMonth,Toast.LENGTH_LONG).show();

            }
        });
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_add:

                    final String yearr = Memberdate.getInstance().getYear();
                    final String monthh = Memberdate.getInstance().getMonth();
                    final String dayofmonthh = Memberdate.getInstance().getDayofmonth();


                    AlertDialog.Builder ad = new AlertDialog.Builder(calendarActivity.this);
                    ad.setTitle("일정 입력");
                    ad.setMessage("일정 입력2");
                    final EditText et = new EditText(calendarActivity.this);
                    ad.setView(et);

                    //확인 버튼 설정

                    ad.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String inputtext = et.getText().toString();
                            int i = 1;
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                FirebaseFirestore db = FirebaseFirestore.getInstance();


                                while(db.collection("users").orderBy("todo"+Integer.toString(i)) != null){
                                    i++;
                                }
                                if(db.collection("users").orderBy("todo"+Integer.toString(i)) == null) {
                                    HashMap<Object, Object> todo = new HashMap<>();
                                    todo.put("todo"+Integer.toString(i),inputtext);
                                    Memberdate memberdate = new Memberdate(yearr,monthh,dayofmonthh);
                                    if (user != null) {
                                        db.collection("users").document(user.getUid()).set(todo);
                                        db.collection("users").document(user.getUid()).set(memberdate)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        startToast("회원정보 등록을 성공하였습니다.");
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
                            dialog.dismiss();

                        }
                    });

                    ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startToast("등록취소");
                            dialog.dismiss();
                        }
                    });





                    break;
            }
        }
    };



    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
