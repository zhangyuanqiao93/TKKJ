package com.tkkj.base;

/**
 * Created by YANGB on 2016/4/3.
 */

import android.app.Application;
import android.content.Context;


public class BasicApplication extends Application {

    private AppManager _SpActivityManager = null;
    private static Context mContext;

    @Override
    public void onCreate() {

        initData();
        super.onCreate();
    }

    private void initData() {
        _SpActivityManager = AppManager.getInstance();
        mContext = getApplicationContext();

    }





    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public AppManager getActivityManager() {
        return this._SpActivityManager;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getContext() {
        return mContext;
    }

}
