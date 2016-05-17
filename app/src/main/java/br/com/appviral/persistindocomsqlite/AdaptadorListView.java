package br.com.appviral.persistindocomsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;
import br.com.appviral.persistindocomsqlite.Persistencia.PessoaDAO;

/**
 * Created by Martin on 14/05/2016.
 */
public class AdaptadorListView extends BaseAdapter {
    static ArrayList<Pessoa> listaPessoas;
    LayoutInflater layoutInflater;

    public AdaptadorListView(Context context) {
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        listaPessoas = pessoaDAO.listar();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return listaPessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        Pessoa umaPessoa = listaPessoas.get(position);
        return umaPessoa.id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_item_listview, null);
        }

        TextView tvNome = (TextView) convertView.findViewById(R.id.tvNome);
        TextView tvFone = (TextView) convertView.findViewById(R.id.tvFone);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEMail);

        Pessoa umaPessoa = listaPessoas.get(position);

        tvNome.setText(umaPessoa.nome);
        tvFone.setText(umaPessoa.fone);
        tvEmail.setText(umaPessoa.email);

        return convertView;
    }


    public static void addPessoa(Pessoa pessoa) {
        listaPessoas.add(pessoa);
    }

    public static Pessoa getPessoa(int posicao) {
        return listaPessoas.get(posicao);
    }

    public static void alteraPessoa(Pessoa pessoa) {
        for (int x = 0; x < listaPessoas.size(); x++) {
            Pessoa umaPessoa = listaPessoas.get(x);
            if (umaPessoa.id == pessoa.id) {
                listaPessoas.remove(x);
                listaPessoas.add(x, pessoa);
                break;
            }
        }
    }

    public static void removePessoa(int posicao){
        listaPessoas.remove(posicao);
    }
}

