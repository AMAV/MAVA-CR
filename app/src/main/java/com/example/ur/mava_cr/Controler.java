package com.example.ur.mava_cr;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Controler extends AppCompatActivity  {
    private BluetoothDevice dispositivo;
    private OutputStream outSt;
    private InputStream inSt;
    private long previusTime;
    ImageButton ibtnForward;
    ImageButton ibtnBack;
    ImageButton ibtnRight;
    ImageButton ibtnLeft;
    WebView wv;
    EditText et;
    Switch pa;
    ImageView btnBLue;
    BluetoothSocket socket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controler);
        pa = (Switch) findViewById(R.id.switch1);

        ibtnForward = (ImageButton) findViewById(R.id.ibtnForward);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        ibtnRight = (ImageButton) findViewById(R.id.ibtnRight);
        ibtnLeft = (ImageButton) findViewById(R.id.ibtnLeft);
        //Asignar evento de pulsación del boton hacia delante
        wv = (WebView) findViewById(R.id.Video);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCod,String description, String failingUrl) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        wv.loadUrl("http://192.168.43.235:8080/browserfs.html");

        btnBLue = (ImageView) findViewById(R.id.imageView3);


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

        outSt = null;
        try{
            socket = dispositivo.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            socket.connect();
            outSt = socket.getOutputStream();




        }catch (IOException ex){
            //Hacer algo
        }


    }
    public void reconectar (View view){
        if(socket != null){
            try{
                socket.connect();
                outSt = socket.getOutputStream();
            }catch(IOException ex){

            }
        }else{
//            dispositivo = (BluetoothDevice)getIntent().getExtras().getParcelable("dispositivo");
            try{
                socket = dispositivo.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            }catch (IOException e){
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }

            try{

                socket.connect();
                outSt = socket.getOutputStream();
            }catch(IOException ex2){
                Toast.makeText(getBaseContext(), "Fallo al reconectar", Toast.LENGTH_SHORT).show();
            }
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

    public void pilotoAutomatico(View view){
        if(pa.isChecked()){
            try{
                outSt.write("p".getBytes());
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            try{
                outSt.write("s".getBytes());
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Fallo", Toast.LENGTH_SHORT).show();
            }
        }

    }






}
