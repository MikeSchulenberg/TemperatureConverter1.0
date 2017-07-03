/** This class stores temperature data entered by the user and converts it
 * from Fahrenheit to Celsius and from Celsius to Fahrenheit.
 *
 * filename: Converter.java
 * @author Mike Schulenberg - mike.schulenberg@gmail.com
 * @version 1.0
 * Copyright (c) 2017 Mike Schulenberg
 */

package temperatureconverter;

public class Converter
{
    // the temperature in degrees before conversion
    double initialTemperature = 0;      
    // the temperature in degrees after conversion
    double convertedTemperature = 0;
    
    Converter()
    {
        // Intentionally empty.
    }
    
    /** Gets the temperature data as entered by the user.
     * 
     * @return The temperature in degrees before conversion.
     */
    public double getInitialTemperature()
    {
        return initialTemperature;
    }
    
    /** Gets the converted temperature.
     * 
     * @return The temperature in degrees after conversion.
     */
    public double getConvertedTemperature()
    {
        return convertedTemperature;
    }
    
    /** Converts the temperature entered by the user from Fahrenheit to Celsius.
     * 
     * @param temperature The temperature in degrees.
     */
    public void doConvertToCelsius(double temperature)
    {
        initialTemperature = temperature;
        convertedTemperature = (temperature - 32.0) * (0.5556);
    }
    
    /** Converts the temperature entered by the user from Celsius to Fahrenheit.
     * 
     * @param temperature The temperature in degrees.
     */
    public void doConvertToFahrenheit(double temperature)
    {
        initialTemperature = temperature;
        convertedTemperature = (temperature * (1.8)) + 32.0;
    }
}