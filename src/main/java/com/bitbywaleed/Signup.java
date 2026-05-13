package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Signup extends JFrame implements ActionListener {
    private JTextField tName, tFname, tEmail, tAddress, tCity, tPincode, tState;
    private JRadioButton rMale, rFemale, rMarried, rUnmarried, rOther;
    private JComboBox<String> dayCombo, monthCombo, yearCombo;
    private JButton bNext;
    private String formNo;
    private Color primaryColor = new Color(0, 102, 204);

    Signup() {
        Random ran = new Random();
        formNo = "" + Math.abs((ran.nextLong() % 9000L) + 1000L);

        setTitle("ATM Simulator - Account Opening");

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setBounds(0, 0, 900, 80);
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("NEW ACCOUNT APPLICATION");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 400, 40);
        headerPanel.add(titleLabel);

        JLabel formLabel = new JLabel("Form No: " + formNo);
        formLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formLabel.setForeground(Color.WHITE);
        formLabel.setBounds(700, 30, 150, 30);
        headerPanel.add(formLabel);

        mainPanel.add(headerPanel);

        // Form Fields
        int y = 100;
        int labelWidth = 150;
        int fieldWidth = 300;
        int xLabel = 100;
        int xField = 280;

        // Personal Details Section
        addSectionHeader(mainPanel, "PERSONAL DETAILS", 50, y);
        y += 40;

        addField(mainPanel, "Full Name:", xLabel, y, tName = new JTextField(), xField, fieldWidth);
        y += 50;

        addField(mainPanel, "Father's Name:", xLabel, y, tFname = new JTextField(), xField, fieldWidth);
        y += 50;

        // Date of Birth
        JLabel lDob = new JLabel("Date of Birth:");
        lDob.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lDob.setBounds(xLabel, y, labelWidth, 30);
        mainPanel.add(lDob);

        String[] days = new String[31];
        for(int i=0; i<31; i++) days[i] = String.valueOf(i+1);
        dayCombo = new JComboBox<>(days);
        dayCombo.setBounds(xField, y, 70, 30);
        mainPanel.add(dayCombo);

        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        monthCombo = new JComboBox<>(months);
        monthCombo.setBounds(xField + 80, y, 80, 30);
        mainPanel.add(monthCombo);

        String[] years = new String[50];
        for(int i=0; i<50; i++) years[i] = String.valueOf(1980 + i);
        yearCombo = new JComboBox<>(years);
        yearCombo.setBounds(xField + 170, y, 80, 30);
        mainPanel.add(yearCombo);
        y += 50;

        // Gender
        JLabel lGender = new JLabel("Gender:");
        lGender.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lGender.setBounds(xLabel, y, labelWidth, 30);
        mainPanel.add(lGender);

        rMale = new JRadioButton("Male");
        rFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rMale);
        genderGroup.add(rFemale);
        rMale.setBounds(xField, y, 80, 30);
        rFemale.setBounds(xField + 90, y, 80, 30);
        mainPanel.add(rMale);
        mainPanel.add(rFemale);
        y += 50;

        addField(mainPanel, "Email Address:", xLabel, y, tEmail = new JTextField(), xField, fieldWidth);
        y += 50;

        // Marital Status
        JLabel lMarital = new JLabel("Marital Status:");
        lMarital.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lMarital.setBounds(xLabel, y, labelWidth, 30);
        mainPanel.add(lMarital);

        rMarried = new JRadioButton("Married");
        rUnmarried = new JRadioButton("Unmarried");
        rOther = new JRadioButton("Other");
        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(rMarried);
        maritalGroup.add(rUnmarried);
        maritalGroup.add(rOther);
        rMarried.setBounds(xField, y, 90, 30);
        rUnmarried.setBounds(xField + 100, y, 100, 30);
        rOther.setBounds(xField + 210, y, 80, 30);
        mainPanel.add(rMarried);
        mainPanel.add(rUnmarried);
        mainPanel.add(rOther);
        y += 50;

        addSectionHeader(mainPanel, "ADDRESS DETAILS", 50, y);
        y += 40;

        addField(mainPanel, "Address:", xLabel, y, tAddress = new JTextField(), xField, fieldWidth);
        y += 50;

        addField(mainPanel, "City:", xLabel, y, tCity = new JTextField(), xField, fieldWidth);
        y += 50;

        addField(mainPanel, "Pincode:", xLabel, y, tPincode = new JTextField(), xField, fieldWidth);
        y += 50;

        addField(mainPanel, "State:", xLabel, y, tState = new JTextField(), xField, fieldWidth);
        y += 70;

        bNext = createStyledButton("NEXT →", primaryColor);
        bNext.setBounds(600, y, 150, 40);
        mainPanel.add(bNext);
        bNext.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        setSize(950, 750);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addSectionHeader(JPanel panel, String title, int x, int y) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(primaryColor);
        label.setBounds(x, y, 300, 30);
        panel.add(label);

        JSeparator separator = new JSeparator();
        separator.setBounds(x, y + 25, 800, 2);
        panel.add(separator);
    }

    private void addField(JPanel panel, String labelText, int xLabel, int y, JTextField field, int xField, int width) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setBounds(xLabel, y, 150, 30);
        panel.add(label);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBounds(xField, y, width, 35);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(field);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tName.getText().trim();
        String fname = tFname.getText().trim();
        String dob = dayCombo.getSelectedItem() + "-" + monthCombo.getSelectedItem() + "-" + yearCombo.getSelectedItem();
        String gender = rMale.isSelected() ? "Male" : (rFemale.isSelected() ? "Female" : "");
        String email = tEmail.getText().trim();
        String marital = rMarried.isSelected() ? "Married" : (rUnmarried.isSelected() ? "Unmarried" : (rOther.isSelected() ? "Other" : ""));
        String address = tAddress.getText().trim();
        String city = tCity.getText().trim();
        String pincode = tPincode.getText().trim();
        String state = tState.getText().trim();

        try {
            if (name.isEmpty() || email.isEmpty() || pincode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields (*)");
                return;
            }

            Conn c1 = new Conn();
            String q1 = "INSERT INTO signup VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = c1.prepareStatement(q1);
            pstmt.setString(1, formNo);
            pstmt.setString(2, name);
            pstmt.setString(3, fname);
            pstmt.setString(4, dob);
            pstmt.setString(5, gender);
            pstmt.setString(6, email);
            pstmt.setString(7, marital);
            pstmt.setString(8, address);
            pstmt.setString(9, city);
            pstmt.setString(10, pincode);
            pstmt.setString(11, state);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Personal details saved successfully!");
            dispose();
            new Signup2(formNo).setVisible(true);
            c1.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}