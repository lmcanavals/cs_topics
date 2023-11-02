import sys
from pade.acl.aid import AID
from pade.behaviours.protocols import TimedBehaviour
from pade.misc.utility import start_loop
from fishagent import FishAgent
from pade.core.agent import Agent

from globals import Global
from gui import Gui

class MyTimedBehaviour(TimedBehaviour):
    def __init__(self, agent, time):
        super(MyTimedBehaviour, self).__init__(agent, time)
        self.agent = agent

    def on_time(self):
        super(MyTimedBehaviour, self).on_time()
        # display_message(self.agent.aid.localname, 'Updating the fishies!')
        for fish in self.agent.fish_list:
            fish.updateStatus()
            fish.swim()

        self.agent.gui.update()

class HostAgent(Agent):
    gui = None
    num_fishes = 5
    fish_list = []
    enabled = False

    def __init__(self, aid):
        super(HostAgent, 
              self).__init__(aid=aid, debug=False)

        Global.x_center = 0

        for _ in range(self.num_fishes):
            self.fish_list.append(FishAgent())
            # TODO name it and launch it as an agent of chaos

        self.gui = Gui(self)
        self.gui.show()

        mytimed = MyTimedBehaviour(self, .2)
        self.behaviours.append(mytimed)


if __name__ == '__main__':
    agents = list()
    port = int(sys.argv[1])
    host_agent_name = 'host_agent_{}@localhost:{}'.format(port, port)
    host_agent = HostAgent(AID(name=host_agent_name))
    agents.append(host_agent)

    start_loop(agents)
