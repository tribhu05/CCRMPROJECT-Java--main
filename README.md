# CCRMProject (Campus Course & Records Manager)

## About
A Java console-based application to manage students, courses, enrollment, grades, transcripts, and backups.  
Built as part of *Programming in Java Project 1* assignment.

## Features
- Add/list/update students and courses  
- Enroll students and record marks  
- Compute GPA and generate transcripts  
- Import/export CSV data  
- Backup data folder with timestamp (recursive size calculator)  
- Implements OOP, enums, exceptions, builder, singleton, streams, recursion, and NIO.2  

## Folder Structure
## How to Run
1. Compile the source code:
   ```bash
   javac (find src -name "*.java")
   java src-name
   
    b. Run the Main class:
    java -cp out edu.ccrm.cli.Main
    
   
## Requirements 
- JDK 17 or higher  
- Eclipse IDE (optional, for development)  
- Git (for version control)

## Screenshots
### Git installation
![Git Version]Screenshot git-version.png
### Eclipse Project Explorer (showing packages and files)  
![Eclipse Project]EclipseProjectExplorer.png
### CLI menu running in console 
cliMenu.png
cliMenu2.png
cliMenu3.png
cliMenu4.png
cliMenu5.png
### Exported CSV files in data
![Data]DataRecords.png
/ - Backup folder created with timestamp
backupservice.png
### Push to GitHUb
gitOriginmain.png
connection.png
connected.png
connected2.png
RepoPage.png


## Evolution of Java
- 1995: Java 1.0 released by Sun Microsystems  
- 2004: Java 5 introduced generics, annotations, enums  
- 2011: Java 7 introduced NIO.2, try-with-resources  
- 2014: Java 8 introduced lambdas, streams, default methods  
- 2017: Java 9 introduced modules (Project Jigsaw)  
- 2021+: Latest LTS versions (Java 17, Java 21)

## Java Editions (JAVA ME vs SE vs EE)
- **Java ME (Micro Edition):** Lightweight, used for embedded/mobile devices.  
- **Java SE (Standard Edition):** Core features, desktop and console apps.  
- **Java EE (Enterprise Edition):** Server-side apps, web services, large-scale systems.

## Java Architecture (JDK/JVM/JRE)
- **JVM (Java Virtual Machine):** Runs Java bytecode on any platform.  
- **JRE (Java Runtime Environment):** JVM + libraries to run Java programs.  
- **JDK (Java Development Kit):** JRE + tools like compiler (javac), debugger, etc.


## Installation & Setup
1. Download and install JDK 17.  
2. Verify installation with `java -version`.  
javaVersion.png
3. Install Eclipse IDE and create a new Java project.

EclipsejavaProject.png


## Syllabus Mapping
| Topic | Where Implemented |
|-------|-------------------|
| Encapsulation | Student.java (private fields + getters/setters) |
| Inheritance | Person.java (abstract) → Student, Instructor |
| Singleton | AppConfig.java |
| Builder | Course.Builder |
| Recursion | BackupUtil.java (recursive size calculation) |
| Streams | CourseService.java (filter/search) |
| Enum | Semester.java, Grade.java |

## Assertions
To enable assertions when running:  
bash
java -ea -cp out edu.ccrm.cli.Main



