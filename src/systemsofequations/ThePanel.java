
package systemsofequations;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/* Charles Penunia. CMPSC 221.
 * This panel creates the interface for the systems of equations program
 */

public class ThePanel extends JPanel{
    JTextField a1Field, b1Field, cField, a2Field, b2Field, dField,
            xAnsField, yAnsField;
    JButton newProbButton, submitButton;
    int a1Num, b1Num, cNum, a2Num, b2Num, dNum;
    String xSol, ySol;
    Random generator;
    ArrayList <Matrix> m;
    ArrayList <Fraction> f;
    public ThePanel(){
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 1));
        // Creates and adds instructions to the top panel
        topPanel.add(new JLabel("Solve for x and y. Enter \"R\" for both fields"
                + " if there are infinite solutions or \"{}\" if there are"
                + " no "));
        topPanel.add(new JLabel("solutions. Otherwise, enter your answer "
                + "as a reduced fraction. You can adjust the systems of"));
        topPanel.add(new JLabel("equations by typing an integer in the"
                + " corresponding text fields. Do not enter zero in the"));
        topPanel.add(new JLabel("coefficients of the system of equations."));
        add(topPanel, BorderLayout.NORTH);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        // Creates and adds first equation to the left panel
        JPanel firstEq = new JPanel();
        a1Field = new JTextField("     ");
        firstEq.add(a1Field);
        firstEq.add(new JLabel("x +"));
        b1Field = new JTextField("     ");
        firstEq.add(b1Field);
        firstEq.add(new JLabel("y ="));
        cField = new JTextField("     ");
        firstEq.add(cField);
        leftPanel.add(firstEq);
        //Creates and adds second equation to the left panel
        JPanel secondEq = new JPanel();
        a2Field = new JTextField("     ");
        secondEq.add(a2Field);
        secondEq.add(new JLabel("x +"));
        b2Field = new JTextField("     ");
        secondEq.add(b2Field);
        secondEq.add(new JLabel("y ="));
        dField = new JTextField("     ");
        secondEq.add(dField);
        leftPanel.add(secondEq);
        add(leftPanel, BorderLayout.WEST);
        
        JPanel centerPanel = new JPanel();
        // Creates and adds the labels and fields for the user answer
        // to the center panel
        centerPanel.add(new JLabel("x = "));
        xAnsField = new JTextField("            ");
        centerPanel.add(xAnsField);
        centerPanel.add(new JLabel("y = "));
        yAnsField = new JTextField("            ");
        centerPanel.add(yAnsField);
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel rightPanel = new JPanel();
        // Creates and adds two buttons to the right panel 
        newProbButton = new JButton("New Problem");
        newProbButton.addActionListener(new NewProblemListener());
        rightPanel.add(newProbButton);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new GetSolutionListener());
        rightPanel.add(submitButton);
        add(rightPanel, BorderLayout.EAST);
        
        generator = new Random();
        m = new ArrayList<>();
        f = new ArrayList<>();
        assignRandNumbers();
    }
    private void assignRandNumbers(){
        a1Num = 1 + generator.nextInt(9);
        b1Num = 1 + generator.nextInt(9);
        cNum = -5 + generator.nextInt(15);
        a2Num = 1 + generator.nextInt(9);
        b2Num = 1 + generator.nextInt(9);
        dNum = -5 + generator.nextInt(15);
        
        a1Field.setText(" " + a1Num);
        b1Field.setText(" " + b1Num);
        cField.setText("   " + cNum);
        a2Field.setText(" " + a2Num);
        b2Field.setText(" " + b2Num);
        dField.setText("   " + dNum);
        xAnsField.setText("            ");
        yAnsField.setText("            ");
        getSolution();
    }
    private void assignUserNumbers(){
        a1Num = Integer.parseInt((a1Field.getText()).trim());
        b1Num = Integer.parseInt((b1Field.getText()).trim());
        cNum = Integer.parseInt((cField.getText()).trim());
        a2Num = Integer.parseInt((a2Field.getText()).trim());
        b2Num = Integer.parseInt((b2Field.getText()).trim());
        dNum = Integer.parseInt((dField.getText()).trim());
        
        if(a1Num == 0 || b1Num == 0 || a2Num == 0 || b2Num == 0)
            throw new NumberFormatException();
        getSolution();
    }
    private void getSolution(){
        int top, bottom;
        double rightSide;
        
        xSol = "R";
        ySol = "R";
        m.add(new Matrix(a1Num, b1Num, a2Num, b2Num));
        bottom = (m.get(0)).determinant();
        if (bottom != 0){
            m.add(new Matrix(cNum, b1Num, dNum, b2Num));
            top = (m.get(1)).determinant();
            f.add(new Fraction(top, bottom));
            xSol = (f.get(0)).getFractionString();
            
            m.add(new Matrix(a1Num, cNum, a2Num, dNum));
            top = (m.get(2)).determinant();
            f.add(new Fraction(top, bottom));
            ySol = (f.get(1)).getFractionString();
            
            f.clear();
        }
        else{
            rightSide = cNum*-a2Num/a1Num + dNum;
            if (rightSide != 0){
                xSol = "{}";
                ySol = "{}";
            }
        }
        m.clear();
    }
    class NewProblemListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            assignRandNumbers();
        }
    }
    class GetSolutionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                assignUserNumbers();
                String xInput = xAnsField.getText().trim();
                String yInput = yAnsField.getText().trim();
                if(xInput.equals("")|| yInput.equals(""))
                    throw new IllegalArgumentException();
                URL smileyFace = new URL("http://emojipedia.org/"
                        + "wp-content/uploads/2013/07/"
                        + "160x160x2-smiling-face-with-open-mouth.png"
                        + ".pagespeed.ic.GOzxA2LZaU.jpg");
                URL sadFace = new URL("http://emojipedia.org/"
                        + "wp-content/uploads/2013/07/"
                        + "46-frowning-face-with-open-mouth.png");
                if (xInput.equals(xSol) && yInput.equals(ySol))
                    JOptionPane.showMessageDialog(null, "Correct", 
                            "Your answer is...", JOptionPane.PLAIN_MESSAGE, 
                            new ImageIcon(smileyFace));
                else{
                    JOptionPane.showMessageDialog(null, "Wrong!\n"
                            + "x = " + xSol + " and y = " + ySol,
                            "Your answer is...", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon(sadFace));
                }
            }
            catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Coefficients in the "
                        + "equations must be non-zero integers and "
                        + "constants must be integers!");
            }
            catch (IllegalArgumentException e2){
                JOptionPane.showMessageDialog(null, "Please enter a"
                        + " value for x and y.");
            }
            catch (MalformedURLException ex) {
                Logger.getLogger(ThePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
