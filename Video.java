public interface Video {

    String getTitle();
    String getType();

    void watchSeries(String membership);
    void watchMovie(String membership);
    void watchSports(String membership);
    
}
