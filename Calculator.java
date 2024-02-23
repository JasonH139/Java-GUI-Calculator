import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator2 extends JFrame
{
	
	URL iconUrl = getClass().getResource("/calc.png");
	ImageIcon icon = new ImageIcon(iconUrl);
    
	private JFrame calculatorFrame;
    
    public Calculator2()
    {
        
        calculatorFrame = new JFrame();
        calculatorFrame.setLocation(100,100);
        calculatorFrame.setSize(520,130);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setTitle("GUI Calculator");
        
        initializeComponents();
        calculatorFrame.setIconImage(icon.getImage());
        
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
        JLabel resultLabel = new JLabel("Result =");
        resultLabel.setName("resultLabel");
        
        JPanel textPanel = new JPanel();
        JTextField infixExpression = new JTextField(25);
        infixExpression.setName("infixExpression");

        textPanel.add(infixExpression);

        
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        
        resultPanel.add(resultLabel);
        
        infixExpression.setHorizontalAlignment(JTextField.CENTER);
        infixExpression.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
        resultLabel.setFont(new Font("Serif",Font.CENTER_BASELINE,20));
        
        calculatorFrame.add(textPanel,BorderLayout.PAGE_START);
        calculatorFrame.add(resultPanel,BorderLayout.CENTER);
        calculatorFrame.add(buttonPanel,BorderLayout.PAGE_END);
        
        
        
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String temp;
                    ExpressionEvaluator answer = new ExpressionEvaluator();
                    temp = answer.toPostfix(infixExpression.getText());
                    if(answer.evaluate(temp) == -9999)
                    {
                        resultLabel.setText("Result = error");
                    }
                    else
                    resultLabel.setText("Result = " + answer.evaluate(temp));
                }
                catch(Exception e1) {
                    resultLabel.setText("Result = Error");
                    resultLabel.setName("Error");
                }
            }
        });
        
        
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    infixExpression.setText("");
                    resultLabel.setText("Result =");
                }
        });
    }
   
}
