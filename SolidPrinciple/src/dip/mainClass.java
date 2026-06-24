package dip;

public class mainClass {

    DataBase db;
    mainClass(DataBase dataBase) {
        this.db = dataBase;
    }
    public void saveUser(String user){
        db.saveData();
    }
    public static void main(String[] args){
        mainClass obj=new mainClass(new MySql());
        obj.saveUser("karthik");
    }
}
