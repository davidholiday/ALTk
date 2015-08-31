Narrative:
In order to define elements, the basic unit of being in alTk
As a development team
I want to define elements, the manner in which they are presented to the user, 
and a means of creating them. 

* all dorgs and environment constituents are types of elements 

* elements consist of a protocol, presence, and presentation. 

* presense is a collection of points between which lines are drawn. those lines 
have attributes associated with them that define how the world interacts with 
them. 

* presentation is simply the view presented to the user of that object. 

* the world itself is an element 

* crowd wisdom -- the phenomena where a large group of people can be asked a 
question like 'how many beans in this jar?' or 'how much does this cow weigh?'
and have the average of all the answers be pretty close to the actual answer. 
--this thing could potentially solve problems that way. 

** protocols can dicate what happens to the presentation of an elemment!


// create an element
// define presentation
// define presence
// update presentation
// update presence 
// define protocol ** not applicable to non-creature **
// update protocol ** not applicable to non-creatures ** 
 
					 
Scenario: Environment with bouncing ball
Given An environment that is initialized with basic physics as protocol, a 
simple line around the edges as presentation, and solid edges as presence with 
size of 200x200
And an element that is initialized with no protocol, a presence of a circle of
a diameter of 20, and a presentation of a solid red ball
When the ball is placed in the environment and a force is applied to it
Then the ball bounces according to the physics protocol the environment 
was initialized with.

