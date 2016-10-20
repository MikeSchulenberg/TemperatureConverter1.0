/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Mike Schulenberg
 */
public class View extends JFrame
{
    private Controller controller;
    
    private final int WIDTH = 350;
    private final int HEIGHT = 250;
    private final String DEGREE_SYMBOL = "\u00B0";
    
    private enum ConversionMode {FAHRENHEIT_TO_CELSIUS, CELSIUS_TO_FAHHRENHEIT};
    private ConversionMode conversionMode;
    
    JTextField temperatureField;
    JLabel scaleLabel;
    JRadioButton celsiusRadioButton;
    JRadioButton fahrenheitRadioButton;
    JLabel outputLabel;
    
    boolean convertToCelsius = true;
    
    View()
    {
        conversionMode = ConversionMode.FAHRENHEIT_TO_CELSIUS;
    }
    
    public void initializeView(Controller controller)
    {
        this.controller = controller;
        
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new GridLayout(4,1));
        
        buildInputPanel();
        buildScaleSelectionPanel();
        buildOKbuttonPanel();
        buildOutputPanel();
        
        setVisible(true);
    }
    
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
        DecimalFormat df = new DecimalFormat("#.##");
        String initialTemp = df.format(converter.getInitialTemperature());
        String convertedTemp = df.format(converter.getConvertedTemperature());
        
        outputLabel.setText(initialTemp + DEGREE_SYMBOL + " " + initialTempStr 
                + " = " + convertedTemp + DEGREE_SYMBOL + " " 
                + convertedTempStr);
    }
    
    private void buildInputPanel()
    {
        JLabel temperatureLabel = new JLabel("Temperature");
        
        temperatureField = new JTextField(6);
        temperatureField.addActionListener(new doConversionListener());
        
        scaleLabel = new JLabel(DEGREE_SYMBOL + " F");
        
        JPanel inputPanel = new JPanel();
        inputPanel.add(temperatureLabel);
        inputPanel.add(temperatureField);
        inputPanel.add(scaleLabel);
        
        add(inputPanel);
    }
    
    private void buildScaleSelectionPanel()
    {
        celsiusRadioButton = new JRadioButton("Convert to Celsius", true);
        fahrenheitRadioButton = new JRadioButton("Convert to Fahrenheit", 
                false);
        
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(celsiusRadioButton);
        radioButtons.add(fahrenheitRadioButton);
        
        celsiusRadioButton.addActionListener(new radioButtonListener());
        fahrenheitRadioButton.addActionListener(new radioButtonListener());
        
        JPanel scaleSelectionPanel = new JPanel();
        scaleSelectionPanel.add(celsiusRadioButton);
        scaleSelectionPanel.add(fahrenheitRadioButton);
        
        add(scaleSelectionPanel);
    }
    
    private void buildOKbuttonPanel()
    {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new doConversionListener());
        
        JPanel okButtonPanel = new JPanel();
        okButtonPanel.add(okButton);
        
        add(okButtonPanel);
    }
    
    private void buildOutputPanel()
    {
        outputLabel = new JLabel();
        
        JPanel outputPanel = new JPanel();
        outputPanel.add(outputLabel);
        
        add(outputPanel);
    }
    
    private class radioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {           
            if (e.getSource() == celsiusRadioButton)
            {
                conversionMode = ConversionMode.FAHRENHEIT_TO_CELSIUS;
            }
            
            else
            {
                conversionMode = ConversionMode.CELSIUS_TO_FAHHRENHEIT;
            }
            
            switch(conversionMode)
            {
                case FAHRENHEIT_TO_CELSIUS :
                    scaleLabel.setText(DEGREE_SYMBOL + " F");
                    break;
                case CELSIUS_TO_FAHHRENHEIT :
                    scaleLabel.setText(DEGREE_SYMBOL + " C");
                    break;               
            }
        }
    }
    
    private class doConversionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String input = temperatureField.getText();           
            
            switch(conversionMode)
            {
                case FAHRENHEIT_TO_CELSIUS :
                    controller.convertToCelsius(input);
                    break;
                case CELSIUS_TO_FAHHRENHEIT :
                    controller.convertToFahrenheit(input);
                    break;               
            }
        }    
    }
}
