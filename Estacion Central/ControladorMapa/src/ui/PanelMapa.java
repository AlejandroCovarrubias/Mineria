/**
 * PanelMapa.java
 *
 * Creado el 20/04/2020 a las 07:21PM
 */
package ui;

import interfazMapa.IMapa;
import java.awt.Graphics;
import java.util.List;
import objetos.ComponenteMapa;

/**
 * Panel en la interfaz gráfica que muestra de manera gráfica el mapa de la mina
 * con los vehículos y semáforos.
 *
 * @author Equipo Mineria.
 */
public class PanelMapa extends javax.swing.JPanel {

    private IMapa mapa;

    /**
     * Constructor que inicializa el panel
     */
    public PanelMapa() {
        initComponents();
        this.setSize(900, 550);
    }

    public void setMapa(IMapa mapa) {
        this.mapa = mapa;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (mapa != null) {
            mapa.dibujarMapa(g);
        }
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
