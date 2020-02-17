- Import this as a module (File -> New -> Module from existing source).
- File -> Project Structure -> Modules -> (+) on the right -> Add Module Dependency -> select 'helperj'
- Create a server.json file (use the server.json.example). It will look for the file in your launch path.
- Add the following dependencies to your POM/Gradle...
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.19</version>
    </dependency>

    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.6</version>
    </dependency>
- Make sure your compiler is set to version 1.8 or higher (pom.xml)...
    <properties>
        <maven.compiler.source>1.11</maven.compiler.source>
        <maven.compiler.target>1.11</maven.compiler.target>
    </properties>