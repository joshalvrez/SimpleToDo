package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Need to define a list of strings which will store every item in the to-do list
    List<String> items;

    // importing buttons and text view
    ItemsAdapter itemsAdapter;
    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Defining the variables btnAdd, etItem, and rvItem
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);

        items = new ArrayList<>();

        //items.add("Buy Milk");  // entering mock data into the program
        //items.add("Buy Oranges");  // entering mock data into the program
        //items.add("Buy Juice");  // entering mock data into the program

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){

            @Override
            public void onItemLongClick(int position) {
                items.remove(position);
                itemsAdapter.notifyItemRemoved(position);
            }
        };

        // call the adapter
        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                items.add(todoItem);
                itemsAdapter.notifyItemInserted(items.size()-1);
                etItem.setText("");
            }
        });
    }
}