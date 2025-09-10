# Tennis Score Board

## 🧱Built with
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Java-Servlet](https://img.shields.io/badge/Java%20SERVLET-003545?style=for-the-badge&logo=openjdk&logoColor=white) ![JDBC](https://img.shields.io/badge/JDBC-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) [![Hibernate](https://img.shields.io/badge/Hibernate-ORM-blue?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)
  ![APACHE MAVEN](https://img.shields.io/badge/Apache%20Maven-blue?style=for-the-badge&logo=apachemaven&logoSize=auto&color=%23C71A36) [![JUnit 5](https://img.shields.io/badge/JUnit_5-testing-green?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
 ![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black) [![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)](https://www.h2database.com/)

 ## 📋Application features 

1. 🎾 Match Operations
* Create a new match — allows users to initiate a match between two players.
* View completed matches & search by player name — users can browse finished matches and filter them by entering a player’s name.
* Score points in the current match — interactive update of match score during gameplay
  
2. 🧮 Tennis Match Scoring Rules
* Best-of-3 sets format — the match is won by the first player to win two sets.
* Tie-break at 6‑6 — if a set reaches 6‑6, a tie-break is played up to 7 points

3. 🌐 Web Interface (UI)
* Home Page — links to start a new match or view a list of finished matches.

* New Match Page (/new‑match)
  * HTML form with fields:
  * "Player 1 Name",
  * "Player 2 Name"
  * Submitting the form sends a POST request to /new-match
* Match Score Page (/match-score?uuid=$match_id)
   * Displays players’ names and the current score
   * Two buttons/forms:
       * Player 1 won point
       * Player 2 won point
   * Sends POST requests to update score
* Finished Matches Page (/matches)
    * Supports:
        * page=$page_number — pagination
        * filter_by_player_name=$player_name — search
    * Displays a paginated and searchable list of completed matches.
4. 🛠️ Backend Functionality (MVC(S) Architecture)
* Uses MVC(S) pattern: Model–View–Controller–(Service)
Main Controller
* MatchScoreController
    *Handles POST requests to /match-score
    * Uses OngoingMatchesService to retrieve active match
    * Calls MatchScoreCalculationService to update score.
    * On match end, saves it with FinishedMatchesPersistenceService.
    * Renders score with JSP view

Service Components
* OngoingMatchesService — stores active matches in memory.
* MatchScoreCalculationService — implements tennis rules (points, games, sets, tie-breaks).
* FinishedMatchesPersistenceService — saves completed matches to the database.

5. 💾 Data Storage & Database

 * Uses in-memory H2 database via Hibernate.
* Initializes schema at startup.
Tables:
* Players: ID (auto-increment), Name (unique)
* Matches: ID (auto-increment), Player1, Player2, Winner

6. 🚀 Deployment
* Built as a WAR file and deployed to Apache Tomcat on Linux.
* Local WAR build → upload to Tomcat Admin Panel.
* Accessible at:
http://<server_ip>:8080/<app_root>
* No external DB needed — uses embedded H2.

# ⚙️ Installation
Follow these steps to build and run the Tennis Scoreboard application locally or on a remote server.
✅ Prerequisites
* Java 17+
* Maven 3.6+
* Apache Tomcat 9 or 10
* Git
* (Optional) IntelliJ IDEA or another Java IDE

## 🛠️ Steps to Run
1. **Clone the Repository**

Clone the project from GitHub to your local machine:
```bash
git clone https://github.com/your-username/your-project.git
cd your-project
```

2. **Build the Project**
If your project uses Maven, build the project with:
```bash
mvn clean package
```
This will generate a .war file inside the target/ directory

3. **Deploy the .war File to Tomcat**
* Copy the generated .war file from target/ into the webapps folder of your local Tomcat installation.
Example:
```bash
cp target/your-project.war /path/to/tomcat/webapps/
```
4. **Start Tomcat**
Start your Tomcat server:
On Linux/macOS:
```bash
/path/to/tomcat/bin/startup.sh
```
On Windows:
```bash
\path\to\tomcat\bin\startup.bat
```
5. **Access the Application**
Open your browser and navigate to:
```bash
http://localhost:8080/your-project/
```
Replace your-project with the name of your .war file (without the .war extension).

