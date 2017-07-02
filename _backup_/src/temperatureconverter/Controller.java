/** This class relays commands to the Converter and View objects.
 *
 * @author Mike Schulenberg
 * @version 1.0
 * @since 10/19/16
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
     * Celsius. Instructs the View to display the converted temperature.
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
     * Fahrenheit. Instructs the View to display the converted temperature.
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