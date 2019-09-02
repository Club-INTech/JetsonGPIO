import fr.club_intech.gpio.GPIO;
import fr.club_intech.gpio.Pin;
import fr.club_intech.gpio.Pins;

import java.io.IOException;

public class Tests {

    public static void main(String[] args) {
        try(Pin pin = GPIO.open(Pins.GEN0, GPIO.Mode.OUTPUT)) {
            pin.high();
            try {
                Thread.sleep(1000*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
