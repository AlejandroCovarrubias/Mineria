/**
 * PanelMapa.java
 * 
 * Creado el 20/04/2020 a las 07:21PM
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import objetos.ComponenteMapa;
import java.awt.geom.Rectangle2D;

/**
 * Panel en la interfaz gráfica que muestra de manera gráfica el mapa de la 
 * mina con los vehículos y semáforos.
 * 
 * @author Equipo Mineria.
 */
public class PanelMapa extends javax.swing.JPanel {

    private List<ComponenteMapa> componentes;
    
    /**
     * Constructor que inicializa el panel
     */
    public PanelMapa() {
        initComponents();
        this.setSize(900, 550);
    }

    public void setComponentes(List<ComponenteMapa> componentes) {
        this.componentes = componentes;
    }
    
    
    @Override
            protected void paintComponent(Graphics g) {
                super.paintComponents(g);
                Graphics2D g2 = (Graphics2D)g;
                
                // Imagen fondo
                
                // Itera sobre componentes
                if(componentes !=null){
                    for(ComponenteMapa com : componentes){
                        g2.setColor(com.getColor());
                        g2.draw(com.getDibujo());
                        g2.fill(com.getDibujo());
                        g2.setColor(Color.WHITE);
                        g2.drawString(com.getId(), (float)com.getDibujo().getCenterX(), (float)com.getDibujo().getCenterY());
                    }
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
