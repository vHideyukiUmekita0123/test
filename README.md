selenium-java-framework　[![CircleCI](https://circleci.com/gh/suzuki-maruchan/selenium-java-framework/tree/master.svg?style=svg&circle-token=0d02ec8332f0b2a1f002d0d8471cf8163783cfb2)](https://circleci.com/gh/suzuki-maruchan/selenium-java-framework/tree/master)
====
Framework to enable to test E2E-test by selenium and java.

## Description
### Database
If set database infomation to `database.properties` , connect to database and get data by query.

### Evidence
Create path for evidence to save.

### Exception
Original exception for automatic test.

### Observer
Observe to execution test.

### PageObject
Page object pattern is a best practice for E2E-test. This package contains page object interface.

### ScreenShot
Enable to capture browser.

### TestClass
This is an object for Junit Test. This package contains interface, abstract, and template test.

### Utilities
This package contains implements to automate test conveniently.

## Demo
![demo](https://user-images.githubusercontent.com/34160750/72681797-7bbe4400-3b0a-11ea-9037-c3d30395e254.gif)

## VS.
TBA

## Requirement
### JAVA
~~~
java version "1.8.0_221"
Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.221-b11, mixed mode)
~~~

### MAVEN
~~~
Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-05T04:00:29+09:00)
Maven home: C:\apache-maven-3.6.1
Java version: 1.8.0_221, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_221\jre
Default locale: ja_JP, platform encoding: MS932
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
~~~

### Git
~~~
git version 2.22.0.windows.1
~~~

### GOOGLE CHROME
~~~
Google Chrome   79.0.3945.88 (Official Build) （64 ビット） (cohort: Stable)
~~~

## Usage
### Step1
Download from this repository by zip file. Don't `git clone` . Because it is need to modify common implement for each projects.

### Step2
Implement `src/main/java/testClass/TemplateTestClass.java` refer to `TODO` comment.

### Step3
Modify `src/main/resources/settings.properties` coforming with test target.

### Step4
Modify `src/main/resources/database.properties` conforming with test target database.

### Step5
Implement `src/main/java/screenShot/ScreenShot.java` conforming with test target.

### Step6
If necessary, implement and modify `src/main/java/database/Manager.java`.

### Step7
Create new page object class in `src/test/main/java/pageObject` for each web pages to need. <br>
See: `src/main/java/pageObject/sample`

### Step8
Create new test class and write test script. <br>
See: `src/test/java/testClass/SampleTestClass.java`

## Install
`git clone https://github.com/suzuki-maruchan/selenium-java-framework.git` <br>
or download as Zip file from this page.

## Contribution
Welcome always!!

## Licence
TBA

## Author
Yuto suzuki
