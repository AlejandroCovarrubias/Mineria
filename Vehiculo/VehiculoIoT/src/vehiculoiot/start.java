/**
 * start.java
 * 
 * Creado el 20/04/2020 a las 09:26PM
 */
package vehiculoiot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexion.ClientGPSVehiculo;
import conexion.REST_Transporte;
import conexion.REST_UsuariosClient;
import java.net.URI;
import java.util.Random;
import java.util.Scanner;
import javax.websocket.Session;
import mensajeIoT.MensajeIoT;
import objetos.Material;
import objetos.Transporte;
import static org.glassfish.grizzly.http.CookiesBuilder.client;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Clase para probar el programa.
 * 
 * @author Equipo Mineria.
 */
public class start {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Conectando a servicio GPS...");
        int intentos = 0;
        int intentosMax = 5;

        //RUTA
        String ruta = "wss://localhost:8443/GPS/gps";

        ClientGPSVehiculo gps = new ClientGPSVehiculo();
        REST_Transporte rest = new REST_Transporte();
        REST_UsuariosClient auth = new REST_UsuariosClient();
        Session s = null;
        
        

        while (intentos < intentosMax) {
            try {
                ClientManager cm = ClientManager.createClient();
                
                System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "src/conexion/keystore.jks");
                System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "src/conexion/keystore.jks");
                System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "mineria");
                System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "mineria");
                final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();

                defaultConfig.retrieve(System.getProperties());
                    // or setup SSLContextConfigurator using its API.

                SSLEngineConfigurator sslEngineConfigurator =
                    new SSLEngineConfigurator(defaultConfig, true, false, false);
                cm.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR,
                    sslEngineConfigurator);
                
                s = cm.connectToServer(gps, new URI(ruta));
                break;
            } catch (Exception e) {
                e.printStackTrace();
                intentos++;
                System.out.println("Error conectando al gps, intentando " + (intentosMax - intentos) + " veces mas");
            }
        }

        if (intentos == intentosMax || s == null) {
            System.out.println("No se pudo conectar...");
            System.exit(1);
        }
        
        Scanner sc = new Scanner(System.in);

        // AUTENTIFICACION
        System.out.println("Autentificacion, favor de ingresar los siguientes datos...");
        int intentosAuth = 0;
        int intentosAuthMax = 5;
        while(intentosAuth<intentosAuthMax){
            System.out.print("Correo electronico: ");
            String correo = sc.nextLine();
            System.out.print("Contrasena: ");
            String pass = sc.nextLine();
            
            if(auth.autenticarUsuario(correo, pass)){
                break;
            }else{
                System.out.println("Error en autentificacion, intente denuevo.");
                intentosAuth++;
            }
            
        }
        
        if(intentosAuth==intentosAuthMax){
            System.out.println("Demasiados errores en autentificacion, terminando ejecucion...");
            System.exit(1);
        }
        
        // Manda el token al otro REST
        rest.setJWToken(auth.getJWToken());
        
        
        System.out.println("Datos del vehiculo...");

        String matricula;
        String nombre;
        String material; 
        double cantidad;
        String medida;

        int x;
        int y;


        System.out.print("Matricula: ");
        matricula = sc.nextLine();
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Material: ");
        material = sc.nextLine();

        System.out.print("Cantidad: ");
        String cantidadS = sc.nextLine();
        try {
            cantidad = Double.valueOf(cantidadS);
        } catch (NumberFormatException e) {
            System.out.println("numero no reconocido, se puso valor establecido de 100");
            cantidad = 100.0;
        }

        System.out.print("Medida: ");
        medida = sc.nextLine();

        System.out.print("Donde empiezo (X): ");
        cantidadS = sc.nextLine();
        try {
            x = Integer.valueOf(cantidadS);
        } catch (NumberFormatException e) {
            System.out.println("numero no reconocido, se puso valor establecido de 9000");
            x = 9000;
        }

        System.out.print("Donde empiezo (Y): ");
        cantidadS = sc.nextLine();
        try {
            y = Integer.valueOf(cantidadS);
        } catch (NumberFormatException e) {
            System.out.println("numero no reconocido, se puso valor establecido de 9000");
            y = 9000;
        }

        Vehiculo vehiculo = new Vehiculo(matricula, nombre, new Material(material, "N/A"), cantidad, medida, x, y);

        vehiculo.setSocket(gps);
        vehiculo.setRest(rest);

        System.out.println("Conduciendo...");

        int vueltas = 0;

        while (true) {
            System.out.println("Vroom vroom");
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            MensajeIoT msj = new MensajeIoT(vehiculo);

            String msg = gson.toJson(msj);

            vehiculo.getSocket().mandaPosicion(msg, s);

            // Mueve el vehiculo
            moverCarro(vehiculo);
            vueltas++;
            // Espera 5 segundos antes de mandarlo denuevo
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (vueltas == 2) {
                // Registra el transporte despues de varias vueltas

                // Transforma a vehiculo de objetos mina
                objetos.Vehiculo vehiculoNuevo = new objetos.Vehiculo(vehiculo.getMatricula(), vehiculo.getNombre(), "Carro mina", "Normal", vehiculo.getX(), vehiculo.getY());

                Transporte transporte = new Transporte(vehiculoNuevo, vehiculo.getNombre(), vehiculo.getMaterial(), vehiculo.getCantidad(), vehiculo.getMedida());

                vehiculo.getRest().registrarTransporte(transporte);
                vueltas = 0;
            }
        }

    }

    public static void moverCarro(Vehiculo v) {
        v.setX(random(v.getX() - 1000, v.getX() + 1000));
        v.setY(random(v.getY() - 1000, v.getY() + 1000));
    }

    public static int random(int min, int max) {
        Random r = new Random();
        int ran = r.nextInt(max - min) + min;

        // Minimos y maximos
        if (ran < 0) {
            ran = 0;
        }
        if (ran > 18500) {
            ran = 18500;
        }

        return ran;
    }
}