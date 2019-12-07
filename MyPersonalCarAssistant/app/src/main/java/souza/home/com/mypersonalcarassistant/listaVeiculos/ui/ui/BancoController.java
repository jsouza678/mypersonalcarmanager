package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BancoController {

    private SQLiteDatabase db;
    private DBClass dbClass;
    private Calendar cal;
    String novaData;
    private DateFormat sdf;



    public BancoController(Context context){
        dbClass = new DBClass(context);
    }

    public String insereDados(String nome, String email, int kmAtual, String marca, String modelo, String ano){
        ContentValues values;
        long resultado;



        db = dbClass.getWritableDatabase();
        values = new ContentValues();

        values.put(DBClass.nome, nome);
        values.put(DBClass.email, email);
        values.put(DBClass.marca, marca);
        values.put(DBClass.modelo, modelo);
        values.put(DBClass.ano, ano);
        values.put(DBClass.kmAtual, kmAtual);


        resultado = db.insert(DBClass.TABELA, null, values);
        db.close();
        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public Cursor carregaData(){
        Cursor cursor;

        String[] campos = {DBClass.nome, DBClass.marca, DBClass.modelo, DBClass.ano, DBClass.kmAtual};
        db = dbClass.getReadableDatabase();
        cursor = db.query(DBClass.TABELA, campos, null, null, null, null, null, null);

        if(cursor !=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insereDadosManut(int km_oleo, int km_filtros, String troca_oleo, String troca_filtros){
        ContentValues values;
        long resultado;

        db = dbClass.getWritableDatabase();
        values = new ContentValues();

        values.put(DBClass.km_oleo, km_oleo);
        values.put(DBClass.km_filtros, km_filtros);
        values.put(DBClass.troca_oleo, troca_oleo);
        values.put(DBClass.troca_filtros, troca_filtros);




        resultado = db.insert(DBClass.TABELA2, null, values);
        db.close();
        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public Cursor carregaDataManut(){
        Cursor cursor;

        String[] campos = {DBClass.km_oleo, DBClass.km_filtros, DBClass.troca_oleo, DBClass.troca_filtros};
        db = dbClass.getReadableDatabase();
        cursor = db.query(DBClass.TABELA2, campos, null, null, null, null, null, null);

        if(cursor !=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void updateOleo(int kmOleo) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        novaData = sdf.format(cal.getTime());
        int km_oleo = 2000;
                db = dbClass.getReadableDatabase();
        String updateStmnt  = "UPDATE " + dbClass.TABELA2 + " SET " +
                DBClass.km_oleo + " = " +
                km_oleo + ", " +
                DBClass.troca_oleo + " = " + " ' " +
                novaData + " ' " + " WHERE " +
                DBClass.km_oleo + " = " + kmOleo + ";";
        db.execSQL(updateStmnt);
        db.close();
    }


    public void updateFiltro() {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        novaData = sdf.format(cal.getTime());
        int km_filtro = 20000;
        db = dbClass.getReadableDatabase();
        String updateStmnt  = "UPDATE " + dbClass.TABELA2 + " SET " + DBClass.km_filtros + " = " +  km_filtro + ", " + DBClass.troca_filtros + " = " + " ' " + novaData + " ' "+ " WHERE " +
                DBClass.id + " = 1;";
        db.execSQL(updateStmnt);
        db.close();
    }

    public void updateKm(int km_nova) {
        db = dbClass.getReadableDatabase();
        String updateStmnt  = "UPDATE " + dbClass.TABELA + " SET " + DBClass.kmAtual + " = " +  km_nova + " WHERE " +
                DBClass.id + " = 1;";
        db.execSQL(updateStmnt);
        db.close();
    }

    public void updateManutencaoOleo(int km_nova, String data) {
        db = dbClass.getReadableDatabase();
        String updateStmnt  = "UPDATE " + dbClass.TABELA2 + " SET " + DBClass.km_oleo + " = " +  km_nova + ", " + DBClass.troca_oleo + " = " + " ' "+ data + " ' " +  " WHERE " +
                DBClass.id + " = 1;";
        db.execSQL(updateStmnt);
        db.close();
    }

    public void updateManutencaoFiltro(int km_nova, String data) {
        db = dbClass.getReadableDatabase();
        String updateStmnt  = "UPDATE " + dbClass.TABELA2 + " SET " + DBClass.km_filtros + " = " +  km_nova + ", " + DBClass.troca_filtros + " = " + " ' "+ data + " ' " +  " WHERE " +
                DBClass.id + " = 1;";
        db.execSQL(updateStmnt);
        db.close();
    }


    public void recomeca(){
        db = dbClass.getReadableDatabase();
        db.delete(DBClass.TABELA, null, null);
        db.delete(DBClass.TABELA2, null, null);
        db.close();
    }

}
