package com.example.proj4_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<WishItem> list = new ArrayList<>();
    TextView totalSum;
    boolean allSelected = false;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalSum = findViewById(R.id.txtTotal);
        Button btnSelectAll = findViewById(R.id.btnSelectAll);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        list.add(new WishItem(R.drawable.chair, "Chair", 350));
        list.add(new WishItem(R.drawable.tablet, "Tablet", 2300));
        list.add(new WishItem(R.drawable.mouse, "Mouse", 50));

        WishAdapter adapter = new WishAdapter(list, this::updateTotal);
        recycler.setAdapter(adapter);

        btnSelectAll.setOnClickListener(v -> {
            allSelected = !allSelected;

            for (WishItem item : list) {
                item.checked = allSelected;
            }

            adapter.notifyDataSetChanged();
            updateTotal();

            btnSelectAll.setText(allSelected ? "Unselect all" : "Select all");
        });

        updateTotal();
    }
    @SuppressLint("SetTextI18n")
    void updateTotal() {
        int total = 0;
        for (WishItem i : list) {
            if (i.checked) total += i.price;
        }
        totalSum.setText("Total: $" + total);
    }
}
