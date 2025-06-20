from PySide6.QtCore import Qt
from PySide6.QtGui import QColor, QPaintEvent, QPainter
from PySide6.QtWidgets import QFrame


class Gui(QFrame):
    def __init__(self, agent) -> None:
        super(Gui, self).__init__()
        self.agent = agent
        self.setFocusPolicy(Qt.FocusPolicy.StrongFocus)

    def paintEvent(self, _: QPaintEvent) -> None:
        painter = QPainter(self)
        for fish in self.agent.fish_list:
            #    print(fish.x, fish.y)
            painter.fillRect(fish.x, fish.y, fish.size, fish.size//2, fish.color)
