package com.aguirreariel.gestionempleados.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Icon;

import java.util.ArrayList;

public class DAOEmpleado extends SQLiteOpenHelper {

    //Definicion de la base de datos SUPERMERCADO y la tabla EMPLEADO
    private static final String base="SUPERMERCADO";
    private static final int version=1;
    private static final String tabla="EMPLEADO";

    //Definicion de las columnas
    private static final String cod_emp="COD_EMP";
    private static final String nom_emp="NOM_EMP";
    private static final String ape_emp="APE_EMP";
    private static final String fon_emp="FON_EMP";
    private static final String img_emp="IMG_EMP";



    public DAOEmpleado(Context context) {
        super(context, base, null, version);
    }

    //Metodo para crear la tabla Empleado
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL="CREATE TABLE "+tabla+" ("+cod_emp+" text PRIMARY KEY,"
                +nom_emp+" text,"+ape_emp+" text,"
                +fon_emp+" text /*,"+img_emp+"blog*/)";
        db.execSQL(SQL);
    }

    //Metodo que valida la existencia de la tabla Empleado
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tabla);
        onCreate(db);
    }

    //Metodo para agregar un empleado
    public void nuevoEmpleado(Empleado objEm){

        //Obtener los valores a registrar
        ContentValues values=new ContentValues();
        values.put(cod_emp,objEm.getCodigo());
        values.put(nom_emp,objEm.getNombres());
        values.put(ape_emp,objEm.getApellidos());
        values.put(fon_emp,objEm.getTelefono());
        //values.put(img_emp,objEm.getImagen());

        //Ejecutar el metodo Insert
        SQLiteDatabase db=getWritableDatabase();
        db.insert(tabla,null,values);

        //Cerrar la conexion
        db.close();
    }

    //Metodo para listar los empleados
    public ArrayList listaEmpleados(){
        ArrayList listaEmpleados = new ArrayList();
        SQLiteDatabase db=getWritableDatabase();

        //Aplicar la sentencia de seleccion de registros de empleados
        String SQL="SELECT * FROM "+tabla;
        Cursor c=db.rawQuery(SQL,null);

        //Concatenar la informacion obtenida de la BD
        if(c.moveToFirst()) {
            do {
                listaEmpleados.add(c.getInt(0)+" "+c.getString(1)+" "
                        +c.getString(2)+" "+c.getString(3));
            }while(c.moveToNext());
        }
        return listaEmpleados;
    }

    //Metodo para actualizar un empleado
    public void actualizaEmpleado(Empleado objEm){

        //Obtener informacion del empleado a modificar
        int cod=objEm.getCodigo();
        String nom=objEm.getNombres();
        String ape=objEm.getApellidos();
        String tel=objEm.getTelefono();


        //Ejecutando la sentencia UPDATE
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("update empleado set nom_emp='"+nom+"',ape_emp='"+ape+"',fon_emp='"+tel+"' where cod_emp="+cod);
        db.close();
    }

    //Metodo para eliminar un registro de empleado
    public void eliminaEmpleado(Empleado objEm){

        //Obtener el codigo del empleado a eliminar
        int cod=objEm.getCodigo();

        //Ejecutando la sentencia DELETE
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from empleado where cod_emp="+cod);
        db.close();
    }



}
