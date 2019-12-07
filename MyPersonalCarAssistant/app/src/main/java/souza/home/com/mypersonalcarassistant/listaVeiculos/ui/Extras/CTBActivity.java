package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.Extras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import souza.home.com.mypersonalcarassistant.R;

public class CTBActivity extends AppCompatActivity {
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctb);
        wv = findViewById(R.id.web_view);

        wv.loadUrl("http://www.planalto.gov.br/ccivil_03/leis/l9503.htm");

    }
}
