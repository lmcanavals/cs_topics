package org.topics;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.awt.Color;

/**
 *
 * @author WillyUgarte
 */
public class FishAgent extends Agent {
  public int x;
  public int y;
  public int status; // 1:vigilar,2:comer,3:subir,4:bajar
  public Color color;
  public int size;
  public int speed;
  private int cont = 0;

  public FishAgent() {
    HostAgent.lista_peces.add(this);
  }

  @Override
  protected void setup() {
    super.setup();
    x = (int) (1300 * Math.random());
    y = (int) (500 * Math.random());
    color = new Color(
        (int) (Math.random() * 50) + 200,
        (int) (Math.random() * 100) + 50,
        0);
    size = (int) (25 * Math.random() + 5);
    speed = 10 * 25 / size;

    addBehaviour(new TickerBehaviour(this, (int) (Math.random() * 5000 + 5000)) {
      @Override
      protected void onTick() {
        if (Math.random() < 0.001) {
          System.out.printf("Hello I'm %s\n", getName());
        }
      }
    });
  }

  public void definirStatus() {
    if (y < 500) {
      status = 1;
      if (x > 100 + HostAgent.x_center)
        status = 4;
    } else if (y > 900) {
      status = 2;
      if (x < 30 + HostAgent.x_center)
        status = 3;
    } else {
      if (x < 30 + HostAgent.x_center)
        status = 3;
      else
        status = 4;
    }
  }

  public void nadar() {
    switch (status) {
      case 1:
        x += speed;
        break;
      case 2:
        x -= speed;
        break;
      case 3:
        y -= speed;
        break;
      case 4:
        y += speed;
        break;
    }
  }
}
