package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

/**
 * Cash Withdrawal Screen
 * Package: com.bitbywaleed
 */
public class Withdrawal extends JFrame implements ActionListener {
    JTextField t1;
    JButton b1, b2;
    JLabel l1, l2;
    String pin;

    Withdrawal(String pin) {
        this.pin = pin;
        setTitle("CASH WITHDRAWAL");

        l1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        l1.setFont(new Font("System", Font.BOLD, 16));

        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setFont(new Font("System", Font.BOLD, 16));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("WITHDRAW");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        setLayout(null);

        l1.setBounds(150, 150, 400, 20);
        add(l1);

        l2.setBounds(150, 180, 400, 20);
        add(l2);

        t1.setBounds(150, 220, 330, 30);
        add(t1);

        b1.setBounds(220, 300, 160, 35);
        add(b1);

        b2.setBounds(220, 350, 160, 35);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

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
            String amount = t1.getText();
            Date date = new Date();

            if (ae.getSource() == b1) {
                if (t1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
                } else {
                    double balance = getBalance();
                    double withdrawAmount = Double.parseDouble(amount);

                    if (balance < withdrawAmount) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    } else {
                        Conn c1 = new Conn();
                        String q = "INSERT INTO bank VALUES(?,?,?,?)";
                        PreparedStatement pstmt = c1.prepareStatement(q);
                        pstmt.setString(1, pin);
                        pstmt.setString(2, date.toString());
                        pstmt.setString(3, "Withdrawal");
                        pstmt.setString(4, amount);
                        pstmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                        setVisible(false);
                        new Transactions(pin).setVisible(true);
                    }
                }
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Withdrawal("").setVisible(true);
    }
}
