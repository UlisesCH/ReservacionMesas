package com.example.reservaciondemesas.Modelo;

import java.util.List;

public class Usuario {

    public String Documento;
    public String Nombre;
    public String Apellido;
    public int Edad;
    public String Tipo;
    public String Correo;
    public String Foto;

    public List<Local> ObjLocal;

    public String toString(){
        return Documento;
    }

}
