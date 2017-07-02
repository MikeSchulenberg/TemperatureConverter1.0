/** This class creates the GUI and displays it to the user.
 * 
 * It should accept outside commands only through a Controller object.
 *
 * filename: View.java
 * @author Mike Schulenberg - mike.schulenberg@gmail.com
 * @version 1.0
 * Copyright (c) 2017 Mike Schulenberg
 */

package temperatureconverter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.text.DecimalFormat;

public class View extends JFrame
{
    // to signal the Controller when ActionEvents are triggered
    private Controller controller;
    
    // default width of the GUI in pixels
    private final int WIDTH = 500;
    // default height of the GUI in pixels
    private final int HEIGHT = 250;
    // the character ° for use in text output
    private final String DEGREE_SIGN = "\u00B0";
    
    // used to switch between temperature conversion modes
    private enum ConversionMode {FAHRENHEIT_TO_CELSIUS, CELSIUS_TO_FAHHRENHEIT};
    // default conversion mode
    private ConversionMode conversionMode 
            = ConversionMode.FAHRENHEIT_TO_CELSIUS;
    
    // field where the user enters the tempature to be converted
    JTextField temperatureField;
    
    /* radio buttons that allow the user to select the temperature conversion
    mode */
    JRadioButton celsiusRadioButton;
    JRadioButton fahrenheitRadioButton;
    
    // label that displays the conversion result; also displays error messages
    JLabel outputLabel;
    
    View()
    {
        // Intentionally empty.
    }
    
    /** Builds the GUI. Requires a Controller object so ActionListener events
     * can be passed along to the Converter.
     * 
     * @param controller A Controller object.
     */
    public void doInitializeView(Controller controller)
    {
        this.controller = controller;
        
        // set window characteristics
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Temperature Converter 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new GridLayout(4,1));
        
        // build the component panels
        buildInputPanel();
        buildScaleSelectionPanel();
        buildOKbuttonPanel();
        buildOutputPanel();
        
        setVisible(true);
    }
    
    /** Prints an error message to the GUI to inform the user of an input
     * error.
     */
    public void printInputError()
    {
        outputLabel.setText("Error. Invalid temperature data.");
    }
    
    /** Prints the result of the temperature conversion.
     * 
     * @param converter A Converter object storing temperature data entered by
     * the user.
     * @param initialTempStr A String naming the measurement used for the
     * initial temperature--either "Fahrenheit" or "Celsius."
     * @param convertedTempStr A String naming the measurement used for the
     * converted temperature--either "Fahrenheit" or "Celsius."
     */
    public void printConvertedTemperature(Converter converter, 
            String initialTempStr, String convertedTempStr)
    {
        /* Prepare the unconverted and converted temperatures for output by
        converting them to Strings. */
        DecimalFormat df = new DecimalFormat("#.##");
        String initialTemp = df.format(converter.getInitialTemperature());
        String convertedTemp = df.format(converter.getConvertedTemperature());
        
        // Print the temperature conversion result to the GUI.
        outputLabel.setText(initialTemp + DEGREE_SIGN + " " + initialTempStr 
                + " = " + convertedTemp + DEGREE_SIGN + " " 
                + convertedTempStr);
    }
    
    /** Builds the JPanel where the user enters the temperature to be converted.
     */
    private void buildInputPanel()
    {
        /* Create all components, including an ActionListener for the JTextField
        where the user enters the temperature to be converted. Hitting the 
        Return key will execute the conversion. */
        
        JLabel temperatureLabel = new JLabel("Temperature");
        
        temperatureField = new JTextField(6);
        temperatureField.addActionListener(new doConversionListener());
        
        // Add components to a JPanel.
        JPanel inputPanel = new JPanel();
        inputPanel.add(temperatureLabel);
        inputPanel.add(temperatureField);
        
        // Add the JPanel to the GUI.
        add(inputPanel);
    }
    
    /** Builds the JPanel where the user selects the temperature conversion 
     * mode.
     */
    private void buildScaleSelectionPanel()
    {
        /* Create RadioButtons to allow the use to select the temperature
        conversion mode. */      
        celsiusRadioButton = new JRadioButton("Convert to Celsius", true);
        fahrenheitRadioButton = new JRadioButton("Convert to Fahrenheit", 
                false);
        
        // Add the RadioButtons to a ButtonGroup.
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(celsiusRadioButton);
        radioButtons.add(fahrenheitRadioButton);
        
        // Add ActionListeners to the RadioButtons.
        celsiusRadioButton.addActionListener(new radioButtonListener());
        fahrenheitRadioButton.addActionListener(new radioButtonListener());
        
        // Add components to a JPanel.
        JPanel scaleSelectionPanel = new JPanel();
        scaleSelectionPanel.add(celsiusRadioButton);
        scaleSelectionPanel.add(fahrenheitRadioButton);
        
        // Add the JPanel to the GUI.
        add(scaleSelectionPanel);
    }
    
    /** Builds the JPanel where the user can press a button to execute the
     * temperature conversion.
     */
    private void buildOKbuttonPanel()
    {
        // Prepare a JButton with ActionListener.
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new doConversionListener());
        
        // Add components to a JPanel.
        JPanel okButtonPanel = new JPanel();
        okButtonPanel.add(okButton);
        
        // Add the JPanel to the GUI.
        add(okButtonPanel);
    }
    
    /** Builds the JPanel where the results of the temperature conversion are
     * displayed, as well as any error messages generated by user action. 
     */
    private void buildOutputPanel()
    {
        outputLabel = new JLabel();
        
        // Add components to a JPanel.
        JPanel outputPanel = new JPanel();
        outputPanel.add(outputLabel);
        
        // Add the JPanel to the GUI.
        add(outputPanel);
    }
    
    /** Lets the user select the temperature conversion mode by clicking on one
     * of the radio buttons. 
     */
    private class radioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {           
            /* Update the temperature selection mode based on which radio
            button the user selects */
            
            if (e.getSource() == celsiusRadioButton)
            {
                conversionMode = ConversionMode.FAHRENHEIT_TO_CELSIUS;
            }
            
            else    
            {
                conversionMode = ConversionMode.CELSIUS_TO_FAHHRENHEIT;
            }            
        }
    }
    
    /** Signals the Controller to execute the temperature conversion.
     * 
     */
    private class doConversionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String temperature = temperatureField.getText();           
            
            switch(conversionMode)
            {
                case FAHRENHEIT_TO_CELSIUS :
                    controller.convertToCelsius(temperature);
                    break;
                case CELSIUS_TO_FAHHRENHEIT :
                    controller.convertToFahrenheit(temperature);
                    break;               
            }
        }    
    }
}