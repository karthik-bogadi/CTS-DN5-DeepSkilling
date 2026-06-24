package dip;

public class MySql implements DataBase{
    @Override
    public void saveData() {
        System.out.println("Saved in MySql database");
    }
}
