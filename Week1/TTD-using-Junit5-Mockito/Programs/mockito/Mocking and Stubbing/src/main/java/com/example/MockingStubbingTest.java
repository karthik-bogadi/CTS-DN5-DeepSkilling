public class MockingStubbingTest {

    interface ExternalApi {
        String getData();
    }

    static class MockExternalApi implements ExternalApi {
        public String getData() {
            return "Mock Data";
        }
    }

    static class MyService {
        private ExternalApi api;

        public MyService(ExternalApi api) {
            this.api = api;
        }

        public String fetchData() {
            return api.getData();
        }
    }

    public static void main(String[] args) {
        ExternalApi mockApi = new MockExternalApi();

        MyService service = new MyService(mockApi);

        String result = service.fetchData();

        if (result.equals("Mock Data")) {
            System.out.println("Test Passed");
            System.out.println("Fetched Data: " + result);
        } else {
            System.out.println("Test Failed");
        }
    }
}