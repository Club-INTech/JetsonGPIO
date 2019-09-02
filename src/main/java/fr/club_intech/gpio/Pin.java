package fr.club_intech.gpio;

import java.io.Closeable;
import java.io.IOException;

public class Pin implements Closeable {

    private int number;
    private GPIO.Mode mode;

    Pin(int number, GPIO.Mode mode) {
        this.number = number;
        this.mode = mode;
    }

    public void high() {
        Sysfs.setState(number, true);
    }

    public void low() {
        Sysfs.setState(number, false);
    }

    public boolean isHigh() {
        return Sysfs.isHigh(number);
    }

    public void setMode(GPIO.Mode mode) {
        Sysfs.setMode(number, mode);
    }

    public int getNumber() {
        return number;
    }

    public GPIO.Mode getMode() {
        return mode;
    }

    @Override
    public void close() throws IOException {
        GPIO.close(this);
    }
}
