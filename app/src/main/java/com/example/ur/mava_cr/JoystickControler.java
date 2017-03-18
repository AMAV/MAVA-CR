package com.example.ur.mava_cr;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class JoystickControler extends AppCompatActivity implements JoystickView.JoystickListener {
    private BluetoothDevice dispositivo;
    private OutputStream outSt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick_controler);
        JoystickView joystick = new JoystickView(this);
        dispositivo = (BluetoothDevice)getIntent().getExtras().getParcelable("dispositivo");
        Button conectar = (Button)findViewById(R.id.button3);
        BluetoothSocket socket = null;
        outSt = null;
        try{
            socket = dispositivo.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            socket.connect();
            outSt = socket.getOutputStream();
        }catch (IOException ex){
            //Hacer algo
        }

        conectar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    try{
                        outSt.write("b".getBytes());
                    }catch (IOException ex){

                    }
                }
                else if(event.getActionMasked() == MotionEvent.ACTION_UP){
                    try{
                        outSt.write("f".getBytes());
                    }catch (IOException ex){

                    }
                }
                return true;
            }
        });
    }
    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {
        switch (id)
        {
            case R.id.joystickRight:
                Log.d("Right Joystick", "X percent: " + xPercent + " Y percent: " + yPercent);
                break;
            case R.id.joystickLeft:
                Log.d("Left Joystick", "X percent: " + xPercent + " Y percent: " + yPercent);
                break;
        }
    }
}
