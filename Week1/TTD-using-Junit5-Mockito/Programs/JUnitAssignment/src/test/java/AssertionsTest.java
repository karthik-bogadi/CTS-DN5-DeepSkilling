public class AssertionsTest {
    public static void main(String[] args) {
        if (5 == 2 + 3) {
            System.out.println("assertEquals passed");
        }

        if (5 > 3) {
            System.out.println("assertTrue passed");
        }

        if (!(5 < 3)) {
            System.out.println("assertFalse passed");
        }

        Object value = null;
        if (value == null) {
            System.out.println("assertNull passed");
        }

        Object obj = new Object();
        if (obj != null) {
            System.out.println("assertNotNull passed");
        }

        System.out.println("All assertions passed successfully");
    }
}