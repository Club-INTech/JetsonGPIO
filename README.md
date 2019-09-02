# Jetson GPIO

Project to control the GPIO pins of a Jetson Nano with Java but can be used with other devices.
Uses the Linux GPIO endpoints

Tested on a Jetson Nano with Ubuntu 18.04 LTS

# Examples
## Accessing a GPIO pin in output mode.
```java
Pin pin = GPIO.open(Pins.PIN11, GPIO.Mode.OUTPUT)
```

## Setting the state of a pin
```java
pin.high();
pin.low();
```

## Getting the state of a pin
```java
pin.isHigh()
```

## Releasing a pin
```java
pin.close();
```

## Pin with try-with-resources
Example with a blinking pin
```java
try(Pin pin = GPIO.open(Pins.PIN11, GPIO.Mode.OUTPUT)) {
    for (int i = 0; i < N; i++) {
        if(i % 2 == 0) {
            pin.high();
        } else {
            pin.low();
        }
        // delay code
    }
}
```

Made for Club INTech