package com.example.reservaciondemesas.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.reservaciondemesas.R;

import java.util.ArrayList;

public class AdaptadorMesa extends BaseAdapter {

    public ArrayList<Mesa> data;
    public Context context;
    TextView TxtNumMesa, TxtMesaHubicacion, TxtCapacidad, TxtLocal;

    public AdaptadorMesa(ArrayList<Mesa> lista, Context context){

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

        Mesa mesa = (Mesa) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_mesa, null);

        TxtNumMesa = view.findViewById(R.id.TxtViewNumMesa);
        TxtCapacidad = view.findViewById(R.id.TxtViewCapacidad);
        TxtMesaHubicacion = view.findViewById(R.id.TxtViewMesaHubicacion);
        TxtLocal = view.findViewById(R.id.TxtViewLocal);

        TxtNumMesa.setText(String.valueOf(mesa.NumMesa));
        TxtCapacidad.setText(String.valueOf(mesa.Capasidad));
        TxtMesaHubicacion.setText(mesa.Hubicacion);
        TxtLocal.setText(mesa.ObjLocal.get(i).NombreLocal);

        return view;
    }
}
