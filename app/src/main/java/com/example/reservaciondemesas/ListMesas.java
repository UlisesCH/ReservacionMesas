package com.example.reservaciondemesas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.reservaciondemesas.Modelo.AdaptadorLocal;
import com.example.reservaciondemesas.Modelo.AdaptadorMesa;
import com.example.reservaciondemesas.Modelo.Local;
import com.example.reservaciondemesas.Modelo.Mesa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListMesas extends AppCompatActivity {

    public FirebaseDatabase database;
    public DatabaseReference referenciData;
    public ArrayList<Mesa> arrayListMesa;
    public ListView listaMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mesas);

        database = FirebaseDatabase.getInstance();
        referenciData = database.getReference();

        arrayListMesa = new ArrayList<Mesa>();
        listaMesa = findViewById(R.id.ListMesa);

        Button btnAddMesa = findViewById(R.id.BtnAddMesa);
        btnAddMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMesas.this, FrmMesa.class);
                startActivity(intent);
            }
        });

        CargarMesa();

    }

    public void CargarMesa(){

        referenciData.child("MESA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ItemMesa:snapshot.getChildren()) {
                    Mesa mesa = ItemMesa.getValue(Mesa.class);
                    arrayListMesa.add(mesa);
                }

                AdaptadorMesa adaptadorMesa = new AdaptadorMesa(arrayListMesa, getApplicationContext());
                listaMesa.setAdapter(adaptadorMesa);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}