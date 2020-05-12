/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazMapa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import objetos.ComponenteMapa;
import objetos.Semaforo;
import objetos.SemaforoGrafico;
import objetos.Vehiculo;
import objetos.VehiculoGrafico;

/**
 *
 * @author Home
 */
public class Mapa implements IMapa {

    private List<SemaforoGrafico> semaforos;
    private List<VehiculoGrafico> vehiculos;
    private List<ComponenteMapa> componentes;
    BufferedImage img;

    public Mapa() {
        semaforos = new ArrayList();
        vehiculos = new ArrayList();
    }

    @Override
    public void actualizarComponentes() {
        ArrayList<ComponenteMapa> nueva = new ArrayList<>();
        for (VehiculoGrafico veh : vehiculos) {
            nueva.add(veh.dibujar());
        }
        for (SemaforoGrafico sem : semaforos) {
            nueva.add(sem.dibujar());
        }
        componentes = nueva;
    }

    @Override
    public void actualizarSemaforos(List<Semaforo> semaforos) {
        ArrayList<SemaforoGrafico> nueva = new ArrayList<>();
        for (Semaforo sem : semaforos) {
            nueva.add(new SemaforoGrafico(sem));
        }
        this.semaforos = nueva;
    }

    @Override
    public void actualziarVehiculos(List<Vehiculo> vehiculos) {
        ArrayList<VehiculoGrafico> nueva = new ArrayList<>();
        for (Vehiculo veh : vehiculos) {
            nueva.add(new VehiculoGrafico(veh));
        }
        this.vehiculos = nueva;
    }

    @Override
    public void dibujarMapa(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Imagen fondo
        try {
            img = ImageIO.read(new File("src/img/mapa.png"));
            g2.drawImage(img, 0, 0, null);
        } catch (IOException e) {
            System.out.println("error al leer el mapa: " + e.getMessage());
        }
        // Itera sobre componentes
        if (componentes != null) {
            for (ComponenteMapa com : componentes) {
                // Trata de dibujar la imagen, si falla, cuadritos
                try {
                    // No funciona con switch :c
                    if (com.getColor() == Color.BLACK) {
                        // Carro
                        img = ImageIO.read(new File("src/img/vehiculo.png"));
                    } else if (com.getColor() == Color.RED) {
                        // Rojo
                        img = ImageIO.read(new File("src/img/semaforo_Rojo.png"));
                    } else if (com.getColor() == Color.YELLOW) {
                        // Amarillo
                        img = ImageIO.read(new File("src/img/semaforo_Amarillo.png"));
                    } else {
                        // Verde
                        img = ImageIO.read(new File("src/img/semaforo_Verde.png"));
                    }
                    g2.drawImage(img, (int) com.getDibujo().getX(), (int) com.getDibujo().getY(), null);
                } catch (IOException e) {
                    System.out.println("No se pudo leer la imagen: " + e.getMessage());
                    g2.setColor(com.getColor());
                    g2.draw(com.getDibujo());
                    g2.fill(com.getDibujo());
                }
                // Vehiculos
                if (com.getColor() == Color.BLACK) {
                    g2.setColor(Color.WHITE);
                    g2.fillRect((int) com.getDibujo().getCenterX() - 10, (int) com.getDibujo().getCenterY() - 10, 30, 10);
                    g2.setColor(Color.BLACK);
                    g2.drawString(com.getId(), (float) com.getDibujo().getCenterX(), (float) com.getDibujo().getCenterY());
                } // Semaforos
                else {
                    g2.setColor(Color.BLACK);
                    g2.fillRect((int) com.getDibujo().getX(), (int) com.getDibujo().getY() - 10, 30, 10);
                    g2.setColor(Color.WHITE);
                    g2.drawString(com.getId(), (float) com.getDibujo().getX() + 10, (float) com.getDibujo().getY());
                }

            }
        }
    }
}