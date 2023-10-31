import random

from hostagent import HostAgent

class FishAgent:
    def __init__(self) -> None:
        self.x = random.randint(1, 100)
        self.y = random.randint(1, 500)
        self.color = random.randint(0, 0xffffff)
        self.size = random.randint(5, 30)
        self.speed = 10 * 25 / self.size
        self.status = -1

    def definirStatus(self):
        if self.y < 500:
            self.status = 1
            if self.x > 100 + HostAgent.x_center:
                self.status = 4;

        elif self.y > 900:
            self.status = 2;
            if self.x < 30 + HostAgent.x_center:
                self.status = 3

        else:
            if self.x < 30 + HostAgent.x_center:
                self.status = 3
            else:
                self.status = 4

    def nadar(self):
        if self.status == 1: self.x += self.speed
        elif self.status == 2: self.x -= self.speed
        elif self.status == 3: self.y -= self.speed
        elif self.status == 4: self.y += self.speed


