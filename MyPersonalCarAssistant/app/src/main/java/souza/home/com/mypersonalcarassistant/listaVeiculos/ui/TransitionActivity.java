package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import souza.home.com.mypersonalcarassistant.R;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);



           // Intent it = new Intent(this, CadastroActivity.class);
           // startActivity(it);

            Intent it = new Intent(this, MenuActivity.class);
            startActivity(it);

    }

}
