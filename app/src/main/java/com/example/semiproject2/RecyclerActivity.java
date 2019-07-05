package com.example.semiproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semiproject2.model.MyItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    // 원본 데이터
    private List<MyItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 원본 데이터 준비
        items.add(new MyItem(R.drawable.customerservice, "햄버거", "특별한 햄버거", "5000"));
        items.add(new MyItem(R.drawable.cafemain, "만두", "특별한 만두", "1000"));
        items.add(new MyItem(R.drawable.dd, "전골", "특별한 전골", "2000"));

        // Adapter 생성
        MyAdapter myAdapter = new MyAdapter(items);
        recyclerView.setAdapter(myAdapter);
    }
    // Adapter
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

        private List<MyItem> items; // 원본 데이터

        // 생성자
        public MyAdapter(List<MyItem> items) {
            this.items = items;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {
            protected ImageView imgView;
            protected TextView txtTitle;
            protected TextView txtSubTitle;
            protected TextView txtPrice;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imgView = itemView.findViewById(R.id.itemImg);
                this.txtTitle = itemView.findViewById(R.id.txtvTitle);
                this.txtSubTitle = itemView.findViewById(R.id.txtvTitleDesc);
                this.txtPrice = itemView.findViewById(R.id.txtvDetailPrice);
            }
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
            MyItem item = items.get(position);

            // holder의 각 요소에 데이터 설정
            holder.imgView.setImageResource(item.getImgId());
            holder.txtTitle.setText(item.getTitle());
            holder.txtSubTitle.setText(item.getSubTitle());
            holder.txtPrice.setText(item.getPrice());
            // 아이템 클릭시 Toast 표시
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext()
                            , "item clicked..."+ position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
