package com.example.apkmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ViewRecycler extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recycler);

        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewRecycler.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Aliffrianto Yudha Prakoso", "123456098", "085731379***"));
        mahasiswaArrayList.add(new Mahasiswa("Boy Dymas Hidayat", "789012765", "085231143***"));
        mahasiswaArrayList.add(new Mahasiswa("Anton Sebrianto", "345678432", "082228905***"));
        mahasiswaArrayList.add(new Mahasiswa("Prasetyo Dwiki Nugroho", "901234109", "082333546***"));
    }
}