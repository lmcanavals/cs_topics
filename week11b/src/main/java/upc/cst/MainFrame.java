package upc.cst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public static AmbientPanel ambientPanel;
	protected HostAgent hostAgent;

	public MainFrame(HostAgent hostAgent) {
		this.hostAgent = hostAgent;
		try {
			initialize();
		} catch (Exception e) {
			System.err.println("Oh no! " + e);
			e.printStackTrace();
		}
	}

	public void initialize() {
		final GraphicsConfiguration config = getGraphicsConfiguration();
		final int left = Toolkit.getDefaultToolkit().getScreenInsets(config).left;
		final int right = Toolkit.getDefaultToolkit().getScreenInsets(config).right;
		final int top = Toolkit.getDefaultToolkit().getScreenInsets(config).top;
		final int bottom = Toolkit.getDefaultToolkit().getScreenInsets(config).bottom;
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int width = screenSize.width - left - right;
		final int height = screenSize.height - top - bottom;

		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		setBackground(Color.BLUE);

		ambientPanel = new AmbientPanel();
		ambientPanel.setBackground(Color.BLUE);

		add(ambientPanel, BorderLayout.CENTER);
	}
}
