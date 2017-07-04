package com.samples.hardware.light;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private TextView text0;

    private SensorManager manager;
    private List<Sensor> sensorList;

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            text0.setText(String.valueOf(sensorEvent.values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        text0 = (TextView) findViewById(R.id.text0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorList = manager.getSensorList(Sensor.TYPE_LIGHT);
        sensorList = manager.getSensorList(Sensor.TYPE_PROXIMITY);
        sensorList = manager.getSensorList(Sensor.TYPE_ORIENTATION);
        sensorList = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);


        if (sensorList.size() != 0){
            manager.registerListener(listener, sensorList.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.error_msg);
            builder.setPositiveButton(
                    "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            SensorActivity.this.finish();
                        }
                    });
            builder.show();
        }
    }
}
