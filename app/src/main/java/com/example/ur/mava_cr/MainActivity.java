package com.example.ur.mava_cr;

import android.bluetooth.BluetoothAdapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
            Context context = getApplicationContext();
            CharSequence text = "tu movil no soporta bluetooth";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            finish();
        }
    }
    protected void onActivityForResult (int requestcode, int resultcode, Intent enabledItent){
        if (requestcode == REQUEST_ENABLE_BT){
            if(resultcode == RESULT_OK){
                //nothing
            }else{
                Context context = getApplicationContext();
                CharSequence text = "bluetooth no activado";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }

        }
    }
}
