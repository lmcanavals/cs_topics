package cstopics;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author WillyUgarte
 */
public class AvionAgent extends Agent {
    
    @Override
    public void setup() {
        //System.out.println("Avion presente " + getLocalName());
        try {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            DFService.register(this, dfd);
            //Comportamientos
            ParallelBehaviour parallel = new ParallelBehaviour();
            //Comportamiento movimiento
            parallel.addSubBehaviour(new TickerBehaviour(this, 1000) {
                @Override
                protected void onTick() {
                    System.out.println("(" + getLocalName() + ")");
                }
            });
            //Comportamiento para mensajes
            parallel.addSubBehaviour(new TickerBehaviour(this, 5000) {
                @Override
                protected void onTick() {
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.setContent("volando");
                    msg.addReceiver(new AID("Torre", AID.ISLOCALNAME));
                    send(msg);
                }
            });
            //permiso para aterrizar
            parallel.addSubBehaviour(new TickerBehaviour(this, 10000) {
                @Override
                protected void onTick() {
                    ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                    msg.setContent("aterrizar");
                    msg.addReceiver(new AID("Torre", AID.ISLOCALNAME));
                    send(msg);
                }
            });
            //recibir mensajes
            parallel.addSubBehaviour(new CyclicBehaviour(this) {
                @Override
                public void action() {
                    ACLMessage msg = myAgent.receive();
                    if (msg != null) {
                        switch (msg.getContent()) {
                            case "recibido": 
                                System.out.println("->" + getLocalName() + " confirmación de la Torre");
                                break;
                            case "aceptar":
                                TorreAgent.pista_ocupada = true;
                                myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                                    @Override
                                    public void action() {
                                        System.err.println("=>\t" + getLocalName() + " (inicio aterrizaje)");
                                        block(2000);
                                        System.err.println("=>\t" + getLocalName() + " (fin aterrizaje)");
                                        doDelete();
                                        TorreAgent.pista_ocupada = false;
                                    }
                                });
                            case "rechazo":
                                break;
                                
                        }
                    }
                }
            });
            addBehaviour(parallel);
        }
        catch(Exception e){ e.printStackTrace(); }
    }
}
