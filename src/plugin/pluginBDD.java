package plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class pluginBDD {

	private Connection conn;
	
	
	public pluginBDD(){
		conn = connect();
	}
	
	
	public Connection connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3307/buntan";
			String user = "root";
			String passwd = "esgi";
			
			Connection conn = DriverManager.getConnection(url, user, passwd);
			return conn;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}
	
	public ArrayList selectPdf(int idUser){
		try {
			ArrayList media = new ArrayList();
			Statement state = (Statement) conn.createStatement();
			ResultSet result = state.executeQuery("SELECT DISTINCT file_road, note FROM media,pdf,appartien where media.id_media = pdf.idmediafk and media.id_media = appartien.id_media_fk and appartien.id_user_fk ="+idUser+"");
			ResultSetMetaData resultMeta = (ResultSetMetaData) result.getMetaData();
				         
			while(result.next()){ 
					String[] obj = new String[]{result.getObject(1).toString(), result.getObject(2).toString()};
					media.add(obj);        
				
			}

			result.close();
			state.close();
			return media;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void insertPdf(String name, String fileRoad, int id_user){
		try{
			int id = 0;
			StringTokenizer st = new StringTokenizer(fileRoad, "\\");
			fileRoad = "?";
			while(st.hasMoreTokens()){
				fileRoad += st.nextToken()+"\\\\";
				
			}
			fileRoad += "\\";
			fileRoad = fileRoad.substring(0, fileRoad.length()-1);

			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT id_media from media where Name like '"+name+"'");
			if(!result.next()){
				state.executeUpdate("INSERT INTO `media`(`Name`,`file_road`)VALUES('"+name+"','"+fileRoad+"')");				
				result = state.executeQuery("SELECT id_media from media where Name like '"+name+"'");
				result.next();
				id = Integer.parseInt(result.getObject(1).toString());
				state.executeUpdate("INSERT INTO `pdf`(`idmediafk`)VALUES("+id+")");
				state.executeUpdate("INSERT INTO `appartien`(`id_media_fk`, `id_user_fk`)VALUES("+id+", "+id_user+")");

				result.close();
				state.close();
			}
			else{
				result = state.executeQuery("SELECT id_media from media where Name like '"+name+"'");
				result.next();
				id = Integer.parseInt(result.getObject(1).toString());
				state.executeUpdate("INSERT INTO `appartien`(`id_media_fk`, `id_user_fk`)VALUES("+id+", "+id_user+")");
				result.close();
				state.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}
}
