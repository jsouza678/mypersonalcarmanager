package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;

public class MenuActivity extends AppCompatActivity {

    private static AlertDialog alerta;
    private static String m_Text = "";
    private static BancoController crud;
    private static Cursor cursor;
    private static String strDateManutencao;
    private static Calendar cal, cal_bd, cal_bd_6_months, cal_today;
    private static int day, month, year;
    private static TextView dis1;
    private static String data_bd;
    private static String data_nova = "";
    private static DateFormat sdf;
    private static boolean dateSet;
    public static Fragment frag;
    public ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        backButton = findViewById(R.id.back_button);


        Toolbar toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Personal Car Manager");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(LoginActivity.class);
            }
        });

    }
    
    @Override
    public void onBackPressed() {
// super.onBackPressed();
        openNewActivity(LoginActivity.class);
// Not calling **super**, disables back button in current screen.
    }

    public void openNewActivity(Class c){
        Intent it = new Intent(this, c);
        startActivity(it);
    }
}

