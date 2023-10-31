from fishagent import FishAgent

class HostAgent:
    gui = None
    num_fishes = 5
    fish_list = []
    enabled = False
    x_center = 0

    def setup(self):
        pass

    def initialize(self):
        for _ in range(self.num_fishes):
            self.fish_list.append(FishAgent())
            # TODO name it and launch it as an agent of chaos
