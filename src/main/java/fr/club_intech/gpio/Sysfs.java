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

import java.io.*;

/**
 * Utility methods to use the filesystem to control the GPIO
 * @author Xavier "jglrxavpok" Niochaut
 */
class Sysfs {
    private static final String CONTROL_INTERFACES = "/sys/class/gpio";
    private static final String EXPORT = CONTROL_INTERFACES + "/export";
    private static final String UNEXPORT = CONTROL_INTERFACES + "/unexport";

    private static final String GPIO_BASE = CONTROL_INTERFACES + "/gpio";
    private static final String DIRECTION_ENDPOINT = "/direction";
    private static final String VALUE_ENDPOINT = "/value";

    /**
     * Setups a GPIO pin in a given mode
     * @param gpioNumber the pin number
     * @param mode the mode to configure to
     */
    static void setMode(int gpioNumber, GPIO.Mode mode) {
        safeWrite(GPIO_BASE+gpioNumber+DIRECTION_ENDPOINT, mode.id());
    }

    /**
     * Requests access to the given GPIO pin
     * @param gpioNumber the pin to export
     * @return 'true' if the access was granted, 'false' otherwise
     */
    static boolean export(int gpioNumber) {
        return safeWrite(EXPORT, ""+gpioNumber);
    }

    /**
     * Release access to the given GPIO pin
     * @param gpioNumber the pin to release
     */
    static void unexport(int gpioNumber) {
        safeWrite(UNEXPORT, ""+gpioNumber);
    }

    /**
     * Is a given pin high or low?
     * @param gpioNumber the pin number
     * @return 'true' if the pin is HIGH, 'false' if it is LOW
     */
    static boolean isHigh(int gpioNumber) {
        return "1".equals(safeRead(GPIO_BASE+gpioNumber+VALUE_ENDPOINT));
    }

    /**
     * Puts the given GPIO pin in LOW or HIGH state
     * @param gpioNumber the gpio to change state of
     * @param active the gpio state
     */
    static void setState(int gpioNumber, boolean active) {
        safeWrite(GPIO_BASE+gpioNumber+VALUE_ENDPOINT, active ? "1" : "0");
    }

    /**
     * Writes a message to the given file, with no exception thrown
     * @param path the file to write to
     * @param message the message to send
     * @return 'true' if the write was successful, 'false' if an error occurred
     */
    private static boolean safeWrite(String path, String message) {
        if(GPIO.DEBUG) {
            System.out.println("Writing '"+message+"' to "+path);
        }
        try(FileOutputStream out = new FileOutputStream(path)) {
            PrintStream printer = new PrintStream(out);
            printer.println(message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Reads a line from the given file, throwing no exception.
     * Returns null if an error occured
     * @param path the file to read
     * @return the read line or null if an error occurred
     */
    private static String safeRead(String path) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
