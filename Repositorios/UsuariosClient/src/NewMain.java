
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Galindo
 */
public class NewMain {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "https://localhost:8443/UsuariosAPI/usuariosAPI/usuarios/login?correo=alejandrogacova@gmail.com&contrasenia=123456";
        HttpURLConnection urlConn = null;
        BufferedReader reader = null;
        
        try {
            URL urlObj = new URL(url);
            urlConn = (HttpURLConnection) urlObj.openConnection();
            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setConnectTimeout(10000);
            urlConn.setReadTimeout(10000);
            urlConn.setRequestProperty("Accept", "application/json");

            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("Unable to connect to the URL...");
                return;
            }
            
            System.out.println("Connected to the server...");
            InputStream is = urlConn.getInputStream();
            reader = new BufferedReader(new InputStreamReader((is)));
            String tmpStr = null;
            while((tmpStr = reader.readLine()) != null){
                System.out.println(tmpStr);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) reader.close();
                if(urlConn != null) urlConn.disconnect();
            } catch(Exception ex){
                 
            }
        }
    }
}
