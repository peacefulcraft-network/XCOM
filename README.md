## What is XCOM?
XCOM is a toolset for improving intra-network continuity. This includes tools for live or short-lived data messaging (command and control messaging usually) and longer-term, persistent data storage.

## XCOM Eco System
**XCOM-Core** is the client-server messaging and data storage system. This is pure XCOM. This includes the server implementation, the client API, and the client implementations. This is where the TCP socketry and MongoDB communication occurs.

**XCOM Class 1 Dependent** is an external application or plugin that utilizes an XCOM-Client via the XCOM-API, but exposes it's own functionality through it's own API to perform 'contextualized' XCOM operations. This includes things like the chat and party plugin. These implementations utilize XCOM, expose their own 'contextualized' API to perform operations; some of which are backed by XCOM, and also expose functionality that is visible at a user level. Historically, user visibility is what separates API additions from class 1 dependence.

**XCOM Class 2 Dependent** is an external application or plugin that utilizes an XCOM-Client via the XCOM-API, but does not expose it's own services which rely or mainly rely on the XCOM system. These are typically gamemode plugins which are more about providing a wholistic experience on their own rather than providing a utility service.

# Compiling
- Open your OS' command terminal and navigate to this project's folder ( folder with `src`, `pom.xml`, `README.md`, etc ).
- Type `mvn package`
- Once the command is complete, there will be a jar file in `target\[<artifactId>]-[version].jar`

  For questions, comments, or suggestions on this repo or Bukkit plugin development in general:
  - https://spigotmc.org
  - https://bukkit.org
  - https://www.peacefulcraft.net/flarum/t/github