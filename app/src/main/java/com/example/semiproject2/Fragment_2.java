package com.example.semiproject2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_2 extends Fragment {

    public Fragment_2() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Fragment UI 생성
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        // 버튼 객체 획득
        Button btn_2 = view.findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog("공부하고 싶어요?");
                DialogUtil.showDialog(
                        getActivity(), // <주의> Fragment에서 호출시: getActivity()
                        "타이틀","공부하고 싶어요?",
                        "예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "예 클릭...",
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        "아니요", new DialogInterface.OnClickListener()  {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "아니요 클릭...",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

        return view;
    }

    public void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("공부");
        builder.setMessage(msg);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "예 클릭....", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "아니요 클릭....", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show(); // 표시
    }
}
