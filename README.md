# MULTITHREADED-CHAT-APPLICATION

COMPANY: CODTECH IT SOLUTIONS

NAME: CHAVVAAKULA ANIL KUMAR

INTERN ID: CT04DL445

DOMAIN: Java Programming

DURATION: 4 WEEKS

MENTOR: NEELA SANTHOSH KUMAR


🧠 Overview
This Java application allows multiple clients to connect and chat with each other through a central server. The server uses multithreading to handle multiple users concurrently, and the clients can send and receive messages in real time.

🧱 Components Explained
1. ChatServer.java
Purpose: Starts a server socket on a given port and listens for incoming client connections.

Core Logic:

Waits in a loop (serverSocket.accept()) to accept new client connections.

For each new client:

Creates a new ClientHandler object.

Adds the client handler to a shared set of handlers.

Starts a new thread for each client using new Thread().start().

2. ClientHandler.java
Purpose: Handles the communication between the server and a single client.

Responsibilities:

Listens for messages from the assigned client.

When a message is received, it broadcasts it to all other clients.

Manages I/O streams for that specific client.

Closes resources if the client disconnects or an error occurs.

Broadcasting Logic:

Uses a synchronized Set<ClientHandler> so multiple threads can access it safely.

Sends incoming messages to all clients except itself using a loop.

3. ChatClient.java
Purpose: Acts as the user-facing client application.

Functionality:

Connects to the server via socket.

Takes user input from the console and sends it to the server.

Starts a separate thread to continuously receive messages from the server.

Inner Class:

ReceiveMessages runs on a background thread to constantly read and display messages from the server.

⚙️ Technology Used
Tool	Purpose
Java Sockets	For TCP/IP communication
Java Threads	To allow multiple clients concurrently
Collections.s	
