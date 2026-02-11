package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class PreparacionPizzas extends AppCompatActivity {
    String pizzaElegida;
    TextView textViewPizzaElegida;
    private WebView webView;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparacion_pizzas);

        Bundle datos = this.getIntent().getExtras();
        pizzaElegida = datos.getString("pizzaElegida");
        textViewPizzaElegida = findViewById(R.id.textViewPizzaElegida);
        webView = (WebView) findViewById(R.id.preparacionPizzas);
        regresar = findViewById(R.id.regresarMenuPizzas);

        textViewPizzaElegida.setText(pizzaElegida);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(pizzaElegida);


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
