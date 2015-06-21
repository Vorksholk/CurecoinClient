import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;

public class CurecoinClient
{
    public static void main(String[] args)
    {
        launch();
        try 
        {
            Socket socket = new Socket("127.0.0.1", 8016);
            InputThread inputThread = new InputThread(socket);
            OutputThread outputThread = new OutputThread(socket);
            inputThread.start();
            outputThread.start();
            Scanner scan = new Scanner(System.in);
            while (true)
            {
                outputThread.write(scan.nextLine());
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void launch()
    {
        Console console = System.console(); //Get a system console object
        if (console != null) //If the application has a console
        {
            File f = new File("launch.bat");
            if (f.exists())
            {
                //f.delete(); //delete bat file if it exists
            }
        } 
        else if (!GraphicsEnvironment.isHeadless()) //Application doesn't have a console, let's give it one!
        {
            String os = System.getProperty("os.name").toLowerCase(); //Get OS
            if (os.contains("indows")) //If OS is a windows OS
            { 
                try
                {
                    File JarFile = new File(CurecoinClient.class.getProtectionDomain().getCodeSource().getLocation().toURI());//Get the absolute location of the .jar file
                    PrintWriter out = new PrintWriter(new File("launch.bat")); //Get a PrintWriter object to make a batch file
                    out.println("@echo off"); //turn echo off for batch file
                    out.println("title Curecoin 2.0.0a1 Client"); 
                    out.println("java -jar \"" + JarFile.getPath() + "\"");
                    out.println("start /b \"\" cmd /c del \"%~f0\"&exit /b");
                    out.close(); //saves file
                    Runtime rt = Runtime.getRuntime(); //gets runtime
                    rt.exec("cmd /c start launch.bat"); //executes batch file
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                System.exit(0); //Exit program, so only instance of program with command line runs!
            }
        }
    }
}