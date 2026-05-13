package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

/**
 * Fast Cash Screen
 * Package: com.bitbywaleed
 */
public class FastCash extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    FastCash(String pin) {
        this.pin = pin;
        setTitle("FAST CASH");

        JLabel l1 = new JLabel("SELECT WITHDRAWAL AMOUNT");
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(200, 150, 400, 35);
        add(l1);

        b1.setBounds(150, 220, 150, 35); add(b1);
        b2.setBounds(350, 220, 150, 35); add(b2);
        b3.setBounds(150, 280, 150, 35); add(b3);
        b4.setBounds(350, 280, 150, 35); add(b4);
        b5.setBounds(150, 340, 150, 35); add(b5);
        b6.setBounds(350, 340, 150, 35); add(b6);
        b7.setBounds(250, 400, 150, 35); add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 600);
        setLocation(500, 0);
        setVisible(true);
    }

    private double getBalance() {
        double balance = 0;
        try {
            Conn c1 = new Conn();
            String q = "SELECT type, amount FROM bank WHERE pin = ?";
            PreparedStatement pstmt = c1.prepareStatement(q);
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                double amt = Double.parseDouble(rs.getString("amount"));
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b7) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
                return;
            }

            String amountStr = ((JButton) ae.getSource()).getText().replace("Rs ", "");
            double amount = Double.parseDouble(amountStr);
            double balance = getBalance();

            if (balance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            Date date = new Date();
            Conn c1 = new Conn();
            String q = "INSERT INTO bank VALUES(?,?,?,?)";
            PreparedStatement pstmt = c1.prepareStatement(q);
            pstmt.setString(1, pin);
            pstmt.setString(2, date.toString());
            pstmt.setString(3, "Withdrawal");
            pstmt.setString(4, amountStr);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Rs. " + amountStr + " Debited Successfully");
            setVisible(false);
            new Transactions(pin).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}
