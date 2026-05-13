package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    private JTextField tfCardNo;
    private JPasswordField pfPin;
    private JButton bSignIn, bClear, bSignUp;
    private Color primaryColor = new Color(0, 102, 204);
    private Color secondaryColor = new Color(240, 248, 255);

    Login() {
        setTitle("ATM Simulator - Secure Banking");

        // Main Panel with Gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(25, 118, 210), 0, getHeight(), new Color(13, 71, 161));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // Welcome Card
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(200, 50, 400, 400);
        cardPanel.setLayout(null);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Title
        JLabel titleLabel = new JLabel("WELCOME TO ATM");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(primaryColor);
        titleLabel.setBounds(100, 30, 300, 40);
        cardPanel.add(titleLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Secure Banking at your fingertips");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setBounds(100, 70, 250, 20);
        cardPanel.add(subtitleLabel);

        // Card Number
        JLabel lCardNo = new JLabel("Card Number");
        lCardNo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lCardNo.setBounds(50, 120, 150, 25);
        cardPanel.add(lCardNo);

        tfCardNo = new JTextField();
        tfCardNo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfCardNo.setBounds(50, 148, 300, 35);
        tfCardNo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        cardPanel.add(tfCardNo);

        // PIN
        JLabel lPin = new JLabel("PIN");
        lPin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lPin.setBounds(50, 200, 150, 25);
        cardPanel.add(lPin);

        pfPin = new JPasswordField();
        pfPin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pfPin.setBounds(50, 228, 300, 35);
        pfPin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        cardPanel.add(pfPin);

        // Buttons
        bSignIn = createStyledButton("SIGN IN", primaryColor);
        bSignIn.setBounds(50, 290, 140, 40);
        cardPanel.add(bSignIn);

        bClear = createStyledButton("CLEAR", Color.GRAY);
        bClear.setBounds(210, 290, 140, 40);
        cardPanel.add(bClear);

        bSignUp = createStyledButton("CREATE NEW ACCOUNT", new Color(76, 175, 80));
        bSignUp.setBounds(50, 345, 300, 40);
        cardPanel.add(bSignUp);

        bSignIn.addActionListener(this);
        bClear.addActionListener(this);
        bSignUp.addActionListener(this);

        mainPanel.add(cardPanel);

        add(mainPanel);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            if (ae.getSource() == bSignIn) {
                String cardno = tfCardNo.getText().trim();
                String pin = new String(pfPin.getPassword()).trim();

                if (cardno.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter Card Number and PIN");
                    return;
                }

                Conn c1 = new Conn();
                String q = "SELECT * FROM login WHERE cardno = ? AND pin = ?";
                PreparedStatement pstmt = c1.prepareStatement(q);
                pstmt.setString(1, cardno);
                pstmt.setString(2, pin);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    dispose();
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect Card Number or PIN");
                }
                c1.close();
            } else if (ae.getSource() == bClear) {
                tfCardNo.setText("");
                pfPin.setText("");
            } else if (ae.getSource() == bSignUp) {
                dispose();
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}