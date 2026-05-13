package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    private JTextField tAmount;
    private JButton bDeposit, bBack;
    private String pin;
    private Color primaryColor = new Color(76, 175, 80);

    Deposit(String pin) {
        this.pin = pin;
        setTitle("ATM Simulator - Deposit");

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("DEPOSIT MONEY");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(primaryColor);
        titleLabel.setBounds(250, 40, 300, 40);
        mainPanel.add(titleLabel);

        JLabel lAmount = new JLabel("Enter Amount:");
        lAmount.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lAmount.setBounds(150, 120, 150, 30);
        mainPanel.add(lAmount);

        tAmount = new JTextField();
        tAmount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tAmount.setBounds(300, 120, 250, 40);
        tAmount.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        mainPanel.add(tAmount);

        bDeposit = createStyledButton("DEPOSIT", primaryColor);
        bDeposit.setBounds(200, 220, 150, 45);
        mainPanel.add(bDeposit);

        bBack = createStyledButton("BACK", Color.GRAY);
        bBack.setBounds(380, 220, 150, 45);
        mainPanel.add(bBack);

        bDeposit.addActionListener(this);
        bBack.addActionListener(this);

        add(mainPanel);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
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
        try {
            if (ae.getSource() == bDeposit) {
                String amount = tAmount.getText().trim();

                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter an amount");
                    return;
                }

                double amt = Double.parseDouble(amount);
                if (amt <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount");
                    return;
                }

                Date date = new Date();
                Conn c1 = new Conn();
                String q = "INSERT INTO bank VALUES(?,?,?,?)";
                PreparedStatement pstmt = c1.prepareStatement(q);
                pstmt.setString(1, pin);
                pstmt.setString(2, date.toString());
                pstmt.setString(3, "Deposit");
                pstmt.setString(4, amount);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "✓ Rs. " + amount + " Deposited Successfully!");
                dispose();
                new Transactions(pin).setVisible(true);
                c1.close();
            } else if (ae.getSource() == bBack) {
                dispose();
                new Transactions(pin).setVisible(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
