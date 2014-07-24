package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.UserBean;

public class MainServer {

	public static final int TCP_PORT = 9000;

	/**
	 * @param args
	 */
	private static ArrayList<UserBean> users = new ArrayList<UserBean>();
	
	public static void main(String[] args) {
				
		try {
			@SuppressWarnings("resource")
			ServerSocket ss = new ServerSocket(TCP_PORT);
			System.out.println("Server running...");
			
			DBMS.getInstance().loadDBMS();
			System.out.println("Users count: " + DBMS.getInstance().getUsersDatabase().size());
			for (int i=0; i<DBMS.getInstance().getUsersDatabase().size(); i++) {
				System.out.println(DBMS.getInstance().getUsersDatabase().get(i).getUsername() + "/" +
						DBMS.getInstance().getUsersDatabase().get(i).getUsername() + " id: " + 
						DBMS.getInstance().getUsersDatabase().get(i).getId());
			}
			System.out.println("Next userID : " + DBMS.getInstance().getUserID() + "\n");
			
			if (DBMS.getInstance().getUsersDatabase().size() > 0) {
				System.out.println(
						DBMS.getInstance().getUsersDatabase().get(
								DBMS.getInstance().getUsersDatabase().size()-1).getRole() + 
							   " " + DBMS.getInstance().getUsersDatabase().get(
									   DBMS.getInstance().getUsersDatabase().size()-1).getUsername() + 
									   " " + DBMS.getInstance().getUsersDatabase().get(
											   DBMS.getInstance().getUsersDatabase().size()-1).getId()  + "\n");
			}
				
			
			while (true) {
				Socket sock = ss.accept();
				@SuppressWarnings("unused")
				ServerThread st = new ServerThread(sock, users);				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
