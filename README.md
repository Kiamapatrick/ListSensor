# Sensor Explorer

An Android app that lists all available sensors on the device with detailed information using Material Design 3.

## Features

- **Complete sensor listing** - Shows all hardware sensors available on the device
- **Detailed sensor info** - Name, category (Motion/Environment/Position/Other), vendor, power consumption, resolution, and maximum range
- **Modern Material Design 3 UI** - RecyclerView with MaterialCardView items, rounded corners, elevation, and proper theming
- **Light/Dark theme support** - Automatic theme switching with proper Material color tokens
- **Edge-to-edge display** - Proper window insets handling for modern Android devices
- **Debug logging** - Logs all sensor details to logcat for debugging

## Screenshots

*(Add screenshots here)*

## Requirements

- Android 7.0 (API 24) or higher
- Android Studio Hedgehog or later

## Building

```bash
./gradlew assembleDebug
```

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/lists/
│   │   └── MainActivity.java          # Main activity with RecyclerView and sensor logging
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml      # Main layout with Toolbar and RecyclerView
│   │   │   └── item_sensor.xml        # Sensor card item layout
│   │   ├── drawable/
│   │   │   └── ic_sensor.xml          # Sensor icon vector drawable
│   │   ├── values/
│   │   │   ├── colors.xml             # Material Design 3 color palette
│   │   │   ├── strings.xml            # String resources
│   │   │   └── themes.xml             # Light theme
│   │   └── values-night/
│   │       └── themes.xml             # Dark theme
```

## Sensor Categories

- **Motion**: Accelerometer, Gyroscope, Gravity, Linear Acceleration, Rotation Vector, Step Counter, etc.
- **Environment**: Light, Pressure, Temperature, Humidity
- **Position**: Magnetic Field, Proximity, Geomagnetic Rotation Vector, Hinge Angle, Head Tracker
- **Other**: Heart Rate, Heart Beat, and unclassified sensors

## Logging

The app logs detailed sensor information to logcat with the tag `Sensors`:

```
Total sensors: 12
Accelerometer | type=1 | range=78.45 | resolution=0.0024 | mA=0.5
Gyroscope | type=4 | range=34.91 | resolution=0.0001 | mA=1.2
...
```

## License

MIT License