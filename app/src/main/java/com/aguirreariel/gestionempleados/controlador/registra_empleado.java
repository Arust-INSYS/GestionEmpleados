package com.aguirreariel.gestionempleados.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aguirreariel.gestionempleados.R;
import com.aguirreariel.gestionempleados.modelo.DAOEmpleado;
import com.aguirreariel.gestionempleados.modelo.Empleado;

public class registra_empleado extends AppCompatActivity {

    Button btGuardar,btRetorno;
    EditText etCodigo,etNombres,etApellidos, etFono;
    //ImageView etImagen;

    DAOEmpleado objDAO=new DAOEmpleado(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_empleado);
        etCodigo = (EditText)findViewById(R.id.etCodigo);
        etNombres = (EditText)findViewById(R.id.etNombres);
        etApellidos = (EditText)findViewById(R.id.etApellidos);
        etFono = (EditText)findViewById(R.id.etFono);
        /*etImagen = (ImageView) findViewById(R.id.selectedImage);
        etImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean pick=true;
              if(pick==true){
                  if(!checkCameraPermission()){
                      requestCameraPermission();
                  }else PickImage();
              }else{
                  if(!checkStoragePermission()){
                      requestStoragePermission();
                  }else PickImage();

              }
            }
        });*/
        btGuardar = (Button) findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabar(Integer.parseInt(etCodigo.getText().toString()),etNombres.getText().toString(),etApellidos.getText().toString(),etFono.getText().toString()
                       //etImagen.setImageIcon(R.drawable.);
                );
                startActivity(new Intent(registra_empleado.this,MainActivity.class));
            }
        });

        btRetorno = (Button) findViewById(R.id.btRetorno);
        btRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registra_empleado.this,MainActivity.class));
            }
        });
    }

    //Metodo para grabar un cliente
    public void grabar(int codigo,String nombres, String apellidos, String telefono
                      /*byte[] imagen*/){
        Empleado objEm=new Empleado();
        objEm.setCodigo(codigo);
        objEm.setNombres(nombres);
        objEm.setApellidos(apellidos);
        objEm.setTelefono(telefono);
       // objEm.setImagen(imagen);
        objDAO.nuevoEmpleado(objEm);
        Toast.makeText(this,"Empleado registrado..!!",Toast.LENGTH_SHORT).show();
    }

}