package br.com.vempracaruaru.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;
    private SharedPreferences sharedPrefEmail;
    private SharedPreferences sharedPrefUsuario;
    private String isEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_layout);

        ImageButton btnPonto = (ImageButton) findViewById(R.id.ibt_pontos_turisticos);
        ImageButton btnArtista = (ImageButton) findViewById(R.id.ibt_artistas);
        ImageButton btnObras = (ImageButton) findViewById(R.id.ibt_obras);
        ImageButton btnConta = (ImageButton) findViewById(R.id.ibt_minha_conta);
        ImageButton btnScan = (ImageButton) findViewById(R.id.ibt_scan);
        ImageButton btnSair = (ImageButton) findViewById(R.id.ibt_sair);

        btnPonto.setOnClickListener(this);
        btnArtista.setOnClickListener(this);
        btnObras.setOnClickListener(this);
        btnConta.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        btnSair.setOnClickListener(this);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        progressDialog.dismiss();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibt_pontos_turisticos:
                Intent itsPonto = new Intent(this,ListaPontosTuristicosActivity.class);
                startActivity(itsPonto);
                break;
            case R.id.ibt_artistas:
                Intent itsArtista = new Intent(this,ListaArtistaActivity.class);
                startActivity(itsArtista);
                break;
            case R.id.ibt_obras:
                Intent itsObra = new Intent(this,ListaObrasActivity.class);
                startActivity(itsObra);
                break;
            case R.id.ibt_minha_conta:
                Intent itsMinhaConta = new Intent(this,MinhaContaActivity.class);
                startActivity(itsMinhaConta);
                break;
            case R.id.ibt_scan:
                scan();
                break;
            case R.id.ibt_sair:

                sharedPrefEmail = getSharedPreferences("LOGIN", 0);
                SharedPreferences.Editor editorEmail = sharedPrefEmail.edit();
                editorEmail.putString("email", null);
                editorEmail.commit();

                sharedPrefUsuario = getSharedPreferences("USUARIO", 0);
                SharedPreferences.Editor editorUsuario = sharedPrefUsuario.edit();
                editorUsuario.putInt("idUsuario", 0);
                editorUsuario.commit();

                Intent itsLogin = new Intent(this,LoginActivity.class);
                startActivity(itsLogin);

                break;

        }
    }

    private void scan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null){
            if(result.getContents() == null){
//                Log.d("Main","scan cancelado");
//                Toast.makeText(this,"cancelado: "+result.getContents(),Toast.LENGTH_LONG).show();
            }else {
                Log.d("Main","scanned");
                Toast.makeText(this,"scanned: "+result.getContents(),Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPrefEmail = getSharedPreferences("LOGIN", 0);
        isEmail = sharedPrefEmail.getString("email", null);

        if(isEmail == null) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            finish();
        }
    }
}
