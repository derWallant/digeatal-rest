package restService.digeatal.service.data.DB;

public class Main {
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess("restaurants");
        dao.readDataBase();
    }

}