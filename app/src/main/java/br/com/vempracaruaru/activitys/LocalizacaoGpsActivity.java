package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.joao.vempracaruaruapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;

public class LocalizacaoGpsActivity extends AppCompatActivity {

    private GoogleMap mGoogleMap;
    private LatLng mLatLng;
    private PontoTuristico ponto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localizacao_ponto_layout);

        Intent it = getIntent();
        ponto = (PontoTuristico) it.getSerializableExtra("ponto");


        SupportMapFragment fragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mGoogleMap = fragment.getMap();
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Log.i("Mapa",ponto.getLatitude()+" - "+ponto.getLongitude());
        mLatLng = new LatLng(Float.parseFloat(ponto.getLatitude()),
                Float.parseFloat(ponto.getLongitude()));
        atualizarMapa();
    }

    private void atualizarMapa() {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,17.0f));
        mGoogleMap.addMarker(new MarkerOptions()
                .position(mLatLng)
                .title(ponto.getNome()));
    }
}
