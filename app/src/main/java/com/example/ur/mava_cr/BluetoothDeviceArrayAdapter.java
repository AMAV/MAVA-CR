package com.example.ur.mava_cr;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by andres on 3/7/2017.
 */

public class BluetoothDeviceArrayAdapter extends ArrayAdapter {
    private List<BluetoothDevice> deviceList; // Contendra el listado de dispositivos
    private Context context;// Contexto activo
    public BluetoothDeviceArrayAdapter(@NonNull Context context, @LayoutRes int resource, List<BluetoothDevice> objects) {
        super(context, resource);
        this.deviceList = objects;
        this.context = context;
    }
    public int getCount()
    {
        if(deviceList != null)
            return deviceList.size();
        else
            return 0;
    }
    @Override
    public Object getItem(int position)
    {
        return (deviceList == null ? null : deviceList.get(position));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if((deviceList == null) || (context == null)){
            return null;
        }
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Creamos una vista a partir de simple_list_item_2, que contiene dos TextView.
        // El primero (text1) lo usaremos para el nombre, mientras que el segundo (text2)
        // lo utilizaremos para la direccion del dispositivo.
        View elemento = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

        // Referenciamos los TextView
        TextView tvNombre = (TextView)elemento.findViewById(android.R.id.text1);
        TextView tvDireccion = (TextView)elemento.findViewById(android.R.id.text2);
        // Obtenemos el dispositivo del array y obtenemos su nombre y direccion, asociandosela
        // a los dos TextView del elemento
        BluetoothDevice dispositivo = (BluetoothDevice)getItem(position);
        if(dispositivo != null)
        {
            tvNombre.setText(dispositivo.getName());
            tvDireccion.setText(dispositivo.getAddress());
        }
        else
        {
            CharSequence text = "Error en al crear la lista";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        // Devolvemos el elemento con los dos TextView cumplimentados
        return elemento;
    }
}
