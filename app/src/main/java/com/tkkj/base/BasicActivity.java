package com.tkkj.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tkkj.R;

import java.lang.ref.WeakReference;

import butterknife.Unbinder;


/**

 */
public class BasicActivity extends FragmentActivity {

    private static final String TAG = BasicActivity.class.getSimpleName();
    private ServiceConnection mConnection;

    public Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.overridePendingTransition(R.anim.activity_from_in, R.anim.activity_from_out);
        //隐藏虚拟按键
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        window.setAttributes(params);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.title_bg_color));
            tintManager.setStatusBarTintEnabled(true);
        }
        super.onCreate(savedInstanceState);
    AppManager.getInstance().addActivity(this);


        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "bindService success! ComponentName:" + name.toString());
                //playerBar();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    /*数跳转页面（不带参数）*/
    protected void next(Class<?> nextAty) {
        Intent intent = new Intent(this, nextAty);
        startActivity(intent);
    }

    /*数跳转页面（带参数）*/
    protected void next(Class<?> nextAty, Bundle extras) {
        Intent intent = new Intent(this, nextAty);
        intent.putExtras(extras);
        startActivity(intent);
    }



    protected Activity getActivity() {
        return this;
    }


    @Override
    public void onBackPressed() {
        this.overridePendingTransition(R.anim.activity_from_in, R.anim.activity_from_out);
        super.onBackPressed();
    }

    @Override
    public void finish() {
        this.overridePendingTransition(R.anim.activity_from_in, R.anim.activity_from_out);
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        AppManager.getInstance().finishActivity(this);
        super.finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            View view = getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 防止内部Handler类引起内存泄露
     */
    protected static class SafeHandler extends Handler {
        private final WeakReference<Activity> mActivity;

        public SafeHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity.get() == null) {
                return;
            }
        }
    }

}
