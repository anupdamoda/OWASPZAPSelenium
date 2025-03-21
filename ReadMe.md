<h1 align="center"> OWASP Security test reports using ZAP (Zed Attack Proxy) & Selenium </h1>
<p align="center">
  Description: This is a demo testscript using OWASP ZAP Api and Selenium which generates the report - which outlines the alerts generated 

</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)


## Introduction
This is a sample test script on how to use selenium tool as web scraping tool and using zap tool for doing the penetration testing and getting a report on vulnerabilities of the web application


## Features
Appium Tests


## Test Requirements

### Local
* [Java 11 SDK](https://www.oracle.com/au/java/technologies/javase/jdk11-archive-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Node](https://nodejs.org/en/download)
* [Appium Driver](https://appium.io/docs/en/2.1/quickstart/uiauto2-driver/)
* [Android SDK Platform Tools ](https://developer.android.com/tools/releases/platform-tools)


## Execution modes ## 🤖 Starting up

<b>Step 1: You need to have Java, Maven setup in your machine that you have want to script and run Appium Tests (pre-requisite).</b>

make sure you have configured the JAVA_HOME and MAVEN_HOME in your environment system variables

The below command helps to check java and maven in your machine

> java -version
> mvn -version

Setup Java and Maven system environment variables post installation
![img.png](img.png)
<b>Step 2: You need node installed in your machine</b>

The below command helps to check node in your machine

node -v
If node is not in the machine you can follow the steps:

https://nodejs.org/en/download

<b>Step 3: Install Appium in the machine</b>

The below command helps to install Appium server in your machine using node

node appium
the below short command helps to start the Appium server

appium

<b>Step 4: About the Android Emulator and setup the emulator with the help of android studio </b>

go the virtual device manager in andriod studio and create the emulator session
![img.png](img.png)

click to run the emulator and make sure the app is running
![img_1.png](img_1.png)

run adb devices in the command prompt so that to check list of devices attached,
and the emulator device should appear
![img_2.png](img_2.png)

<b>Step 5: About capabilities of mobile app that you are testing</b>

Understanding capabilities are the most important part of the appium as it needs to know which device, which OS, which mobile app etc needs to be launched to test. The below set up capabilities will give you an idea about the capabilities

cap.setCapability("autoGrantPermissions", true);
cap.setCapability("deviceName","sdk_gphone64_x86_64");
cap.setCapability("udid","emulator-5554");
cap.setCapability("platformName","ANDROID");
cap.setCapability("platformVersion","14");
cap.setCapability("automationName","UiAutomator2");
cap.setCapability("appPackage","org.simple.clinic.staging");
cap.setCapability("appActivity","org.simple.clinic.setup.SetupActivity");

in the above set of capabilities it helps Appium code to understand that which device, which OS, which OS platformversion, which driver, which app, which activity needs to be launched to start Appium tests.

<b>Step 6: Install Appium driver in your machine</b>

Appium needs one of the Appium drivers to be installed and present prior to running the test. So in this case as we are using Android mobile app we need to use ‘UiAutomator2’ as our Appium driver. Now if you are still doubtful on which Appium driver to use then kindly have a look at the official Appium driver documentation

https://appium.io/docs/en/2.1/quickstart/uiauto2-driver/

<b>Step 7: Installation of android sdk platform tools</b>

Android sdk platform tools needs to be downloaded and installed.

https://developer.android.com/tools/releases/platform-tools

post installation — the environment path needs to be setup for the ANDROID_HOME
![img_3.png](img_3.png)

<b>Step 8: Setting up ANDROID_HOME in environment variables</b>

post installation in above step the environment system variables the ANDROID_HOME needs to be setup


<b>Step 9: Install Appium inspector to inspect the elements on the mobile app</b>

Appium inspector is a tool used to provide you the best possible selectors which can help you in Appium tests.
![img_4.png](img_4.png)

Appium inspector starting session
![img_5.png](img_5.png)

The section which provides the best selectors
<b>Step 10: Launching mobile application using Appium</b>

After writing the simple steps of launching the mobile app and then using the Appium driver steps to navigate on the app and validating, you will be able to successfully run the Appium tests
![img_6.png](img_6.png)