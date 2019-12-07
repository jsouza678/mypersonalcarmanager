package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.Extras.CTBActivity;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.Extras.DicasActivity;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.Extras.DirecaoDefensivaActivity;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.LoginActivity;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.OpenActivity;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;


public class DashboardFragment extends Fragment {

    GridLayout gl;
    ImageButton ib1, ib2, ib3, ib4;
    private DashboardViewModel dashboardViewModel;
    private BancoController crud;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ib1 = (ImageButton) root.findViewById(R.id.ib1);
        ib2 = (ImageButton) root.findViewById(R.id.ib2);
        ib3 = (ImageButton) root.findViewById(R.id.ib3);
        // para zerar tudo. apertar sai um alert dialog pra confirmar ou n.. se sim, zera e joga pra open activity, se nao, faz nada.

        ib4 = (ImageButton) root.findViewById(R.id.ib4);


        crud = new BancoController(getActivity().getBaseContext());



        Boolean isFirstRun = getContext().getSharedPreferences("PREFERENCE", getContext().MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show sign up activity
            Toast.makeText(getActivity(), "Para zerar seu cadastro, clique no quarto botão!", Toast.LENGTH_LONG)
                    .show();
        } else {

        }

        getContext().getSharedPreferences("PREFERENCE", getContext().MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        gl = root.findViewById(R.id.grid);

        setSingleEvent(gl, ib1, ib2, ib3, ib4);


        return root;
    }

    public void setSingleEvent(final GridLayout gl, final ImageButton ib1, final ImageButton ib2, final ImageButton ib3, final ImageButton ib4){
        for(int i = 0; i<gl.getChildCount(); i++){
            setButton1(ib1);
            setButton2(ib2);
            setButton3(ib3);
            setButton4(ib4);
        }
    }

    public void setButton1(ImageButton ib){
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(CTBActivity.class);
            }
        });
    }

    public void setButton2(ImageButton ib){
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(DirecaoDefensivaActivity.class);
            }
        });
    }

    public void setButton3(ImageButton ib){
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(DicasActivity.class);
            }
        });
    }

    public void setButton4(ImageButton ib){
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.recomeca();
                openNewActivity(OpenActivity.class);
            }
        });
    }

    public void openNewActivity(Class c){
        Intent it = new Intent(getActivity(), c);
        startActivity(it);
    }
}