package com.example.reservaciondemesas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.reservaciondemesas.Modelo.Local;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FrmLocal extends AppCompatActivity {

    EditText TxtID, TxtNombre, TxtHubicacion, TxtH_Abierto, TxtH_Cerrado;
    public FirebaseDatabase database;
    public DatabaseReference referenciData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_local);

        TxtID = findViewById(R.id.TxtId);
        TxtNombre = findViewById(R.id.TxtNombre);
        TxtHubicacion = findViewById(R.id.TxtHubicacion);
        TxtH_Abierto = findViewById(R.id.TxtH_Abierto);
        TxtH_Cerrado = findViewById(R.id.TxtH_Cierre);

        database = FirebaseDatabase.getInstance();
        referenciData = database.getReference();

    }

    public void ClickAddLocal(View v){

        Local local = new Local();
        local.IDLocal = Integer.parseInt(TxtID.getText().toString());
        local.NombreLocal = TxtNombre.getText().toString();
        local.Hubicacion = TxtHubicacion.getText().toString();
        local.H_Abierto = TxtH_Abierto.getText().toString();
        local.H_Cierre = TxtH_Cerrado.getText().toString();

        referenciData.child("LOCAL").child(String.valueOf(local.IDLocal)).setValue(local);

        referenciData.child("LOCAL").addValueEventListener(LocalLista);

    }

    public ValueEventListener LocalLista = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot itemProduc: snapshot.getChildren()) {
                Local local = itemProduc.getValue(Local.class);
                Log.d("RESPUESTA","NOMBRE -> "+local.NombreLocal+" HUBICACION -> "+local.Hubicacion);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}