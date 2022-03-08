package emeka.powermock;

public class UtilityClass {
    static int staticMethod(long value) {
        // Some complex logic is done here...
        throw new RuntimeException(
                "I don't want to be executed. I will anyway be mocked out.");
    }
}
