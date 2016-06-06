package br.com.vempracaruaru.activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.adapters.AdapterTab;


public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tab = (TabLayout) findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.viewPager);

        pager.setAdapter(new AdapterTab(getSupportFragmentManager(),getResources().getStringArray(R.array.titulo_tab)));
        tab.setupWithViewPager(pager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
