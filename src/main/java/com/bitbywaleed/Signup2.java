package com.bitbywaleed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Signup Page 2: Additional Details
 * Package: com.bitbywaleed
 */
public class Signup2 extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JButton b;
    JRadioButton r1, r2, r3, r4;
    JTextField t1, t2;
    JComboBox<String> c1, c2, c3, c4, c5;
    String formno;

    Signup2(String formno) {
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        l1 = new JLabel("Page 2: Additional Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));

        l2 = new JLabel("Religion:");
        l2.setFont(new Font("Raleway", Font.BOLD, 20));

        l3 = new JLabel("Category:");
        l3.setFont(new Font("Raleway", Font.BOLD, 20));

        l4 = new JLabel("Income:");
        l4.setFont(new Font("Raleway", Font.BOLD, 20));

        l5 = new JLabel("Educational Qualification:");
        l5.setFont(new Font("Raleway", Font.BOLD, 20));

        l6 = new JLabel("Occupation:");
        l6.setFont(new Font("Raleway", Font.BOLD, 20));

        l7 = new JLabel("PAN Number:");
        l7.setFont(new Font("Raleway", Font.BOLD, 20));

        l8 = new JLabel("CNIC Number:");
        l8.setFont(new Font("Raleway", Font.BOLD, 20));

        l9 = new JLabel("Senior Citizen:");
        l9.setFont(new Font("Raleway", Font.BOLD, 20));

        l10 = new JLabel("Existing Account:");
        l10.setFont(new Font("Raleway", Font.BOLD, 20));

        l11 = new JLabel("Form No:");
        l11.setFont(new Font("Raleway", Font.BOLD, 13));

        l12 = new JLabel(formno);
        l12.setFont(new Font("Raleway", Font.BOLD, 13));

        b = new JButton("Next");
        b.setFont(new Font("Raleway", Font.BOLD, 14));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 14));

        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 14));

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);

        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);

        r3 = new JRadioButton("Yes");
        r3.setFont(new Font("Raleway", Font.BOLD, 14));
        r3.setBackground(Color.WHITE);

        r4 = new JRadioButton("No");
        r4.setFont(new Font("Raleway", Font.BOLD, 14));
        r4.setBackground(Color.WHITE);

        String[] religion = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        c1 = new JComboBox<>(religion);
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] category = {"General", "OBC", "SC", "ST", "Other"};
        c2 = new JComboBox<>(category);
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] income = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        c3 = new JComboBox<>(income);
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] education = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Others"};
        c4 = new JComboBox<>(education);
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] occupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Others"};
        c5 = new JComboBox<>(occupation);
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.BOLD, 14));

        setLayout(null);

        l11.setBounds(700, 10, 100, 30); add(l11);
        l12.setBounds(760, 10, 60, 30); add(l12);

        l1.setBounds(280, 30, 600, 40); add(l1);

        l2.setBounds(100, 120, 100, 30); add(l2);
        c1.setBounds(350, 120, 320, 30); add(c1);

        l3.setBounds(100, 170, 100, 30); add(l3);
        c2.setBounds(350, 170, 320, 30); add(c2);

        l4.setBounds(100, 220, 100, 30); add(l4);
        c3.setBounds(350, 220, 320, 30); add(c3);

        l5.setBounds(100, 270, 250, 30); add(l5);
        c4.setBounds(350, 270, 320, 30); add(c4);

        l6.setBounds(100, 320, 150, 30); add(l6);
        c5.setBounds(350, 320, 320, 30); add(c5);

        l7.setBounds(100, 370, 150, 30); add(l7);
        t1.setBounds(350, 370, 320, 30); add(t1);

        l8.setBounds(100, 420, 180, 30); add(l8);
        t2.setBounds(350, 420, 320, 30); add(t2);

        l9.setBounds(100, 470, 150, 30); add(l9);
        r1.setBounds(350, 470, 100, 30); add(r1);
        r2.setBounds(460, 470, 100, 30); add(r2);

        l10.setBounds(100, 520, 180, 30); add(l10);
        r3.setBounds(350, 520, 100, 30); add(r3);
        r4.setBounds(460, 520, 100, 30); add(r4);

        b.setBounds(570, 640, 100, 30); add(b);
        b.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 750);
        setLocation(500, 120);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String religion = (String) c1.getSelectedItem();
        String category = (String) c2.getSelectedItem();
        String income = (String) c3.getSelectedItem();
        String education = (String) c4.getSelectedItem();
        String occupation = (String) c5.getSelectedItem();
        String pan = t1.getText();
        String CNIC = t2.getText();
        String scitizen = "";
        if (r1.isSelected()) scitizen = "Yes";
        else if (r2.isSelected()) scitizen = "No";
        String eaccount = "";
        if (r3.isSelected()) eaccount = "Yes";
        else if (r4.isSelected()) eaccount = "No";

        try {
            if (t2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
            } else {
                Conn c1 = new Conn();
                String q1 = "INSERT INTO signup2 VALUES(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = c1.prepareStatement(q1);
                pstmt.setString(1, formno);
                pstmt.setString(2, religion);
                pstmt.setString(3, category);
                pstmt.setString(4, income);
                pstmt.setString(5, education);
                pstmt.setString(6, occupation);
                pstmt.setString(7, pan);
                pstmt.setString(8, CNIC);
                pstmt.setString(9, scitizen);
                pstmt.setString(10, eaccount);
                pstmt.executeUpdate();

                setVisible(false);
                new Signup3(formno).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup2("").setVisible(true);
    }
}
