package calculatorapplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* 
* the new class will have two interfaces added: 
* the WindowListener and the ActionListener     
* This is class for making display of the calculator
* @author Michael Githinji
* @version CS 480 
* @since 11/2/2016
*/       
public class CalculatorApplication extends JFrame implements WindowListener,ActionListener {
        private static final long serialVersionUID = 1L;
        
        /* set contentPane and border layout */
        JPanel contentPane = new JPanel();
        BorderLayout main = new BorderLayout();
        
        /* create text boxes */
        TextField Box1 = new TextField(""); // input
        TextField Box2 = new TextField(""); // RPN
        TextField Box3 = new TextField(""); // Result
        
        /* create label for boxes */
        JLabel label1 = new JLabel("input");
        JLabel label2 = new JLabel("RPN");
        JLabel label3 = new JLabel("Result");
        
        
        /**
        * Main program area                                                    
        * main program to display the frame with all of its features     
        * This is the main method for this program
        * @param args arguments
        */        
        public static void main(String[] args) {
                /* Create a new JFrame with this title */        
                CalculatorApplication myFrame = new CalculatorApplication("Calculator");
                
                /* set the size of the frame in number of pixzels */        
                myFrame.setSize(500,500);
                
                /* display the frame */        
                myFrame.setVisible(true);
        }
        
        /**
        * Calculator Application class
        */        
        public CalculatorApplication (String title) {

                /* send the title to the super */
                super(title); // set title
                this.setContentPane(contentPane); // set content pane
                contentPane.setLayout(main); //set border layout
               
                /* set Panel for every button in center of the border layout */
                JPanel btnPanel = new JPanel(); // set panel for every button
                btnPanel.setLayout(new GridLayout(0,2));
                contentPane.add(btnPanel, BorderLayout.CENTER);
                
                /****************************************************************/
                /* make Panel for Number                                        */
                JPanel NumPanel = new JPanel(); // set panel for number button
                NumPanel.setLayout(new GridLayout(4, 3)); // grid layout 4 x 3 scale
                btnPanel.add(NumPanel, 0); // add Panel for number to btn Panel
                /* set numbers to buttons */
                NumPanel.add(new NumBtn("7"),0);
                NumPanel.add(new NumBtn("8"),1);
                NumPanel.add(new NumBtn("9"),2);
                NumPanel.add(new NumBtn("4"),3);
                NumPanel.add(new NumBtn("5"),4);
                NumPanel.add(new NumBtn("6"),5);
                NumPanel.add(new NumBtn("1"),6);
                NumPanel.add(new NumBtn("2"),7);
                NumPanel.add(new NumBtn("3"),8);
                NumPanel.add(new Clearbtn("C"),9); // This is for cancel the calculation when people have mistake. 
                NumPanel.add(new NumBtn("0"),10);
                NumPanel.add(new NumBtn("."),11);
                
                /****************************************************************/
                /* make Panel for Operator                                      */
                JPanel OperationPanel = new JPanel(); // set panel for operator button
                OperationPanel.setLayout(new GridLayout(4, 2));
                btnPanel.add(OperationPanel, 1); // add Panel for operator to btn Panel
                OperationPanel.add(new CalcBtn("("), 0);
                OperationPanel.add(new CalcBtn(")"), 1);
                OperationPanel.add(new CalcBtn("+"), 2);
                OperationPanel.add(new CalcBtn("-"), 3);
                OperationPanel.add(new CalcBtn("*"), 4);
                OperationPanel.add(new CalcBtn("/"), 5);
                OperationPanel.add(new CalcBtn("^"), 6);
                OperationPanel.add(new CalcBtn("="), 7);
                
                /****************************************************************/
                /* set text fields in south of the border layout                */
                JPanel boxPanel = new JPanel(); // set panel for text fields
                boxPanel.setLayout(new GridLayout(3,2));
                contentPane.add(boxPanel, BorderLayout.SOUTH);  // set gridlayout to South part of the borderlayout 
                boxPanel.add(label1, 0);
                boxPanel.add(Box1, 1);
                boxPanel.add(label2, 2);
                boxPanel.add(Box2, 3);
                boxPanel.add(label3, 4);
                boxPanel.add(Box3, 5);
                

                // register the listener object with a window
                addWindowListener(this);
        }
        
        /**
        * add numbers to Box1 for showing input         
        * This method is for getting input
        * @param btn get keyword from button
        * @throws NumberFormatException throw NumberFormatException 
        */
        public void expression(String btn) {
            int i;
            boolean num = false;
            try {
                i = Integer.parseInt(btn);
                num = true;
            } catch(NumberFormatException e) {}   // throw NumberFormatException
            if(num || btn.equals(".")) {    // if button pushed is number or point
                Box1.setText(Box1.getText() + btn);
            } else { // if button pushed is operator
                Box1.setText(Box1.getText() + " " + btn + " "); // blank for separating numbers
            }
        }
        
        
        /* 
        * This method is window action when window is open
        * @param e this is action when window is open
        */
        public void windowOpened(WindowEvent e) {
            System.out.println("Window event - openned window");
            System.out.println("Openned the window");
        }
        
