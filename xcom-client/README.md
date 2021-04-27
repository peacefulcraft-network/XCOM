# XCOM-Client

### ISM Usage
Loopback example
```java
RabbitMQJsonMessageChannel testChannel = new RabbitMQJsonMessageChannel("json_test");
testChannel.registerReceiver((msg) -> {
	this.logNotice("Got message: " + msg.getData().toString());
});

JsonObject obj = new JsonObject();
obj.addProperty("message", "Hello world");

// Source, destination, json message
JsonMessage msg = new JsonMessage("dev", "json_test", obj);
try {
	testChannel.sendMessage(msg);
} catch (IOException ex) {
	ex.printStackTrace();
	this.logSevere("Error sending message " + ex.getMessage());
}
```

Outputs
```json
{"message": ":Hello world"}
```