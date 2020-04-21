/**
 * PanelMapa.java
 * 
 * Creado el 20/04/2020 a las 07:21PM
 */
package ui;

/**
 * Panel en la interfaz gráfica que muestra de manera gráfica el mapa de la 
 * mina con los vehículos y semáforos.
 * 
 * @author Equipo Mineria.
 */
public class PanelMapa extends javax.swing.JPanel {

    /**
     * Constructor que inicializa el panel
     */
    public PanelMapa() {
        initComponents();
        this.setSize(900, 550);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
