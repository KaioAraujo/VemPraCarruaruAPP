package br.com.vempracaruaru.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.vempracaruaru.activitys.Tela1;
import br.com.vempracaruaru.activitys.Tela2;
import br.com.vempracaruaru.activitys.Tela3;

/**
 * Created by joao on 06/06/16.
 */
public class AdapterTab extends FragmentStatePagerAdapter {

    private String[] nTabTitles;

    public AdapterTab(FragmentManager fm,String[] nTabTitles) {
        super(fm);
        this.nTabTitles = nTabTitles;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Tela1();
            case 1:
                return new Tela2();
            case 2:
                return new Tela3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nTabTitles[position];
    }
}
