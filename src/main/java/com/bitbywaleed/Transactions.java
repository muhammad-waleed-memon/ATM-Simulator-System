package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transactions extends JFrame implements ActionListener {
    private JButton[] buttons;
    private String pin;
    private Color primaryColor = new Color(0, 102, 204);
    private String[] buttonLabels = {"DEPOSIT", "WITHDRAWAL", "FAST CASH", "MINI STATEMENT", "PIN CHANGE", "BALANCE", "SIGN OUT"};
    private Color[] buttonColors = {new Color(76, 175, 80), new Color(244, 67, 54), new Color(255, 152, 0),
            new Color(33, 150, 243), new Color(156, 39, 176), new Color(0, 150, 136), new Color(244, 67, 54)};

    Transactions(String pin) {
        this.pin = pin;
        setTitle("ATM Simulator - Transaction Dashboard");

        // Set default close operation to DO_NOTHING_ON_CLOSE
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add window listener for close button
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                showSignOutConfirmation();
            }
        });

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
        JPanel welcomeCard = new JPanel();
        welcomeCard.setBackground(Color.WHITE);
        welcomeCard.setBounds(150, 30, 500, 100);
        welcomeCard.setLayout(null);
        welcomeCard.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JLabel welcomeLabel = new JLabel("Welcome to ATM Services");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(primaryColor);
        welcomeLabel.setBounds(120, 20, 300, 35);
        welcomeCard.add(welcomeLabel);

        JLabel instructionLabel = new JLabel("Please select your transaction type");
        instructionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        instructionLabel.setForeground(Color.GRAY);
        instructionLabel.setBounds(150, 55, 250, 25);
        welcomeCard.add(instructionLabel);

        mainPanel.add(welcomeCard);

        // Transaction Buttons
        buttons = new JButton[7];
        int startX = 150;
        int startY = 160;
        int width = 220;
        int height = 60;
        int gap = 20;

        for (int i = 0; i < buttons.length; i++) {
            int row = i / 2;
            int col = i % 2;
            int x = startX + (col * (width + gap));
            int y = startY + (row * (height + gap));

            buttons[i] = createStyledButton(buttonLabels[i], buttonColors[i]);
            buttons[i].setBounds(x, y, width, height);
            buttons[i].addActionListener(this);
            mainPanel.add(buttons[i]);
        }

        add(mainPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void showSignOutConfirmation() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to Sign Out?",
                "Sign Out",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            signOut();
        }
    }

    private void signOut() {
        JOptionPane.showMessageDialog(this,
                "✓ You have been signed out successfully!\n\n" +
                        "Thank you for using ATM Simulator.",
                "Signed Out",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();  // Close transactions window
        new Login().setVisible(true);  // Go back to login screen
    }

    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        String text = source.getText();

        switch(text) {
            case "DEPOSIT":
                dispose();
                new Deposit(pin).setVisible(true);
                break;
            case "WITHDRAWAL":
                dispose();
                new Withdrawal(pin).setVisible(true);
                break;
            case "FAST CASH":
                dispose();
                new FastCash(pin).setVisible(true);
                break;
            case "MINI STATEMENT":
                new MiniStatement(pin).setVisible(true);
                break;
            case "PIN CHANGE":
                dispose();
                new Pin(pin).setVisible(true);
                break;
            case "BALANCE":
                dispose();
                new BalanceEnquiry(pin).setVisible(true);
                break;
            case "SIGN OUT":
                showSignOutConfirmation();
                break;
        }
    }
}