/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperatureconverter;

/**
 *
 * @author Mike Schulenberg
 */
public class Converter
{
    double initialTemperature = 0;
    double convertedTemperature = 0;
    
    Converter()
    {
        // Intentionally empty.
    }
    
    /** Gets the initialTemperature to be converted.
     * 
     * @return The initialTemperature to be converted, in either Fahrenheit or Celsius.
     */
    public double getInitialTemperature()
    {
        return initialTemperature;
    }
    
    /** Gets the initialTemperature after conversion.
     * 
     * @return The initialTemperature after conversion, in either Fahrenheit or 
 Celsius.
     */
    public double getConvertedTemperature()
    {
        return convertedTemperature;
    }
    
    /** Converts the initialTemperature entered by the user from Fahrenheit to Celsius.
     * 
     * @param temperature
     */
    public void doConvertToCelsius(double temperature)
    {
        this.initialTemperature = temperature;
        convertedTemperature = (temperature - 32.0) * (0.5556);
    }
    
    /** Converts the initialTemperature entered by the user from Celsius to Fahrenheit.
     * 
     * @param temperature
     */
    public void doConvertToFahrenheit(double temperature)
    {
        this.initialTemperature = temperature;
        convertedTemperature = (temperature * (1.8)) + 32.0;
    }
}
