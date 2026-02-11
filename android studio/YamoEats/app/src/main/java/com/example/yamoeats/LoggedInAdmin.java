package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoggedInAdmin extends AppCompatActivity {
    TextView bienvenidoadmin;
    RequestQueue requestQueue;
    ListView listView;
    String direccion,nombrePizza,usuario,celular,noPedido2;
    int noPedido;
    private ListView lvProduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;
    MediaPlayer click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_admin);

        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        String email = preferences.getString("usuario", "");

        bienvenidoadmin = findViewById(R.id.bienvenidoadmin);
        bienvenidoadmin.setText("Admin: " + email);
        lvProduct = findViewById(R.id.listView);
        click = MediaPlayer.create(this,R.raw.clickpizza);




        buscarPedidos("http://yamoeats.000webhostapp.com/yamoeats/listapedidos.php");


        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                Toast.makeText(getApplicationContext(), "Pedido Numero: "+view.getTag(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoggedInAdmin.this,EntregarPedido.class);
                i.putExtra("noPedido",view.getTag().toString());
                click.start();
                startActivity(i);
                finish();

            }


        });


    }

    private void buscarPedidos(String URL) {
        mProductList = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        direccion = jsonObject.getString("direccion");
                        nombrePizza = jsonObject.getString("nombrePizza");
                        usuario = jsonObject.getString("usuario");
                        celular = jsonObject.getString("celular");
                        noPedido = jsonObject.getInt("noPedido");
                        //noPedido2 = noPedido + "";

                        mProductList.add(new Product(noPedido,direccion,nombrePizza,usuario,celular));
                        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
                        lvProduct.setAdapter(adapter);


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"NO HAY PEDIDOS PENDIENTES / ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);



    }



}