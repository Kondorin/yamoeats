package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextemail, editTextpassword;
    MediaPlayer click;
    Button buttonLogin, btnAjustes;
    String usuario,password;
    RequestQueue requestQueue;
    ProgressBar progressBar;
    String email,nombre,tipoUsuario, status, pizza;
    int noPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextemail = findViewById(R.id.editTextEmail);
        editTextpassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.btnLogin);
        btnAjustes = findViewById(R.id.btnAjustes);
        click = MediaPlayer.create(this,R.raw.click);

        progressBar = findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.INVISIBLE);
        //final Sprite doubleBounce = new DoubleBounce();
        recuperarPreferencias();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=editTextemail.getText().toString();
                password=editTextpassword.getText().toString();
                click.start();
                if (!usuario.isEmpty() && !password.isEmpty()){

                    progressBar.setVisibility(View.VISIBLE);
                    iniciarSesion("http://yamoeats.000webhostapp.com/yamoeats/validar_usuario.php");

                }else if(usuario.isEmpty() && password.isEmpty()){
                    editTextpassword.setError("Ingrese contraseña");
                    editTextemail.setError("Ingrese usuario");
                }else if (usuario.isEmpty()){
                   editTextemail.setError("Ingrese usuario");

                }else if(password.isEmpty()){
                    editTextpassword.setError("Ingrese contraseña");

                }

            }
        });

        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Opciones.class);
                startActivity(i);
            }
        });

    }

    public void revisarPedidos(String URL, final String nombre){

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);

                        status = jsonObject.getString("status");
                        pizza = jsonObject.getString("nombrePizza");
                        noPedido = jsonObject.getInt("noPedido");
                        if (status.equalsIgnoreCase("pendiente")){
                            Intent intent = new Intent(MainActivity.this, DetailsPedido.class);
                            intent.putExtra("pizza", pizza);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("option", 1);

                            Toast.makeText(getApplicationContext(),"PIZZA PENDIENTE",Toast.LENGTH_SHORT).show();
                            intent.putExtra("botonactivado", 1);
                            startActivity(intent);

                        }else{
                            Intent intent = new Intent(MainActivity.this, DetailsUser.class);
                            intent.putExtra("email", email);
                            startActivity(intent);

                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No hay pedidos recientes", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,DetailsUser.class);
                i.putExtra("email",email);
                startActivity(i);


            }

        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }



    public void tipoUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        tipoUsuario = jsonObject.getString("tipoUsuario");
                        nombre = jsonObject.getString("nombre");
                        email = jsonObject.getString("email");
                        if (tipoUsuario.equalsIgnoreCase("administrador")){

                            Intent intent  = new Intent(MainActivity.this,LoggedInAdmin.class);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("email", email);
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);

                        }else{

                            progressBar.setVisibility(View.INVISIBLE);
                            revisarPedidos("http://yamoeats.000webhostapp.com/yamoeats/verificar_status.php?email=" + email,nombre);


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
    public void registrar (View view){
        Intent i = new Intent(this, RegistrarUsuario.class);
        click.start();
        startActivity(i);
    }



    public void iniciarSesion (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    guardarPreferencias();
                    tipoUsuario("http://yamoeats.000webhostapp.com/yamoeats/buscar_tipousuario.php?usuario=" + usuario);
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario",usuario);
                parametros.put("password", password);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("password",password);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        editTextemail.setText(preferences.getString("usuario", ""));
        editTextpassword.setText(preferences.getString("password", ""));
    }


}
