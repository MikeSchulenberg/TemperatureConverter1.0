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
public class Controller
{
    private final Converter CONVERTER;
    private final View VIEW;
    
    Controller(Converter converter, View view)
    {
        this.CONVERTER = converter;
        this.VIEW = view;
    }
    
    public void initializeView()
    {
        VIEW.initializeView(this);
    }
    
    public void convertToCelsius(String input)
    {
        try
        {
            double initialTemp = Double.parseDouble(input);
            CONVERTER.convertToCelsius(initialTemp);
            VIEW.printConvertedTemperature(CONVERTER, "F", "Celsius");
        }
        
        catch(Exception e)
        {
            VIEW.printInputError();
        }
    }
    
    public void convertToFahrenheit(String input)
    {
        try
        {
            double initialTemp = Double.parseDouble(input);
            CONVERTER.convertToFahrenheit(initialTemp);
            VIEW.printConvertedTemperature(CONVERTER, "C", "Fahrenheit");
        }
        
        catch(Exception e)
        {
            VIEW.printInputError();
        }
    }
}