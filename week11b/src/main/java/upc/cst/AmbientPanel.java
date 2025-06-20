package upc.cst;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class AmbientPanel extends JPanel {
	public static int MAX_X = 1280, MAX_Y = 720, BORDE = 20;

	public AmbientPanel() {
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
			for (FishAgent fish : HostAgent.fishes) {
				g.setColor(fish.color);
				int length = 10 + 4 * fish.size / 5;
				int width = 5 + fish.size / 5;
				switch (fish.status) {
					case 1:
						g.fillOval(fish.x, fish.y, length, width);
						g.setColor(Color.WHITE);
						g.fillOval(fish.x + length / 4, fish.y + width / 4,
								length / 2, width / 2);
						g.setColor(fish.color);
						break;
					case 2:
						g.drawOval(fish.x, fish.y, length, width);
						break;
					case 3:
						g.drawOval(fish.x, fish.y, length, width);
						break;
					case 4:
						g.setColor(Color.WHITE);
						g.fillOval(fish.x, fish.y, length, width);
						g.setColor(fish.color);
						g.fillOval(fish.x + length / 4, fish.y + width / 4,
								length / 2, width / 2);
						break;

				}
			}
		}
	}
}
