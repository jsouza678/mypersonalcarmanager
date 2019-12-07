package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import souza.home.com.mypersonalcarassistant.R;

public class OpenActivity extends AppCompatActivity {

    private static int TIME_OUT = 2000; // time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(OpenActivity.this, LoginActivity.class);
                        startActivity(i);


                        finish();
                    }
                }, TIME_OUT);
            }


}
