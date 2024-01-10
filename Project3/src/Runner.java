import javax.swing.*;

public class Runner {
    
    public static void main(String[] args) {
        DataProcessor dataProcessor = new DataProcessor();
        JFrame frame = new JFrame("Netflix");
        frame.setContentPane(new userInterface(dataProcessor).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
