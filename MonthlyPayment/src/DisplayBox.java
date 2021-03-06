/** This application calculates the mortgage, the monthly payment, annualPayment
*and displays the history of that payment
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

public class DisplayBox extends JFrame 
{
	/**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private int WIDTH=500;
   private int HEIGHT=300;
   private double monthlyPayment;   
   private JTextField loanTF;
   private JTextField interestTF;
   private JTextField durationTF;	   
   private JButton paymentJB;
   private JButton totalInterestJB;
   private JButton showHistoryJB;
   private JLabel bigTitle;	 
   private JLabel outputLabel;
   private JPanel topPanel;
   private JPanel outputPanel;
   private String output="";
   private double principle;
   private double interest;
   private int years;
   private Formulas formulas;
   
   public DisplayBox(Formulas f)
   {
      formulas=f;
   /**Creates the Frame where the values are displayed*/
      setTitle("Loan Calculator");
      setSize(WIDTH,HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      JPanel mainPanel= new JPanel(); //Creates the main panel
   
   /** Creates three text field where the user types the values*/
      loanTF= new JTextField(6);
      interestTF= new JTextField(6);
      durationTF= new JTextField(6);
      
   // Creates a label where the title is written
      bigTitle= new JLabel("Loan Calculator");
      bigTitle.setFont(new Font("SansSerif", Font.BOLD,36));
      
   //Creates the top label where the title is placed and changes its color   
      topPanel= new JPanel();
      topPanel.add(bigTitle);
      topPanel.setBackground(Color.pink);
      
   //Creates labels and adds them directly to the mainPanel, as well as the textFields      
      mainPanel.add(new JLabel("Loan Amount: "));
      mainPanel.add(loanTF);
   
      mainPanel.add(new JLabel("Interest Rate: "));
      mainPanel.add(interestTF);
   
      mainPanel.add(new JLabel("Duration: "));
      mainPanel.add(durationTF);
      
   //Creates three different buttons and connects them the their listener methods   
      paymentJB= new JButton("Compute Monthly Payment");
      paymentJB.addActionListener(new PaymentButtonListener());
      totalInterestJB = new JButton("Compute Total Interest");
      totalInterestJB.addActionListener(new InterestButtonListener());
      showHistoryJB= new JButton("Compute the Total History");
      showHistoryJB.addActionListener(new HistoryButtonListener() );
      
   //Adds the buttons to the mainPanel    
      mainPanel.add(paymentJB);
      mainPanel.add(totalInterestJB);
      mainPanel.add(showHistoryJB);
      mainPanel.setBackground(Color.white);
      
   //Creates the bottom panel, creates a label for it     
      outputPanel= new JPanel();
      outputLabel= new JLabel("Enter Values above");
      outputLabel.setFont(new Font("SansSerif", Font.PLAIN,18));
      outputPanel.add(outputLabel);
      outputPanel.setBackground(Color.pink);
      
   //Adds the panels to the frame  
      add(topPanel, BorderLayout.NORTH);
      add(mainPanel, BorderLayout.CENTER);
      add(outputPanel, BorderLayout.SOUTH);
      setBackground(Color.white);
      setVisible(true);
   }
   //Gets the loan amount (principle) from the TextField and checks its value
   private double getPrinciple()
   {
      try
      {principle= Double.parseDouble(loanTF.getText());
         if(principle<=0)
         {JOptionPane.showMessageDialog(null,"Invalid input (negative or zero): Loan Amount");
            throw new IllegalArgumentException("Only Positive Numbers allowed!");}
      }
      catch(IllegalArgumentException e)
      {JOptionPane.showMessageDialog(null,"Invalid input: Loan Amount");}
      return principle;
   }
      //Gets the interest rate from the TextField and checks its value

   private double getInterest()
   {
      try
      {
         interest=Double.parseDouble(interestTF.getText());
         if(interest<=0)
         {
            JOptionPane.showMessageDialog(null,"Invalid input (negative or zero): Interest Rate");
            throw new IllegalArgumentException("Only Positive Numbers allowed!");
         }
      
      }
      catch(IllegalArgumentException e)
      {
         JOptionPane.showMessageDialog(null,"Invalid input: Interest Rate");
      }
      return interest;
   }
      //Gets the loan duration (years) from the TextField and checks its value

   private int getYears()
   {
      try
      {
         years= Integer.parseInt(durationTF.getText());
         if(years<=0)
         {JOptionPane.showMessageDialog(null,"Invalid input (negative or zero): Duration");
            throw new IllegalArgumentException("Only Positive Numbers allowed!");}
      
      
      }
      catch(IllegalArgumentException e)
      {
         JOptionPane.showMessageDialog(null,"Invalid input: Duration");
      }
      return years;
   }
   
     //The listener method for the first button 
   private class PaymentButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         monthlyPayment= formulas.computeMonthlyPayment(getPrinciple(),getInterest(),getYears());
         output= formattingMethod(monthlyPayment);	
         outputLabel.setText("The monthly payment for your loan is: "+output+" $");  	  
      }
   }
   // The listener method for the second button
   private class InterestButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {                 
         double interest= (monthlyPayment*getYears()*12)-getPrinciple();
         output= formattingMethod(interest);	
         outputLabel.setText("The total interest paid for your loan is: " +output+" $");          
      }
   }
   
   //The listener method for the third button
   private class HistoryButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         int count=1;
         int month;
         int numberOfMonths= getYears()*12;
         double history= getPrinciple();
         for(month=numberOfMonths; month>=0; month--)
         {
            history=((1+(getInterest()/12))*history)-monthlyPayment;
            output= formattingMethod(history);
            JOptionPane.showMessageDialog(null, "After payment "+count+" there are : "+output+"$ left");                
            System.out.println("After payment "+count+" there are : "+output+"$ left");
            count++;
         }
      
      }
   }
   private String formattingMethod(double d)
   {
      return new DecimalFormat("0.00").format(d);
   }  

}
