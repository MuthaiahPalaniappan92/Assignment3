/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c0652674
 */
public class DB_work {
    String url="jdbc:as400:174.79.32.158";
    String userName="IBM82";
    String password="IBM82";
    
    
    public Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("sdfsdf "+e.getMessage());
        }
        try {
            con=DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println("fsdf "+e.getMessage());
        }
        return con;
    }
    
    public void createProductTable(){
        try {
            Connection con=getConnection();
            Statement stmt=con.createStatement();
            String query="IF (( SELECT COUNT(*)  FROM SYSCAT.TABLES  WHERE  TABSCHEMA='IBM82' AND TABNAME='PRODUCT'  )  = 0  )  BEGIN CREATE  TABLE TEST_SCHEMA.TEST_TABLE  (  productid INT , name VARCHAR(150),description VARCHAR(150),quantity INT  ) END ;";
            stmt.executeQuery(query);
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertProduct(Product product){
        try {
            Connection con=getConnection();
            String query="INSERT INTO product VALUES(?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getQuantity());
            stmt.executeUpdate();
            System.out.println("INSERT INTO product VALUES(?,?,?,?)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Product selectById(int id){
        Product p=new Product();
        try {
            Connection con=getConnection();
            String query="SELECT * FROM product WHERE productid=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("productid"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }
    
    public Product selectByName(String name){
        Product p=new Product();
        try {
            Connection con=getConnection();
            String query="SELECT * FROM product WHERE name=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("productid"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }
    
    public List<Product> selectAllProducts(){
        
        List<Product> productList=new ArrayList<Product>();
        try {
            Connection con=getConnection();
            String query="SELECT * FROM product";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                Product p=new Product();
                p.setId(rs.getInt("productid"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
                productList.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }
    
    public void deleteById(int id){
        Product p=new Product();
        try {
            Connection con=getConnection();
            String query="DELETE FROM product WHERE productid=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void updateById(int id){
        
        try {
            Product p=new Product();
            Connection con=getConnection();
            String query="UPDATE product SET (productid,name,description,quantity)=(?,?,?,?)  WHERE productid=?";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
            stmt.setInt(4, p.getQuantity());
            stmt.executeUpdate();
           } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}

    
           