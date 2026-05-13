package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * PIN Change Screen
 * Package: com.bitbywaleed
 */
public class Pin extends JFrame implements ActionListener {
    JPasswordField t1, t2;
    JButton b1, b2;
    JLabel l1, l2, l3;
    String pin;

    Pin(String pin) {
        this.pin = pin;
        setTitle("PIN CHANGE");

        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));

        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));

        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));

        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("CHANGE");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        setLayout(null);

        l1.setBounds(250, 130, 400, 35);
        add(l1);

        l2.setBounds(150, 200, 200, 35);
        add(l2);
        t1.setBounds(350, 200, 180, 25);
        add(t1);

        l3.setBounds(150, 250, 200, 35);
        add(l3);
        t2.setBounds(350, 250, 180, 25);
        add(t2);

        b1.setBounds(220, 320, 150, 35);
        add(b1);

        b2.setBounds(220, 370, 150, 35);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 600);
        setLocation(500, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String npin = new String(t1.getPassword());
            String rpin = new String(t2.getPassword());

            if (ae.getSource() == b1) {
                if (npin.equals("") || rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter both PIN fields");
                } else if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "PINs do not match");
                } else if (npin.length() != 4) {
                    JOptionPane.showMessageDialog(null, "PIN must be 4 digits");
                } else {
                    Conn c1 = new Conn();

                    // Update login table
                    String q1 = "UPDATE login SET pin = ? WHERE pin = ?";
                    PreparedStatement pstmt1 = c1.prepareStatement(q1);
                    pstmt1.setString(1, npin);
                    pstmt1.setString(2, pin);
                    pstmt1.executeUpdate();

                    // Update bank table
                    String q2 = "UPDATE bank SET pin = ? WHERE pin = ?";
                    PreparedStatement pstmt2 = c1.prepareStatement(q2);
                    pstmt2.setString(1, npin);
                    pstmt2.setString(2, pin);
                    pstmt2.executeUpdate();

                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
                    setVisible(false);
                    new Transactions(npin).setVisible(true);
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
        new Pin("").setVisible(true);
    }
}
