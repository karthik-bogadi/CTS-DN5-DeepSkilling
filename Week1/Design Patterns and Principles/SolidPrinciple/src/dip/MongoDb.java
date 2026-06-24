package dip;

public class MongoDb implements DataBase{
    @Override
    public void saveData() {
        System.out.println("Data saved in MongoDb");
    }
}
