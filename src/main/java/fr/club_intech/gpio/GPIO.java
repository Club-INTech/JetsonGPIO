package fr.club_intech.gpio;

import java.io.IOException;

public class GPIO {

    public static boolean DEBUG = true;

    public enum Mode {
        INPUT,
        OUTPUT;
    }

    public static Pin open(int number, GPIO.Mode mode) throws IOException {
        if(Sysfs.export(number)) {
            Sysfs.setMode(number, mode);
            return new Pin(number, mode);
        }
        throw new IOException("Failed to export pin "+number);
    }

    public static void close(Pin pin) {
        Sysfs.unexport(pin.getNumber());
    }
}
