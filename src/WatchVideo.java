import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class WatchVideo {
    public static void main(String[] args) {
        new WatchVideo().run(args[0], args[1]);
    }

    public void run(String username, String membership) {
        String selected;
        String prompt = "";
        List<Video> videoList = buildVideoList();

        User user = new User(username, membership, videoList);

        prompt = prompt.concat("\nHi "+ user.name +". Here is our video library:\n");
        for (Video video : videoList) {
            prompt = prompt.concat("- " + video.getTitle() + " : " + video.getType()+"\n");
        }
        prompt = prompt.concat("Write <<Log out>> to log out.\n");
        prompt = prompt.concat("Write the title: ");

        Scanner scanner = new Scanner(System.in);

        System.out.print(prompt);
        while (!((selected = scanner.nextLine()).equals("Log out"))) {
            try {
                user.watchVideo(selected);
            } catch (Exception e) {
                System.out.println("Access denied for "+user.name+". \n(Your membership is "+user.membership+". You must upgrade or change your membership.)");
            }
            System.out.print(prompt);
        }

        scanner.close();
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