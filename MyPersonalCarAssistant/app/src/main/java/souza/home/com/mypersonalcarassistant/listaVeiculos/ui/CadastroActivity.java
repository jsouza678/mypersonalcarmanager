package souza.home.com.mypersonalcarassistant.listaVeiculos.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import souza.home.com.mypersonalcarassistant.R;
import souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.BancoController;
import souza.home.com.mypersonalcarassistant.data.network.retrofit.ApiService;
import souza.home.com.mypersonalcarassistant.data.network.response.AnosResponse;
import souza.home.com.mypersonalcarassistant.data.network.response.MarcasResponse;
import souza.home.com.mypersonalcarassistant.data.network.response.VeiculosResponse;
import souza.home.com.mypersonalcarassistant.data.network.response.VeiculosResult;
import souza.home.com.mypersonalcarassistant.listaVeiculos.Adapters.ListaMarcasAdapter;
import souza.home.com.mypersonalcarassistant.listaVeiculos.Adapters.ListaVeiculosAdapter;

public class  CadastroActivity extends AppCompatActivity {


    ListaVeiculosAdapter adapter;
    ListaMarcasAdapter adapterM;
    List<VeiculosResponse> list = new ArrayList<>();
    List<AnosResponse> listA = new ArrayList<>();
    List<MarcasResponse> listM = new ArrayList<>();
    String idMarca, marca, model, ano;


    private EditText editEmail, editNome, editKm;
    private TextInputLayout layoutName, layoutEmail, layoutKm;



    //Spinner
    private Spinner spinner1, spinner2, spinner3;
    private Button btnSubmit;
    private String teste;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        layoutName = (TextInputLayout)findViewById(R.id.name_txt_layout);
        layoutEmail = (TextInputLayout)findViewById(R.id.email_txt_layout);
        layoutKm = (TextInputLayout)findViewById(R.id.km_txt_layout);

        editEmail = findViewById(R.id.txt_email);
        editNome = findViewById(R.id.txt_name);
        editKm = findViewById(R.id.txt_kmm);

        spinner2 = findViewById(R.id.spinner2);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        addItemsOnSpinner1(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                //Toast.makeText(CadastroActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                idMarca = response.toString();
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isInputEditTextEmail(editEmail, layoutEmail, "E-mail incorreto!")
                        && isInputEditTextEmpy( editNome,layoutName,"Preencha todos os dados")
                        && isInputEditTextEmpy( editKm,layoutKm,"Preencha todos os dados")){
                        cadastro(marca, model, ano);
                        openNewActivity();
                }
            }
        });
        // Toolbar toolbar = findViewById(R.id.my_toolbar);
        //setSupportActionBar(toolbar);


    }

    public boolean isInputEditTextEmail(EditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextEmpy(EditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /////////////////////////////////////// /////////////////////////////////
    // add items into spinner dynamically
    public void addItemsOnSpinner1(final VolleyCallback responseM) {
        listM = new ArrayList<>();

        ApiService.getInstance().getMarcas().enqueue(new Callback<List<MarcasResponse>>() {
            @Override
            public void onResponse(Call<List<MarcasResponse>> call, final Response<List<MarcasResponse>> response) {
                listM = response.body();
                spinner1 = (Spinner) findViewById(R.id.spinner1);
                ArrayAdapter<MarcasResponse> adapterM = new ArrayAdapter<>(CadastroActivity.this,
                        android.R.layout.simple_spinner_item, listM);
                adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapterM);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(CadastroActivity.this, listM.get(position).getCodigo(), Toast.LENGTH_SHORT).show();
                        responseM.onSuccess(response.body().get(spinner1.getSelectedItemPosition()).getCodigo());
                        list.clear();
                        addItemsOnSpinner2(String.valueOf(idMarca));
                        marca = spinner1.getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                // after getting new data you have to notify your adapter that your data set is changed like below.
                adapterM.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<MarcasResponse>> call, Throwable t) {
            }
        });
    }
/////////////////////////////////////////////////////////////////////////
    public void addItemsOnSpinner2(final String marca) {

        ApiService.getInstance().getModels(marca).enqueue(new Callback<VeiculosResult>() {
            @Override
            public void onResponse(Call<VeiculosResult> call, final Response<VeiculosResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    spinner2 = (Spinner) findViewById(R.id.spinner2);
                    list.addAll(response.body().getModelos());
                    ArrayAdapter<VeiculosResponse> adapter = new ArrayAdapter<>(CadastroActivity.this,
                            android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String modeloC = response.body().getModelos().get(spinner2.getSelectedItemPosition()).getCodigo();
                            addItemsOnSpinner3(marca, modeloC);
                            model = spinner2.getSelectedItem().toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    // after getting new data you have to notify your adapter that your data set is changed like below.
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<VeiculosResult> call, Throwable t) {
            }
        });
    }
/////////////////////////////////////////////////////////////////////////
    public void addItemsOnSpinner3(String marca, String modelo) {
        listA = new ArrayList<>();
        ApiService.getInstance().getAnos(marca, modelo).enqueue(new Callback<List<AnosResponse>>() {
            @Override
            public void onResponse(Call<List<AnosResponse>> call, final Response<List<AnosResponse>> response) {
                listA = response.body();
                spinner3 = (Spinner) findViewById(R.id.spinner3);
                ArrayAdapter<AnosResponse> adapterA = new ArrayAdapter<>(CadastroActivity.this,
                        android.R.layout.simple_spinner_item, listA);
                adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(adapterA);
                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ano = spinner3.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                // after getting new data you have to notify your adapter that your data set is changed like below.
                adapterA.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<AnosResponse>> call, Throwable t) {
            }
        });
    }
/////////////////////////////////////////////////////////////////////////
    public interface VolleyCallback {
        void onSuccess(String response);
    }

    public void addListenerOnButton() {

    }

    public void openNewActivity(){
        Intent it = new Intent(this, MaintenanceActivity.class);
        startActivity(it);
    }

    public void cadastro(String marca, String modelo, String ano){
        BancoController crud = new BancoController(getBaseContext());
        //EditKm = findViewById(R.id.km_txt);


        String nome = layoutName.getEditText().getText().toString().trim();
        String email = layoutEmail.getEditText().getText().toString().trim();
        int kmAtual = Integer.parseInt(layoutKm.getEditText().getText().toString());

        String resultado;

        resultado = crud.insereDados(nome, email, kmAtual, marca, modelo, ano);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }
}