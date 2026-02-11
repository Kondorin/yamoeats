package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailsUser extends AppCompatActivity {
    String usuario,nombre,apellido,contrasena,celular,direccion;
    Button editarCelular,editarDireccion;
    Button enviarCelular,enviarDireccion;
    RequestQueue requestQueue;
    MediaPlayer click;
    Button detallesOrdenar,cerrarSesion;
    EditText etCelular, etDireccion;
    TextView tvnombre, tvapellido;
    ImageView imagenusuario;
    String celularmod, direccionmod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);
        editarCelular = findViewById(R.id.cambiarCelular);
        editarDireccion = findViewById(R.id.cambiarDireccion);
        enviarCelular = findViewById(R.id.enviarCelular);
        enviarDireccion = findViewById(R.id.enviarDireccion);
        etCelular = findViewById(R.id.etCambiarCelular);
        etDireccion = findViewById(R.id.etCambiarDireccion);
        tvnombre = findViewById(R.id.txvNombre);
        tvapellido = findViewById(R.id.txvApellido);
        cerrarSesion = findViewById(R.id.btnCerrarSesion2);
        imagenusuario = findViewById(R.id.imagenUsuario);
        click = MediaPlayer.create(this,R.raw.click);

        enviarDireccion.setVisibility(View.INVISIBLE);
        enviarCelular.setVisibility(View.INVISIBLE);

        detallesOrdenar = findViewById(R.id.btnDetallesOrdenar);

        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        usuario = preferences.getString("usuario", "micorreo@gmail.com");

        datosUsuario("https://yamoeats.000webhostapp.com/yamoeats/datosusuario.php?email="+ usuario);


        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                finish();
            }
        });

        detallesOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsUser.this, LoggedIn.class);
                click.start();
                startActivity(i);
                finish();
            }
        });

        editarCelular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCelular.setEnabled(true);
                editarCelular.setVisibility(View.INVISIBLE);
                enviarCelular.setVisibility(View.VISIBLE);
            }
        });

        editarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDireccion.setEnabled(true);
                editarDireccion.setVisibility(View.INVISIBLE);
                enviarDireccion.setVisibility(View.VISIBLE);
            }
        });

        enviarCelular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                celularmod = etCelular.getText().toString();
                direccionmod = "";
                cambiarDatos("https://yamoeats.000webhostapp.com/yamoeats/modificardatos.php");
                editarCelular.setVisibility(View.VISIBLE);
                enviarCelular.setVisibility(View.INVISIBLE);
                etCelular.setEnabled(false);
            }
        });

        enviarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccionmod = etDireccion.getText().toString();
                celularmod = "";
                cambiarDatos("https://yamoeats.000webhostapp.com/yamoeats/modificardatos.php");
                editarDireccion.setVisibility(View.VISIBLE);
                enviarCelular.setVisibility(View.INVISIBLE);
                etDireccion.setEnabled(false);
            }
        });

        Picasso.with(this).load("https://yamoeats.000webhostapp.com/imagesusuarios/" + usuario +".png")
                .error(R.mipmap.errorimg)
                .fit().centerCrop().into(imagenusuario);
    }

    public void cambiarDatos(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Dato Modificado", Toast.LENGTH_SHORT).show();
                DetailsUser.this.recreate();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailsUser.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("celular", celularmod);
                parametros.put("direccion", direccionmod);
                parametros.put("email", usuario);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void datosUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombre = jsonObject.getString("nombre");
                        apellido = jsonObject.getString("apellido");
                        celular = jsonObject.getString("celular");
                        direccion = jsonObject.getString("direccion");
                        tvnombre.setText(nombre);
                        tvapellido.setText(apellido);
                        etCelular.setText(celular);
                        etDireccion.setText(direccion);

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
