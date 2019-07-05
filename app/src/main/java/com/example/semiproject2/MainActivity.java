package com.example.semiproject2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 로그인
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TabActivity.class);
                startActivity(intent);
            }
        });

        // 회원가입
        Button btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        RegActivity.class);
                startActivity(intent);
            }
        });

        // Tab 레이아웃
        Button btnTab = findViewById(R.id.btnTab);
        btnTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        TabActivity.class);
                startActivity(intent);
            }
        });

        /*
        // 다이얼로그 표시
        Button btnDialog = findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtil.showDialog(
                        MainActivity.this, // <주의> Fragment에서 호출시: getActivity()
                        "타이틀","공부하고 싶어요?",
                        "예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "예 클릭...",
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        "아니요", new DialogInterface.OnClickListener()  {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "아니요 클릭...",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        );
            }
        });*/

        // 리스트뷰 표시
        Button btnListview = findViewById(R.id.btnListview);
        btnListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        ListViewActivity.class);
                startActivity(intent);
            }
        });

        // TTS 호출
        Button btnTts = findViewById(R.id.btnTts);
        btnTts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TtsActivity.class));
            }
        });

        /*
        // Recycler 뷰
        Button btnRecycler = findViewById(R.id.btnRecycler);
        btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RecyclerActivity.class));
            }
        });*/

        // 카메라
        Button btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CameraActivity.class));
            }
        });
    }
}
