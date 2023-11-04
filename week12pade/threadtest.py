import threading
import time

def goflowers(param):
    for i in range(100):
        print(param, i)
        time.sleep(0.1)

threads = []
for i in range(5):
    threads.append(threading.Thread(target=goflowers, args=(f"{' '*(i+1)}{i}",)))
    threads[-1].start()

for i in range(5):
    threads[i].join()

print("that's all")



