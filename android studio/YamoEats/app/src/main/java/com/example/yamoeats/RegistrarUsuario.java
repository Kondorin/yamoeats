package com.example.yamoeats;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class RegistrarUsuario extends AppCompatActivity {
    EditText etNombre, etApellido, etEmail, etPass,etCel,etDireccion;
    ImageView iv;
    Button enviar, btnBuscar, btnTomar;
    Bitmap bitmap;
    int PICK_IMAGE_REQUEST = 1;
    String UPLOAD_URL = "https://yamoeats.000webhostapp.com/uploadimage.php";
    String KEY_IMAGE = "foto";
    String KEY_NOMBRE ="nombre";
    MediaPlayer click,clickpizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        /**if (android.os.Build.VERSION.SDK_INT > 9) {

         StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

         StrictMode.setThreadPolicy(policy);

         }**/
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        etCel = findViewById(R.id.etCelular);
        etDireccion = findViewById(R.id.etDireccion);
        enviar = findViewById(R.id.btnEnviar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnTomar = findViewById(R.id.btnTomar);
        iv = findViewById(R.id.imageView);
        click = MediaPlayer.create(this,R.raw.click);
        clickpizza = MediaPlayer.create(this,R.raw.clickpizza);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                if (etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() ||
                        etEmail.getText().toString().isEmpty() || etPass.getText().toString().isEmpty() || etCel.getText().toString().isEmpty()
                        || etDireccion.getText().toString().isEmpty()){
                    Toast.makeText(RegistrarUsuario.this, "Hay un campo vacio", Toast.LENGTH_SHORT).show();

                }else{
                    if (null!=iv.getDrawable()){
                        uploadImagen();
                        ejecutarServicio("http://yamoeats.000webhostapp.com/yamoeats/registrarusuario.php");

                    }else{
                        Toast.makeText(RegistrarUsuario.this, "Falta imagen del usuario", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickpizza.start();
                showFileChooser();
            }
        });

        btnTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                clickpizza.start();
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, PICK_IMAGE_REQUEST);
                }
            }
        });

    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imagenBytes = baos.toByteArray();
        String encodedImagen = Base64.encodeToString(imagenBytes, Base64.DEFAULT);

        return encodedImagen;

    }

    public void uploadImagen(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(RegistrarUsuario.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegistrarUsuario.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String imagen = getStringImagen(bitmap);
                String nombre = etEmail.getText().toString().trim();

                Map<String, String> params = new Hashtable<String, String>();
                params.put(KEY_IMAGE, imagen);
                params.put(KEY_NOMBRE, nombre);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selecciona una imagen"),PICK_IMAGE_REQUEST);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data!= null & data.getData() != null){
            Uri filePath = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            iv.setImageBitmap(bitmap);


        }
    }

    private void ejecutarServicio(String URL) {
        final ProgressDialog cargando = ProgressDialog.show(RegistrarUsuario.this,"Subiendo...","Espere por favor");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cargando.dismiss();
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegistrarUsuario.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cargando.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre",etNombre.getText().toString());
                parametros.put("apellido",etApellido.getText().toString());
                parametros.put("contrasena",etPass.getText().toString());
                parametros.put("email",etEmail.getText().toString());
                parametros.put("celular",etCel.getText().toString());
                parametros.put("direccion",etDireccion.getText().toString());
                return parametros;


            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


