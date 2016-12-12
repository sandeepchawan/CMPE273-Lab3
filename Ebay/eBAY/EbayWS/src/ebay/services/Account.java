package ebay.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Account {
	public String accountInfo(String user_id){
		try {
			Connection conn=null;
			System.out.println("Get user info");
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE user_id='"+Integer.parseInt(user_id)+"';");
			JSONArray jsonArray = new JSONArray();
	        while (rs.next()) {
	            int total_rows = rs.getMetaData().getColumnCount();
	            JSONObject obj = new JSONObject();
	            for (int i = 0; i < total_rows; i++) {
	                obj.put(rs.getMetaData().getColumnLabel(i + 1)
	                        .toLowerCase(), rs.getObject(i + 1));
	                
	            }
	            jsonArray.put(obj);
	        }
	        System.out.println("success");
	        conn.close();
	        System.out.println(jsonArray.toString());
	        return jsonArray.toString();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; 
	}
	
	public String purchaseHistory(String user_id){
		try {
			Connection conn=null;
			System.out.println("Get purchase History info");
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transactions, product, buyer WHERE buyer.buyer_id = transactions.transaction_shopper_id AND transactions.transaction_product_id = product.product_id AND buyer.user_id = user_id");
			JSONArray jsonArray = new JSONArray();
	        while (rs.next()) {
	            int total_rows = rs.getMetaData().getColumnCount();
	            JSONObject obj = new JSONObject();
	            for (int i = 0; i < total_rows; i++) {
	                obj.put(rs.getMetaData().getColumnLabel(i + 1)
	                        .toLowerCase(), rs.getObject(i + 1));
	                
	            }
	            jsonArray.put(obj);
	        }
	        System.out.println("success");
	        conn.close();
	        System.out.println(jsonArray.toString());
	        return jsonArray.toString();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; 
	}
	
	public String sellHistory(String user_id){
		try {
			Connection conn=null;
			System.out.println("Get sell History info");
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transactions, product, seller WHERE seller.seller_id = transactions.transaction_seller_id AND transactions.transaction_product_id = product.product_id " +
      "AND seller.user_id = " + user_id);
			JSONArray jsonArray = new JSONArray();
	        while (rs.next()) {
	            int total_rows = rs.getMetaData().getColumnCount();
	            JSONObject obj = new JSONObject();
	            for (int i = 0; i < total_rows; i++) {
	                obj.put(rs.getMetaData().getColumnLabel(i + 1)
	                        .toLowerCase(), rs.getObject(i + 1));
	                
	            }
	            jsonArray.put(obj);
	        }
	        System.out.println("success");
	        conn.close();
	        System.out.println(jsonArray.toString());
	        return jsonArray.toString();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; 
	}
	
	public String bidHistory(String user_id){
		try {
			Connection conn=null;
			System.out.println("Get bid info");
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from bidding b, buyer bu, user u, product p where b.bid_buyer_id=bu.buyer_id and bu.user_id=u.user_id and b.bid_product_id=p.product_id and u.user_id= "
            +user_id);
			JSONArray jsonArray = new JSONArray();
	        while (rs.next()) {
	            int total_rows = rs.getMetaData().getColumnCount();
	            JSONObject obj = new JSONObject();
	            for (int i = 0; i < total_rows; i++) {
	                obj.put(rs.getMetaData().getColumnLabel(i + 1)
	                        .toLowerCase(), rs.getObject(i + 1));
	                
	            }
	            jsonArray.put(obj);
	        }
	        System.out.println("success");
	        conn.close();
	        System.out.println(jsonArray.toString());
	        return jsonArray.toString();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; 
	}
}
