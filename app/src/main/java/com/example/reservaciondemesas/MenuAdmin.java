package com.example.reservaciondemesas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        Button btnLocal = findViewById(R.id.BtnLocal);
        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdmin.this, ListLocales.class);
                startActivity(intent);
            }
        });

        Button btnmesa = findViewById(R.id.BtnMesa);
        btnmesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdmin.this, ListMesas.class);
                startActivity(intent);
            }
        });

    }
}