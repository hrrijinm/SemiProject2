package com.example.semiproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.semiproject2.model.MemberModel;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("TEST"); // 문자열
        int age = intent.getIntExtra("AGE", 0); // 숫자

        Toast.makeText(this, "전달받은 문자:" + name + age, Toast.LENGTH_LONG).show();
        Log.e(TAG, "전달받은 문자:" + name + age);

        MemberModel member = (MemberModel)intent.getSerializableExtra("MEMBER");
        if (member == null) {
            Log.e(TAG, "전달받은 객체: NULL");
        } else {
            Log.e(TAG, "전달받은 객체:" + member.toString());
        }

        // 버튼 클릭시 데이터 전달
        Button btnRet = findViewById(R.id.btnReturn);
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 리턴할 Intent 정의
                Intent retIntent = new Intent();
                retIntent.putExtra("RETURN", "SUCCESS");
                setResult(0, retIntent);
                finish();
            }
        });
    }
}
