public class Show {

    // Declaring all object variables

    private String show_id, type, title, country, date_added,release_year, rating, description;
    private String[] listed_in;
    private String[] director;
    private String[] cast;
    private String[] duration;
    
    // Creating constructor as well as getters and setters for all class scoped variables

    public Show(String show_id, String type, String title, String director, String cast, String country, String date_added, String release_year, String rating, String duration, String listed_in, String description) {
        this.show_id = show_id;
        this.type = type;
        this.title = title;
        this.director = director.split(",");
        this.cast = cast.split(",");
        this.country = country;
        this.date_added = date_added;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration.split(" ");
        this.listed_in = listed_in.split(",");
        this.description = description;
    }

    public String getShow_id() {
        return show_id;
    }

    public void setShow_id(String show_id) {
        this.show_id = show_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDirector() {
        return director;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getDuration() {
        return duration;
    }

    public void setDuration(String[] duration) {
        this.duration = duration;
    }

    public String[] getListed_in() {
        return listed_in;
    }

    public void setListed_in(String[] listed_in) {
        this.listed_in = listed_in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}