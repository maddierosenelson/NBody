Name: Maddie Nelson
NetID: mrn14
Hours Spent: 7.5
Consulted With: Wilson Zhang(TA) and Yumin Zhang during TA hours.
Resources Used: I referenced the N-Body assignment page and the equation page. I also watched the video posted by the TA who did a presentation on Wednesday about the assingment. I do not know his name... 
Impressions: I thought this was a really interesting assignment. However, I did not think that the video by the TA was very helpful at all and found that I needed extra, more detailed, explanations to better understand the assignment.
This was great practice with loops.
Question 1: What is the final position of the planets after 1,000,000
seconds with a timestep of 25,000?
Below are the positions that my code printed out following 1,000,000 seconds with a 25,000 timestep. 
The x positions are on the left and the y positions are on the right.
Name      XPosition                   YPosition
Earth	  1.4657072579675333E11       2.9603571820026024E10 
Mars	  2.265919409244593E11        2.4055025673504623E10 
Mercury	  3.863596759797241E10        4.2569286276404396E10 
Sun	      26826.758124022024          2979.2451384889378 
Venus	  1.0243682251001347E11       3.4391417962295876E10 

Question 2: For what values of timeStep, does the simulation no longer behave correctly?
The simulation no longer behaves correctly when the value of timeStep is equal to the totalTime that we would like to see.
For example when run correctly with the timeStep equal to 25000 seconds for 10,000,000 seconds, Mercury can be seen above and to the left of the Sun. However, when we run the program with the timeStep equal to 1,000,000 seconds, Mercury ends up below and slightly to the right of the Sun.
The larger the timeStep the less accurate the change in position is. 
Even when the time step is half of the total time, the final positions are very far from where the final positions would be when you do a smaller step which gives you a more accurate answer.
Also, as to be expected, the code will not run when timeStep is larger than the total time you are wishing to simulate.
Because I have a very powerful computer, even when doing the above calculations where the total time was 1,000,000 and the timestep was 25,000, my graphic displayed the planets blinking a little because it was working so fast.
Thus, even when I made the total time 10,000,000 seconds and made the timestep 100 seconds, my code still worked and the computer still did the calculations, it simply took a longer time and the planets blinked the whole time.
I was even able to set my timesteop to only 1 second with a total time of 10,000,000 seconds and my code still ran, it simply took a VERY long time(as in I left my computer running and came back a while later and it was still running but on track..).