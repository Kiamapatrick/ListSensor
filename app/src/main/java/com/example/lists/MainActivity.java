package com.example.lists;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private RecyclerView sensorRecyclerView;
    private SensorAdapter sensorAdapter;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorRecyclerView = findViewById(R.id.sensorRecyclerView);
        sensorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sensorRecyclerView.setHasFixedSize(true);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<SensorItem> sensorItems = new ArrayList<>();

        for (Sensor s : sensorList) {
            sensorItems.add(new SensorItem(
                s.getName(),
                getSensorTypeName(s.getType()),
                s.getVendor(),
                s.getPower(),
                s.getResolution(),
                s.getMaximumRange()
            ));
        }

        sensorAdapter = new SensorAdapter(sensorItems);
        sensorRecyclerView.setAdapter(sensorAdapter);

        if (sensorItems.isEmpty()) {
            toolbar.setSubtitle(R.string.no_sensors_found);
        } else {
            toolbar.setSubtitle(getString(R.string.sensor_count, sensorItems.size()));
        }
        logInventory();
    }

    private void logInventory() {
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (manager == null) {
            Log.w("Sensors", "Sensor service is unavailable.");
            return;
        }

        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

        if (sensors.isEmpty()) {
            Log.d("Sensors", "No sensors are available on this device.");
            return;
        }

        Log.d("Sensors", "Total sensors: " + sensors.size());

        for (Sensor sensor : sensors) {
            Log.d("Sensors",
                    sensor.getName()
                            + " | type=" + sensor.getType()
                            + " | range=" + sensor.getMaximumRange()
                            + " | resolution=" + sensor.getResolution()
                            + " | mA=" + sensor.getPower());
        }
    }

    private String getSensorTypeName(int type) {
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_MAGNETIC_FIELD: return getString(R.string.sensor_category_position);
            case Sensor.TYPE_GYROSCOPE: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_LIGHT: return getString(R.string.sensor_category_environment);
            case Sensor.TYPE_PRESSURE: return getString(R.string.sensor_category_environment);
            case Sensor.TYPE_TEMPERATURE: return getString(R.string.sensor_category_environment);
            case Sensor.TYPE_PROXIMITY: return getString(R.string.sensor_category_position);
            case Sensor.TYPE_GRAVITY: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_LINEAR_ACCELERATION: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_ROTATION_VECTOR: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_RELATIVE_HUMIDITY: return getString(R.string.sensor_category_environment);
            case Sensor.TYPE_AMBIENT_TEMPERATURE: return getString(R.string.sensor_category_environment);
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED: return getString(R.string.sensor_category_position);
            case Sensor.TYPE_GAME_ROTATION_VECTOR: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_SIGNIFICANT_MOTION: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_STEP_DETECTOR: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_STEP_COUNTER: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR: return getString(R.string.sensor_category_position);
            case Sensor.TYPE_HEART_RATE: return getString(R.string.sensor_category_other);
            case Sensor.TYPE_POSE_6DOF: return getString(R.string.sensor_category_position);
            case Sensor.TYPE_STATIONARY_DETECT: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_MOTION_DETECT: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_HEART_BEAT: return getString(R.string.sensor_category_other);
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_HINGE_ANGLE: return getString(R.string.sensor_category_position);
            case Sensor.TYPE_HEAD_TRACKER: return getString(R.string.sensor_category_position);
            case Sensor.TYPE_ACCELEROMETER_LIMITED_AXES: return getString(R.string.sensor_category_motion);
            case Sensor.TYPE_GYROSCOPE_LIMITED_AXES: return getString(R.string.sensor_category_motion);
            default: return getString(R.string.sensor_category_other);
        }
    }

    static class SensorItem {
        final String name;
        final String category;
        final String vendor;
        final float power;
        final float resolution;
        final float maxRange;

        SensorItem(String name, String category, String vendor, float power, float resolution, float maxRange) {
            this.name = name;
            this.category = category;
            this.vendor = vendor;
            this.power = power;
            this.resolution = resolution;
            this.maxRange = maxRange;
        }
    }

    static class SensorViewHolder extends RecyclerView.ViewHolder {
        ImageView iconSensor;
        TextView textSensorName;
        TextView textSensorType;
        TextView textSensorVendor;
        TextView textSensorPower;
        TextView textSensorResolution;
        TextView textSensorRange;

        SensorViewHolder(View itemView) {
            super(itemView);
            iconSensor = itemView.findViewById(R.id.iconSensor);
            textSensorName = itemView.findViewById(R.id.textSensorName);
            textSensorType = itemView.findViewById(R.id.textSensorType);
            textSensorVendor = itemView.findViewById(R.id.textSensorVendor);
            textSensorPower = itemView.findViewById(R.id.textSensorPower);
            textSensorResolution = itemView.findViewById(R.id.textSensorResolution);
            textSensorRange = itemView.findViewById(R.id.textSensorRange);
        }
    }

    class SensorAdapter extends RecyclerView.Adapter<SensorViewHolder> {
        private final List<SensorItem> items;

        SensorAdapter(List<SensorItem> items) {
            this.items = items;
        }

        @Override
        public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sensor, parent, false);
            return new SensorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SensorViewHolder holder, int position) {
            SensorItem item = items.get(position);
            holder.textSensorName.setText(item.name);
            holder.textSensorType.setText(String.format("%s", item.category));
            holder.textSensorVendor.setText(String.format(holder.itemView.getContext().getString(R.string.sensor_vendor_prefix), item.vendor));
            holder.textSensorPower.setText(String.format(holder.itemView.getContext().getString(R.string.sensor_power_prefix), item.power));
            holder.textSensorResolution.setText(String.format(holder.itemView.getContext().getString(R.string.sensor_resolution_prefix), item.resolution));
            holder.textSensorRange.setText(String.format(holder.itemView.getContext().getString(R.string.sensor_range_prefix), item.maxRange));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}