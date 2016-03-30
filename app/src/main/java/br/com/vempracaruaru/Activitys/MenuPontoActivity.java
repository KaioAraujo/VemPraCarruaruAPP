package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joao.vempracaruaruapp.R;

public class MenuPontoActivity extends AppCompatActivity {

    private Button btnFotosPonto;
    private Button btnVerObras;
    private Button btnGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_ponto_layout);

        btnFotosPonto = (Button)findViewById(R.id.btn_ver_fotos_ponto);
        btnVerObras = (Button) findViewById(R.id.btn_ver_obras_ponto);
        btnGps = (Button) findViewById(R.id.btn_ver_gps_ponto);

        btnFotosPonto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                btnFotosPontoOnclik();
            }
        });

        btnVerObras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                btnVerObrasOnclik();
            }
        });

        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGpsOnclick();
            }
        });
    }



    private void btnFotosPontoOnclik(){
        Intent intent = new Intent(this,FotosPontosActivity.class);
        startActivity(intent);
    }

    private void btnVerObrasOnclik(){
        Intent intent = new Intent(this,FotosObrasActivity.class);
        startActivity(intent);
    }

    private  void btnGpsOnclick(){
        Intent intent = new Intent(this,LocalizacaoPontoTuristicoActivity.class);
        startActivity(intent);
    }
}
