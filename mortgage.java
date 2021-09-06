import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.text.DecimalFormat;

public class mortgage implements ActionListener {
    JLabel payments = new JLabel("Monthly Payment: ");
    JLabel amountLabel = new JLabel("Principle Loan Amount ($)");
    JTextArea amount = new JTextArea("");
    JLabel interestLabel = new JLabel("Annual Interest Rate (%)");
    JTextArea interest = new JTextArea();
    JLabel timeLabel = new JLabel("Mortgage Period (years)");
    JTextArea time = new JTextArea();

    public mortgage ()
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton go = new JButton("Calculate");
        go.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(amountLabel);
        panel.add(amount);
        panel.add(interestLabel);
        panel.add(interest);
        panel.add(timeLabel);
        panel.add(time);
        panel.add(go);
        panel.add(payments);

        frame.setPreferredSize(new Dimension(500, 500));
        frame.setResizable(false);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mortgage Calculator");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new mortgage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
        try 
        {
            double mortgageAmount = Double.parseDouble(amount.getText());
            double interestRate = Double.parseDouble(interest.getText())/100/12;
            double mortgagePeriod = Double.parseDouble(time.getText())*12;

            // M = P [ i(1 + i)^n ] / [ (1 + i)^n – 1]
            double monthlyPayments = 
            mortgageAmount * (interestRate*Math.pow((1 + interestRate),(mortgagePeriod))) / (Math.pow((1+interestRate),mortgagePeriod)-1);

            int output = (int) Math.round(monthlyPayments);
            payments.setText("Monthly Payement: $"+Integer.toString(output));
        } 
        catch (Exception ex) 
        {
            payments.setText("Invalid Inputs! Type in only numbers and decimals");
        }
        
        
    }
}