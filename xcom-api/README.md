# XCOM API
### Releasing to [Nexus](https://nexus.peacefulcraft.net/#browse/browse:peacefulcraft-bukkit-release)
1. Commit all changes
2. `mvn release:prepare` If it puts {project.version} in for the remote tag, be sure to change it to the proper version.
3. `mvm release:perform`
4. Rejoice.

### Including as a project dependency
```xml
<repository>
	<id>nexus.peacefulcraft.net</id>
	<url>https://nexus.peacefulcraft.net/repository/peacefulcraft-bukkit-release/</url>
</repository>

<dependency>
	<groupId>net.peacefulcraft.xcom.xcom_api</groupId>
	<artifactId>xcom-api</artifactId>
	<version>0.0.1</version>
</dependency>
```

**Snaphots are available at**
```xml
<repository>
	<id>nexus.peacefulcraft.net</id>
	<url>https://nexus.peacefulcraft.net/repository/peacefulcraft-bukkit-snapshot/</url>
</repository>

<dependency>
	<groupId>net.peacefulcraft.xcom.xcom_api</groupId>
	<artifactId>xcom-api</artifactId>
	<version>{snapshot version you want}</version>
</dependency>
```