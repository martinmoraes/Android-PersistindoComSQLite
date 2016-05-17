package br.com.appviral.persistindocomsqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PrincipalActivity extends AppCompatActivity {
    AdaptadorListView adaptadorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final Context context = this;

        ListView listView = (ListView) findViewById(R.id.listView);
        adaptadorListView = new AdaptadorListView(this);
        listView.setAdapter(adaptadorListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,DetalesActivity.class);
                intent.putExtra("OPERACAO", "alterar");
                intent.putExtra("POSICAO", position);
                intent.putExtra("ID", id);

                startActivity(intent);
            }
        });
    }

    public void inserir(View view){
        Intent intent = new Intent(this,DetalesActivity.class);
        intent.putExtra("OPERACAO", "inserir");
        startActivity(intent);
    }


    @Override
    public void onResume(){
        super.onResume();
        adaptadorListView.notifyDataSetChanged();
    }
}
