package solution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator2 extends JFrame
{
    private JFrame calculatorFrame;
    
    public Calculator2()
    {
        
        calculatorFrame = new JFrame();
        calculatorFrame.setLocation(100,100);
        calculatorFrame.setSize(320,130);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setTitle("GUI Calculator");
        
        initializeComponents();
        
        calculatorFrame.setVisible(true);
        
    }
    
    public static void main(String[] args)
    {
        Calculator2 calc = new Calculator2();
    }
    
    
    public JFrame getFrame()
    {
        return calculatorFrame;
    }
    
    public void initializeComponents()
    {
        JPanel buttonPanel = new JPanel();
        JButton calculateButton= new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        clearButton.setName("clearButton");
        calculateButton.setName("calculateButton");
        
        JPanel resultPanel = new JPanel();
        JLabel resultLabel = new JLabel("  Result");
        resultLabel.setName("resultLabel");
        
        JPanel textPanel = new JPanel();
        JTextField infixExpression = new JTextField(25);
        infixExpression.setName("infixExpression");

        textPanel.add(infixExpression);

        
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        
        resultPanel.add(resultLabel);
        
        
        calculatorFrame.add(textPanel,BorderLayout.PAGE_START);
        calculatorFrame.add(resultPanel,BorderLayout.LINE_START);
        calculatorFrame.add(buttonPanel,BorderLayout.PAGE_END);
        
        
        
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String temp;
                    ExpressionEvaluator answer = new ExpressionEvaluator();
                    temp = answer.toPostfix(infixExpression.getText());
                    if(answer.evaluate(temp) == -9999)
                    {
                        resultLabel.setText("  Result = error");
                    }
                    else
                    resultLabel.setText("  Result = " + answer.evaluate(temp));
                }
                catch(Exception e1) {
                    resultLabel.setText("  Result = Error");
                    resultLabel.setName("Error");
                }
            }
        });
        
        
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    infixExpression.setText("");
                    resultLabel.setText("  Result");
                }
        });
    }
   
}
