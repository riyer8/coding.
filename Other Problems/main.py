# My introduction to Python scripting from basic to advanced
# links used:
#   The Complete Python Course For Beginners (Tech with Tim) https://www.youtube.com/watch?v=sxTmJE4k0ho
#   Learn Python - Full Course for Beginners (freeCodeCamp.org) https://www.youtube.com/watch?v=rfscVS0vtbw


for x in range(0, 10, 1):  # start, stop, step
    print(x)

fruits = ["apples", "oranges", "pears", "strawberries", "bananas"]
# to insert an object between oranges and pears -->
fruits[1:1] = "blueberries"
print(fruits)

file = open('file.txt', 'r')  # 'r' means to read the file, 'w' means to write more on the file, 'a' means to edit the
# file
f = file.readlines();
print(f)

newList = []
for line in f:
    newList.append(line.strip())

print(newList)


# class/ inheritance
class Vehicle():
    def __init__(self, price, gas, color):
        self.price = price
        self.gas = gas
        self.color = color

    def fillUpTank(self):
        self.gas = 100

    def emptyTank(self):
        self.gas = 0

    def gasLeft(self):
        return self.gas


class Car(Vehicle):
    def __init__(self, price, gas, color, speed):
        super().__init__(price, gas, color)
        self.speed = speed

    def beep(self):
        print("Beep Beep")


# overloading methods
class Point():
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y
        self.coords = (self.x, self.y)

    def move(self, x, y):
        self.x += x
        self.y += y

    def __add__(self, p):
        return Point(self.x + p.x, self.y + p.y)

    def __sub__(self, p):
        return Point(self.x - p.x, self.y - p.y)

    def __mul__(self, p):
        return self.x * p.x + self.y * p.y

    @staticmethod  # must define the class as @classmethod or @staticmethod when making it static or class
    def length(self):
        import math
        return math.sqrt(self.x ** 2 + self.y ** 2)

    def __gt__(self, other):  # greater than
        return self.length() > other.length()

    def __ge__(self, other):  # greater than or equal to
        return self.length() >= other.length()

    def __lt__(self, other):  # less than
        return self.length() < other.length()

    def __le__(self, other):  # less than or equal to
        return self.length() <= other.length()

    def __eq__(self, other):  # equal to
        return self.x == other.x and self.y == other.y

    def __str__(self):
        return "(" + str(self.x) + "," + str(self.y) + ")"


p1 = Point(3, 4)
p2 = Point(3, 2)
p3 = Point(1, 3)
p4 = Point(0, 1)

# map operators
li = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


def func(x):
    return x ** x


print(list(map(func, li)))
print([func(x) for x in li])

# filter function
li = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


def isOdd(x):
    return x % 2 == 1  # everything except 0 and False will evaluate to True


print(list(map(func, li)))
print(list(filter(isOdd, li)))  # filter takes out those that don't satisfy the condition


# lambda
def func6(x):  # the same function can be wrote with lambda shown below
    return x + 5


func2 = lambda x: x + 5  # lambda defines a function that only returns 1 line
func3 = lambda x, y: x + y

print(func(2))

import collections  # collections inclues all data types
from collections import Counter

# Containers -- list, set, dict, tuple - inmuttable
# Types -- counter, deque, namedTuple(), orderDict, defaultdict

# counter
c = Counter('ramya')
print(c)
c = Counter(['a', 'a', 'b', 'c', 'c'])
print(c)
c = Counter(cats=4, dogs=7)
print(c)

list(c.elements())  # prints each key with the frequency of the key
c.most_common()  # finds th e highest most common element based on frequency
# if you want to find the most common 3 elements, put most_common(3)
c = Counter(a=3, b=2, c=0, d=-2)
d = Counter(['a', 'b', 'b', 'c'])
c.subtract(d)  # subtracts the elements in d from the frequency of c
print(c)
# we can also do c-d and c+d to add the elements
print(c & d)  # finds the intersection of the elements. finds the elements in common
print(c | d)  # finds the union of the elements. all the elements that are part of either one of the
# lists/collections/counters

# named tuple
from collections import namedtuple

Point = namedtuple('Point', 'x y z')  # name of the tuple is Point and has objects x, y, z <-- breaks the latter string
# to all the elements. can also make a list like ['x','y','z'] or a dictionary like {'x':0, 'y':0, 'z':0} with
# 0 as default values
newP = Point(3, 4, 5)
print(newP)
print(newP[0])
print(newP._asdict())
print(newP._fields)
newP = newP._replace(y=6)  # can't change the original tuple, so must set it to another newP

