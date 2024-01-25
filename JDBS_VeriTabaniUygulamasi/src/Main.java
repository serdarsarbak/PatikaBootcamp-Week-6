import java.sql.*;

public class Main {
    /* Proje:
    JDBC kullanarak bir MySQL veritabanına bağlanan ve belirli bir tablodan veri çeken ve çekilen veriyi ekrana yazdıran
    bir Java uygulaması yazınız. Veritabanında işlem yaparak "employees" adında bir tablo ve her bir çalışanın "id",
    "name", "position" ve "salary" olmak üzere 4 sütun oluşturun. Manuel olarak veri tabanındaki employees tablosuna en
    az 5 tane employee kaydedin.
    */

    public static class DbConnect {
        //işlemleri company isminde oluşturduğum bir database üzerinden yapmaktadır:
        public static final String DB_URL = "jdbc:mysql://localhost/company";
        public static final String DB_USERNAME = "root";
        public static final String DB_PASSWORD = "mysql";
    }

    public static void main(String[] args) {
        Connection connect = null;

        // tablonun oluşturulması:
        String createTable = "CREATE TABLE IF NOT EXISTS Employees (\n" +
                "    ID int NOT NULL,\n" +
                "    Name varchar(255) NOT NULL,\n" +
                "    Position varchar(255) NOT NULL,\n" +
                "    Salary int,\n" +
                "    PRIMARY KEY (ID)\n" +
                ");";

        // verilerin eklenmesi:
        String updateTable = "INSERT INTO Employees (ID, Name, Position, Salary) VALUES (1, 'Brett Gerhartz', 'Development Analyst', 81980), (2, 'Stefan Godsmark', 'Account Executive', 21600), (3, 'Nichol Chalk', 'Senior Editor', 88565), (4, 'Romola Elloit', 'Media Manager', 90207)";

        // verilerin okunması:
        String readTable = "SELECT * FROM Employees";

        try {
            connect = DriverManager.getConnection(DbConnect.DB_URL, DbConnect.DB_USERNAME, DbConnect.DB_PASSWORD);
            //transtion metodu:
            connect.setAutoCommit(false);

            Statement st = connect.createStatement();
            st.executeUpdate(createTable);
            st.executeUpdate(updateTable);
            connect.commit();

            //şirket çalışanların yazdırılması:
            ResultSet data = st.executeQuery(readTable);
            System.out.printf("--------------------------------%n");
            System.out.printf(" ŞİRKET ÇALIŞANLARI         %n");
            System.out.printf("--------------------------------%n");
            System.out.printf("| %-5s | %-18s | %-20s | %-8s |%n", "ID", "NAME", "POSITION", "SALARY");
            System.out.printf("--------------------------------%n");

            while (data.next()) {
                System.out.printf("| %-5s | %-18s | %-20s | %-8s |%n", data.getInt("ID") , data.getString("Name"), data.getString("Position"), data.getInt("Salary"));
                System.out.printf("--------------------------------%n");
            }
            st.close();

        } catch (SQLException ex) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        }
    }
    }

