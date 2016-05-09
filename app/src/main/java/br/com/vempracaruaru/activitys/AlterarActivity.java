package br.com.vempracaruaru.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

public class AlterarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_layout);

        Button btnAlterar = (Button) findViewById(R.id.btn_alterar);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Alteração feita com sucesso",Toast.LENGTH_LONG).show();
            }
        });
    }
}
