import fr.club_intech.gpio.GPIO;
import fr.club_intech.gpio.Pin;
import fr.club_intech.gpio.Pins;
import org.junit.Test;

import java.io.IOException;

public class Tests {

    @Test
    public void test_SetGEN0ToHigh() throws IOException {
        try(Pin pin = GPIO.open(Pins.PIN11, GPIO.Mode.OUTPUT)) {
            for (int i = 0; i < 60; i++) {
                if(i % 2 == 0) {
                    pin.high();
                } else {
                    pin.low();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
