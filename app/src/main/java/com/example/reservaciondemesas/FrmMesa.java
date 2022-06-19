package com.example.reservaciondemesas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.reservaciondemesas.Modelo.Local;
import com.example.reservaciondemesas.Modelo.Mesa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FrmMesa extends AppCompatActivity {

    EditText TxtNumMesa, TxtMesaHubicacion, TxtCapacidad;
    public Spinner SpLocal;
    public FirebaseDatabase database;
    public DatabaseReference referenciData;

    public ArrayList<Local> arrayListLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_mesa);

        database = FirebaseDatabase.getInstance();
        referenciData = database.getReference();

        TxtNumMesa = findViewById(R.id.TxtNumMesa);
        TxtMesaHubicacion = findViewById(R.id.TxtMesaHubicacion);
        TxtCapacidad = findViewById(R.id.TxtCapacidad);

        SpLocal = findViewById(R.id.spinnerLocal);
        arrayListLocal = new ArrayList<Local>();

        cargarLocal();

    }

    public void cargarLocal(){

        referenciData.child("LOCAL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemCategoria:snapshot.getChildren()) {
                    Local local = itemCategoria.getValue(Local.class);
                    arrayListLocal.add(local);
                }

                ArrayAdapter<Local> adaptador = new ArrayAdapter<Local>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayListLocal);
                adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adaptador.notifyDataSetChanged();
                SpLocal.setAdapter(adaptador);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void ClickAddMesa(View v){

        Mesa mesa = new Mesa();
        mesa.NumMesa = Integer.parseInt(TxtNumMesa.getText().toString());
        mesa.Hubicacion = TxtMesaHubicacion.getText().toString();
        mesa.Capasidad = Integer.parseInt(TxtCapacidad.getText().toString());

        mesa.ObjLocal = new ArrayList<Local>();
        Local local = (Local) SpLocal.getSelectedItem();
        mesa.ObjLocal.add(local);

        referenciData.child("MESA").child(String.valueOf(mesa.NumMesa)).setValue(mesa);

    }

}