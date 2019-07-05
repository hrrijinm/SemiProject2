package com.example.semiproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.semiproject2.database.Database;
import com.example.semiproject2.model.MyItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.semiproject2.ListViewActivity.SAVE;

public class Fragment_1 extends Fragment { // 메모 탭
    public Fragment_1() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<MyItem> items = new ArrayList<>();
        ListView listView;

        // Fragment UI 생성
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        listView = view.findViewById(R.id.listView);

        // 기 저장된 Items 리스트 획득
        items = Database.getInstance(this).loadItems();

        ListViewActivity.ListAdapter adapter;
        adapter = new ArrayAdapter<String>(getActivity(), ListViewActivity.class);
        // Adapter 생성 및 적용
        //adapter = new ListViewActivity.ListAdapter(items, this);
        // 리스트뷰에 Adapter 설정
        //listView.setAdapter(adapter);

        Button btnAddItem = view.findViewById(R.id.btnAddItem);
        /*
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Item추가 Activity 호출
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, SAVE);
            }
        });*/


        return view;
    }
}
