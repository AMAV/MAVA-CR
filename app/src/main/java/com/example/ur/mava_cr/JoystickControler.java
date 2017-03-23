package com.example.ur.mava_cr;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
       // Button conectar = (Button)findViewById(R.id.button3);
        BluetoothSocket socket = null;
        outSt = null;
        try{
            socket = dispositivo.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            socket.connect();
            outSt = socket.getOutputStream();
        }catch (IOException ex){
            //Hacer algo
        }

        //conectar.setOnTouchListener(new View.OnTouchListener() {
          //  @Override
        //    public boolean onTouch(View v, MotionEvent event) {
         //       if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
         //           try{
          //              outSt.write("b".getBytes());
          //          }catch (IOException ex){

           //         }
          //      }
          //      else if(event.getActionMasked() == MotionEvent.ACTION_UP){
           //         try{
           //            outSt.write("f".getBytes());
           //         }catch (IOException ex){

          //          }
          //      }
         //       return true;
          //  }
       // });
    }
    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {



                /*if(yPercent>-0.7 && xPercent>-0.7 && xPercent<0.7){
                    try{
                        outSt.write("b".getBytes());
                    }catch (IOException ex){

                    }
                }
                if(yPercent<0.7 && xPercent>-0.7 && xPercent<0.7){
                    try{
                        outSt.write("f".getBytes());
                    }catch (IOException ex){

                    }
                }
                if(yPercent<0.7 && yPercent>-0.7 && xPercent<0.7){
                    try{
                        outSt.write("r".getBytes());
                    }catch (IOException ex){

                    }
                }
                if(yPercent<0.7 && yPercent>-0.7 && xPercent>-0.7){
                    try{
                        outSt.write("l".getBytes());
                    }catch (IOException ex){

                    }
                }*/

        //Log.d("Left Joystick", "X percent: " + xPercent + " Y percent: " + yPercent);


    }
}
