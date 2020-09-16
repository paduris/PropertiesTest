import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public final class PropertiesUtil {


    private final Properties configProp = new Properties();

    private PropertiesUtil() {


        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LazyPropertyHolder {
        private static final PropertiesUtil INSTANCE = new PropertiesUtil();
    }

    public static PropertiesUtil getInstance() {
        return LazyPropertyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }


}
