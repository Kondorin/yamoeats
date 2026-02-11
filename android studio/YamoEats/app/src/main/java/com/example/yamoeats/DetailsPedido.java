package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailsPedido extends AppCompatActivity {
    SeekBar seekBar;
    TextView textViewCantidad,textViewPrecio,textViewSubtotal,textViewTotal,
           textViewCodigo, textViewNombrePizza, textViewSize, textViewIngredientes ;
    double subtotal, total, precio;
    MediaPlayer click;
    String nombre, saborPizza, codigoPizza, codigo, size, ingredientes, cantidad, direccion, celular, email;
    RequestQueue requestQueue;
    Button detallesOrdenar,cancelarPedido,ubicarRepartidor;
    ImageView imagenPizza;
    SharedPreferences preferences;
    int noPedido;
    String pizza;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_pedido);

        textViewCantidad =(TextView) findViewById(R.id.textViewCantidad);
        textViewPrecio = (TextView)findViewById(R.id.textViewPrecio);
        precio = Integer.parseInt(textViewPrecio.getText().toString());
        textViewSubtotal =(TextView) findViewById(R.id.textViewSubtotal);
        textViewTotal =(TextView) findViewById(R.id.textViewTotal);
        textViewCodigo =(TextView) findViewById(R.id.textViewCodigo);
        textViewNombrePizza = (TextView)findViewById(R.id.textViewNombrePizza);
        textViewSize =(TextView) findViewById(R.id.textViewSize);
        textViewIngredientes = (TextView)findViewById(R.id.textViewIngredientes);
        detallesOrdenar = findViewById(R.id.btnDetallesOrdenar);
        click = MediaPlayer.create(this,R.raw.click);
        imagenPizza = findViewById(R.id.imagenPizzaDetails);
        ubicarRepartidor = findViewById(R.id.ubicarRepartidor);
        cancelarPedido = findViewById(R.id.btnCancelarPedido);
        seekBar = (SeekBar) findViewById(R.id.seekbarCantidad);


        ubicarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsPedido.this,PosicionRepartidor.class);
                intent.putExtra("noPedido",noPedido);
                click.start();
                startActivity(intent);
            }
        });

        Bundle datos = this.getIntent().getExtras();
        pizza = datos.getString("pizza");


        int botonactivado = datos.getInt("botonactivado");

        buscarProducto("https://yamoeats.000webhostapp.com/yamoeats/buscar_pizza.php?pizza=" + pizza);
        if (botonactivado==1){
            preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
            email= preferences.getString("usuario", "");
            revisarPedidos("http://yamoeats.000webhostapp.com/yamoeats/verificar_status.php?email=" + email,email);
            detallesOrdenar.setVisibility(View.INVISIBLE);
            seekBar.setVisibility(View.INVISIBLE);
            preferences=getSharedPreferences("cantidad",Context.MODE_PRIVATE);
            cantidad = preferences.getString("cantidad", "");
            textViewCantidad.setText(cantidad);
            subtotal = (Integer.parseInt(textViewPrecio.getText().toString())) * Integer.parseInt(cantidad);
            total = subtotal * 1.16;
            textViewSubtotal.setText("$"+subtotal+"");
            textViewTotal.setText("$"+total+"");
        }else{
            textViewSubtotal.setText("$"+precio+"");
            textViewTotal.setText("$"+String.valueOf(precio*1.16));
            seekBar.setProgress(1);
            seekBar.setMax(10);
            cancelarPedido.setVisibility(View.INVISIBLE);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressChanged = 1;
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressChanged = progressChanged + progress;
                    textViewCantidad.setText(String.valueOf(progress));
                    subtotal= precio* Integer.parseInt(textViewCantidad.getText().toString());
                    total = subtotal * 1.16;
                    textViewSubtotal.setText("$"+subtotal+"");
                    textViewTotal.setText("$"+total+"");



                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


        }


        Picasso.with(this).load("https://yamoeats.000webhostapp.com/yamoeats/images/" + pizza.replace(" ","") + ".jpg")
                .error(R.mipmap.errorimg)
                .fit().centerCrop().into(imagenPizza);
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        email= preferences.getString("usuario", "");
        datosUsuario("https://yamoeats.000webhostapp.com/yamoeats/datosusuario.php?email=" +email);


        detallesOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                ordenar("https://yamoeats.000webhostapp.com/yamoeats/ordenar.php");
                detallesOrdenar.setVisibility(View.INVISIBLE);
                cancelarPedido.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.INVISIBLE);


            }
        });

        cancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                revisarPedidos("http://yamoeats.000webhostapp.com/yamoeats/verificar_status.php?email=" + "alejandro","alejandro");

                cancelar("https://yamoeats.000webhostapp.com/yamoeats/verificar_statuscancelar.php?noPedido="+noPedido+"",noPedido);

            }
        });








    }


    public void ordenar (String URL){
        SharedPreferences preferences2=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        preferences2=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        final String usuario= preferences2.getString("usuario", "");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Orden Pedida", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailsPedido.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
                preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                String usuario= preferences.getString("usuario", "");
                parametros.put("usuario",usuario);
                parametros.put("codigoPizza", codigoPizza);
                parametros.put("nombrePizza", pizza);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();

                String fecha = dateFormat.format(date);
                parametros.put("fechaCompra",fecha);
                parametros.put("cantidad", textViewCantidad.getText().toString());
                parametros.put("total",textViewTotal.getText().toString());
                parametros.put("direccion", direccion);
                parametros.put("celular", celular);
                parametros.put("size",size);
                parametros.put("status", "pendiente");
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    public void cancelar(String URL, final int noPedido){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String status="";
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        status = jsonObject.getString("status");


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (status.equalsIgnoreCase("pendiente")){
                        actualizarStatus("https://yamoeats.000webhostapp.com/yamoeats/cancelarstatus.php",noPedido);
                        Intent intent = new Intent(DetailsPedido.this, LoggedIn.class);
                        preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                        String email= preferences.getString("usuario", "");
                        intent.putExtra("email",email);
                        startActivity(intent);

                        finish();

                    }else{
                        //puede que aqui no muestre el boton AQUI ALERTA ALERTA !!!
                        Intent intent = new Intent(DetailsPedido.this, LoggedIn.class);
                        detallesOrdenar.setVisibility(View.VISIBLE);
                        cancelarPedido.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Pedido fue entregado o cancelado por el rapartidor", Toast.LENGTH_SHORT).show();
                        preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                        String email= preferences.getString("usuario", "");
                        intent.putExtra("email",email);
                        startActivity(intent);
                        finish();

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




    public void revisarPedidos(String URL, final String nombre){

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                        noPedido = jsonObject.getInt("noPedido");
                        cantidad =  jsonObject.getString("cantidad");
                        preferences=getSharedPreferences("cantidad", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("cantidad",cantidad);
                        editor.commit();
                        preferences=getSharedPreferences("codigoPedidos", Context.MODE_PRIVATE);
                        editor.putInt("noPedido",noPedido);
                        editor.commit();


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No hay pedidos recientes", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DetailsPedido.this,DetailsUser.class);
                i.putExtra("email",email);
                startActivity(i);


            }

        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    private void buscarProducto(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        textViewNombrePizza.setText(jsonObject.getString("nombrePizza"));

                        textViewNombrePizza.setText(jsonObject.getString("nombrePizza"));

                        saborPizza = jsonObject.getString("nombrePizza");
                        codigoPizza = jsonObject.getString("codigo");
                        textViewCodigo.setText(jsonObject.getString("codigo"));
                        textViewSize.setText(jsonObject.getString("size"));
                        textViewIngredientes.setText(jsonObject.getString("ingredientes"));
                        textViewPrecio.setText("$"+jsonObject.getString("precio"));
                        textViewSubtotal.setText("$" + Integer.parseInt(jsonObject.getString("precio"))*Integer.parseInt(textViewCantidad.getText().toString())+"");
                        double precioTemp = Double.parseDouble(jsonObject.getString("precio"))*1.16;
                        String precioTemp2 = precioTemp +"";
                        //textViewTotal.setText(precioTemp2);
                        size = jsonObject.getString("size");



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

    public void datosUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        direccion = jsonObject.getString("direccion");
                        celular = jsonObject.getString("celular");

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

    public void actualizarStatus(String URL, final int noPedido){
        //falta el codigo para mandar el noPedido y que actualice el status

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("noPedido",noPedido+"");

                return parametros;


            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
