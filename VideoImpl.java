public class VideoImpl implements Video {
    
    public String title;
    public String type;
    
    public VideoImpl(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public void watchSeries(String membership) {
        System.out.println("Video is loading...");
    }

    public void watchMovie(String membership) {
        System.out.println("Video is loading...");
    }

    public void watchSports(String membership) {
        System.out.println("Video is loading...");
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }


}
