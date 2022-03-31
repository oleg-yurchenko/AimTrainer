# Aim Trainer

## Description of the Project
This application will be used to train aim and precision. Many online games require good aim and mouse positioning to succeed, and I want to create an aim trainer that will help achieve just that. I believe people who play competitive games that involve some form of precise mouse movement would like to use this application. I am interested in making this project as I have played a lot of games that require mouse precision, however have not found an **effective tool that is also free**. My project will store a user profile that will keep track of their accuracy, improvement, precision, time to travel, and more.


## User stories
As a user:
- I want to be able to make multiple profiles and keep track of them (delete them as needed)
- I want to be able to view my statistics (as mentioned in the summary)
- I want to be able to practice my aim and have this reflected in my summary
- I want to have multiple modes (multi-target, speed completion, and more)
- I want to be able to reset a profile
- I want to be able to save my profiles (including stats)
- I want to be able to load all of my saved profiles

## EventLog
Unfortunately, due to decisions made early into the design process, the adding and removing of profiles is done entirely inside the GUI.
In order to reduce coupling and the need for more Classes, I deemed the adding and removing of profiles to be too insignificant of a task to require an entire class in and of itself.
Because of this, throughout my code I make calls to the top-level ArrayList of profiles in the AimTrainer class to remove or add profiles to it in the methods of various buttons.
Since none of the actions in UI are logged, the logger does not actually output anything, however the adding and removing process is there.
If I were to refactor my code, I think I will make a main class in the model that stores any of the "meta" variables such as the profiles, the selected game mode, and more, since I would be able to pass it, and it alone into all my UI classes instead of different variables (AimTrainer some times, Profile other times, etc.).

## UML Reflection
A few things I'd do if I had more time after looking at the class diagram:
 - Most of the classes inheriting JPanel also have an AimTrainer association. This can probably be extracted into a superclass which extends JPanel and has an AimTrainer (and perhaps more than one function). This will also make it so that AimTrainer can have an association directly with the new class rather than a generic JPanel.
 - The list of profiles is used fairly often by various classes and inside different methods. Perhaps a separate ProfileList class can be made.
 - To reduce coupling, it is possible to have Profile extend Observer, and have its associations update it using notifyObservers(); However this may not always be useful, since usually only one profile is changed at a time, so there would only be one observer at any given time.