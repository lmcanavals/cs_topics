package upc.cst;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.wrapper.AgentController;
import jade.wrapper.PlatformController;

public class HostAgent extends Agent {
	public static MainFrame mainFrame;
	public static int numFishes = 26000;
	public static ArrayList<FishAgent> fishes = new ArrayList<>();
	public static boolean ENABLED;
	public static int shoal_center;

	@Override
	public void setup() {
		try {
			DFAgentDescription dfad = new DFAgentDescription();
			dfad.setName(getAID());

			mainFrame = new MainFrame(this);
			mainFrame.setVisible(true);

			initialize();

			addBehaviour(new CyclicBehaviour(this) {

				@Override
				public void action() {
					try {
						Thread.sleep(200);
					} catch (Exception _ex) {
					}
					MainFrame.ambientPanel.repaint();
				}
			});
			addBehaviour(new TickerBehaviour(this, 200) {

				@Override
				protected void onTick() {
					for (FishAgent fish : fishes) {
						fish.defineStatus();
						fish.swim();
					}
				}
			});
			addBehaviour(new TickerBehaviour(this, 1000) {

				@Override
				protected void onTick() {
					shoal_center += 5;
				}
			});
		} catch (Exception e) {
			System.err.println("oh no!! " + e);
			e.printStackTrace();
		}
	}

	void initialize() {
		PlatformController container = getContainerController();
		try {
			for (int i = 0; i < numFishes; ++i) {
				fishes.add(new FishAgent());
				String localname = "fish_" + i;
				AgentController ac = container.createNewAgent(localname, "upc.cst.FishAgent", null);
				ac.start();
			}
			MainFrame.ambientPanel.setEnabled(true);
			ENABLED = true;
		} catch (Exception e) {
			System.err.println("oh no!!! " + e);
			e.printStackTrace();
		}
	}
}
