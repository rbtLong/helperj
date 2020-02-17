- Import this as a module (File -> New -> Module from existing source).
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