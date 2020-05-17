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
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.websocket.Session;
import objetos.Semaforo;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Frame principal donde se despliega el mapa, se puede cambiar el estado de los
 * semáforos y manda las ubicaciones de los vehículos al sistema de
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
        this.setSize(1280, 720);
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
    private void conectar() {
        System.out.println("Conectando a servicio GPS...");
        int intentos = 0;
        int intentosMax = 5;

        // GPS
        gps = new ClienteGPS(this);
        sesionGPS = null;

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

                SSLEngineConfigurator sslEngineConfigurator
                        = new SSLEngineConfigurator(defaultConfig, true, false, false);
                
                cm.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR,
                        sslEngineConfigurator);

                sesionGPS = cm.connectToServer(gps, new URI(rutaGPS));
                break;
            } catch (Exception e) {
                intentos++;
                System.out.println("Error conectando al gps, intentando " + (intentosMax - intentos) + " veces mas");
            }
        }
        if (intentos == intentosMax || sesionGPS == null) {
            System.out.println("No se pudo conectar...");
            System.exit(1);
        }
        System.out.println("Conectado a gps!");
        

        // Semaforos
        intentos = 0;
        semaforos = new ClienteSemaforos();
        sesionSemaforos = null;

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

                SSLEngineConfigurator sslEngineConfigurator
                        = new SSLEngineConfigurator(defaultConfig, true, false, false);
                cm.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR,
                        sslEngineConfigurator);

                sesionSemaforos = cm.connectToServer(semaforos, new URI(rutaSemaforos));
                break;
            } catch (Exception e) {
                intentos++;
                System.out.println("Error conectando al controlador semaforos, intentando " + (intentosMax - intentos) + " veces mas");
            }
        }
        if (intentos == intentosMax || sesionGPS == null) {
            System.out.println("No se pudo conectar...");
            System.exit(1);
        }
        System.out.println("Conectado a Controlador Semaforos!");
    }

    /**
     * Procesa las ubicaciones.
     */
    public void procesarUbicaciones() {
        // Actualiza el mapa
        mapa.actualizarSemaforos(gps.getSemaforos());
        mapa.actualziarVehiculos(gps.getVehiculos());
        mapa.actualizarComponentes();
        pnl.setMapa(mapa);
        pnl.repaint();
    }
    
    public void actualizarComboSemaforos(){
        List<Semaforo> semfs = gps.getSemaforos();
        DefaultComboBoxModel dml = new DefaultComboBoxModel();
        for (Semaforo semf : semfs) {
            dml.addElement(semf.getIdentificador());
        }
        comboSemaforos.setModel(dml);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboSemaforos = new javax.swing.JComboBox<>();
        btnEstadoVerde = new javax.swing.JButton();
        btnEstadoAmarillo = new javax.swing.JButton();
        btnEstadoRojo = new javax.swing.JButton();
        btnEmergencia = new javax.swing.JButton();

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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Lato", 1, 48)); // NOI18N
        jLabel1.setText("MINERIA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Lato", 1, 14)); // NOI18N
        jLabel2.setText("Panel de Control");

        jLabel3.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        jLabel3.setText("Semaforos:");

        comboSemaforos.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N

        btnEstadoVerde.setText("VERDE");
        btnEstadoVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoVerdeActionPerformed(evt);
            }
        });

        btnEstadoAmarillo.setText("AMARILLO");
        btnEstadoAmarillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoAmarilloActionPerformed(evt);
            }
        });

        btnEstadoRojo.setText("ROJO");
        btnEstadoRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoRojoActionPerformed(evt);
            }
        });

        btnEmergencia.setText("BOTÓN DE EMERGENCIA");
        btnEmergencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmergenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEstadoAmarillo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstadoRojo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboSemaforos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstadoVerde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnEmergencia, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(comboSemaforos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEstadoVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstadoAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstadoRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
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

    private void btnEstadoVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoVerdeActionPerformed
        String semaforoSeleccionado = (String) comboSemaforos.getSelectedItem();
        cambiarEstadoSemaforo(semaforoSeleccionado, "VERDE");
    }//GEN-LAST:event_btnEstadoVerdeActionPerformed

    private void btnEstadoAmarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoAmarilloActionPerformed
        String semaforoSeleccionado = (String) comboSemaforos.getSelectedItem();
        cambiarEstadoSemaforo(semaforoSeleccionado, "AMARILLO");
    }//GEN-LAST:event_btnEstadoAmarilloActionPerformed

    private void btnEstadoRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoRojoActionPerformed
        String semaforoSeleccionado = (String) comboSemaforos.getSelectedItem();
        cambiarEstadoSemaforo(semaforoSeleccionado, "ROJO");
    }//GEN-LAST:event_btnEstadoRojoActionPerformed

    private void btnEmergenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmergenciaActionPerformed
        List<Semaforo> semfs = gps.getSemaforos();
        for (Semaforo semf : semfs) {
            cambiarEstadoSemaforo(semf.getIdentificador(), "ROJO");
        }
    }//GEN-LAST:event_btnEmergenciaActionPerformed

    private void cambiarEstadoSemaforo(String identificador, String estado){
        String idfinal = identificador + "," + estado;
        semaforos.cambiarEstado(idfinal, sesionSemaforos);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmergencia;
    private javax.swing.JButton btnEstadoAmarillo;
    private javax.swing.JButton btnEstadoRojo;
    private javax.swing.JButton btnEstadoVerde;
    private javax.swing.JComboBox<String> comboSemaforos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlMapa;
    // End of variables declaration//GEN-END:variables
}