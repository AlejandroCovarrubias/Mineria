/**
 * FrameMain.java
 * 
 * Creado el 20/04/2020 a las 7:16PM
 */
package ui;

import conexion.ClienteGPS;
import conexion.ClienteSemaforos;
import interfazMapa.IMapa;
import interfazMapa.Mapa;
import java.net.URI;
import javax.websocket.Session;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Frame principal donde se despliega el mapa, se puede cambiar el estado 
 * de los semáforos y manda las ubicaciones de los vehículos al sistema de 
 * notificaciones.
 * 
 * @author Equipo Mineria.
 */
public class FrameMain extends javax.swing.JFrame {
    
    // interfaz mapa
    private IMapa mapa;
    private PanelMapa pnl;
    
    // GPS
    private ClienteGPS gps;
    private Session sesionGPS = null;
    private String rutaGPS = "wss://localhost:8443/GPS/gps";
    
    // Semaforos
    private ClienteSemaforos semaforos;
    private Session sesionSemaforos = null;
    private String rutaSemaforos = "wss://localhost:8443/ControladorSemaforos/controladorSemaforos";
    


    /**
     * Contrusctor que crea el Frame Principal.
     */
    public FrameMain() {
        initComponents();
        this.setSize(1080, 720);
        this.setLocationRelativeTo(null);
        this.pnl = new PanelMapa();
        
        pnl.setSize(this.pnlMapa.getSize());
        pnl.setBorder(this.pnlMapa.getBorder());
        this.pnlMapa.add(pnl);
        this.pnlMapa.setOpaque(false);
        pnl.setOpaque(false);
        pnl.revalidate();
        this.pnlMapa.revalidate();
        
        // interfaz mapa
        mapa = new Mapa();
        
        conectar();
        
    }
    
    /**
     * Conecta al servicio GPS.
     */
    private void conectar(){
        System.out.println("Conectando a servicio GPS...");
        int intentos = 0;
        int intentosMax = 5;
        
        // GPS
        
        gps = new ClienteGPS(this);
        sesionGPS = null;
        
        while(intentos<intentosMax){
            try{
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
                
                
                sesionGPS = cm.connectToServer(gps, new URI(rutaGPS));
                break;
            }catch(Exception e){
                intentos++;
                System.out.println("Error conectando al gps, intentando "+(intentosMax-intentos)+" veces mas");
            }
        }
        if(intentos==intentosMax || sesionGPS == null){
            System.out.println("No se pudo conectar...");
            System.exit(1);
        }
        System.out.println("conectado a gps!");
        
        // Semaforos
        
        intentos = 0;
        semaforos = new ClienteSemaforos();
        sesionSemaforos = null;
        
        while(intentos<intentosMax){
            try{
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
                
                sesionSemaforos = cm.connectToServer(semaforos, new URI(rutaSemaforos));
                break;
            }catch(Exception e){
                intentos++;
                System.out.println("Error conectando al controlador semaforos, intentando "+(intentosMax-intentos)+" veces mas");
            }
        }
        if(intentos==intentosMax || sesionGPS == null){
            System.out.println("No se pudo conectar...");
            System.exit(1);
        }
        System.out.println("conectado a semaforos!");
        
    }
    
    /**
     * Procesa las ubicaciones.
     */
    public void procesarUbicaciones(){
        // Actualiza el mapa
        mapa.actualizarSemaforos(gps.getSemaforos());
        mapa.actualziarVehiculos(gps.getVehiculos());
        pnl.setComponentes(mapa.obtenerComponente());
        pnl.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlMapa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnlMapa.setBackground(new java.awt.Color(255, 255, 255));
        pnlMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlMapa.setMaximumSize(new java.awt.Dimension(900, 550));
        pnlMapa.setMinimumSize(new java.awt.Dimension(900, 550));
        pnlMapa.setPreferredSize(new java.awt.Dimension(900, 550));

        javax.swing.GroupLayout pnlMapaLayout = new javax.swing.GroupLayout(pnlMapa);
        pnlMapa.setLayout(pnlMapaLayout);
        pnlMapaLayout.setHorizontalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 898, Short.MAX_VALUE)
        );
        pnlMapaLayout.setVerticalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Lato", 1, 48)); // NOI18N
        jLabel1.setText("MINERIA");

        jButton1.setText("mandar algo al semaforos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(157, 157, 157)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        semaforos.cambiarEstado("S1,ROJO", sesionSemaforos);
        semaforos.cambiarEstado("S2,ROJO", sesionSemaforos);
        semaforos.cambiarEstado("S3,ROJO", sesionSemaforos);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlMapa;
    // End of variables declaration//GEN-END:variables
}
