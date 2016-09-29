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
    double initialTemp = 0;
    double convertedTemp = 0;
    
    Converter()
    {
        // Intentionally empty.
    }
    
    /** Gets the temperature to be converted.
     * 
     * @return The temperature to be converted, in either Fahrenheit or Celsius.
     */
    public double getInitialTemp()
    {
        return initialTemp;
    }
    
    /** Gets the temperature after conversion.
     * 
     * @return The temperature after conversion, in either Fahrenheit or 
     * Celsius.
     */
    public double getConvertedTemp()
    {
        return convertedTemp;
    }
    
    /** Converts the temperature entered by the user from Fahrenheit to Celsius.
     * 
     * @param initialTemp
     */
    public void convertToCelsius(double initialTemp)
    {
        this.initialTemp = initialTemp;
        convertedTemp = (initialTemp - 32.0) * (0.5556);
    }
    
    /** Converts the temperature entered by the user from Celsius to Fahrenheit.
     * 
     * @param initialTemp
     */
    public void convertToFahrenheit(double initialTemp)
    {
        this.initialTemp = initialTemp;
        convertedTemp = (initialTemp * (1.8)) + 32.0;
    }
}
