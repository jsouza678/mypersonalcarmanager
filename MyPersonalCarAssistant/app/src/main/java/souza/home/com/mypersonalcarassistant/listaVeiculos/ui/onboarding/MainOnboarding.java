package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.MenuActivity;

public class MainOnboarding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_onboarding);

        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        if(!preferences.getBoolean("onboarding_complete",false)){

            Intent onboarding = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding);

            finish();
            return;
        }else{
            Intent onboarding = new Intent(this, MenuActivity.class);
            startActivity(onboarding);
        }
    }
}
