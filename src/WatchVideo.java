import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class WatchVideo {
    public static void main(String[] args) {
        new WatchVideo().run();
    }

    public void run() {
        
        User user = new User("Cigdem", "Sports", buildVideoList());

        try {
            user.watchVideo("Football Game");
        } catch (Exception e) {
            System.out.println("Access denied for "+user.name+". Your membership is "+user.membership+". You must upgrade or change your membership.");
        }
    }

    public List<Video> buildVideoList() {

        List<Video> videoList = new ArrayList<Video>();

        Video houseMD = new VideoImpl("House MD", "Series");
        InvocationHandler houseMDHandler = new InvocationHandlerImpl(houseMD);
        Video houseMDProxy = (Video) Proxy.newProxyInstance(Video.class.getClassLoader(), new Class[]{Video.class}, houseMDHandler);

        Video bladeRunner = new VideoImpl("Blade Runner", "Movie");
        InvocationHandler bladeRunnerHandler = new InvocationHandlerImpl(bladeRunner);
        Video bladeRunnerProxy = (Video) Proxy.newProxyInstance(Video.class.getClassLoader(), new Class[]{Video.class}, bladeRunnerHandler);

        Video footballGame = new VideoImpl("Football Game", "Sports");
        InvocationHandler footballGameHandler = new InvocationHandlerImpl(footballGame);
        Video footballGameProxy = (Video) Proxy.newProxyInstance(Video.class.getClassLoader(), new Class[]{Video.class}, footballGameHandler);
       
        videoList.add(houseMDProxy);
        videoList.add(bladeRunnerProxy);
        videoList.add(footballGameProxy);

        return videoList;
    }
}