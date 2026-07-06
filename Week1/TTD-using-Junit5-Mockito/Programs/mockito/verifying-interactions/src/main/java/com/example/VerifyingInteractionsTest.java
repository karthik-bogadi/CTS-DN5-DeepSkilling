public class VerifyingInteractionsTest {

    interface ExternalApi {
        String getData();
    }

    static class MockExternalApi implements ExternalApi {
        private boolean getDataCalled = false;

        public String getData() {
            getDataCalled = true;
            return "Mock Data";
        }

        public boolean isGetDataCalled() {
            return getDataCalled;
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
        MockExternalApi mockApi = new MockExternalApi();

        MyService service = new MyService(mockApi);

        service.fetchData();

        if (mockApi.isGetDataCalled()) {
            System.out.println("Test Passed");
            System.out.println("Verified: getData() method was called.");
        } else {
            System.out.println("Test Failed");
        }
    }
}