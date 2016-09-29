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
        
        buildPanel1();
        buildPanel2();
        buildPanel3();
        buildPanel4();
        
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
        String initialTemp = df.format(converter.getInitialTemp());
        String convertedTemp = df.format(converter.getConvertedTemp());
        
        outputLabel.setText(initialTemp + "\u00B0 " + initialTempStr 
                + " = " + convertedTemp + "\u00B0 " + convertedTempStr);
    }
    
    private void buildPanel1()
    {
        JLabel temperatureLabel = new JLabel("Enter temperature");
        
        temperatureField = new JTextField(6);
        temperatureField.addActionListener(new okButtonListener());
        
        scaleLabel = new JLabel("\u00B0 F");
        
        JPanel panel1 = new JPanel();
        panel1.add(temperatureLabel);
        panel1.add(temperatureField);
        panel1.add(scaleLabel);
        
        add(panel1);
    }
    
    private void buildPanel2()
    {
        toCelsius = new JRadioButton("Convert to Celsius", true);
        toFahrenheit = new JRadioButton("Convert to Fahrenheit", false);
        
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(toCelsius);
        radioButtons.add(toFahrenheit);
        
        toCelsius.addActionListener(new radioButtonListener());
        toFahrenheit.addActionListener(new radioButtonListener());
        
        JPanel panel2 = new JPanel();
        panel2.add(toCelsius);
        panel2.add(toFahrenheit);
        
        add(panel2);
    }
    
    private void buildPanel3()
    {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new okButtonListener());
        
        JPanel panel3 = new JPanel();
        panel3.add(okButton);
        
        add(panel3);
    }
    
    private void buildPanel4()
    {
        outputLabel = new JLabel();
        
        JPanel panel4 = new JPanel();
        panel4.add(outputLabel);
        
        add(panel4);
    }
    
    private class radioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            convertToCelsius = (e.getSource() == toCelsius);
            
            if (convertToCelsius)
            {
                scaleLabel.setText("\u00B0 F");
            }
            
            else
            {
                scaleLabel.setText("\u00B0 C");
            }
        }
    }
    
    private class okButtonListener implements ActionListener
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
