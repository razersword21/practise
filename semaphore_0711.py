import threading



class FooBar():
    def __init__(self, n,):
        self.n = n
        
    def foo(self):
        count = 0
        while True:
            print('foo',end='')
            s_foo.release()
            s_yeah.acquire()
            count+=1
            if count >= self.n:
                break

    def bar(self):
        count = 0
        while True:
            s_foo.acquire()
            print('bar',end='')
            s_bar.release()
            count+=1
            if count >= self.n:
                break

    def yeah(self):
        count = 0
        while True:
            s_bar.acquire()
            print('yeah')
            s_yeah.release()
            count+=1
            if count >= self.n:
                break
if __name__ == "__main__":
    n=3
    s_foo = threading.Semaphore(0)
    s_bar = threading.Semaphore(0)
    s_yeah = threading.Semaphore(0)
    foobar = FooBar(n)
    threading.Thread(target=foobar.foo).start()
    threading.Thread(target=foobar.bar).start()
    threading.Thread(target=foobar.yeah).start()