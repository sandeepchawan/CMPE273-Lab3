package ebay.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Product {
	public String showProduct(String product_id){
		try {
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Trying to render product");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM product WHERE product_id='"+product_id+"';");
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
	        System.out.println("Render successful");
				conn.close();
				return jsonArray.toString();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	public int directSell(String product_name, String product_category_id, String product_price,String product_condition){
		try {
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Trying direct sell");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			
			int rs = st.executeUpdate("INSERT INTO product (product_name, product_category_id, product_price, product_condition) VALUES "
					+ "('"+product_name+"', '"+product_category_id+"', '"+product_price+"', '"+product_condition+"');");
			if(rs == 1){
				System.out.println("Direct sell successful");
				conn.close();
				return 1;
			}else{
				System.out.println("Direct sell fail");
				conn.close();
				return 0;
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int auctionSell(String product_name, String product_category_id, String product_price,String product_condition, String start_time, String end_time){
		try {
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Trying direct sell");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			
			int rs = st.executeUpdate("INSERT INTO product (product_name, product_category_id, product_price, product_condition, start_time, end_time) VALUES "
					+ "('"+product_name+"', '"+product_category_id+"', '"+product_price+"', '"+product_condition+"','"+start_time+"', '"+end_time+"');");
			if(rs == 1){
				System.out.println("Auction sell successful");
				conn.close();
				return 1;
			}else{
				System.out.println("Auction sell fail");
				conn.close();
				return 0;
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String searchProduct(String param, String category){
		try {
			Connection conn=null;
			System.out.println("Trying to search");
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM product WHERE product_name LIKE '%"+param+"%'");
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
	        System.out.println("Returning search results");
	        conn.close();
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
	
	public int bidProduct(String product_id, String user_id, String bid_price){
		try {
			Connection conn=null;
			System.out.println("Trying to place bid");
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			int rs = st.executeUpdate("UPDATE product SET product_price='"+Double.parseDouble(bid_price)+"' WHERE product_id='"+Integer.parseInt(product_id)+"';");
			if(rs == 1){
				Statement st1 = conn.createStatement();
				int rs1 = st1.executeUpdate("INSERT into bid(user_id, bid_amount,product_id) VALUES('"+Integer.parseInt(user_id)+"','"+Double.parseDouble(bid_price)+"','"+Integer.parseInt(product_id)+"');");
				if(rs1 == 1){
					System.out.println("Bid placed");
					conn.close();
					return 1;
				}else{
					System.out.println("Bid fail");
					conn.close();
					return 0;
				}
			}else{
				System.out.println("Bid fail");
				conn.close();
				return 0;
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
