package cstopics;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author WillyUgarte
 */
public class TorreAgent extends Agent {
    public static boolean pista_ocupada = false;
    
    @Override
    public void setup() {
        //System.out.println("Torre presente");
        try {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            DFService.register(this,dfd);
            //comportamiento de movimiento
            addBehaviour(new CyclicBehaviour(this) {
                @Override
                public void action() {
                    ACLMessage msg = myAgent.receive();
                    if (msg != null) {
                        System.out.println(getLocalName() + ":reporte de " + msg.getSender().getLocalName());                        
                        ACLMessage reply = msg.createReply();
                        switch (msg.getContent()) {
                            case "volando":
                                reply.setPerformative(ACLMessage.INFORM);
                                reply.setContent("recibido");
                                break;
                            case "aterrizar":
                                if (pista_ocupada) {
                                    reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                                    reply.setContent("rechazo");
                                }
                                else {
                                    System.out.println(getLocalName() + ": permiso para " + msg.getSender().getLocalName());
                                    reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                                    reply.setContent("aceptar");
                                    pista_ocupada = true;
                                }   
                                break;
                            default:
                                reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                                reply.setContent("desconocido");
                                break;
                        }
                        myAgent.send(reply);
                    }
                }
            });
        }
        catch(Exception e){ e.printStackTrace(); }
    }
}
