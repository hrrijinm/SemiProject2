package com.example.semiproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.semiproject2.model.MyItem;

public class ItemDetailActivity extends AppCompatActivity {

    ImageView imgDetailView;
    TextView txtvDetailTitle;
    TextView txtvDetailDesc;
    TextView txtvDetailPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        imgDetailView = findViewById(R.id.imgDetailView);
        txtvDetailTitle = findViewById(R.id.txtvDetailTitle);
        txtvDetailDesc = findViewById(R.id.txtvDetailDesc);
        txtvDetailPrice = findViewById(R.id.txtvDetailPrice);

        Intent intent = getIntent();
        MyItem item;
        int index = intent.getIntExtra("INDEX", -1); // 리스트 데이터의 Index
        if (index != -1) {
            item = (MyItem) intent.getSerializableExtra("ITEM"); // 원본 Item

            // 원본 Item을 UI에 적용
            imgDetailView.setImageResource(item.getImgId());
            txtvDetailTitle.setText(item.getTitle());
            txtvDetailDesc.setText(item.getSubTitle());
            txtvDetailPrice.setText(item.getPrice());
        }

        // 뒤로가기 버튼
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 화면 닫기(이전으로 가기 효과)
                finish();
            }
        });

    }
}
