package cstopics;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.wrapper.AgentController;
import jade.wrapper.PlatformController;

/**
 *
 * @author WillyUgarte
 */
public class HostAgent extends Agent {
    public static int numero_aviones = 10;
    
    @Override
    public void setup() {
        try {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            DFService.register(this, dfd);
            PlatformController container = getContainerController();
            //crear agente Torre
            String localname = "Torre";
            AgentController ac = container.createNewAgent(localname, "cstopics.TorreAgent", null);
            ac.start();
            //crear agentes avion
            for (int i = 0; i < numero_aviones; i++) {
                localname = "Avion_" + (i+1);
                ac = container.createNewAgent(localname, "cstopics.AvionAgent", null);
                ac.start();
            }
        }
        catch(Exception e){ e.printStackTrace(); }
    }
}
