package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoggedIn extends AppCompatActivity {
    Button cerrarSesion,volverInformacion;
    ImageView pizzamex, pizzaita, pizzahaw, pizzaveg, pizzacaf, pizzacla;
    TextView textViewn1,textViewi1, textViewp1;
    TextView textViewn2,textViewi2, textViewp2;
    TextView textViewn3,textViewi3, textViewp3;
    TextView textViewn4,textViewi4, textViewp4;
    TextView textViewn5,textViewi5, textViewp5;
    TextView textViewn6,textViewi6, textViewp6;
    TextView bienvenidousuario;
    MediaPlayer clickpizza, click;
    Spinner spinner;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        String email = preferences.getString("usuario", "micorreo@gmail.com");




        bienvenidousuario = findViewById(R.id.bienvenidousuario);
        volverInformacion = findViewById(R.id.volverInformacion);
        pizzamex = findViewById(R.id.pizzamex);
        pizzaita = findViewById(R.id.pizzaita);
        pizzahaw = findViewById(R.id.pizzahaw);
        pizzaveg = findViewById(R.id.pizzaveg);
        pizzacaf = findViewById(R.id.pizzacaf);
        pizzacla = findViewById(R.id.pizzacla);
        textViewn1 = findViewById(R.id.tvn1);
        textViewi1 = findViewById(R.id.tvi1);
        textViewp1 = findViewById(R.id.tvp1);
        textViewn2 = findViewById(R.id.tvn2);
        textViewi2 = findViewById(R.id.tvi2);
        textViewp2 = findViewById(R.id.tvp2);
        textViewn3 = findViewById(R.id.tvn3);
        textViewi3 = findViewById(R.id.tvi3);
        textViewp3 = findViewById(R.id.tvp3);
        textViewn4 = findViewById(R.id.tvn4);
        textViewi4 = findViewById(R.id.tvi4);
        textViewp4 = findViewById(R.id.tvp4);
        textViewn5 = findViewById(R.id.tvn5);
        textViewi5 = findViewById(R.id.tvi5);
        textViewp5 = findViewById(R.id.tvp5);
        textViewn6 = findViewById(R.id.tvn6);
        textViewi6 = findViewById(R.id.tvi6);
        textViewp6 = findViewById(R.id.tvp6);
        spinner = (Spinner) findViewById(R.id.spinner1);

        clickpizza = MediaPlayer.create(this,R.raw.clickpizza);
        click = MediaPlayer.create(this,R.raw.click);

        Resources res = getResources();
        String[] pizzitas = res.getStringArray(R.array.pizzas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, pizzitas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(LoggedIn.this);


        buscarProducto("https://yamoeats.000webhostapp.com/yamoeats/buscar_productos.php");

        Picasso.with(this).load("https://yamoeats.000webhostapp.com/yamoeats/images/mexicana.jpg").fit().centerCrop().into(pizzamex);
        Picasso.with(this).load("https://yamoeats.000webhostapp.com/yamoeats/images/italiana.jpg")
                .error(R.mipmap.errorimg)
                .fit().centerCrop().into(pizzaita);

        Picasso.with(this).load("https://yamoeats.000webhostapp.com/yamoeats/images/hawaiana.jpg")
                .error(R.mipmap.errorimg)
                .fit().centerCrop().into(pizzahaw);

        Picasso.with(this).load("https://yamoeats.000webhostapp.com/yamoeats/images/vegetariana.jpg")
                .error(R.mipmap.errorimg)
                .fit().centerCrop().into(pizzaveg);

        Picasso.with(this).load("https://yamoeats.000webhostapp.com/yamoeats/images/carnesfrias.jpg")
                .error(R.mipmap.errorimg)
                .fit().centerCrop().into(pizzacaf);

        Picasso.with(this).load("https://yamoeats.000webhostapp.com/yamoeats/images/clasica.jpg")
                .error(R.mipmap.errorimg)
                .fit().centerCrop().into(pizzacla);

        bienvenidousuario.setText("Usuario: " + email);


        cerrarSesion = findViewById(R.id.btnCerrarSesion);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences preferences =getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                //preferences.edit().clear().commit();
                click.start();
                finish();
            }
        });

        volverInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoggedIn.this, DetailsUser.class);
                click.start();
                startActivity(i);
                finish();
            }
        });

        pizzamex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this, DetailsPedido.class);
                intent.putExtra("pizza", textViewn1.getText().toString());
                intent.putExtra("botonactivado",0);
                clickpizza.start();
                startActivity(intent);
                finish();
            }
        });

        pizzaita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this, DetailsPedido.class);
                intent.putExtra("pizza", textViewn2.getText().toString());
                intent.putExtra("botonactivado",0);
                clickpizza.start();
                startActivity(intent);
                finish();
            }
        });

        pizzahaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this, DetailsPedido.class);
                intent.putExtra("pizza", textViewn3.getText().toString());
                intent.putExtra("botonactivado",0);
                clickpizza.start();
                startActivity(intent);
                finish();
            }
        });

        pizzaveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this, DetailsPedido.class);
                intent.putExtra("pizza", textViewn4.getText().toString());
                intent.putExtra("botonactivado",0);
                clickpizza.start();
                startActivity(intent);
                finish();
            }
        });

        pizzacaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this, DetailsPedido.class);
                intent.putExtra("pizza", textViewn5.getText().toString());
                intent.putExtra("botonactivado",0);
                clickpizza.start();
                startActivity(intent);
                finish();
            }
        });

        pizzacla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this, DetailsPedido.class);
                intent.putExtra("pizza", textViewn6.getText().toString());
                intent.putExtra("botonactivado",0);
                clickpizza.start();
                startActivity(intent);
                finish();
            }
        });

spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int posicion = spinner.getSelectedItemPosition();
        Intent i = new Intent(LoggedIn.this, PreparacionPizzas.class);
        switch (posicion){
            case 1:
                i.putExtra("pizzaElegida","https://www.youtube.com/embed/lXmo0x3SIRw");
                startActivity(i);

                break;
            case 2:
                i.putExtra("pizzaElegida","https://www.youtube.com/embed/TtDDTJvmcTE");
                startActivity(i);

                break;
            case 3:
                i.putExtra("pizzaElegida","https://www.youtube.com/embed/0dkBnNQMiIg");
                startActivity(i);
                break;
            case 4:
                i.putExtra("pizzaElegida","https://www.youtube.com/embed/6jNMinJKE40");
                startActivity(i);
                break;
            case 5:
                i.putExtra( "pizzaElegida","https://www.youtube.com/embed/Hk_fo6sGZoc");
                startActivity(i);
                break;
            case 6:
                i.putExtra("pizzaElegida","https://www.youtube.com/embed/K22dfMG-PiA");
                startActivity(i);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});



    }



    private void buscarProducto(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        if (i==0) {
                            jsonObject = response.getJSONObject(i);
                            textViewn1.setText(jsonObject.getString("nombrePizza"));
                            textViewi1.setText(textViewi1.getText().toString() + jsonObject.getString("ingredientes"));
                            textViewp1.setText(textViewp1.getText().toString() + jsonObject.getString("precio"));
                        }else if (i==1){
                            jsonObject = response.getJSONObject(i);
                            textViewn2.setText(jsonObject.getString("nombrePizza"));
                            textViewi2.setText(textViewi2.getText().toString() + jsonObject.getString("ingredientes"));
                            textViewp2.setText(textViewp2.getText().toString() + jsonObject.getString("precio"));
                        }else if (i==2){
                            jsonObject = response.getJSONObject(i);
                            textViewn3.setText(jsonObject.getString("nombrePizza"));
                            textViewi3.setText(textViewi3.getText().toString() + jsonObject.getString("ingredientes"));
                            textViewp3.setText(textViewp3.getText().toString() + jsonObject.getString("precio"));
                        }else if (i==3){
                            jsonObject = response.getJSONObject(i);
                            textViewn4.setText(jsonObject.getString("nombrePizza"));
                            textViewi4.setText(textViewi4.getText().toString() + jsonObject.getString("ingredientes"));
                            textViewp4.setText(textViewp4.getText().toString() + jsonObject.getString("precio"));
                        }else if (i==4){
                            jsonObject = response.getJSONObject(i);
                            textViewn5.setText(jsonObject.getString("nombrePizza"));
                            textViewi5.setText(textViewi5.getText().toString() + jsonObject.getString("ingredientes"));
                            textViewp5.setText(textViewp5.getText().toString() + jsonObject.getString("precio"));
                        }else if (i==5){
                            jsonObject = response.getJSONObject(i);
                            textViewn6.setText(jsonObject.getString("nombrePizza"));
                            textViewi6.setText(textViewi6.getText().toString() + jsonObject.getString("ingredientes"));
                            textViewp6.setText(textViewp6.getText().toString() + jsonObject.getString("precio"));
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}
