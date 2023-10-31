from hostagent import HostAgent

host = HostAgent()

class Gui:
    def __init__(self) -> None:
        pass

    def update(self):
        for fish in HostAgent.fish_list:
            print(fish)
