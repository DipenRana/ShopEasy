package Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;


public class Connection {
    public int connectionID;
    private Socket socket;
    private int status;
    public Connection(Socket socket){
        this.status=0;
        this.socket=socket;
    }
    public void setTimeout(){
    	try {
			socket.setSoTimeout(5000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
    public int getStatus() {
        return status;
    }
}

