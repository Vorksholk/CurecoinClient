import java.io.*;
import java.net.*;

public class OutputThread extends Thread
{
    private Socket socket;
    private String toWrite;
    public OutputThread(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true)
            {
                if (toWrite != null)
                {
                    if (!toWrite.equals(""))
                    {
                        out.println(toWrite);
                        toWrite = null;
                    }
                }
                Thread.sleep(100);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void write(String toWrite)
    {
        this.toWrite = toWrite;
    }
}
