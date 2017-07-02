/** This program converts the temperature in degrees from Fahrenheit to Celsius
 * and Celsius to Fahrenheit. It uses the Model-View-Controller design pattern.
 *
 * filename: TemperatureConverter.java
 * @author Mike Schulenberg - mike.schulenberg@gmail.com
 * @version 1.0
 * Copyright (c) 2017 Mike Schulenberg
 */

package temperatureconverter;

public class TemperatureConverter
{

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args )
    {
        /* Create all program objects, passing the Model and View objects to
        the controller. */
        Converter converter = new Converter();
        View view = new View();
        Controller controller = new Controller(converter, view);
        
        // Create the GUI.
        controller.initializeView();
    } 
}