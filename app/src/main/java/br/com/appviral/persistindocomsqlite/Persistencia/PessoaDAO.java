package br.com.appviral.persistindocomsqlite.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;

/**
 * Created by Martin on 13/05/2016.
 */
public class PessoaDAO {

    Context context;
    DBSQLite dbsqLite;

    public PessoaDAO(Context context){
        this.context = context;
        dbsqLite = new DBSQLite(context);
    }

    public Long inserir(Pessoa pessoa){
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Pessoa.CAMPO_NOME, pessoa.nome);
        values.put(Pessoa.CAMPO_FONE, pessoa.fone);
        values.put(Pessoa.CAMPO_EMAIL, pessoa.email);
        Long id = db.insert(Pessoa.TABELA, null, values);
        db.close();
        return id;
    }

    public boolean alterar(Pessoa pessoa){
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Pessoa.CAMPO_NOME, pessoa.nome);
        values.put(Pessoa.CAMPO_FONE, pessoa.fone);
        values.put(Pessoa.CAMPO_EMAIL, pessoa.email);
        String whare = Pessoa.CAMPO_ID + " = ?";

        int id = db.update(Pessoa.TABELA, values, whare, new String[]{String.valueOf(pessoa.id)});
        db.close();
        return id > 0 ? true : false;
    }

    public boolean excluir(Pessoa pessoa){
        return excluir(pessoa.id);
    }

    public boolean excluir(Long id){
        SQLiteDatabase db = dbsqLite.getWritableDatabase();
        String whare = Pessoa.CAMPO_ID + " = ?";
        int ret = db.delete(Pessoa.TABELA,whare,new String[]{String.valueOf(id)});
        db.close();
        return ret > 0 ? true : false;
    }

    public ArrayList<Pessoa> listar(){
        SQLiteDatabase db = dbsqLite.getReadableDatabase();
        ArrayList<Pessoa> lista = new ArrayList<>();

        String selectQuery = "SELECT  " +
                Pessoa.CAMPO_ID + "," +
                Pessoa.CAMPO_NOME + "," +
                Pessoa.CAMPO_FONE + "," +
                Pessoa.CAMPO_EMAIL +
                " FROM " + Pessoa.TABELA;

        Cursor cursor = db.rawQuery(selectQuery,null);
        Pessoa umaPessoa;

        if(cursor.moveToFirst()){
            do{
                umaPessoa = new Pessoa();
                umaPessoa.id = cursor.getLong(0);
                umaPessoa.nome = cursor.getString(1);
                umaPessoa.fone = cursor.getString(2);
                umaPessoa.email = cursor.getString(3);
                lista.add(umaPessoa);
            }while (cursor.moveToNext());
            db.close();
        }
        return lista;
    }
}
