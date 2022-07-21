package mysql;

import java.sql.*;

public class MySql {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://northwind.c34l1rcgahc1.us-east-1.rds.amazonaws.com:3306/northwind", "admin", "Passw0rd");

            String query = "SELECT A.ProductID, A.ProductName, C.SupplierID, C.CompanyName, B.CategoryID, B.CategoryName FROM Products A JOIN Categories B using(CategoryID)JOIN Suppliers C using(SupplierID)ORDER BY A.ProductID;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int idProd      = rs.getInt("ProductID");
                String nomeProd = rs.getString("ProductName");
                int idSupp      = rs.getInt("SupplierID");
                String nomeSupp = rs.getString("CompanyName");
                int idCat       = rs.getInt("CategoryID");
                String nomeCat  = rs.getString("CategoryName");

                System.out.format("%3s %35s %3s %40s %3s %15s\n", idProd, nomeProd, idSupp, nomeSupp, idCat, nomeCat);
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Erro! "+e.getMessage());
        }
    }
}
