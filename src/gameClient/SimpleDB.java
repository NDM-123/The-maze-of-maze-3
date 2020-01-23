package gameClient;
import java.sql.*;
import java.util.ArrayList;

import static gameClient.KML_Logger.KML_Save;

/**
 * This class represents a simple example of using MySQL Data-Base.
 * Use this example for writing solution. 
 * @author boaz.benmoshe
 *
 */
public class SimpleDB {
	public static final String jdbcUrl="jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	public static final String jdbcUser="student";
	public static final String jdbcUserPassword="OOP2020student";
	public static ArrayList<Integer> levelId = new ArrayList<>();
	public static ArrayList<Integer> id = new ArrayList<>();
	public static ArrayList<Integer> moves = new ArrayList<>();
	public static ArrayList<Integer> score = new ArrayList<>();
	public static ArrayList<Date> dateTime = new ArrayList<>();
	public static int PosLevel[] = new int[24];
	public static int[] maxScore = new int[24];
	public static int numberGames;
	/**
	 * Simple main for demonstrating the use of the Data-base
	 * @param args
	 */
	public static void main(String[] args) {
		int id1 = 206226706;
		int level = 0;

		//allUsers();
		printLog();

		
		//String kml = getKML(id1,level);
		//	System.out.println(kml);
	
	}
	/** simply prints all the games as played by the users (in the database).
	 * 
	 */
	public static void printLog() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			String allCustomersQuery = "SELECT * FROM Logs;";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			int[] minMove = new int[24];
			for (int i = 0; i < minMove.length; i++) {
				minMove[i]=1040;
			}
			minMove[0] = 290;
			minMove[1] = 580;
			minMove[3] = 580;
			minMove[5] = 500;
			minMove[9] = 580;
			minMove[11] = 580;
			minMove[13] = 580;
			minMove[16] = 290;
			minMove[19] = 580;
			minMove[20] = 290;
			minMove[23] = 1140;

			boolean[] level = new boolean[24];
			int i=0;
			numberGames=0;
			while(resultSet.next())
			{
				if(resultSet.getInt("UserID")==206226706&&minMove[resultSet.getInt("levelID")]>resultSet.getInt("moves")&&maxScore[resultSet.getInt("levelID")]<resultSet.getInt("score")||(resultSet.getInt("levelID")>=0&&resultSet.getInt("UserID")==206226706&&resultSet.getInt("levelID")<24&&level[resultSet.getInt("levelID")]==false)){
					id.add(resultSet.getInt("UserID"));
					levelId.add(resultSet.getInt("levelID"));
					level[resultSet.getInt("levelID")] = true;
					moves.add(resultSet.getInt("moves"));
					//minMove[resultSet.getInt("levelID")] = resultSet.getInt("moves");
					dateTime.add(resultSet.getDate("time"));
					score.add(resultSet.getInt("score"));
					maxScore[resultSet.getInt("levelID")] = resultSet.getInt("score");
				}

				if(resultSet.getInt("UserID")==206226706)numberGames++;

				if(resultSet.getInt("levelID")>-1&&maxScore[resultSet.getInt("levelID")]<resultSet.getInt("score") && resultSet.getInt("UserID")!=206226706){
					PosLevel[resultSet.getInt("levelID")]++;
			}
			}
			resultSet.close();
			statement.close();
			connection.close();
		}

		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this function returns the KML string as stored in the database (userID, level);
	 * @param id
	 * @param level
	 * @return
	 */
	public static String getKML(int id, int level) {
		String ans = null;
		String allCustomersQuery = "SELECT * FROM Users where userID="+id+";";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);		
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			if(resultSet!=null && resultSet.next()) {
				ans = resultSet.getString("kml_"+level);
			}
		}
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ans;
	}
	public static int allUsers() {
		int ans = 0;
		String allCustomersQuery = "SELECT * FROM Users;";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection =
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			while(resultSet.next()) {
				System.out.println("Id: " + resultSet.getInt("UserID"));
				ans++;
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ans;
	}
}


