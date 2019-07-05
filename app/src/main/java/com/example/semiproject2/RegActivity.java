package com.example.semiproject2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semiproject2.database.Database;
import com.example.semiproject2.model.MemberModel;

// 회원가입
public class RegActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName(); // 클래스명 획득
    public static final int VIEW_DETAIL = 100;  // 상세화면 식별자
    public static final int VIEW_SAVE = 101;  // 저장화면 식별자

    EditText editId;
    EditText editPwd;
    EditText editName;
    //EditText editBirth;
    TextView txtvResult; // 결과표시

    DatePicker datePicker;
    Spinner genderSpinner;

    Button btnSave, btnCancel, btnGet, btnCheck, btnDetail; // 버튼
    // DB객체
    Database db;

    // 성별
    int intGender = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        // DB객체 획득
        db = Database.getInstance(getApplicationContext());

        // 입력필드 객체 획득
        editId = findViewById(R.id.editId);
        editPwd = findViewById(R.id.editPwd);
        editName = findViewById(R.id.editName);
        //editBirth = findViewById(R.id.editBirth);
        // 버튼 객체 획득
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        btnGet = findViewById(R.id.btnGet);
        btnCheck = findViewById(R.id.btnCheck);
        btnDetail = findViewById(R.id.btnDetail);

        // 결과표시 객체
        txtvResult = findViewById(R.id.txtvResult);

        datePicker = findViewById(R.id.datePicker);
        genderSpinner = findViewById(R.id.spinnerGender);

        // 성별 spinner
        //ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_dropdown_item);
        //genderSpinner.setAdapter(arrayAdapter);
        // 성별 이벤트 등록
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                intGender = i; // 리스트의 index 저장
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // 저장버튼 이벤트
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저장
                Log.d(TAG, "id:" + editId.getText());
                Log.d(TAG, "pwd:" + editPwd.getText());
                Log.d(TAG, "name:" + editName.getText());
                //Log.d(TAG, "birthday:" + editBirth.getText());
                MemberModel member = new MemberModel();
                member.setId(editId.getText().toString());
                member.setPwd(editPwd.getText().toString());
                member.setName(editName.getText().toString());
                member.setBirth(getDatePicker());

                Log.d(TAG, "datePicker=" + getDatePicker());
                Log.d(TAG, "성별=" + intGender);

                // 테스트 : picker 설정
                //setDatePicker("19971230");

                // 저장
                db.setMember(member);
                txtvResult.setText("회원정보 저장=" + member.toString());
            }
        });

        // 취소버튼 이벤트
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 창 닫기
                finish();
            }
        });

        // 회원정보 획득
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 획득
                MemberModel savedMember = db.getMember(editId.getText().toString());
                if (savedMember != null) {
                    Log.e(TAG, "savedMember=" + savedMember.toString());

                    txtvResult.setText("회원정보 획득=" + savedMember.toString());
                } else {
                    txtvResult.setText("회원정보 없음");
                }
            }
        });

        // ID/PW 체크
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ID, PWD 체크
                boolean check = db.checkMember(editId.getText().toString(),
                        editPwd.getText().toString());
                Log.e(TAG, "checkMember=" + check);

                txtvResult.setText("체크결과=" + (check == true? "로그인 성공": "로그인 실패"));
            }
        });

        // 상세화면 이동(객체 전달)
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Activity 호출 + 객체전달
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                intent.putExtra("TEST", "SungYoonhan");// 문자열 전달
                intent.putExtra("AGE", 20); // 숫자 전달
                // 회원객체 전달
                MemberModel member = db.getMember(editId.getText().toString());
                if (member != null) {
                    intent.putExtra("MEMBER", member);
                }

                //startActivity(intent); // 데이터 전달
                startActivityForResult(intent, VIEW_DETAIL); // 리턴값이 있는 경우

            }
        });
        Toast.makeText(this, "onCreate...", Toast.LENGTH_SHORT).show();
    }

    // startActivityResult를 호출하고 해당 Activity가 종료될때 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) { // 화면 식별자로 구분
            case VIEW_DETAIL:
                // TODO
                Log.d(TAG, "resultCode=" +resultCode + "data="
                        + data.getStringExtra("RETURN"));
                break;
            case VIEW_SAVE:
                // TODO
                break;
            default:
        }
    }

    private String getDatePicker() {
        String date = "";

        date += datePicker.getYear();
        date += String.format("%02d", datePicker.getMonth()+1);
        date += String.format("%02d", datePicker.getDayOfMonth());

        return date;
    }

    private void setDatePicker(String date) {
        if (date != null && date.length() == 8) {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6);

            datePicker.init(Integer.parseInt(year)
                    , Integer.parseInt(month)-1
                    , Integer.parseInt(day)
                    , null);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart...", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop...", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy...", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume...", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause...", Toast.LENGTH_SHORT).show();
    }
}
