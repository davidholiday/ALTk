# Artificial Life Toolkit (ALTk) 

ALTk is a Java implementation of the exercises in Daniel Shiffman's book [The Nature of Code](http://natureofcode.com/book/preface/). 

### how to install
It is assumed you have git, maven, and Java8 installed on your computer. If you don't, do that then come back. To install, you should only need to do a maven install from the project root directory.

### how to run
Open a terminal window, and from the project root directory type:

```java -jar altk/target/altK-0.0.1-SNAPSHOT-jar-with-dependencies.jar -Dsun.java2d.opengl=true```

### how to use
There is a simple Swing interface, but interaction with ALTk is currently limited to [BeanShell](http://www.beanshell.org/) scripts. All commands must be suffixed with ```();```. To run any of the demos, type ```{name of demo}();``` .

### currently implemented demos

* **vectorBall** -- Balls that bounce around the window. Not managed by JBox2D.
* **gravityBall** -- 'Planet' ball in the center of the screen to which all other elements are attracted. 'Planet' ball is movable via mouse. Not managed by JBox2D.
* **spaceWar** -- Simple (and incomplete) asteroids-like simulation. No collision detection. Ship can fly around via arrow keys (up for thrust, right/left to turn). Ship thruster effect made using a particle system. Not managed by JBox2D.
* **bounce2** -- Click mouse anywhere in the window to create a stream of randomly generated bouncy balls. First demo managed by JBox2D so collision detection is present. 

### how to make your own demos
There is an [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) framework that hooks into [JBox2D](https://github.com/jbox2d/jbox2d). To make new simulations, simply extend the Managed Element, Runner, Controller, View, and Model classes. Note also that the JBox2D testbed library is included, which is enormously helpful in working out the 'physics' of your world before integrating your demo into the application. Enable/disable the testbed in ALTk's main method. 

### some helpful links
* [JBox2D JavaDoc](http://trentcoder.github.io/JBox2D_JavaDoc/apidocs/)
* [Box2D Manual (JBox2D is a port of Box2D and is similar enough to where the Box2D manual is applicable)](http://box2d.org/manual.pdf)
* [JBox2D wiki](https://github.com/jbox2d/jbox2d/wiki)
