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
    public static ArrayList<Pessoa> listaPessoas;

    public PessoaDAO(Context context) {
        this.context = context;
        dbsqLite = new DBSQLite(context);
        listar();
    }

    public boolean inserir(Pessoa pessoa) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Pessoa.CAMPO_NOME, pessoa.nome);
        values.put(Pessoa.CAMPO_FONE, pessoa.fone);
        values.put(Pessoa.CAMPO_EMAIL, pessoa.email);
        Long id = db.insert(Pessoa.TABELA, null, values);
        db.close();
        if (id > 0) {
            pessoa.id = id;
            listaPessoas.add(pessoa);
        }
        return id > 0;
    }

    public boolean alterar(Pessoa pessoa) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Pessoa.CAMPO_NOME, pessoa.nome);
        values.put(Pessoa.CAMPO_FONE, pessoa.fone);
        values.put(Pessoa.CAMPO_EMAIL, pessoa.email);
        String whare = Pessoa.CAMPO_ID + " = ?";

        int id = db.update(Pessoa.TABELA, values, whare, new String[]{String.valueOf(pessoa.id)});
        db.close();
        if (id > 0)
            listaPessoas.set(listaPessoas.indexOf(localizaPessoaPorId(pessoa.id)), pessoa);
        return id > 0;
    }

    public boolean excluir(Pessoa pessoa) {
        return excluir(pessoa.id);
    }

    public boolean excluir(Long id) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();
        String whare = Pessoa.CAMPO_ID + " = ?";
        int ret = db.delete(Pessoa.TABELA, whare, new String[]{String.valueOf(id)});
        db.close();

        if (ret > 0)
            listaPessoas.remove(localizaPessoaPorId(id));

        return ret > 0;
    }

    public void listar() {
        SQLiteDatabase db = dbsqLite.getReadableDatabase();
        listaPessoas = new ArrayList<>();

        String selectQuery = "SELECT  " +
                Pessoa.CAMPO_ID + "," +
                Pessoa.CAMPO_NOME + "," +
                Pessoa.CAMPO_FONE + "," +
                Pessoa.CAMPO_EMAIL +
                " FROM " + Pessoa.TABELA;

        Cursor cursor = db.rawQuery(selectQuery, null);
        Pessoa umaPessoa;

        if (cursor.moveToFirst()) {
            do {
                umaPessoa = new Pessoa();
                umaPessoa.id = cursor.getLong(0);
                umaPessoa.nome = cursor.getString(1);
                umaPessoa.fone = cursor.getString(2);
                umaPessoa.email = cursor.getString(3);
                listaPessoas.add(umaPessoa);
            } while (cursor.moveToNext());
            db.close();
        }
    }

    public Pessoa localizaPessoaPorId(long id) {
        for (Pessoa umaPessoa : listaPessoas)
            if (umaPessoa.id == id)
                return umaPessoa;
        return null;
    }
}
