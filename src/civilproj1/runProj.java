/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civilproj1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;
import org.json.*;


/**
 *
 * @author Administrator
 */
public class runProj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
       MainFrame f = new MainFrame();
    }
    
    public static Vector readSurvey(){
        URL url;
        InputStream is = null;
        BufferedReader reader;
        String line;
        StringBuilder sb = new StringBuilder();     
        String[] coloumnText = {"Q1. Please select the stakeholder group* that you belong to from one of the followings:",
            "Q2. What is your gender?",
            "Q3. What is your age?"};
        Vector retVec = new Vector();
        retVec.add(coloumnText);
        try {
                        
            url = new URL("https://spreadsheets.google.com/feeds/list/1hIrKgV1bFwuZojaD0V-xb11Dd-zZ4M8PNKWLyHVZCcg/1/public/values?alt=json");
            is = url.openStream();  // throws an IOException
            reader = new BufferedReader((new InputStreamReader(is)));
            while ((line = reader.readLine()) != null) {
		sb.append(line);
            }
            String s = sb.toString();
            JSONObject obj = new JSONObject(s);
            JSONArray content = obj.getJSONObject("feed").getJSONArray("entry");
            JSONObject row;
            for(int i = 0;i<content.length();i++){
                Vector v = new Vector();
                row = content.getJSONObject(i);
                Iterator x = row.keys();
              /*  while (x.hasNext()){
                    String key = (String) x.next();
                    if(!key.contains("gsx"))
                        continue;
                    System.out.println(row.getJSONObject(key));
                    System.out.println(key);
                    v.addElement(row.getJSONObject(key).getString("$t"));
                }*/
                
                for(int j = 0;j<coloumnText.length;j++){
                    String queryStr = "gsx$"+coloumnText[j].toLowerCase();
                    queryStr = queryStr.replaceAll("\\s", "");
                    queryStr = queryStr.replaceAll("\\:", "");
                    queryStr = queryStr.replaceAll("\\*", "");
                    queryStr = queryStr.replaceAll("\\?", "");
                    v.addElement(row.getJSONObject(queryStr).getString("$t"));
                }
                retVec.addElement(v);
            }
            

        } catch (MalformedURLException mue) {
             mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return retVec;
    }
    
}
