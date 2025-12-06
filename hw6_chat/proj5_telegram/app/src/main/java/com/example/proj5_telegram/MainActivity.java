package com.example.proj5_telegram;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        List<Map<String, Object>> data = new ArrayList<>();

        data.add(makeChat(
                R.drawable.avatar2,
                "Telegram Group",
                "John: where are u",
                "Yesterday",
                5,
                false,
                false
        ));

        data.add(makeChat(
                R.drawable.avatar1,
                "Mom",
                "I made you lunch! :)",
                "13:20",
                1,
                false,
                false
        ));
        data.add(makeChat(
                R.drawable.avatar3,
                "Maria",
                "Ok",
                "17:35",
                0,
                true,
                true
        ));

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item_chat,
                new String[]{"avatar", "name", "last_message", "time", "unread_count", "is_read"},
                new int[]{R.id.avatar, R.id.name, R.id.last_message, R.id.time, R.id.unread, R.id.checkmark}
        );

        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String text) {
                int id = view.getId();

                if (id == R.id.avatar) {
                    ImageView img = (ImageView) view;
                    int res = (int) data;
                    if (res == 0) img.setImageResource(R.drawable.avatar1);
                    else img.setImageResource(res);
                    return true;
                }

                if (id == R.id.unread) {
                    TextView counter = (TextView) view;
                    int count = (int) data;
                    if (count > 0) {
                        counter.setVisibility(View.VISIBLE);
                        counter.setText(String.valueOf(count));
                    } else {
                        counter.setVisibility(View.GONE);
                    }
                    return true;
                }
                if (id == R.id.checkmark) {
                    ImageView check = (ImageView) view;
                    boolean isRead = (boolean) data;

                    if (isRead) {
                        check.setImageResource(R.drawable.ic_double_check);
                    }
                    else {
                        check.setImageResource(R.drawable.ic_single_check);
                    }
                    return true;
                }
                return false;
            }
        });


        listView.setAdapter(adapter);
    }
    private Map<String, Object> makeChat(int avatar, String name, String msg, String time, int unread, boolean isSent, boolean isRead) {
        Map<String, Object> map = new HashMap<>();
        map.put("avatar", avatar);
        map.put("name", name);
        map.put("last_message", msg);
        map.put("time", time);
        map.put("unread_count", unread);
        map.put("is_sent", isSent);
        map.put("is_read", isRead);
        return map;
    }
}
