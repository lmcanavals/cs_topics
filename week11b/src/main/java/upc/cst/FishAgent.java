package upc.cst;

import java.awt.Color;
import jade.core.Agent;

public class FishAgent extends Agent {
	public int x;
	public int y;
	public int status;
	public Color color;
	public int size;
	public int speed;

	public FishAgent() {
		x = (int) (100 * Math.random());
		y = (int) (500 * Math.random());
		color = new Color((float) (Math.random() * 0.1),
				(float) (Math.random() * 0.5 + 0.25),
				(float) (Math.random() * 0.1));
		size = (int) (25 * Math.random() + 5);
		speed = 10 * 25 / size;
	}

	public void defineStatus() {
		if (y < 500) {
			status = 1;
			if (x > 100 + HostAgent.shoal_center) {
				status = 4;
			}
		} else if (y > 700) {
			status = 2;
			if (x < 30 + HostAgent.shoal_center) {
				status = 3;
			}
		} else {
			if (x < 30 + HostAgent.shoal_center) {
				status = 3;
			} else {
				status = 4;
			}
		}
	}

	public void swim() {
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
