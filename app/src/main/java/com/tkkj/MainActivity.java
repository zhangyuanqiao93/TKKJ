package com.tkkj;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.gson.Gson;
import com.tkkj.base.BasicActivity;
import com.tkkj.model.data;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BasicActivity {

    @BindView(R.id.btn1)
    Button btn1;


    BluetoothAdapter mBluetoothAdapter ;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

      /*  Gson gson = new Gson();
      data data = gson.toJson(json, com.tkkj.model.data.class);*/
      initData();
    }

    private void initData() {
        context =MainActivity.this;
        mBluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
    }

    @OnClick(R.id.btn1)
    public void onClick() {
    }
}
