import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// Creating class and initializing variables
public class DataProcessor {

    public DataProcessor() {
        medias = new ArrayList<Show>();
    }

    private ArrayList<Show> medias;

    private String bestMovieDirector;

    public String getBestMovieDirector() {
        return bestMovieDirector;
    }

    public void setBestMovieDirector(String bestMovieDirector) {
        this.bestMovieDirector = bestMovieDirector;
    }

    public DataProcessor(String bestMovieDirector) {
        this.bestMovieDirector = bestMovieDirector;
    }

    private String bestMovieGenre;

    public String getBestMovieGenre() {
        return bestMovieGenre;
    }

    public void setBestMovieGenre(String bestMovieGenre) {
        this.bestMovieGenre = bestMovieGenre;
    }

    private int movieCounter = 0;

    public int getMovieCounter() {
        return movieCounter;
    }

    public void setMovieCounter(int movieCounter) {
        this.movieCounter = movieCounter;
    }

    private int tvCounter = 0;

    public int getTvCounter() {
        return tvCounter;
    }

    public void setTvCounter(int tvCounter) {
        this.tvCounter = tvCounter;
    }

    private String bestTVGenre;

    public String getBestTVGenre() {
        return bestTVGenre;
    }

    public void setBestTVGenre(String bestTVGenre) {
        this.bestTVGenre = bestTVGenre;
    }

    public String getBestTVDirector() {
        return bestTVDirector;
    }

    public void setBestTVDirector(String bestTVDirector) {
        this.bestTVDirector = bestTVDirector;
    }

    private String bestTVDirector;
    private String bestMovieActor;

    public String getBestMovieActor() {
        return bestMovieActor;
    }

    public void setBestMovieActor(String bestMovieActor) {
        this.bestMovieActor = bestMovieActor;
    }

    private String bestTvActor;

    public String getBestTvActor() {
        return bestTvActor;
    }

    public void setBestTvActor(String bestTvActor) {
        this.bestTvActor = bestTvActor;
    }

