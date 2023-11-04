from PySide6.QtCore import Qt
from PySide6.QtWidgets import QApplication, QFrame


class Gui(QFrame):
    def __init__(self, agent) -> None:
        super(Gui, self).__init__()
        self.agent = agent
        self.setFocusPolicy(Qt.FocusPolicy.StrongFocus)

    def update(self):
        for fish in self.agent.fish_list:
            print(fish.x, fish.y)
