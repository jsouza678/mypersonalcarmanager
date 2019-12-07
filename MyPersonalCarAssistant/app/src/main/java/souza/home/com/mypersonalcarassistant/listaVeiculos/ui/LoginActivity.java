package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.onboarding.MainOnboarding;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;

public class LoginActivity extends AppCompatActivity {

    private Button login_bt;
    private TextView tv_cadastro;
    BancoController crud;
    Cursor cursor;
    Cursor cursor2;
    String teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_bt = findViewById(R.id.login_button);
        tv_cadastro = findViewById(R.id.tv_cadastro);

        crud = new BancoController(getBaseContext());
        cursor = crud.carregaDataManut();
        cursor2 = crud.carregaData();


        tv_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });



        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!cursor2.moveToFirst()) {
                    Toast.makeText(LoginActivity.this, "Conta não encontrada! Você será redirecionado ao cadastro!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                    startActivity(i);
                } else if (!cursor.moveToFirst()) {
                    Toast.makeText(LoginActivity.this, "Conta não finalizada! Você continuará de onde parou!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MaintenanceActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(LoginActivity.this, MainOnboarding.class);
                    startActivity(i);
                }
            }




        });
    }

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
        finishAffinity();
    }
}
