# BLEAdvert_Test

## Description
This repository houses the code used to test my practical application made for my Bachlelor's thesis.

## Prerequisites
Make sure you have the following installed:

- Intellij (https://www.jetbrains.com/idea/download)
- Java JDK (https://www.oracle.com/java/technologies/downloads/) (for the project JDK 23 was used)
- Android Studio (https://developer.android.com/studio)
- Apache Maven (https://maven.apache.org/download.cgi)
- Node.js (https://nodejs.org/en)
- Appium


## Install Appium
If you followed the prerequisites list and downloaded all of them, the next step is to download Appium. <br>

Appium is downloaded with the command: 

```bash
npm install -g appium
```
If npm isn't in your PATH, then you'll need to open bash in the folder npm is installed in (typically somewhere here: cd C:\Users\Username\AppData\Roaming\npm) and input the command above.

To check if Appium is correctly installed type:
```bash
appium --version
```
After confirming that Appium is installed, the next step is to run this command:
```bash
appium driver install uiautomator2
```

Like before, if Appium is not in you PATH, you need to go to the folder where Appium is installed to run this command.
After successfully installing all the tools above, the next step is to clone the repository 
## Cloning repos

Since this project is directly correlated with my Bachelor's project, you'll need to clone it.
```bash
git clone https://github.com/Dombah/BLE.git
```
After that clone this repo
```bash
git clone https://github.com/Dombah/BLEAdvert_Testing.git
```

## Android studio

### Opening project

Firstly, open up Android Studio. After the opening dialog shows up, click open project and navigate to the folder you've cloned the first repo (https://github.com/Dombah/BLE.git). When the project opens head to the next step. 

### Setting up the emulator

Next up, open Android Studio and on the right vertical tab you should see Device Manager. Click on Device Manager -> Plus sign(+)-> Create Virtual Device -> Phone -> Click on Pixel 7 -> Next -> Click R (API 30). If you didn't install this beforehand you'll need to wait for it to download. 

### Running the application 

After the first two steps, click on the arrow to boot the project. Make sure that on the left side of the arrow the emulator name is selected as Pixel_7_API_30 if you didn't change anything while making the emulator. If you changed the name of the emulator, it should be displayed accordingly. If the project builds and you see a login screen, you've done everything right. 

## IntelliJ

Firstly, open up IntelliJ. After the opening dialog shows up, click open project and navigate to the folder you've cloned the second repo (https://github.com/Dombah/BLEAdvert_Testing.git). 

### Setup

When the project loaded successfully, the next step is to do a little bit of setup to make this code work locally on your machine. Head over to <b >src/test/java/config/TestConfig </b>. Here you'll need to setup some parameters. If you've followed the steps above, the only thing that needs to be changed in the class TestingSetup is the location of the apk file. Input the project location all the way to the apk-debug file. I've left my file path in the project so it can be easier to find on your machine. Next step is to go to <b>src/main/resources</b> and make a file called <i>adminSdk.json</i>. You'll have to ask me to generate you a private key json file for the tests to work cause of the use of Firebase. If you have the content (I've provided the json for the professor), then copy paste it into <i>adminSdk.json</i>. Next, find the pom.xml file, right click it and go to the bottom where it says Maven, click download sources and wait for it to finish. After that, do the same pattern but instead of download sources click on sync project. <br>

The final step is to open bash and type in appium. After that your project should be ready to use for testing. To test either type in the terminal of IntelliJ the line below:

```bash
mvn clean test
```

or run each test class separately by right clicking on it and pressing run. 

## Bugs

As of writing this readme, the <b>mvn clean test</b> command as well as anything used to generate a report doesn't work for testLoginFailure() and testRegisterFailure() functions. The direct cause of this is unknown and after searching for a long time I still haven't figured the problem. It has to do something with the version of maven. 






