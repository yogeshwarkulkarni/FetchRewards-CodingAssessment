## One time prerequisites setup Instructions
### Install Java Development Kit- JDK
- JAVA path in system variable
  
### Download Maven
- Link :
  - Windows : "https://downloads.apache.org/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.zip"
  - Mac : "https://downloads.apache.org/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz"

### Set Path in System Variable
- In Environment variable -> System Variable -> new ->
  - Variable Name: MAVEN_HOME
- Variable value:
  - For example: C:\Users\ykulkarni\..\Maven\apache-maven-3.6.3
- Also go to path variable set path till bin directory.
  - Open System Variable
  - Look for Path Variable
  - Create new
  - Paste Maven path till bin repository
  - For example: "C:\Users\ykulkarni\..\Maven\apache-maven-3.6.3\bin"
- Download Eclipse IDE for Java Developers
- Import project to eclipse

## How to run project?
<b>Before executing the project data.properties file needs to be configured with</b>
  - <b>Browser: </b>Choice of browser (Currently code compatible with chrome)
  - <b>chromeDriverExePath: </b>Path of chromedriver.exe file on your local machine

<b>Using command prompt</b>
  - Open command prompt
  - Go to the project path:
     - Example: "cd C:\Users\ykulkarni\....\eclipse-workspace\FetchRewards"
  - Type “mvn test” and hit enter

## Screenshot
![image](https://github.com/yogeshwarkulkarni/FetchRewards-CodingAssessment/blob/main/Screenshot1.png)










