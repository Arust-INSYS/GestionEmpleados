package com.aguirreariel.gestionempleados.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aguirreariel.gestionempleados.R;
import com.aguirreariel.gestionempleados.modelo.DAOEmpleado;
import com.aguirreariel.gestionempleados.modelo.Empleado;

public class modifica_empleado extends AppCompatActivity {
    Button btActualizar,btEliminar, btRetorna;
    EditText etCodigo,etNombres,etApellidos, etFono;
    DAOEmpleado objDAO;

    int cod;
    String nom,ape,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_empleado);
        btRetorna = (Button)findViewById(R.id.btRetorna);
        btRetorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(modifica_empleado.this,MainActivity.class));
            }
        });

        Bundle b=getIntent().getExtras();
        if (b !=null){
            cod=b.getInt("cod");
            nom=b.getString("nom");
            ape=b.getString("ape");
            tel=b.getString("tel");
        }

        etCodigo = (EditText)findViewById(R.id.etCodigo);
        etNombres = (EditText)findViewById(R.id.etNombres);
        etApellidos = (EditText)findViewById(R.id.etApellidos);
        etFono = (EditText)findViewById(R.id.etFono);

        etCodigo.setText(""+cod);
        etNombres.setText(nom);
        etApellidos.setText(ape);
        etFono.setText(tel);

        //Invocacion del metodo de actualizacion
        btActualizar = (Button) findViewById(R.id.btActualizar);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(cod,etNombres.getText().toString(),etApellidos.getText().toString(),etFono.getText().toString());
            }
        });

        //Invocacion del metodo de eliminacion
        btEliminar = (Button)findViewById(R.id.btEliminar);
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(cod);
            }
        });

    }

    //Metodo para modificar un empleado
    public void modificar(int codigo,String nombres, String apellidos, String telefono) {

        //Enviar los datos del empleado a un objeto
        Empleado objEm=new Empleado();
        objEm.setCodigo(codigo);
        objEm.setNombres(nombres);
        objEm.setApellidos(apellidos);
        objEm.setTelefono(telefono);

        //Invocar al metodo actualiza empleado de DAOEmpleado
        new DAOEmpleado(this).actualizaEmpleado(objEm);
        Toast.makeText(this,"Actualizado correctamente",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(modifica_empleado.this,listado_empleados.class));
    }

    //Metodo para eliminar un empleado
    public void eliminar(int codigo){

        //Enviar el codigo del empleado a un objeto
        Empleado objEm=new Empleado();
        objEm.setCodigo(codigo);

        //Invocar al metodo eliminar empleado de DAOEmpleado
        new DAOEmpleado(this).eliminaEmpleado(objEm);
        Toast.makeText(this,"Eliminado correctamente",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(modifica_empleado.this,listado_empleados.class));
    }


}