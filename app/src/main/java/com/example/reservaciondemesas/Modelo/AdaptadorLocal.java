package com.example.reservaciondemesas.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reservaciondemesas.R;

import java.util.ArrayList;

public class AdaptadorLocal extends BaseAdapter {

    public ArrayList<Local> data;
    public Context context;
    TextView TxtNombre, TxtHubicacion, TxtH_Abierto, TxtH_Cerrado;

    public AdaptadorLocal(ArrayList<Local> lista, Context context){

        this.data = lista;
        this.context = context;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Local local = (Local) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_local, null);

        TxtNombre = view.findViewById(R.id.TxtViewNombre);
        TxtHubicacion = view.findViewById(R.id.TxtViewHubicacion);
        TxtH_Abierto = view.findViewById(R.id.TxtViewH_Abierto);
        TxtH_Cerrado = view.findViewById(R.id.TxtViewH_Cerrado);

        TxtNombre.setText(local.NombreLocal);
        TxtHubicacion.setText(local.Hubicacion);
        TxtH_Abierto.setText(local.H_Abierto);
        TxtH_Cerrado.setText(local.H_Cierre);

        return view;
    }

}
