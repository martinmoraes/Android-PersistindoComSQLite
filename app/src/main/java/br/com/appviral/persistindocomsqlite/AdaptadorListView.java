package br.com.appviral.persistindocomsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;
import br.com.appviral.persistindocomsqlite.Persistencia.PessoaDAO;

/**
 * Created by Martin on 14/05/2016.
 */
public class AdaptadorListView extends BaseAdapter {

    LayoutInflater layoutInflater;
    PessoaDAO pessoaDAO;

    public AdaptadorListView(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pessoaDAO = new PessoaDAO(context);
    }


    @Override
    public int getCount() {
        return PessoaDAO.listaPessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return PessoaDAO.listaPessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        Pessoa umaPessoa = PessoaDAO.listaPessoas.get(position);
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

        Pessoa umaPessoa = PessoaDAO.listaPessoas.get(position);

        tvNome.setText(umaPessoa.nome);
        tvFone.setText(umaPessoa.fone);
        tvEmail.setText(umaPessoa.email);

        return convertView;
    }



}

