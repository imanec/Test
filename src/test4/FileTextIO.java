/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test4;

/**
 *
 * @author Imane
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class FileTextIO {
	public static ArrayList<String> getFileContent(String fileName)
            throws IOException
    {
        ArrayList<String> result = new ArrayList<String>();

        File aFile = new File(fileName);

        if (!aFile.isFile())
        {
            //throw new IOException( fileName + " is not a regular File" );
            return result; // None
        }

        BufferedReader reader = null;

        try
        {
			reader = new BufferedReader(new FileReader(aFile));
        }
        catch (FileNotFoundException e1)
        {
            // TODO handle Exception
            e1.printStackTrace();

            return result;
        }

        String aLine = null;

        while ((aLine = reader.readLine()) != null)
        {
            result.add(aLine + "\n");
        }

        reader.close();

        return result;
    }
	public static String readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}
	public static String readFile(File f) throws IOException {
		FileInputStream stream = new FileInputStream(f);
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

	public static void writeFile(String path, String content) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			out.write(content);
                        out.write("\n");
			out.close();
		} catch (IOException e) {
		}

	}

}