    // Creating method to read in the data from the tab delimited data set. The method takes in a file and prints any wrong data to the console.
    public void readIn(File file) throws FileNotFoundException {

        // The method headers are stored at medias.get(0)
        Scanner reader = new Scanner(file);
        String header = reader.nextLine();
        String[] headers = header.split("\t");
        Show mediaHeader = new Show(headers[0].toUpperCase(), headers[1].toUpperCase(), headers[2].toUpperCase(), headers[3].toUpperCase(),
                headers[4].toUpperCase(), headers[5].toUpperCase(), headers[6].toUpperCase(), headers[7].toUpperCase(), headers[8].toUpperCase(),
                headers[9].toUpperCase(), headers[10].toUpperCase(), headers[11].toUpperCase());
        medias.add(mediaHeader);

        while (reader.hasNextLine()) {

            String line = reader.nextLine();
            String[] data = line.split("\t");
            try {
                Show media = new Show(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11]);
                medias.add(media);
            } catch (Exception e) {
                System.out.println("There is a problem with the following entry: ");
                System.out.println(line);
            }
        }
    }

    // Creating method that takes in nothing and returns a String that will be sent to the GUI
    public String dataMaker() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < medias.size(); i++) {
            String[] directors = medias.get(i).getDirector();
            String[] cast = medias.get(i).getCast();
            String[] genres = medias.get(i).getListed_in();

            if (medias.get(i).getType().equalsIgnoreCase("movie"))
                movieCounter++;
            else
                tvCounter++;

            sb.append((medias.get(0).getShow_id() + ": ") + (medias.get(i).getShow_id() + ", "));
            sb.append((medias.get(0).getType() + ": ") + (medias.get(i).getType() + ", "));
            sb.append((medias.get(0).getTitle() + ": ") + (medias.get(i).getTitle() + ", "));
            sb.append(medias.get(0).getDirector()[0] + ": ");
            for (int j = 0; j < directors.length; j++) {
                sb.append(directors[j] + ", ");
            }
            sb.append(medias.get(0).getCast()[0] + ": ");
            for (int j = 0; j < cast.length; j++) {
                sb.append(cast[j] + ", ");
            }
            sb.append((medias.get(0).getCountry() + ": ") + (medias.get(i).getCountry() + ", "));
            sb.append((medias.get(0).getDate_added() + ": ") + (medias.get(i).getDate_added() + ", "));
            sb.append((medias.get(0).getRelease_year() + ": ") + (medias.get(i).getRelease_year() + ", "));
            sb.append((medias.get(0).getRating() + ": ") + (medias.get(i).getRating() + ", "));
            sb.append((medias.get(0).getDuration()[0] + ": ") + (medias.get(i).getDuration() + ", "));

            sb.append(medias.get(0).getListed_in()[0] + ": ");
            for (int j = 0; j < genres.length; j++) {
                sb.append(genres[j] + ", ");
            }
            sb.append((medias.get(0).getDescription() + ": ") + (medias.get(i).getDescription() + "\n\n"));
        }


        return sb.toString();
    }


    // Method that calculates the percentage of medias that are movies
    public String moviePercentage() {
        double moviePercent = (movieCounter / (movieCounter + 0.0 + tvCounter)) * 100;
        return String.format("%.2f%%", moviePercent);
    }

    // Creates the percentages of each rating using a set of hashmaps to store their rating and frequency and then their rating and percent.
    public String movieRatingMaker() {
        HashMap<String, Double> ratingsPercent = new HashMap<>();
        HashMap<String, Double> ratingsHashMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        // Calculating percentages of ratings

        for (int i = 0; i < medias.size(); i++) {
            if (medias.get(i).getType().equalsIgnoreCase("movie")) {
                String rating = medias.get(i).getRating();

                if (ratingsHashMap.containsKey(rating)) {
                    ratingsHashMap.put(rating, ratingsHashMap.get(rating) + 1.0);
                } else {
                    ratingsHashMap.put(rating, 1.0);
                }
            }
        }
        for (String key : ratingsHashMap.keySet()) {
            if (!ratingsPercent.containsKey(key)) {
                ratingsPercent.put(key, (ratingsHashMap.get(key) / movieCounter) * 100);
            }
        }
        for (String key : ratingsPercent.keySet()) {

            sb.append(key + ": " + String.format("%.2f%%", ratingsPercent.get(key)) + "\n\n");

        }
        return sb.toString();
    }

    // Method calculate the average duration of all movies
    public String movieDuration(){
        StringBuilder sb = new StringBuilder();
        double totalTime = 0;
        double numMovies = 0;
        for (int i = 0; i< medias.size();i++){

            if(medias.get(i).getType().equalsIgnoreCase("movie")){
                numMovies++;
                totalTime+= Integer.parseInt(medias.get(i).getDuration()[0]);
            }

        }
        sb.append(String.format("%.2f",(totalTime/numMovies)));
        return sb.toString();
    }


    // This method returns the most prolific director by using a hash map to keep track of their name and the frequency in which they pop up
    public String bestMovieDirector() {
        // Calculating most prolific director
        HashMap<String, Integer> directors = new HashMap<>();
        int maxDirector = Integer.MIN_VALUE;
        for (int i = 1; i < medias.size(); i++) {
            String[] director = medias.get(i).getDirector();
            for (int j = 0; j < director.length; j++) {

                if (medias.get(i).getType().equalsIgnoreCase("movie")) {
                    if (directors.containsKey(director[j])) {
                        directors.put(director[j], directors.get(director[j]) + 1);
                    } else {
                        directors.put(director[j], 1);
                    }
                }
                for (String key : directors.keySet()) {
                    if (directors.get(key) > maxDirector) {
                        bestMovieDirector = key;
                    }
                }
            }
        }
        return bestMovieDirector;
    }

    // This method returns the most popular genre by using a hash map to keep track of the genre and the frequency in which they pop up
    public String bestMovieGenre() {
        HashMap<String, Integer> genres = new HashMap<>();
        int maxGenre = Integer.MIN_VALUE;
        for (int i = 1; i < medias.size(); i++) {

            String[] genre = medias.get(i).getListed_in();

            if (medias.get(i).getType().equalsIgnoreCase("movie")) {

                for (int h = 0; h < genre.length; h++) {
                    if (genres.containsKey(genre[h])) {
                        genres.put(genre[h], genres.get(genre[h]) + 1);
                    } else {
                        genres.put(genre[h], 1);
                    }
                }
                for (String key : genres.keySet()) {
                    if (genres.get(key) > maxGenre)
                        bestMovieGenre = key;
                }
            }
        }
        return bestMovieGenre;

    }

    // This method returns the most prolific actor by using a hash map to keep track of their name and the frequency in which they pop up
    public String bestMovieActor() {
        HashMap<String, Integer> casts = new HashMap<>();
        int maxActor = Integer.MIN_VALUE;
        for (int i = 1; i < medias.size(); i++) {

            String[] actors = medias.get(i).getCast();

            if (medias.get(i).getType().equalsIgnoreCase("movie")) {

                for (int h = 0; h < actors.length; h++) {
                    if (casts.containsKey(actors[h])) {
                        casts.put(actors[h], casts.get(actors[h]) + 1);
                    } else {
                        casts.put(actors[h], 1);
                    }
                }
                for (String key : casts.keySet()) {
                    if (casts.get(key) > maxActor)
                        bestMovieActor = key;
                }
            }
        }
        return bestMovieActor;

    }


    // Search Movie Function. Takes in a genre and duration and compares it with all movies to return the genre and anything less than or equal to the duration
    public String searchMovie(String genre, int duration) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < medias.size(); i++) {
                if (medias.get(i).getType().equalsIgnoreCase("movie")) {
                    String[] genres = medias.get(i).getListed_in();
                    String durations[] = medias.get(i).getDuration();


                    for (int j = 0; j < genres.length; j++) {

                        if (genres[j].equalsIgnoreCase(genre) && Integer.parseInt(durations[0]) <= duration && !genre.equalsIgnoreCase("")) {
                            sb.append("\n");
                            sb.append((medias.get(0).getTitle() + ": ") + (medias.get(i).getTitle() + ", "));
                            sb.append("Genre(s): ");
                            for (int h = 0; h < genres.length; h++) {
                                sb.append(genres[h] + ", ");

                            }
                            sb.append("Duration: " + durations[0] + " min");
                        }
                        
                    }
                }
        }

        return sb.toString();
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////TV MAKER///////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Same methods but just for TV Shows
    public String tvPercentage() {
        double tvPercent = (tvCounter / (movieCounter + 0.0 + tvCounter)) * 100;
        return String.format("%.2f%%", tvPercent);
    }

    // TV rating distribution
    public String tvRatingMaker() {
        HashMap<String, Double> ratingsPercent = new HashMap<>();
        HashMap<String, Double> ratingsHashMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        // Calculating percentages of ratings

        for (int i = 0; i < medias.size(); i++) {
            if (medias.get(i).getType().equalsIgnoreCase("tv show")) {
                String rating = medias.get(i).getRating();

                if (ratingsHashMap.containsKey(rating)) {
                    ratingsHashMap.put(rating, ratingsHashMap.get(rating) + 1.0);
                } else {
                    ratingsHashMap.put(rating, 1.0);
                }
            }
        }
        for (String key : ratingsHashMap.keySet()) {
            if (!ratingsPercent.containsKey(key)) {
                ratingsPercent.put(key, (ratingsHashMap.get(key) / tvCounter) * 100);
            }
        }
        for (String key : ratingsPercent.keySet()) {

            sb.append(key + ": " + String.format("%.2f%%", ratingsPercent.get(key)) + "\n\n");

        }
        return sb.toString();
    }
    // Average season length
    public String tvSeasons(){
        StringBuilder sb = new StringBuilder();
        double totalSeasons = 0;
        double numShows = 0;
        for (int i = 0; i< medias.size();i++){
            
            if(medias.get(i).getType().equalsIgnoreCase("tv Show")){
                numShows++;
                totalSeasons+= Integer.parseInt(medias.get(i).getDuration()[0]);
            }
            
        }
        sb.append(String.format("%.2f",(totalSeasons/numShows)));
        return sb.toString();
    }


    // Most prolific director
    public String bestTVDirector() {
        // Calculating most prolific director
        HashMap<String, Integer> directors = new HashMap<>();
        int maxDirector = Integer.MIN_VALUE;
        for (int i = 1; i < medias.size(); i++) {
            String[] director = medias.get(i).getDirector();
            for (int j = 0; j < director.length; j++) {

                if (medias.get(i).getType().equalsIgnoreCase("tv show")) {
                    if (directors.containsKey(director[j])) {
                        directors.put(director[j], directors.get(director[j]) + 1);
                    } else {
                        directors.put(director[j], 1);
                    }
                }
                for (String key : directors.keySet()) {
                    if (directors.get(key) > maxDirector) {
                        bestTVDirector = key;
                    }
                }
            }
        }
        return bestTVDirector;

    }
    // Method to calculate the most popular genre
    public String bestTVGenre() {
        HashMap<String, Integer> genres = new HashMap<>();
        int maxGenre = Integer.MIN_VALUE;
        for (int i = 1; i < medias.size(); i++) {

            String[] genre = medias.get(i).getListed_in();

            if (medias.get(i).getType().equalsIgnoreCase("tv show")) {

                for (int h = 0; h < genre.length; h++) {
                    if (genres.containsKey(genre[h])) {
                        genres.put(genre[h], genres.get(genre[h]) + 1);
                    } else {
                        genres.put(genre[h], 1);
                    }
                }
                for (String key : genres.keySet()) {
                    if (genres.get(key) > maxGenre)
                        bestTVGenre = key;
                }
            }
        }
        return bestTVGenre;

    }
    // Most prolific actor
    public String bestTVActor() {
        HashMap<String, Integer> casts = new HashMap<>();
        int maxActor = Integer.MIN_VALUE;
        for (int i = 1; i < medias.size(); i++) {

            String[] actors = medias.get(i).getCast();

            if (medias.get(i).getType().equalsIgnoreCase("tv show")) {

                for (int h = 0; h < actors.length; h++) {
                    if (casts.containsKey(actors[h])) {
                        casts.put(actors[h], casts.get(actors[h]) + 1);
                    } else {
                        casts.put(actors[h], 1);
                    }
                }
                for (String key : casts.keySet()) {
                    if (casts.get(key) > maxActor)
                        bestTvActor = key;
                }
            }
        }
        return bestTvActor;

    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////MAIN SEARCH////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
     This method takes in the media type, title, director, and genre. It then searches the entire dataset to match any of those. 
     The user can search all or none of those options so any box can be filled to search. It returns a built string to put on the GUI.
     */
    public String totalMovieSearch(String type, String title, String director, String genre) {
        StringBuilder sb = new StringBuilder();
        if (type.equalsIgnoreCase("movie")) {
            ArrayList<Show> movies = new ArrayList<>();
            for(int i = 1; i < medias.size();i++){
                if (medias.get(i).getType().equalsIgnoreCase("movie")) {
                    movies.add(medias.get(i));
                }
            }
            
            for (int i = 0; i < medias.size(); i++) {
                String[] directors = medias.get(i).getDirector();
                String[] genres = medias.get(i).getListed_in();
                boolean hasDirector = false;
                boolean hasGenre = false;
                

                    if (!medias.get(i).getTitle().equalsIgnoreCase(title) && !title.equalsIgnoreCase("")) {
                        movies.remove(medias.get(i));
                    }
                    for (int j = 0; j < directors.length; j++) {

                        if (directors[j].equalsIgnoreCase(director)|| director.equalsIgnoreCase("")) {
                            hasDirector = true;
                            break;
                        }
                    }
                    if(hasDirector == false ){
                        movies.remove(medias.get(i));
                    }
                    for (int j = 0; j < genres.length; j++) {

                        if (genres[j].equalsIgnoreCase(genre)||genre.equalsIgnoreCase("")) {
                            hasGenre = true;
                            break;
                        }
                    }
                    if(hasGenre == false){
                        movies.remove(medias.get(i));
                    }
                
                
            }
            for(int j = 0; j< movies.size();j++){
                sb.append("Title: " + movies.get(j).getTitle() + "    Director(s): ");
                for (int h = 0; h < movies.get(j).getDirector().length; h++) {
                    sb.append(movies.get(j).getDirector()[h]+", ");
                }
                sb.append("     Genre(s): ");
                for (int h = 0; h < movies.get(j).getListed_in().length; h++) {
                    sb.append(movies.get(j).getListed_in()[h]+", ");
                }
                sb.append("\n");
            }
        }
        else{
            if (type.equalsIgnoreCase("tv show")) {
                ArrayList<Show> tvShows = new ArrayList<>();
                for(int i = 1; i < medias.size();i++){
                    if (medias.get(i).getType().equalsIgnoreCase("tv show")) {
                        tvShows.add(medias.get(i));
                    }
                }

                for (int i = 0; i < medias.size(); i++) {
                    String[] directors = medias.get(i).getDirector();
                    String[] genres = medias.get(i).getListed_in();
                    boolean hasDirector = false;
                    boolean hasGenre = false;


                    if (!medias.get(i).getTitle().equalsIgnoreCase(title) && !title.equalsIgnoreCase("")) {
                        tvShows.remove(medias.get(i));
                    }
                    for (int j = 0; j < directors.length; j++) {

                        if (directors[j].equalsIgnoreCase(director)|| director.equalsIgnoreCase("")) {
                            hasDirector = true;
                            break;
                        }
                    }
                    if(hasDirector == false ){
                        tvShows.remove(medias.get(i));
                    }
                    for (int j = 0; j < genres.length; j++) {

                        if (genres[j].equalsIgnoreCase(genre)||genre.equalsIgnoreCase("")) {
                            hasGenre = true;
                            break;
                        }
                    }
                    if(hasGenre == false){
                        tvShows.remove(medias.get(i));
                    }


                }
                for(int j = 0; j< tvShows.size();j++){
                    sb.append("Title: " + tvShows.get(j).getTitle() + "    Director(s): ");
                    for (int h = 0; h < tvShows.get(j).getDirector().length; h++) {
                        sb.append(tvShows.get(j).getDirector()[h]+", ");
                    }
                    sb.append("     Genre(s): ");
                    for (int h = 0; h < tvShows.get(j).getListed_in().length; h++) {
                        sb.append(tvShows.get(j).getListed_in()[h]+", ");
                    }
                    sb.append("\n");
                }
            }
        }
        
        return sb.toString();
    }
}