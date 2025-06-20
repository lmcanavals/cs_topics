package org.topics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author WillyUgarte
 */
public class AmbientePanel extends JPanel {
  public static int MAX_X = 800, MAX_Y = 700, BORDE = 20;

  public AmbientePanel() {
    super();
    Dimension d = getSize();
    MAX_X = d.width;
    MAX_Y = d.height;
    setOpaque(false);
    setPreferredSize(new Dimension(MAX_X, MAX_Y));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.BLUE);
    g.setColor(Color.CYAN);
    Dimension d = getSize();
    g.fillRect(0, 0, d.width, d.height);
    if (HostAgent.ENABLED) {
      for (FishAgent pez : HostAgent.lista_peces) {
        g.setColor(pez.color);
        int largo = 10 + 4 * pez.size / 5;
        int ancho = 5 + pez.size / 5;
        switch (pez.status) {
          case 1:
            g.fillOval(pez.x, pez.y, largo, ancho);
            g.setColor(Color.WHITE);
            g.fillOval(pez.x + largo / 4, pez.y + ancho / 4, largo / 2, ancho / 2);
            g.setColor(pez.color);
            break;
          case 2:
            g.drawOval(pez.x, pez.y, largo, ancho);
            break;
          case 3:
            g.fillOval(pez.x, pez.y, largo, ancho);
            break;
          case 4:
            g.setColor(Color.WHITE);
            g.fillOval(pez.x, pez.y, largo, ancho);
            g.setColor(pez.color);
            g.drawOval(pez.x, pez.y, largo, ancho);
            break;
        }
      }
    }
  }
}
