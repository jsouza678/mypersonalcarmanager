package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.Extras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import souza.home.com.mypersonalcarassistant.R;

public class DirecaoDefensivaActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcao_defensiva);


        wv = findViewById(R.id.web_view2);

        wv.loadUrl("https://doutormultas.com.br/30-dicas-direcao-defensiva/");


    }
}
