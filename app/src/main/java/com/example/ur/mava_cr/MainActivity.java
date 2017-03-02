package com.example.ur.mava_cr;

import android.bluetooth.BluetoothAdapter;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_ENABLE_BT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mostrarDispositivos () {

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            // Device does not support Bluetooth

            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }

        }
        else {
            finish();
        }
    }
    protected void onActivityForResult (int requestcode, int resultcode, Intent enabledItent){
        if (requestcode == REQUEST_ENABLE_BT){
            if(resultcode == RESULT_OK){
                
            }else{

            }

        }
    }
}
