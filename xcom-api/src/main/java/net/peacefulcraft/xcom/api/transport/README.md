# XCOM Transport
The XCOM Transport package is a suite of interfaces which define components for inter-service communication. The API is meant to be transport-agnostic in that the means by which messages are actually exchanged is irrelavent, so long as they arrive at the correct MessageChannel and can be decoded.

### Components
- **Message** A "packet" that contains metadata about the transaction and encapsulates the data that is to be exchanged.
- **MessageChannel** What actually handles passing packets between the application and the underlying transport service. This handles sending messages and dispatching received messages to registered consumers.

### Semantic Components
Semantic components are loose-extensions of the raw components listed above, but refer to more recognizable and commonly used exchange formats.

##### JSON
- **JsonMessageChannel** Channel designed for transmitting and receiving JSON data.
- **JsonMessage** "Packet" designed for encapsulating JSON data.
- **IJsonSerializable** Interface which indicates the implementing class can be represented in JSON, usually for transport by in a `JsonMessage` over a `JsonMessageChannel`.