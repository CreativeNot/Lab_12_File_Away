import javax.swing.*;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = " ";

        ArrayList<String> lines = new ArrayList<>();

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.OPEN_DIALOG);
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while (reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                    line++;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                for (String l:lines)
                {
                    System.out.println(l);
                    String fields[] = l.split(", ");
                    for(String f:fields)
                        System.out.println("\t" + f);
                }
                reader.close();
                System.out.println("\n\nData file read!");
            }
            
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
