package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.data.model.User;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.MenuActivity;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.DBClass;

import static android.text.InputType.TYPE_CLASS_NUMBER;

public class HomeFragment extends Fragment {

    protected AlertDialog alerta;
    private String m_Text;
    private BancoController crud;
    private Cursor cursor;
    private Validation validation;
    private FloatingActionButton fab_oleo, fab_filtros;
    protected TextView km_txtview;


    private static TextInputLayout input;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textNome = root.findViewById(R.id.text_home);
        final TextView textMarca = root.findViewById(R.id.txt_marca);
        final TextView textModelo = root.findViewById(R.id.txt_modelo);
        final TextView textAno = root.findViewById(R.id.txt_ano);
        TextView km_txtview = root.findViewById(R.id.txt_km_atual);

        fab_oleo = root.findViewById(R.id.menu_oleo);

        fab_filtros = root.findViewById(R.id.menu_filtros);


        fab_oleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation.displayDialogOleo(getActivity());
            }
        });

        fab_filtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation.displayDialogFiltros(getActivity());
            }
        });


        BancoController crud = new BancoController( getActivity().getBaseContext() );
        Cursor cursor = crud.carregaData();
        Cursor cursor2 = crud.carregaDataManut();




        textNome.setText("Olá " + cursor.getString(0) + "!");
        textMarca.setText(cursor.getString(1));
        textModelo.setText(cursor.getString(2));
        textAno.setText(cursor.getString(3));
        km_txtview.setText(cursor.getInt(4) + " Km");



        //crud.updateOleo(Integer.parseInt(cursor2.getString(0)));


        Boolean isFirstRun = getContext().getSharedPreferences("PREFERENCE", getContext().MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {

        } else {
            displayDialog(root);
        }

        getContext().getSharedPreferences("PREFERENCE", getContext().MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


        return root;
    }

    public void displayDialog(View v) {
        final AlertDialog.Builder atualizaKm = new AlertDialog.Builder(new ContextThemeWrapper(v.getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog));
        LayoutInflater factory = LayoutInflater.from(v.getContext());
        final View f = factory.inflate(R.layout.custom_dialog, null);

        atualizaKm.setView(f);
        input = new TextInputLayout(v.getContext());
        input = (f.findViewById(R.id.input_km_troca));

        Button negativo = f.findViewById(R.id.n_button);
        Button positivo = f.findViewById(R.id.d_button);


        positivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud = new BancoController(getActivity().getBaseContext());

                m_Text = input.getEditText().getText().toString();

                if (m_Text.equals("")) {
                    Toast.makeText(v.getContext(), "Por favor, digite novamente", Toast.LENGTH_SHORT).show();
                    input.setError("Digite a Km atual");
                } else {
                    cursor = crud.carregaData();
                    int passa = Integer.parseInt(m_Text);
                    if (passa > cursor.getInt(4)) {
                        BancoController crud = new BancoController( getActivity().getBaseContext() );
                        Cursor cursor = crud.carregaData();
                        crud.updateKm(passa);


                        TextView km_txtview = getActivity().findViewById(R.id.txt_km_atual);
                        cursor = crud.carregaData();
                        km_txtview.setText(cursor.getInt(4) + " Km");
                        alerta.dismiss();
                    } else if (passa < cursor.getInt(4)) {
                        input.setError("Km menor do que a atual");
                        Toast.makeText(v.getContext(), "Digite a Km corretamente", Toast.LENGTH_SHORT).show();
                    } else if (passa == cursor.getInt(4)) {
                        input.setError("Km igual a atual");
                        Toast.makeText(v.getContext(), "Digite a Km corretamente", Toast.LENGTH_SHORT).show();
                    } else {
                        input.setError("Erro! Digite corretamente.");

                    }

                }

            }
        });

        negativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });

        alerta = atualizaKm.create();
        alerta.show();
    }

}