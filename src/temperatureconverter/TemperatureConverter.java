/** This program converts the temperature in degrees from Fahrenheit to Celsius
 * and Celsius to Fahrenheit. It follows the Model-View-Controller design 
 * pattern:
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 *
 * @author Mike Schulenberg
 * @version 1.0
 * @since 10/19/16
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