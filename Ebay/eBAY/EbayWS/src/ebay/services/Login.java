package ebay.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login {
	public String checklogin(String username) throws ClassNotFoundException{
		try {
			Connection conn=null;
			//System.out.println("Trying to login the user: " + username);
			System.out.println("Get sell info");
			System.out.println("success");
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE user_email='"+username+"';");
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
	        conn.close();
	        //System.out.println("User found in DB");
	        return jsonArray.toString();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; 
	}
	
	public int register(String user_firstName, String user_lastName, String user_email,
			String  user_password,
			String  user_address,
			String  user_city,
			String user_state,
			String user_zip,
			String user_phone,
			String user_dob,
			String user_handle,
			String user_balance,
			String user_spent,
			String user_earned) {
		
		System.out.println("Trying to register user..");
		try {
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Registered new user succesfully");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			int rs = st.executeUpdate("INSERT into user (user_firstName, user_lastName,user_password, user_address, user_city, user_state, user_zip, user_phone, user_dob, user_handle, user_balance, user_spent, user_earned   ) VALUES ('"+user_firstName+"','"+user_lastName+"','"+user_password+"','"+user_address+"','"+user_city+"',"
					+ "														'"+user_state+"','"+user_zip+"','"+user_phone+"','"+user_dob+"','"+user_handle+"','"+user_balance+"','"+user_spent+"','"+user_earned+"' );");
			if(rs == 1){
				System.out.println("Registered new user succesfully");
				conn.close();
				return 1;
			}else{
				System.out.println("Failed to register new user");
				conn.close();
				return 0;
			}
		}  catch (SQLException e) {
			// TODO: Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getHome(){
		try {
			Connection conn=null;
			 System.out.println("Trying to fectch all products user..");
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root","sandeep");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM product");
			JSONArray jsonArray = new JSONArray();
			int total_rows = rs.getMetaData().getColumnCount();
	        while (rs.next()) {
	            JSONObject obj = new JSONObject();
	            for (int i = 0; i < total_rows; i++) {
	                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
	            }
	            jsonArray.put(obj);
	        }	       
	        conn.close();
			System.out.println("Products fetched succesfully");
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
