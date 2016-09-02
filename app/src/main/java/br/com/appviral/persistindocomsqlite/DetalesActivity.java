package br.com.appviral.persistindocomsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;
import br.com.appviral.persistindocomsqlite.Persistencia.PessoaDAO;

public class DetalesActivity extends AppCompatActivity {
    String operacao;
    int posicao;
    Long id;
    EditText etNome, etFone, etEmail;
    PessoaDAO pessoaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detales);

        operacao = getIntent().getStringExtra("OPERACAO");
        posicao = getIntent().getIntExtra("POSICAO", 0);
        id = getIntent().getLongExtra("ID",0);

        etNome = (EditText) findViewById(R.id.etNome);
        etFone = (EditText) findViewById(R.id.etFone);
        etEmail = (EditText) findViewById(R.id.etEMail);

        if(operacao.equals("alterar")){
            Pessoa pessoa = PessoaDAO.listaPessoas.get(posicao);
            etNome.setText(pessoa.nome);
            etFone.setText(pessoa.fone);
            etEmail.setText(pessoa.email);
        }
        pessoaDAO = new PessoaDAO(this);
    }


    public void salvar(View view) {
        if (operacao.equals("inserir")) {
            Pessoa umaPessoa = new Pessoa();
            umaPessoa.nome = etNome.getText().toString();
            umaPessoa.fone = etFone.getText().toString();
            umaPessoa.email = etEmail.getText().toString();
            if (pessoaDAO.inserir(umaPessoa)) {
                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
//                etNome.setText("");
//                etFone.setText("");
//                etEmail.setText("");
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }


        if(operacao.equals("alterar")){
            Pessoa umaPessoa = new Pessoa();
            umaPessoa.id = id;
            umaPessoa.nome = etNome.getText().toString();
            umaPessoa.fone = etFone.getText().toString();
            umaPessoa.email = etEmail.getText().toString();
            if (pessoaDAO.alterar(umaPessoa)) {
                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                etNome.setText("");
                etFone.setText("");
                etEmail.setText("");
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    public void excluir(View view) {
        if(operacao.equals("alterar")){
            if ( pessoaDAO.excluir(id)) {
                Toast.makeText(this, "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void fechar(View view) {
        finish();
    }
}
