package com.aguirreariel.gestionempleados.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.aguirreariel.gestionempleados.R;
import com.aguirreariel.gestionempleados.modelo.DAOEmpleado;
import com.aguirreariel.gestionempleados.modelo.Empleado;

import java.util.ArrayList;

public class listado_empleados extends AppCompatActivity {

    ListView lvEmpleados;
    ArrayList<Empleado> aEmpleados;
    ArrayAdapter<Empleado> adaptador;

    DAOEmpleado objDAO;

    Button btRetornar;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargaListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_empleados);
        btRetornar = (Button)findViewById(R.id.btRetornar);
        btRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(listado_empleados.this,MainActivity.class));
            }
        });

        lvEmpleados = (ListView)findViewById(R.id.lvEmpleados);

        cargaListado();

        lvEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(listado_empleados.this,objDAO.listaEmpleados().get(position).toString(),Toast.LENGTH_SHORT).show();

                int codigo=Integer.parseInt(objDAO.listaEmpleados().get(position).toString().split(" ")[0]);
                String nombres=objDAO.listaEmpleados().get(position).toString().split(" ")[1];
                String apellidos=objDAO.listaEmpleados().get(position).toString().split(" ")[2];
                String telefono=objDAO.listaEmpleados().get(position).toString().split(" ")[3];

                Intent intent = new Intent(listado_empleados.this,modifica_empleado.class);
                intent.putExtra("cod",codigo);
                intent.putExtra("nom",nombres);
                intent.putExtra("ape",apellidos);
                intent.putExtra("tel",telefono);
                startActivity(intent);
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    ///metodo listar fuera del onCreate

    public void cargaListado(){
        objDAO = new DAOEmpleado(this);
        aEmpleados= objDAO.listaEmpleados();
        objDAO.close();

        adaptador = new ArrayAdapter<Empleado>(this,
                android.R.layout.simple_list_item_1,aEmpleados);
        lvEmpleados.setAdapter(adaptador);
    }




}