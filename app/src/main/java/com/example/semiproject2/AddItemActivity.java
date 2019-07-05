package com.example.semiproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.semiproject2.database.Database;
import com.example.semiproject2.model.MyItem;

public class AddItemActivity extends AppCompatActivity {

    EditText editImg;
    EditText editTitle;
    EditText editDesc;
    EditText editPrice;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mappingUI(); // XML 객체 맵핑

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Item 저장
                MyItem item = new MyItem();
                int index = Integer.parseInt(editImg.getText().toString());
                switch(index) {
                    case 0:
                        item.setImgId(R.drawable.customerservice); // 햄버거
                        break;
                    case 1:
                        item.setImgId(R.drawable.cafemain); // 비빔밥
                        break;
                    case 2:
                        item.setImgId(R.drawable.dd);    // 만두
                }

                item.setTitle(editTitle.getText().toString());
                item.setSubTitle(editDesc.getText().toString());
                item.setPrice(editPrice.getText().toString());

                Database db = Database.getInstance(getApplicationContext());
                db.addItem(item);  // Item 추가
                db.saveItems();    // 저장

                finish();
            }
        });
    }

    private void mappingUI() {
        editImg = findViewById(R.id.editImg);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        editPrice = findViewById(R.id.editPrice);
        btnSave = findViewById(R.id.btnSave);
    }
}
