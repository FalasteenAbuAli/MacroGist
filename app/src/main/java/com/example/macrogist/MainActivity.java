package com.example.macrogist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] macros = {"Protein", "Carbs", "Fat"};

        ListView listView = findViewById(R.id.myListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, macros);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;

                switch (position) {
                    case 0: // Protein
                        intent = new Intent(MainActivity.this, ProteinActivity.class);
                        break;
                    case 1: // Carbs
                        intent = new Intent(MainActivity.this, CarbsActivity.class);
                        break;
                    case 2: // Fat
                        intent = new Intent(MainActivity.this, FatActivity.class);
                        break;
                    default:
                        return;
                }

                startActivity(intent);
            }
        });

        Button startButton = findViewById(R.id.startbt);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}
