/**
 * Copyright (c) 2019, INTech.
 * INTech's JetsonGPIO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * INTech's JetsonGPIO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with it.  If not, see <http://www.gnu.org/licenses/>.
 **/
package fr.club_intech.gpio;

import java.io.Closeable;

/**
 * Represents a single GPIO pin. Can be put in HIGH or LOW state if mode is OUTPUT. Can be queried if mode is INPUT.
 *
 * @author Xavier "jglrxavpok" Niochaut
 */
public class Pin implements Closeable {

    private int number;
    private GPIO.Mode mode;

    Pin(int number, GPIO.Mode mode) {
        this.number = number;
        this.mode = mode;
    }

    /**
     * Sets a pin to HIGH
     */
    public void high() {
        Sysfs.setState(number, true);
    }

    /**
     * Sets a pin to LOW
     */
    public void low() {
        Sysfs.setState(number, false);
    }

    /**
     * Queries the state of the pin
     * @return 'true' is the pin is set to HIGH, 'false' if set to LOW
     */
    public boolean isHigh() {
        return Sysfs.isHigh(number);
    }

    /**
     * Changes the mode of the pin
     * @param mode the pin mode
     */
    public void setMode(GPIO.Mode mode) {
        Sysfs.setMode(number, mode);
    }

    /**
     * GPIO ID of this pin
     * @return the ID
     */
    public int getNumber() {
        return number;
    }

    /**
     * Mode of this pin
     * @return the mode
     */
    public GPIO.Mode getMode() {
        return mode;
    }

    /**
     * Releases the pin to the OS
     */
    @Override
    public void close() {
        GPIO.close(this);
    }
}
