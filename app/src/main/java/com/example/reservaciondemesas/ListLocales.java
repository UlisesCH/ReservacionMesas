package com.example.reservaciondemesas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.reservaciondemesas.Modelo.AdaptadorLocal;
import com.example.reservaciondemesas.Modelo.Local;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListLocales extends AppCompatActivity {

    public FirebaseDatabase database;
    public DatabaseReference referenciData;
    public ArrayList<Local> arrayListLocal;
    public ListView listaLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_locales);

        database = FirebaseDatabase.getInstance();
        referenciData = database.getReference();

        arrayListLocal = new ArrayList<Local>();
        listaLocal = findViewById(R.id.ListLocal);

        Button btnAddLocal = findViewById(R.id.BtnAddLocal);
        btnAddLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListLocales.this, FrmLocal.class);
                startActivity(intent);
            }
        });

        CargarLocal();

    }

    public void CargarLocal(){

        referenciData.child("LOCAL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ItemLocal:snapshot.getChildren()) {
                    Local local = ItemLocal.getValue(Local.class);
                    arrayListLocal.add(local);
                }

                AdaptadorLocal adaptadorLocal = new AdaptadorLocal(arrayListLocal, getApplicationContext());
                listaLocal.setAdapter(adaptadorLocal);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}