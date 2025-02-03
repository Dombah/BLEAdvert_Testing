package config;
public class TestConfig {

    public static class TestingSetup {
        public static final String platformName = "Android"; // Don't change
        public static final String platformVersion = "11"; // Change depending on the emulator version you use. For API 30, the version is 11
        public static final String automationName = "UiAutomator2"; // Don't change
        public static final String deviceName = "Pixel_7_API_30"; // Change depending on the name of the emulator
        public static final String app = "C:\\Users\\Domagoj\\AndroidStudioProjects\\BLEAdvert\\app\\build\\intermediates\\apk\\debug\\app-debug.apk"; // Change to place the project app-debug.apk is located
        public static final String url = "http://127.0.0.1:4723"; // Don't change
    }
    // for firebase to work properly while testing import the adminSdk.json
    // the json should be provided by the project owner if you have the rights
    // the file should be located inside src/main/resources/*filename.json*
    public static class FirebaseSetup{
        public static final String adminSdk = "src/main/resources/adminSdk.json";
    }
}

