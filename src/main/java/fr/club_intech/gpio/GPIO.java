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

import java.io.IOException;

/**
 * Accesses to the GPIO controls: initializing and releasing a pin
 * @author Xavier "jglrxavpok" Niochaut
 */
public class GPIO {

    /**
     * Print out debug info?
     */
    public static boolean DEBUG = true;

    /**
     * Pin modes
     */
    public enum Mode {
        /**
         * Used to read the state of a pin
         */
        INPUT("in"),
        /**
         * Used to set the state of a pin
         */
        OUTPUT("out");

        private String id;

        Mode(String id) {
            this.id = id;
        }

        /**
         * ID used by Linux to know how to configure the pin
         * @return the ID used to configure the pin
         */
        public String id() {
            return this.id;
        }
    }

    /**
     * Opens a given GPIO pin in a given mode
     * @param number the GPIO pin number
     * @param mode the mode to configure
     * @return the pin
     * @see Pins
     * @throws IOException thrown if there is an error while opening the pin
     */
    public static Pin open(int number, GPIO.Mode mode) throws IOException {
        if(Sysfs.export(number)) {
            Sysfs.setMode(number, mode);
            return new Pin(number, mode);
        }
        throw new IOException("Failed to export pin "+number);
    }

    /**
     * Releases the given pin
     * @param pin the pin to release
     */
    public static void close(Pin pin) {
        Sysfs.unexport(pin.getNumber());
    }
}
