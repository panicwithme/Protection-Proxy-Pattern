import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler{
    
    private Video video;

    public InvocationHandlerImpl(Video v) {
        video = v;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {

            String methodName = method.getName();

            // Only Movie and Premium Members can watch movies
            if (methodName.equals("watchMovie") && (args[0].equals("Basic") || args[0].equals("Sports"))) {
                throw new IllegalAccessException();
            }
            // Only Sports and Premium members can watch sports 
            else if (methodName.equals("watchSports") && (args[0].equals("Basic") || args[0].equals("Movie"))) {
                throw new IllegalAccessException();
            } 
            else {
                return method.invoke(video, args);
            }

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
