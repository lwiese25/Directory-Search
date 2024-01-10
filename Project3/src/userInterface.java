import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;


// Creating GUI for user to interact with the program
public class userInterface{
    public JPanel MainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel NetflixData;
    private JPanel Statistics;
    private JPanel Movies;
    private JPanel Search;
    private JButton Load;
    private JButton Quit;
    private JButton Information;
    private JButton searchButton;
    private JTextField titleField;
    private JTextField directorField;
    private JTextField genreField;
    private JComboBox movieOrTvShow;
    private JTextPane movieStatsTextPane;
    private JTextPane tvStatsTextPane;
    private JTextField genreTextField;
    private JTextField durationTextField;
    private JButton searchMovie;
    private JTextArea netflixData;
    private JPanel Buttons;
    private JTextArea movieSearchTextArea;
    private JTextArea mainSearchOutput;
    private DataProcessor dataProcessor;

    
    // Creating Data Processor object to reference Data Processor methods within the GUI
    public userInterface(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    Quit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
    
    // Creating QUIT button
        Information.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Created by: Luke Wiese\nCompleted on: 5/9/23\nThis program can only read tab delimited data.");
            }
        });
        
        // Creating LOAD button which loads all the data into the GUI including the statistics
        Load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int response = jfc.showOpenDialog(null);
                // Using a JFileChooser to load in file
                if (response == JFileChooser.APPROVE_OPTION){
                    File file = jfc.getSelectedFile();
                    try {
                        dataProcessor.readIn(file);
                        netflixData.setText(dataProcessor.dataMaker());
                        movieStatsTextPane.setText(
                                "Percentage of Movies: "+ dataProcessor.moviePercentage()+"\n\n" +
                                dataProcessor.movieRatingMaker()+"\n\n"+
                                "Most prolific director: "+dataProcessor.bestMovieDirector()+"\n\n"+
                                "Most popular genre: "+dataProcessor.bestMovieGenre()+"\n\n"+
                                "Most prolific actor: "+dataProcessor.bestMovieActor()+
                                "\n\nAverage Movie Duration: "+dataProcessor.movieDuration()+" minutes");
                        
                        
                        tvStatsTextPane.setText(
                                "Percentage of TV Shows: "+ dataProcessor.tvPercentage()+"\n\n"+
                                dataProcessor.tvRatingMaker()+ "\n\n"+
                                "Most prolific director: "+dataProcessor.bestTVDirector()+ "\n\n"+ 
                                "Most popular genre: "+dataProcessor.bestTVGenre()+"\n\n" +
                                "Most prolific actor: "+dataProcessor.bestTVActor()+
                                "\n\nAverage Season Length: "+dataProcessor.tvSeasons()+" seasons");
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        
        // Calling search movie after button press

        searchMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genre = genreTextField.getText();
                
                if(durationTextField.getText().equalsIgnoreCase("")||genre.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null,"Please fill both of the boxes to search a movie.");
                }
                else {
                    int duration = Integer.parseInt(durationTextField.getText());
                    if(dataProcessor.searchMovie(genre,duration).equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(null, "There were no results for your search. Please try again.");
                    }
                    else
                        movieSearchTextArea.setText(dataProcessor.searchMovie(genre, duration));
                }
                
            }
        
            
        });
        
        // Total search method is called after search button is pressed
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String title = titleField.getText();
                String director = directorField.getText();
                String genre = genreField.getText();
                
                if(title.equalsIgnoreCase("")&&director.equalsIgnoreCase("")&&genre.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null,"One field must be populated. Please try again.");
                }
                else{
                    String type = (String)movieOrTvShow.getSelectedItem();
                    if(dataProcessor.totalMovieSearch(type, title, director, genre).equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(null, "There are no results for that search. Please try again.");
                    }
                    else{
                        String output = dataProcessor.totalMovieSearch(type,title,director,genre);
                        mainSearchOutput.setText(output);
                    }
                    
                    
                }
                    
                
            }
        });
    }
}
