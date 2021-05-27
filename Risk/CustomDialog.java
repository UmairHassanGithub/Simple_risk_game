package Risk;
 
 
 
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;
 
class CustomDialog extends JDialog
                   implements ActionListener,
                              PropertyChangeListener {
    private static String typedText = null;
    private JTextField textField;
    private Transfer dd; // don't change.
 
    private JOptionPane optionPane;
 
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";
    public static String ReturnNum = "0";
    /**
     * Returns null if the typed string was invalid;
     * otherwise, returns the string as the user entered it.
     */
    public static String getValidatedText() {
        return typedText;
    }
 
    /** Creates the reusable dialog. */
    public CustomDialog(Frame aFrame, Transfer Transfer) {
        super(aFrame, true);
        dd = Transfer;
        setSize(350,150);
       
        setTitle("Transfer");
        
        textField = new JTextField(10);
 
        //Create an array of the text and components to be displayed.
        String msgString1 = "How many whould you like to transfer?";
        String msgString2 = "(Put a number between 1 and 11 .)";
        Object[] array = {msgString1, msgString2, textField};
 
        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};
 
        //Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                    JOptionPane.QUESTION_MESSAGE,
                                    JOptionPane.YES_NO_OPTION,
                                    null,
                                    options,
                                    options[0]);
 
        //Make this dialog display it.
        setContentPane(optionPane);
 
        //Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
                    ReturnNum="0";
            }
        });
 
        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });
 
        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);
 
        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
    }
 
    /** This method handles events for the text field. */
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }
 
    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
 
        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();
 
            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }
 
            //Reset the JOptionPane's value.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);
 
            if (btnString1.equals(value)) {
                    typedText = textField.getText();
                String ucText = typedText;
                if ("1".equals(ucText)||"2".equals(ucText)||"3".equals(ucText)||"4".equals(ucText)||"5".equals(ucText)||"6".equals(ucText)||"7".equals(ucText)||"8".equals(ucText)||"9".equals(ucText)||"10".equals(ucText)||"11".equals(ucText)) {
                    //we're done; clear and dismiss the dialog
                	ReturnNum=ucText;
                    clearAndHide();
                } else {
                    //text was invalid
                    textField.selectAll();
                    JOptionPane.showMessageDialog(
                                    CustomDialog.this,
                                    "Sorry, \"" + typedText + "\" "
                                    + "isn't a valid response.\n"
                                    + "Please enter "
                                    + "A number 1 to 11" + ".",
                                    "Try again",
                                    JOptionPane.ERROR_MESSAGE);
                    typedText = null;
                    textField.requestFocusInWindow();
                }
            } else { 
                clearAndHide();
            }
        }
    }
 
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        textField.setText(null);
        setVisible(false);
    }
}
