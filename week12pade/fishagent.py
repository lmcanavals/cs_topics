import random

from globals import Global
from pade.core.agent import Agent

class FishAgent(Agent):
    def __init__(self) -> None:
        self.x = random.randint(1, 100)
        self.y = random.randint(1, 500)
        self.color = random.randint(0, 0xffffff)
        self.size = random.randint(5, 30)
        self.speed = 10 * 25 / self.size
        self.status = -1

    def updateStatus(self):
        if self.y < 500:
            self.status = 1
            if self.x > 100 + Global.x_center:
                self.status = 4;

        elif self.y > 900:
            self.status = 2;
            if self.x < 30 + Global.x_center:
                self.status = 3

        else:
            if self.x < 30 + Global.x_center:
                self.status = 3
            else:
                self.status = 4

    def swim(self):
        if self.status == 1: self.x += self.speed
        elif self.status == 2: self.x -= self.speed
        elif self.status == 3: self.y -= self.speed
        elif self.status == 4: self.y += self.speed


