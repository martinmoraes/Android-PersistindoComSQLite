package br.com.appviral.persistindocomsqlite.Entidade;

/**
 * Created by Martin on 13/05/2016.
 */
public class Pessoa {
    public static final String TABELA = "pessoa";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOME = "nome";
    public static final String CAMPO_FONE = "fone";
    public static final String CAMPO_EMAIL = "email";



    public Long id;
    public String nome;
    public String fone;
    public String email;
}
