package ebay.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cart {
	public String cart(String user_id){
		try {
			Connection conn=null;
			System.out.println("Trying to fetch cart");
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cart JOIN user_id ON "
					+ "cart.user_id=user_id JOIN product ON cart.product_id=product_id WHERE cart.user_id='"+Integer.parseInt(user_id)+"';");
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
	        System.out.println("cart fetched");
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
		System.out.println("cart fetch fail");
		return "error"; 
	}
	
	public String addToCart(String user_id, String product_id){
		try {
			Connection conn=null;
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ebay?useSSL=false","root","7290");
			System.out.println("Adding item to cart");
				Statement st1 = conn.createStatement();
				ResultSet rs1 = st1.executeQuery("INSERT INTO CART (user_id, product_id) VALUES "
					+ "('"+user_id+"', '"+product_id+"');");
				JSONArray jsonArray = new JSONArray();
		        while (rs1.next()) {
		            int total_rows = rs1.getMetaData().getColumnCount();
		            JSONObject obj = new JSONObject();
		            for (int i = 0; i < total_rows; i++) {
		                obj.put(rs1.getMetaData().getColumnLabel(i + 1)
		                        .toLowerCase(), rs1.getObject(i + 1));
		                
		            }
		            jsonArray.put(obj);
		        }
		        System.out.println("item added");
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
	
	
	public String removeFromCart(String user_id, String product_id){
		try {
			Connection conn=null;
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			System.out.println("Removing item from cart");
				Statement st1 = conn.createStatement();
				ResultSet rs1 = st1.executeQuery("delete from cart where item_product_id = "+product_id+" and item_user_id = "+user_id+" LIMIT 1");
				JSONArray jsonArray = new JSONArray();
		        while (rs1.next()) {
		            int total_rows = rs1.getMetaData().getColumnCount();
		            JSONObject obj = new JSONObject();
		            for (int i = 0; i < total_rows; i++) {
		                obj.put(rs1.getMetaData().getColumnLabel(i + 1)
		                        .toLowerCase(), rs1.getObject(i + 1));
		                
		            }
		            jsonArray.put(obj);
		        }
		        System.out.println("item removed");
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
