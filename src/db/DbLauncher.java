package db;

import java.sql.SQLException;

import org.h2.tools.Server;

public class DbLauncher {
	
	public static void main(String[] args) throws SQLException {
		Server.main();
		
		System.out.println("DB launched  ");
	}

}
