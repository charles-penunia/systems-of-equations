
package systemsofequations;

import javax.swing.JFrame;

/* Charles Penunia. CMPSC 221.
 * This program creates systems of equations for users to solve
 */
public class SystemsofEquations {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Systems of Equations");
        frame.setSize(560, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ThePanel());
        frame.setLocation(400, 130);
        frame.setVisible(true);
    }
}
