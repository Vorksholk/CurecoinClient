import java.io.*;
import java.net.*;
public class InputThread extends Thread
{
    private Socket socket;
    public InputThread(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input;
            while ((input = in.readLine()) != null)
            {
                System.out.println(input);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
