# Dating-Final-Project
Dating Final Project (PIKA App) Group 250

***
## Objectives
***

**Purpose:**
To allow for people to connect in one application to fufill any of their relationship goals.
The program will enable users to create profiles, browse, and connect with compatible individuals based on several criteria, including physical characteristics, location, age, interests, values, and religion. Users will be able to:

* Create and customize their profiles with essential details.
* Set preferences for age range, location, and interests to enhance compatibility.
* Continue practicing with git in preparation for the group project.
* Receive a list of potential matches based on compatibility scores.
* Interact with matches through messaging and can block or report any user for safety concerns.

***
## Coding Techniques
***

**Project Design:**
Clean Architecture: The point of this technique was to successfully implement layers of code organization, extensibility and testing.

**SOLID Principles and Design Patterns:**
Used SOLID principles like Interface Segregation, Dependency Inversion and Single Responsibility to support the Clean Architecture.
To improve code quality, we used Design Patterns like Builder, Factory and Dependency Injection to solve code implementation problems.

**Testing:**
Unit tests for the Entities, Interactors and Database.
Integration tests for the Interactors with the real database and API.
End-to-end tests to test each Use Case.
User Interface: Java Swing and AWT components

***
## API & Database
***

Our database is an instance of Firebase/Firestore. It allows us to store various information about our users, their messages and different analytics securely and on the cloud.

[Firebase Database](https://firebase.google.com/)


***
## How To Run The Project?
***

1. First create a Firebase account.
2. Create a real-time database in Firebase.
3. Head to the settings of the database (Project Settings), select Service Accounts -> Java -> Generate new private key.
3. Once you have downloaded the private key, add the file to the src folder of the project.
4. In Intellij, copy the aboslute path of the json file (private key).
5. Head to the FirebaseInit.java class in the data_access package and replace the FileInputStream URL argument with the absolute path that you copied.
6. Finally, you can run the main method in the Main.java class!

***
## Use Cases
***

Our program will aim to work with the following use cases, each corresponding to a different user story, and each proposed by one member of the group.


Use case 1:

Lebron wants to have an account to log in securely, and log out easily to ensure his data remains private and secure..

Use case 2:

Jordan wants to be able to change her interests, name, etc. As many times as she’d like.

Use case 3:

Jing wants to be able to report a user who sent her inappropriate content. She is concerned about her safety.

Use case 4:

John has taken a look at all  of the users in the People View, He now wishes to like a profile and hope for a match.

Use Case 5:

Rahul wants to have an account to log in securely, and log out easily to ensure his data remains private and secure..

Use Case 6:

Radin wants to have an account to log in securely, and log out easily to ensure his data remains private and secure..




***
## Contributors
***

* Joshua Szkiba
* Luis Calderon Cruz
* Aryan Puri
* Daniel Benavides
* Yousaf Ali

This project was created as a part of the Software Design course project at the University of Toronto.