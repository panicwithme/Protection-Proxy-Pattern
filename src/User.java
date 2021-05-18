import java.util.List;

public class User {
    
    public String name;
    public String membership;
    public List<Video> videoList;

    public User(String name, String membership, List<Video> videoList) {
        this.name = name;
        this.membership = membership;
        this.videoList = videoList;
    }

    public void watchVideo(String videoName) {

        for (Video video : this.videoList) {
            String videoTitle = video.getTitle();

            if (videoTitle.equals(videoName)) {
                String videoType = video.getType();

                switch(videoType) {
                    case "Series":
                        video.watchSeries(this.membership);
                        break;
                    case "Movie":
                        video.watchMovie(this.membership);
                        break;
                    case "Sports":
                        video.watchSports(this.membership);
                        break;
                }

            }
        }

    }

}
