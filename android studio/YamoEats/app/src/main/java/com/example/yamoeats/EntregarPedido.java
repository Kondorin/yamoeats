package com.example.yamoeats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class EntregarPedido extends AppCompatActivity {
    RequestQueue requestQueue;
    ImageView imagenPizzaAdmin;
    String noPedido,nombrePizza,usuario,direccion,celular,total;
    TextView tvpedido,tvpizza, tvusuario, tvdireccion, tvcelular,tvtotal;
    Button cancelar,finalizar,enviarUbicacion;
    MediaPlayer click;
    LocationManager gpsManager;
    private static Boolean gpsOrNot;
    Double latitude, longitude;
    String lat, lon,latitud, longitud;
    private static final int GPS_LOCATION = 64;
    TextView textoLon, textoLat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregar_pedido);

        Bundle datos = this.getIntent().getExtras();
        noPedido = datos.getString("noPedido");
        tvpedido = findViewById(R.id.pedidoAdmin);
        tvpizza = findViewById(R.id.pizzaAdmin);
        tvusuario = findViewById(R.id.usuarioAdmin);
        tvdireccion = findViewById(R.id.direccionAdmin);
        tvcelular = findViewById(R.id.celularAdmin);
        tvtotal = findViewById(R.id.totalAdmin);
        cancelar = findViewById(R.id.btnCancelarAdmin);
        enviarUbicacion = findViewById(R.id.enviarUbicacion);
        finalizar = findViewById(R.id.btnFinalizarAdmin);
        click = MediaPlayer.create(this,R.raw.click);

        imagenPizzaAdmin = findViewById(R.id.imagenPizzaAdmin);
        tvpedido.setText(noPedido);
        tvpizza.setText(nombrePizza);
        tvusuario.setText(usuario);
        tvdireccion.setText(direccion);
        tvcelular.setText(celular);

        buscarPedido("https://yamoeats.000webhostapp.com/yamoeats/buscarpedido.php?noPedido="+noPedido);//convertirlo a integer al recibirlo


        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int noPedido2 = Integer.parseInt(noPedido);
                click.start();
                cancelar("https://yamoeats.000webhostapp.com/yamoeats/finalizarstatus.php?noPedido="+noPedido+"",noPedido2);
                Intent i = new Intent(EntregarPedido.this,LoggedInAdmin.class);
                startActivity(i);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int noPedido2 = Integer.parseInt(noPedido);
                click.start();
                cancelar("https://yamoeats.000webhostapp.com/yamoeats/cancelarstatus.php?noPedido="+noPedido+"",noPedido2);
                Intent i = new Intent(EntregarPedido.this,LoggedInAdmin.class);
                startActivity(i);
                finish();


            }
        });

        enviarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        ubicacionRepartidor("https://yamoeats.000webhostapp.com/yamoeats/ubicacionrepartidor.php?noPedido="+noPedido);
                    }
                }, 2000);
                //Toast.makeText(getApplicationContext(), lat+lon, Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void ubicacionRepartidor(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Ubicacion actualizada", Toast.LENGTH_SHORT).show();

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
                parametros.put("lastPosition",lat + "," + lon);
                //parametros.put("lastPosition","holaAmigo");

                return parametros;


            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void finalizar(String URL, final int noPedido){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Pedido Entregado", Toast.LENGTH_SHORT).show();

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

    public void buscarPedido(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        nombrePizza = jsonObject.getString("nombrePizza");
                        usuario = jsonObject.getString("usuario");
                        direccion = jsonObject.getString("direccion");
                        celular = jsonObject.getString("celular");
                        total = jsonObject.getString("total");

                        tvpizza.setText(nombrePizza);
                        tvusuario.setText(usuario);
                        tvdireccion.setText(direccion);
                        tvcelular.setText(celular);
                        tvtotal.setText(total);

                        Picasso.with(EntregarPedido.this).load("https://yamoeats.000webhostapp.com/yamoeats/images/" + nombrePizza + ".jpg")
                                .error(R.mipmap.errorimg)
                                .fit().centerCrop().into(imagenPizzaAdmin);

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

    public void cancelar(String URL, final int noPedido){
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

    //LO DEL GPS -------------------------------------------------------------------------------------------------------------------------------------------------

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(EntregarPedido.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(EntregarPedido.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EntregarPedido.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, GPS_LOCATION);

        } else {

            // Se inicializa la ubicación y se mandan las coordenadas al textView
            locationStart();
            lat = String.valueOf(latitude);
            lon = String.valueOf(longitude);
            //textoLat.setVisibility(textoLat.VISIBLE);
            //textoLon.setVisibility(textoLon.VISIBLE);
            //latitud.setText(String.valueOf(latitude));
            //longitud.setText(String.valueOf(longitude));


        }
    }



    private void locationStart() {
        gpsManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Localizator localizador = new Localizator();
        localizador.setMainActivity(EntregarPedido.this);


        gpsOrNot = gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsOrNot) {
            Intent settingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingIntent);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, GPS_LOCATION);

            return;
        }

        gpsManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, localizador);
        gpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, localizador);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GPS_LOCATION) {
            //Si los permisos y el codigo de seguridad coinsiden se inicia la localización
            locationStart();
            return;
        }
    }

    // Clase localizador
    public class Localizator implements LocationListener {
        EntregarPedido ma;

        public void setMainActivity(EntregarPedido main) {
            this.ma = main;
        }

        // Este metodo actualiza e tiempo real las coordenadas del gps
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            lat = String.valueOf(latitude);
            lon = String.valueOf(longitude);

            latitud = String.valueOf(latitude);
            longitud=String.valueOf(longitude);

        }

        // Acciones a relizar en diferentes estatus del gps
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(ma, "GPS activado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(ma, "Activa GPS", Toast.LENGTH_SHORT).show();
        }
    }

}