        /**
        * Activate the frame when it is maximized
        * This method is activate the frame when it is maximized
        * @param e activate window
        */
        public void windowActivated(WindowEvent e) {
            System.out.println("Window event - activated window");
            System.out.println("Activated the window");
        }
        
        /**
        * Minimize the frame
        * This method is showing output when minimize the frame
        * @param e when window is minimize
        */
        public void windowIconified(WindowEvent e) { 
            System.out.println("Window event - minimized the window");
            System.out.println("Shrink the window");
        }
        
        /**
        * Maximize the frame
        * This method is showing output when maximize the frame
        * @param e when window is maximize
        */
        public void windowDeiconified(WindowEvent e) { 
            System.out.println("Window event - restored the window size");
            System.out.println("UnShrink the window");
        }
        
        /**
        * Deactivae the frame when it is iconized
        * This method is showing output when deactivate the frame when it is iconized
        * @param e when deactivate the window
        */
        public void windowDeactivated(WindowEvent e) {
            System.out.println("Window event - deactivate the window");
            System.out.println("Deactivate the window"); 
        }
        
        /**     
        * all done , clean up and go home
        * This method is showing output when window is closing
        * @param e when close the window 
        */
        public void windowClosing(WindowEvent e) {
            System.out.println("Window event - closing window");
            System.out.println("We are now leaving");
            dispose();
            System.exit(0);
        }
        
        /**
        * Close the frame display 
        * This method is moment when window is closing but it does not show on output
        * @param e when windows closed
        */
        public void windowClosed(WindowEvent e) {
            /* does not appear because the window already closed */
            System.out.println("We are now gone");
        }
        
        
        /**
        * these are the actions taken within the window         
        * take action when the button is selected               
        * @param e when button pushed
        */
        @Override
        public void actionPerformed(ActionEvent e) { 
            throw new UnsupportedOperationException("UNSUPPORT");
        } 
        
        /**
        * This class is for setting up for number button                             
        * @author ReiOrikata
        * @version NumBtn1.0
        */
        public class NumBtn extends JButton implements ActionListener {
            private static final long serialVersionUID = 1L;
            
            /**
            * This method get number and add to action listener
            * @param key get number from number button 
            */
            public NumBtn(String key) {
                super(key); // call constract of JButton
                this.addActionListener(this); // set action listener
            }
            
            /**
            * This method is action when button pushed
            * @param e when button pushed
            */
            public void actionPerformed(ActionEvent e) {
                String getkey = this.getText(); // get Number
                expression(getkey); // put into the Box1 
            }
        }
        
        /**
        * This class is for setting up for calculation                               
        * @author ReiOrikata
        * @version CalcBtn1.0
        */
        public class CalcBtn extends JButton implements ActionListener {
            private static final long serialVersionUID = 1L;
            
            /**
            * This method is get operator and add to action listener 
            * @param operator get operator
            */
            public CalcBtn(String operator) {
                super(operator);
                this.addActionListener(this);
            }
            
            /**
            * This method is action when button typed. Also doing calculate when push "=" button.
            * @param e when operator button pushed
            */
            public void actionPerformed(ActionEvent e) {
                if("=".equals(e.getActionCommand())) {  // when "=" is typed.
                    String equal = this.getText();  // get text 
                    String input = Box1.getText();  // show expression to Box1
                    Evaluator calculate = new Evaluator(input);  // call Evaluator for get RPN and result
                    String RPN = calculate.Convertor();  // get RPN from convertor method in evaluator class
                    String result = calculate.Compute(RPN); // set RPN as String
                    expression(equal);  // call expression method in evaluator class
                    Box2.setText(RPN);  // show RPN to Box2
                    if(result.equals("DNE")) { // This is case for when the calculation is illegal
                        Box3.setText("ERROR! You can't perform that arithmetic");
                    } else {
                        Box3.setText(result);   // show result to Box3
                    }
                } else {    // when normal operator chosen
                    String getOp = this.getText();
                    expression(getOp);
                }
            }
        }
        
        /**
        * set up cancel button to clear the calculation.
        * @author ReiOrikata
        * @version Clearbtn1.0                 
        */        
        public class Clearbtn extends JButton implements ActionListener {
            private static final long serialVersionUID = 1L;
            
            /**
            * This method is assign "C" and add to action listener
            * @param c get "C" from SimpleCalculator method
            */
            public Clearbtn(String c) {
                super(c);
                this.addActionListener(this);
            }
            
            /**
            * This method is when the button clicked, clear all Boxes
            * @param e when "C" button pushed
            */
            public void actionPerformed(ActionEvent e) {  // clear every Boxes
                Box1.setText("");   // clear Box1
                Box2.setText("");   // clear Box2
                Box3.setText("");   // clear Box3
            }
        } 
        
}