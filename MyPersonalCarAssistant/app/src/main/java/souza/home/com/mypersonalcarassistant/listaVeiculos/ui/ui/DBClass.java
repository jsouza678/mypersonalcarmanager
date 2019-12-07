package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import souza.home.com.mypersonalcarassistant.data.model.User;

public class DBClass extends SQLiteOpenHelper {

    protected static final String TABELA = "user";
    protected static final String nome = "nome";
    protected static final String email = "email";
    protected static final String kmAtual = "km_atual";
    protected static final String marca = "marca";
    protected static final String id = "_id";
    protected static final String modelo = "modelo";
    protected static final String ano = "ano";

    protected static final String TABELA2 = "maintenance";
    protected static final String km_filtros = "km_filtros";
    protected static final String km_oleo = "km_oleo";
    protected static final String troca_filtros = "data_filtros";
    protected static final String troca_oleo = "data_oleo";

    protected static final String NOME_BANCO = "userT.db";

    protected static final int VERSAO_BANCO = 7;


    public DBClass(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE " + TABELA + "("
                + id + " integer primary key autoincrement,"
                + nome + " text,"
                + email + " text,"
                + kmAtual + " integer,"
                + marca + " text,"
                + modelo + " text,"
                + ano + " text" + ")";

        String sql2 = "CREATE TABLE " + TABELA2 + "("
                + id + " integer primary key autoincrement,"
                + km_oleo + " integer, "
                + km_filtros + " integer, "
                + troca_oleo + " text,"
                + troca_filtros + " text " + ")";

                db.execSQL(sql1);
                db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA2);
        onCreate(db);
    }



}