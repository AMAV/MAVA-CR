package com.example.ur.mava_cr;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter mBluetoothAdapter = null;
    private ArrayList<BluetoothDevice> arrayDevices;
    private BluetoothDeviceArrayAdapter arrayAdapter;
    private ListView lstDisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstDisp = (ListView)findViewById(R.id.listDispositivos);
        registrarEventosBluetooth();
        lstDisp.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                try{
                    TwoLineListItem tv = (TwoLineListItem) adapter.getItemAtPosition(position);
                    Toast.makeText(getBaseContext(), tv.getText2().getText(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), "Error al castear el objeto a textview", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
    //Suscripción de BroadcastReciver a eventos de bluetooth
    private void registrarEventosBluetooth()
    {
        // Registramos el BroadcastReceiver que instanciamos previamente para
        // detectar los distintos eventos que queremos recibir
        IntentFilter filtro = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        filtro.addAction(BluetoothDevice.ACTION_FOUND);
        filtro.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        this.registerReceiver(bReceiver, filtro);
    }

    // Instanciamos un BroadcastReceiver que se encargara de detectar cuando
    // un dispositivo es descubierto.
    private final BroadcastReceiver bReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            // Cada vez que se descubra un nuevo dispositivo por Bluetooth, se ejecutara
            // este fragmento de codigo
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction()))
            {
                if(arrayDevices == null)
                    arrayDevices = new ArrayList<BluetoothDevice>();
                BluetoothDevice dispositivo = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrayDevices.add(dispositivo);
                //String descripcionDispositivo = dispositivo.getName() + " [" + dispositivo.getAddress() + "]";

            }

            // Codigo que se ejecutara cuando el Bluetooth finalice la busqueda de dispositivos.
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(intent.getAction()))
            {
                // Instanciamos un nuevo adapter para el ListView
                arrayAdapter = new BluetoothDeviceArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_2, arrayDevices);
                lstDisp.setAdapter(arrayAdapter);
                Toast.makeText(getBaseContext(), "Fin de la búsqueda", Toast.LENGTH_SHORT).show();
            }
        }
    };




    public void mostrarDispositivos (View view) {

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            // Device does not support Bluetooth

            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            if(arrayDevices != null)
                arrayDevices.clear();

            // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se cancela.
            if(mBluetoothAdapter.isDiscovering())
                mBluetoothAdapter.cancelDiscovery();

            // Iniciamos la busqueda de dispositivos y mostramos el mensaje de que el proceso ha comenzado
            if(mBluetoothAdapter.startDiscovery())
                Toast.makeText(this, "Iniciando búsqueda de dispositivos bluetooth", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Error al iniciar búsqueda de dispositivos bluetooth", Toast.LENGTH_SHORT).show();
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "tu movil no soporta bluetooth";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            finish();
        }
    }
    protected void onActivityForResult (int requestcode, int resultcode, Intent enabledItent){
        if (requestcode == REQUEST_ENABLE_BT){
            if(resultcode == RESULT_OK){
            //Nothing
            }else{
                Context context = getApplicationContext();
                CharSequence text = "bluetooth no activado";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }

        }
    }
}
