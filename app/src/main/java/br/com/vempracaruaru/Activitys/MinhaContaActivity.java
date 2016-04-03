package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joao.vempracaruaruapp.R;

public class MinhaContaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minha_conta_layout);

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
