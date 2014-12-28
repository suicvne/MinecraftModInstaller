import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONObject;


public class JSONEditor
{
	/*public static void main(String[] args)
	{
		InputStream is = null;
		try
		{
			is = new FileInputStream("C:\\Users\\Mike\\Desktop\\1.8.1.json");
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		} 
		JSONEditor(is, "id", "hemlo");
	}*/
	
	public static void JSONEditor(String fileToEdit, String keyToEdit, String valueToInsert) throws FileNotFoundException
	{
		Reader reader = new InputStreamReader(new FileInputStream(fileToEdit));
		String jsonFile = "";
		try
		{
			int data = reader.read();
			while(data != -1)
			{
				char ch = (char)data;
				jsonFile += ch;
				data = reader.read();
			}
			reader.close();
			//System.out.println(jsonFile);
		}
		catch(Exception ex)
		{}
		
		JSONObject obj = new JSONObject(jsonFile);
		obj.remove(keyToEdit);
		obj.put(keyToEdit, valueToInsert);
		try
		{
			Writer writer = new FileWriter(fileToEdit);
			obj.write(writer);
			writer.close();
		}
		catch(Exception ex)
		{}
		System.out.println("json file edited and saved");
	}
}