p2 = Point._make(['a', 'b', 'c'])  # set x,y,z to a,b,c respectively

# deque <-- two-sided queue
from collections import deque

d = deque("hello")
print(d)
d.append('4')
d.appendleft(5)
d.pop()
d.popleft()
d.clear()  # removes everything from the deque
d.extend('234324')  # adds each element to the end of the deque
d.extendleft('hey')  # adds the word in reverse order "yeh"
d.rotate(-1)  # negative will rotate to the left, positive will rotate to the right by the given amount
de = deque("hello", maxlen=5)  # can also give the max length of the deque. removes the first element of the deque


# if it is added to. can't change the maxlen after initializing

# class in a function
def make_class(x):
    class Dog:
        def __init__(self, name):
            self.name = name

        def print_value(self):
            print(x)

    return Dog


cls = make_class(10)
d = cls("ramya")
print(d.name)
d.print_value()


# duner/magic method
class Person:
    def __init__(self, name):
        self.name = name

    def __repr__(self):
        return f"Person({self.name}"

    def __mul__(self, other):
        if type(other) is not int:
            raise Exception("Invalid argument, must be integer")
        self.name = self.name * other

    def __call__(self, other):
        print("called this function", other)

    def __len__(self):
        return len(self.name)

    # all the underlying methods with __<smtg>__ can be changed and defined when needed to be used


p = Person("ramya")
p * 4
print(p)

# metaclasses <-- don't really use these often, understand them if they appear in code
'''
class Test:
    pass
'''
Test = type('Test', (), {})  # same as the class Test
Test = type('Test', (), {"x": 5})

print(Test)
print(Test())


class Foo:
    def show(self):
        print("hi")


def add_attribute(self):
    self.z = 9


Test = type('Test', (Foo,), {"x": 5, "add_attribute": add_attribute})
t = Test()
t.add_attribute()
print(t.z)


# makes a metaclass that inherits a type
class Meta(type):
    def __new__(self, class_name, bases, attrs):  # modifies the construction of the object. the first thing that is
        # called when creating an object
        print(attrs)

        a = {}
        for name, val in attrs.items():
            if name.startswith("__"):
                a[name] = val
            else:
                a[name.upper()] = val

        return type(class_name, bases, attrs)
    # def __init__(self): #initalizes the object


class Dog(metaclass=Meta):
    x = 5
    y = 8  # x and y are the attributes of the object

    def hello(self):
        print("hi")
    # we've modified the construction of the object by making the variables and function that aren't a "Python" named
    # variable/function to uppercase


# decorators
def func(f):
    def wrapper(*args, **kwargs):  # the wrapper function must have the same amount of arguments as func2 or func3
        # the *args and **kwargs allows all numbers of  arguments
        print("Started")
        rv = f(*args, **kwargs)
        print("Ended")
        return rv

    return wrapper


@func
def func2():
    print("I am func2")


@func  # this is the same as line 315 where. a function can be decorated with more than one decorator.
def func3():
    print("I am func3")


x = func(func2)
y = func(func3)
print(x)
x()
y()

func3 = func(func3)  # func3 is now a variable that is used to call func(func3)
func3()

import time


def timer(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        rv = func()
        total = time.time() - start
        print("Time: ", total)
        return rv

    return wrapper


@timer
def test():
    for _ in range(10000):
        pass


def test2():
    time.sleep(2)


test()
test2()

# context managers <-- making sure that we reach the file.close() statement once it is opened
file = open("file.txt", "w")
try:
    file.write("ramya")
finally:
    file.close()
# the above code is equivalent to the code below
with open("file.txt",
          "w") as file:  # the open file is stored as the "file". once the code inside is done regardless of what
    # happens inside, the file closes at the end
    file.write("hello")


class File:
    def __init__(self, filename, method):
        self.file = open(filename, method)

    def __enter__(self):
        print("Enter")
        return self.file

    def __exit__(self, type, value, traceback):
        print(f"{type}, {value}, {traceback}")
        print("Exit")
        self.file.close()


with File("file.txt", "w") as f:
    print("Middle")
    f.write("hello!")
    # if we add "raise Exception()" the file was opened and closed even if there was an exception. we can see this by
    # the output of "Exit"

from contextlib import contextmanager


@contextmanager
def file(filename, method):
    file = open(filename, method)
    yield file
    file.close()


with file("file.txt", "w") as f:
    print("middle")
    f.write("hello")
