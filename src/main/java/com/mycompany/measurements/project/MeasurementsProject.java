/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metricconverter;

/**
 *
 * @author fatim
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetricConverterGUI extends JFrame {

    private JLabel fromLabel, toLabel, quantityLabel, resultLabel;
    private JComboBox<String> fromComboBox, toComboBox;
    private JTextField quantityField;
    private JButton convertButton;

    public MetricConverterGUI() {
        setTitle("Metric Converter");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        quantityLabel = new JLabel("Quantity:");
        resultLabel = new JLabel("");

        fromComboBox = new JComboBox<>(new String[]{"Feet", "Pounds", "Fahrenheit"});
        toComboBox = new JComboBox<>(new String[]{"Meters", "Kilograms", "Celsius"});

        quantityField = new JTextField(10);
        convertButton = new JButton("Convert");

        // Set layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(toLabel)
                        .addComponent(quantityLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromComboBox)
                        .addComponent(toComboBox)
                        .addComponent(quantityField)
                        .addComponent(convertButton)
                        .addComponent(resultLabel))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fromLabel)
                        .addComponent(fromComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(toLabel)
                        .addComponent(toComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(quantityLabel)
                        .addComponent(quantityField))
                .addComponent(convertButton)
                .addComponent(resultLabel)
        );

        // Add action listener to convertButton
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        // Add action listeners to combo boxes
        fromComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateToComboBox();
            }
        });

        toComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFromComboBox();
            }
        });

        // Set default selection
        fromComboBox.setSelectedIndex(0);
        updateToComboBox();
    }

    private void updateToComboBox() {
        String fromUnit = fromComboBox.getSelectedItem().toString();
        switch (fromUnit) {
            case "Feet":
                toComboBox.setSelectedItem("Meters");
                break;
            case "Pounds":
                toComboBox.setSelectedItem("Kilograms");
                break;
            case "Fahrenheit":
                toComboBox.setSelectedItem("Celsius");
                break;
        }
    }

    private void updateFromComboBox() {
        String toUnit = toComboBox.getSelectedItem().toString();
        switch (toUnit) {
            case "Meters":
                fromComboBox.setSelectedItem("Feet");
                break;
            case "Kilograms":
                fromComboBox.setSelectedItem("Pounds");
                break;
            case "Celsius":
                fromComboBox.setSelectedItem("Fahrenheit");
                break;
        }
    }

    private void convert() {
        String fromUnit = fromComboBox.getSelectedItem().toString();
        String toUnit = toComboBox.getSelectedItem().toString();
        double quantity;
        try {
            quantity = Double.parseDouble(quantityField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid quantity!");
            return;
        }

        double result;
        switch (fromUnit) {
            case "Feet":
                if (toUnit.equals("Meters"))
                    result = quantity * 0.305;
                else
                    result = quantity / 0.305;
                break;
            case "Pounds":
                if (toUnit.equals("Kilograms"))
                    result = quantity * 0.454;
                else
                    result = quantity / 0.454;
                break;
            case "Fahrenheit":
                if (toUnit.equals("Celsius"))
                    result = (quantity - 32) * 5 / 9;
                else
                    result = quantity * 9 / 5 + 32;
                break;
            default:
                resultLabel.setText("Invalid units!");
                return;
        }
        resultLabel.setText(String.format("%.2f %s = %.2f %s", quantity, fromUnit, result, toUnit));
    }

    public static void main(String[] args) {
        MetricConverterGUI converter = new MetricConverterGUI();
        converter.setVisible(true);
    }
}
