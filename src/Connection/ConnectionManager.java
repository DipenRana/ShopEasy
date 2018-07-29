package Connection;

import java.io.IOException;
import java.net.Socket;


public class ConnectionManager {
	

	public static Connection connect() {
		try {
			return new Connection(new Socket("192.168.0.1", 9180));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void disconnect() {
	}
}
