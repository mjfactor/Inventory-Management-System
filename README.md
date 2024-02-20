# Inventory System

The Inventory System is a robust and user-friendly application built using Java, SQL, and Maven. It provides a comprehensive solution for managing inventory, tracking transactions, and user authentication with password reset functionality. The application uses JavaFX for its user interface.

## Technologies Used

- [Java](https://www.java.com/)
- [SQL](https://www.mysql.com/)
- [Maven](https://maven.apache.org/)
- [JavaFX](https://openjfx.io/)
- [Intellij](https://www.jetbrains.com/idea/)
## Features

- User-friendly login system with password reset functionality
- Intuitive dashboard for managing inventory
- Comprehensive transaction history tracking

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You have installed the latest version of [Java](https://www.java.com/) and [Maven](https://maven.apache.org/).
- You have a Windows machine. The code may work on other platforms, but that hasn't been tested yet.
- Have IntelliJ IDEA installed on your machine. (You can use any other IDE, but the instructions are for IntelliJ IDEA.)
## Setup

To install the Inventory System, follow these steps:

1. Clone the repository:
```bash
git clone https://github.com/mjfactor/invetorysystem.git 
```
or You can create "**New Project from Version Control**" in IntelliJ IDEA and paste the URL.
2. Navigate into the directory: `cd invetorysystem`
3. Build the project: `main`


## Database Setup

This application uses a MySQL database to store inventory data. Follow these steps to set up the database:

1. Download and install XAMPP from the [official website](https://www.apachefriends.org/index.html).
2. Start the XAMPP control panel and start the Apache and MySQL modules.
3. Open a web browser and navigate to `http://localhost/phpmyadmin`.
4. In the phpMyAdmin interface, click on the `Import` tab.
5. Click `Choose File` and navigate to the `inventory.sql` file in the project directory.
6. Click `Go` to import the database.

**The program will not work if the database is not set up correctly.**

## Adding Jar Files to Libraries

This application may require additional jar files to be added to the project libraries. Follow these steps to do so:

1. Download the required jar files and save them in a known location on your system.
2. In IntelliJ IDEA, open the `File` menu and select `Project Structure`.
3. In the Project Structure dialog, select `Modules` under the `Project Settings` section.
4. In the Modules pane, select the `Dependencies` tab.
5. Click the `+` button at the bottom of the pane and select `JARs or directories...`.
6. In the file chooser dialog, navigate to the location where you saved the jar files, select them, and click `OK`.
7. Click `Apply` and then `OK` to close the Project Structure dialog.




## Usage
To use the Inventory System, follow these steps:

1. Run the application: `java -jar target/invetorysystem-1.0-SNAPSHOT.jar`
2. Follow the on-screen prompts to log in or reset your password.
3. Use the dashboard to manage inventory and view transaction history.

## Editing the GUI

The GUI for this application is built using JavaFX and can be edited using SceneBuilder. Here are the steps to do so:

1. Download and install SceneBuilder from [Gluon's website](https://gluonhq.com/products/scene-builder/).
2. Open SceneBuilder.
3. Go to `File > Open` and navigate to the `.fxml` files in the project directory.
4. After opening the file, you can visually edit the GUI.
5. Save your changes and they will be reflected in the application when you run it.

*Please note that you should have a good understanding of JavaFX before editing the GUI.*

