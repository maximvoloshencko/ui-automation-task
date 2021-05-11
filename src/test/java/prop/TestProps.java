package prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public final class TestProps {

    private final static String PROPS_FILE_PATH = "src/main/resources/config.properties";

    private final static int DEFAULT_PASSWORD_LENGTH = 16;

    private final static String PROPERTY_EMAIL = "credentials.email";
    private final static String PROPERTY_PASSWORD = "credentials.password";
    private final static String PROPERTY_URL = "application.url";

    public final static String TEST_EMAIL;
    public final static String TEST_PASSWORD;
    public final static String APPLICATION_URL;

    static {
        FileInputStream iStream = null;
        String credentialsEmail = "";
        String credentialsPassword = "";
        String applicatonUrl = "";

        try {
            iStream = new FileInputStream(new File(PROPS_FILE_PATH));
            Properties props = new Properties();
            props.load(iStream);

            credentialsEmail = props.getProperty(PROPERTY_EMAIL);
            credentialsPassword = props.getProperty(PROPERTY_PASSWORD);
            applicatonUrl = props.getProperty(PROPERTY_URL);

        } catch(IOException ex) {
            ex.printStackTrace();
        }finally {
            try {
                if(iStream != null) {
                    iStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        TEST_EMAIL = credentialsEmail;
        TEST_PASSWORD = credentialsPassword;
        APPLICATION_URL = applicatonUrl;
    }

    private TestProps() {}

    public static String generateDummyEmail() {
        return "dummy" + (UUID.randomUUID()) + "@test.ex";
    }

    public static String generateDummyPassword() {
        return generateDummyPassword(DEFAULT_PASSWORD_LENGTH);
    }

    public static String generateDummyPassword(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
}
