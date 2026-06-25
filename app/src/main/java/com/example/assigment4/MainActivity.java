package com.example.assigment4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AddContactDialog.OnContactAddedListener {

    Button btnAddContacts;
    RecyclerView recyclerView;

    ArrayList<Contact> contactList;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddContacts = findViewById(R.id.btnAddContacts);
        recyclerView = findViewById(R.id.recyclerView);

        contactList = new ArrayList<>();

        adapter = new ContactAdapter(contactList);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        btnAddContacts.setOnClickListener(v -> {

            Toast.makeText(this,
                    "Button Clicked",
                    Toast.LENGTH_SHORT).show();

            AddContactDialog dialog =
                    new AddContactDialog(this);

            dialog.show(
                    getSupportFragmentManager(),
                    "AddContactDialog");
        });
    }

    @Override
    public void onContactAdded(
            String name,
            String phone) {

        contactList.add(
                new Contact(name, phone));

        adapter.notifyItemInserted(
                contactList.size() - 1);
    }
}