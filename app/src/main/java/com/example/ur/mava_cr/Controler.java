package com.example.ur.mava_cr;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class Controler extends AppCompatActivity  {
    private BluetoothDevice dispositivo;
    private OutputStream outSt;
    private long previusTime;
    ImageButton ibtnForward;
    ImageButton ibtnBack;
    ImageButton ibtnRight;
    ImageButton ibtnLeft;
    WebView wv;
    //private static final Uri uri = Uri.parse("http://192.168.43.235:8080/video.mp4");
    //private static final Uri uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controler);

        ibtnForward = (ImageButton) findViewById(R.id.ibtnForward);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        ibtnRight = (ImageButton) findViewById(R.id.ibtnRight);
        ibtnLeft = (ImageButton) findViewById(R.id.ibtnLeft);
        //Asignar evento de pulsación del boton hacia delante
        wv = (WebView) findViewById(R.id.Video);
        wv.loadUrl("http://192.168.43.235:8080/browserfs.html");
            //vv.setMediaController(new MediaController(this));
            //vv.requestFocus();
            //vv.setVideoURI(uri);
            //vv.start();

        ibtnForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                moverCoche("f");

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:{
                        try{
                            outSt.write("b".getBytes());

                        }catch (Exception e){
                            Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                return false;
            }
        });


        //Asignar evento de pulsación del boton hacia atras
        ibtnBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                moverCoche("b");

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:{
                        try{
                            outSt.write("f".getBytes());

                        }catch (Exception e){
                            Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                return false;
            }
        });

        //Asignar evento de pulsación del boton hacia Derecha
        ibtnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                moverCoche("r");

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:{
                        try{
                            outSt.write("r".getBytes());

                        }catch (Exception e){
                            Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                return false;
            }
        });

        //Asignar evento de pulsación del boton hacia Izquierda
        ibtnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                moverCoche("l");

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:{
                        try{
                            outSt.write("l".getBytes());

                        }catch (Exception e){
                            Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                return false;
            }
        });

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

    }
    public void moverCoche(String movimiento){
        String estado = "";

        switch(movimiento){
            case "f":{
                if(!estado.equals(movimiento)){
                    try{
                        outSt.write("b".getBytes());

                    }catch (Exception e){
                        Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            case "b":{
                if(!estado.equals(movimiento)){
                    try{
                        outSt.write("f".getBytes());

                    }catch (Exception e){
                        Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            case "r":{
                if(!estado.equals(movimiento)){
                    try{
                        outSt.write("r".getBytes());

                    }catch (Exception e){
                        Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            case "l":{
                if(!estado.equals(movimiento)){
                    try{
                        outSt.write("l".getBytes());

                    }catch (Exception e){
                        Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            case "s":{
                if(!estado.equals(movimiento)){
                    try{
                        outSt.write("s".getBytes());
                    }catch (Exception e){
                        Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }








}
