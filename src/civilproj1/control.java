/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civilproj1;
import static civilproj1.runProj.readSurvey;
import java.net.URI;
import java.awt.Desktop;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.Vector;
/**
 *
 * @author Administrator
 */
public class control {
    static void openSurveyPage(){
        if(Desktop.isDesktopSupported())
        {
            try {
                Desktop.getDesktop().browse(new URI("http://google.com/"));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
    
    static Vector prepareTableContent(){
        Vector table = readSurvey();
        return table;
    }
}
