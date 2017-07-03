/** This class regulates method calls between a Converter and a View object.
 *
 * filename: Controller.java
 * @author Mike Schulenberg - mike.schulenberg@gmail.com
 * @version 1.0
 * Copyright (c) 2017 Mike Schulenberg
 */

package temperatureconverter;

public class Controller
{
    private final Converter CONVERTER;
    private final View VIEW;
    
    /** Constructor that establishes this object as a Controller for a Converter
     * and a View.
     * 
     * @param converter A Converter object.
     * @param view  A View object.
     */
    Controller(Converter converter, View view)
    {
        this.CONVERTER = converter;
        this.VIEW = view;
    }
    
    /** Instructs the View to build the GUI. It passes this Controller object as
     * an argument so the View can return ActionListener events.
     */
    public void initializeView()
    {
        VIEW.doInitializeView(this);
    }
    
    /** Instructs the Converter to convert the temperature from Fahrenheit to
     * Celsius. Also instructs the View to display the converted temperature.
     * 
     * @param temperature The temperature in degrees.
     */
    public void convertToCelsius(String temperature)
    {
        /* Throw an exception if the user enters invalid temperature data.
        Otherwise, relay the conversion and display commands. */
        try
        {
            double temp = Double.parseDouble(temperature);
            CONVERTER.doConvertToCelsius(temp);
            VIEW.printConvertedTemperature(CONVERTER, "F", "Celsius");
        }
        
        catch(Exception e)
        {
            VIEW.printInputError();
        }
    }
    
    /** Instructs the Converter to convert the temperature from Celsius to
     * Fahrenheit. Also instructs the View to display the converted temperature.
     * 
     * @param temperature The temperature in degrees.
     */
    public void convertToFahrenheit(String temperature)
    {
        /* Throw an exception if the user enters invalid temperature data.
        Otherwise, relay the conversion and display commands. */
        try
        {
            double temp = Double.parseDouble(temperature);
            CONVERTER.doConvertToFahrenheit(temp);
            VIEW.printConvertedTemperature(CONVERTER, "C", "Fahrenheit");
        }
        
        catch(Exception e)
        {
            VIEW.printInputError();
        }
    }
}