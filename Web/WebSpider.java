import java.util.Scanner;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class WebSpider
{    
    private String address;
    private URL pageLocation; 
    private Scanner in;
    public static void main(String[] args) throws MalformedURLException, IOException
    {
        String address = "http://linustechtips.com/main/";
        URL pageLocation = new URL(address);
        Scanner in = new Scanner(pageLocation.openStream());
        String input = in.nextLine();
        WriteToFile(input);
    }
    
    public static void WriteToFile(String input)throws FileNotFoundException
    {
        String i = input;
        PrintWriter webContent = new PrintWriter("Web.txt");
        webContent.println(i);
    }
}
