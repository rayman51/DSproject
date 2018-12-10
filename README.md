# DSproject

My name is Ray Mannion and I am a 4th year student at Galway-Mayo Institute of Technology studying Software Development.

For my Distributed Systems module I was  required to use the JAX-RS/Jersey, Java RMI and JAXB frameworks to develop a simple Car Hire Booking System. A Web Client page should provide users with the ability to Create/Modify/Update/Delete bookings for a specific vehicle for a given set of dates. The Web Client will interact with a RESTful JAX-RS Web Service for bookings which is deployed on Apache Tomcat Server. The RESTful Web Service will act as an RMI client to an RMI Database Server which will handle persistence.

## Running the project
To run the project open a command window and cd to your desired workspace i.e. desktop. 
Then clone the project using the command "git clone" followed by the URL of the project https://github.com/rayman51/DSproject .
Open eclipse oxygen EE (or whatever version you have on your machine but it must be enterprise edition) and switch workspace to the folder you have just cloned. The new workspace will contain four folders. 

* Servers
* Database-Service
* Booking-Server
* Client
There is also a lib folder containing the jar files for the project.

1. Click on the server tab in eclipse and add new apache server v7.0.
2. Right click on Database-Service and choose run as java application after a second or two you will be prompted to choose RMISetup.java with the package ie.gmit.sw and then click ok. This will create and populate a h2 database for use.
3. Right click on the Client folder and choose run on server.

Once the progam is running a web page will open with 3 buttons

* Show Customers
* Show Vehicles
* Show & Add Bookings

By Clicking on customers, a list of customers is displayed  

By Clicking on vehicles, a list of vehicles is displayed

By Clicking on bookings, a list of bookings is displayed

On each page there are also update and delete buttons and on the booking page there is a button to create a new booking.
Unfortunately, this project is incomplete as I was unable to meet the criteria for the Create/Modify/Update/Delete functionality.
This program creates the database and lists the data to screen, but is not able to manipulate it.

