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
    
    JTextField temperatureField;
    JLabel scaleLabel;
    JRadioButton toCelsius;
    JRadioButton toFahrenheit;
    JLabel outputLabel;
    
    boolean convertToCelsius = true;
    
    View()
    {
        // Deliberately empty.
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
        JLabel temperatureLabel = new JLabel("Enter temperature");
        
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
        toCelsius = new JRadioButton("Convert to Celsius", true);
        toFahrenheit = new JRadioButton("Convert to Fahrenheit", false);
        
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(toCelsius);
        radioButtons.add(toFahrenheit);
        
        toCelsius.addActionListener(new radioButtonListener());
        toFahrenheit.addActionListener(new radioButtonListener());
        
        JPanel scaleSelectionPanel = new JPanel();
        scaleSelectionPanel.add(toCelsius);
        scaleSelectionPanel.add(toFahrenheit);
        
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
            convertToCelsius = (e.getSource() == toCelsius);
            
            if (convertToCelsius)
            {
                scaleLabel.setText(DEGREE_SYMBOL + " F");
            }
            
            else
            {
                scaleLabel.setText(DEGREE_SYMBOL + " C");
            }
        }
    }
    
    private class doConversionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String input = temperatureField.getText();           
            
            if (convertToCelsius)
            {
                controller.convertToCelsius(input);
            }
            
            else
            {
                controller.convertToFahrenheit(input);
            }
        }    
    }
}
