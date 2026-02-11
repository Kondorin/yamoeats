package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
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

public class PosicionRepartidor extends AppCompatActivity {

    private WebView webView;
    RequestQueue requestQueue;
    String lastPosition;
    MediaPlayer click;
    int noPedido;
    Button regresarPedido, refrescarUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posicion_repartidor);

        webView = (WebView) findViewById(R.id.webView);
        click = MediaPlayer.create(this,R.raw.click);
        Bundle datos = this.getIntent().getExtras();
        noPedido = datos.getInt("noPedido");
        regresarPedido = findViewById(R.id.regresarPedido);
        refrescarUbicacion = findViewById(R.id.refrescarUbicacion);



        buscarPedido("http://yamoeats.000webhostapp.com/yamoeats/buscarpedido.php?noPedido=" + noPedido);
       // SharedPreferences preferences=getSharedPreferences("ubicacion",Context.MODE_PRIVATE);
       // lastPosition = preferences.getString("ubicacion", "");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                webView.setWebViewClient(new WebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("http://maps.google.com/?q="+lastPosition);
            }
        }, 2000);



        regresarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                finish();
            }
        });

        refrescarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                buscarPedido("http://yamoeats.000webhostapp.com/yamoeats/buscarpedido.php?noPedido=" + noPedido);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        webView.setWebViewClient(new WebViewClient());
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("http://maps.google.com/?q="+lastPosition);
                    }
                }, 2000);
            }
        });


    }

    public void buscarPedido(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        lastPosition = jsonObject.getString("lastPosition");



                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    //Aqui podria preguntar que si esta vacio"" o =null entonces que te muestre la ubicacion de la pizzeria en la variable lastPosition

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
