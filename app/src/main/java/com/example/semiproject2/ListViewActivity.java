package com.example.semiproject2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.semiproject2.database.Database;
import com.example.semiproject2.model.MyItem;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    public static final int SAVE = 1001;
    // 원본 데이터
    List<MyItem> items = new ArrayList<>();
    ListView listView;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listView);


/*        items.add(new MyItem(R.drawable.hamburder, "햄버거", "특별한 햄버거", "5000"));
        items.add(new MyItem(R.drawable.gyoza, "만두", "특별한 만두", "1000"));
        items.add(new MyItem(R.drawable.bibimbub, "전골", "특별한 전골", "2000"));
        items.add(new MyItem(R.drawable.hamburder, "햄버거", "특별한 햄버거", "5000"));
        items.add(new MyItem(R.drawable.gyoza, "만두", "특별한 만두", "1000"));
        items.add(new MyItem(R.drawable.bibimbub, "전골", "특별한 전골", "2000"));*/

        // 기 저장된 Items 리스트 획득
        items = Database.getInstance(this).loadItems();

        // Adapter 생성 및 적용
        adapter = new ListAdapter(items, this);
        // 리스트뷰에 Adapter 설정
        listView.setAdapter(adapter);

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Item추가 Activity 호출
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, SAVE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SAVE) { // 리스트 갱신
            // DB 데이터 획득
            items = Database.getInstance(getApplicationContext()).loadItems();
            // Adapter에 원본데이터 저장
            adapter.setItems(items);
            adapter.notifyDataSetChanged();// 리스트 UI 갱신
        }
    }

    class ListAdapter extends BaseAdapter {
        List<MyItem> items; // 원본 데이터
        Context mContext;
        LayoutInflater inflater;

        public ListAdapter(List<MyItem> items, Context context) {
            this.items = items;
            this.mContext = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        public void setItems(List<MyItem> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            // view_item.xml 획득
            view = inflater.inflate(R.layout.view_item, null);

            // 객체 획득
            ImageView imgView = view.findViewById(R.id.itemImg);
            TextView txtTitle = view.findViewById(R.id.txtvTitle);
            TextView txtTitleDesc = view.findViewById(R.id.txtvTitleDesc);
            TextView txtPrice = view.findViewById(R.id.txtvDetailPrice);

            // 원본에서 i번째 Item 획득
            final MyItem item = items.get(i);
            // 원본 데이터를 UI에 적용
            imgView.setImageResource(item.getImgId());
            txtTitle.setText(item.getTitle());
            txtTitleDesc.setText(item.getSubTitle());
            txtPrice.setText(item.getPrice() + "원");

            // 이미지를 클릭했을 때 -> 상세화면 이동
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ItemDetailActivity.class);
                    intent.putExtra("INDEX", i);   // 원본데이터의 순번
                    intent.putExtra("ITEM", item); // 상세표시할 원본 데이터
                    startActivity(intent);
                }
            });

            return view; // 완성된 UI 리턴
        }
    }
}
