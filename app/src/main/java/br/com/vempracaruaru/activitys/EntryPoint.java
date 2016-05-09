package br.com.vempracaruaru.activitys;

import android.app.Application;
import com.facebook.FacebookSdk;
/**
 * Created by joao on 09/05/16.
 */
public class EntryPoint extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //classe que inicia o sdk do face
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
