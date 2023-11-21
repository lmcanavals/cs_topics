from pade.misc.utility import display_message, start_loop
from pade.core.agent import Agent
from pade.acl.aid import AID
from pade.acl.messages import ACLMessage
from pade.behaviours.protocols import TimedBehaviour
from sys import argv


class SenderTimedBehaviour(TimedBehaviour):
    def __init__(self, agent, other, time):
        super(SenderTimedBehaviour, self).__init__(agent, time)
        self.other = other
        self.n = 0

    def on_time(self):
        super(SenderTimedBehaviour, self).on_time()
        message = ACLMessage(ACLMessage.INFORM)
        message.set_protocol(ACLMessage.FIPA_REQUEST_PROTOCOL)
        message.add_receiver(AID(self.other))
        self.n += 1
        message.set_content(f"{self.n} Ola Agente")
        self.agent.send(message)


class SenderAgent(Agent):
    def __init__(self, aid, receiver, delay):
        super(SenderAgent, self).__init__(aid=aid, debug=False)

        comp_temp = SenderTimedBehaviour(self, receiver, delay)

        self.behaviours.append(comp_temp)


class ReceiverAgent(Agent):
    def __init__(self, aid):
        super(ReceiverAgent, self).__init__(aid=aid, debug=False)

    def react(self, message):
        super(ReceiverAgent, self).react(message)
        display_message(self.aid.localname,
                        f"{message.sender.name}: {message.content}")


if __name__ == '__main__':
    agents_per_process = 2
    agents = list()
    port = int(argv[1])
    receiver_agent_name = 'receiver_agent_{}@localhost:{}'.format(port, port)
    receiver_agent = ReceiverAgent(AID(name=receiver_agent_name))
    agents.append(receiver_agent)
    c = 1000
    for i in range(agents_per_process):
        port = int(argv[1]) + c
        agent_name = 'sender_agent_{}@localhost:{}'.format(port, port)
        agente_hello = SenderAgent(
            AID(name=agent_name), receiver_agent_name, 2.0)
        agents.append(agente_hello)
        c += 1000

    start_loop(agents)
