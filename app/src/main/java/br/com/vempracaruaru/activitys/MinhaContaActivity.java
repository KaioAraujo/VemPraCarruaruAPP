package br.com.vempracaruaru.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

public class MinhaContaActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minha_conta_layout);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        String nome = sharedPref.getString("email","email");

        TextView txtNome = (TextView) findViewById(R.id.txv_info_nome);
        txtNome.setText(nome);

        Button btnAlterar = (Button) findViewById(R.id.btn_alterar_senha);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(getApplicationContext(),AlterarActivity.class);
                startActivity(its);
            }
        });
    }
}
