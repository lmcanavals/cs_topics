package cstopics;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

/**
 * Hello world!
 *
 */
public class MyAgent extends Agent {
	protected void setup() {
		addBehaviour(new SimpleBehaviour(this) {

			public void action() {
				System.out.println("Doing the action!");
				steps++;
			}

			private int steps = 0;

			public boolean done() {
				return steps >= 10;
			}
		});
	}
}
