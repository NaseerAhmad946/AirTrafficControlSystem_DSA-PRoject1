# Air Traffic Control System

Java ATC system built with Swing. Implements custom Graphs (Adjacency Matrix) and a manual Singly Linked List to map networks, schedule flights, track delays, and run Dijkstra's algorithm for shortest-path routing.

## System Preview

Here is how the application interface and the underlying graph network look:

### Main Dashboard Interface
![Application Main GUI Window](images/dashboard_preview.png)

### Shortest Path Routing Output
![Dijkstra Algorithm Distance Calculation](images/shortest_path_result.png)

##  Code Architecture & Data Structures

The system bypasses standard Java collection frameworks to implement fundamental computer science structures manually:

* **Custom Graph Network:** Modeled via an Adjacency Matrix (`int[][]`) capable of managing up to 20 airports.
* **Manual Singly Linked List:** Built using custom `Node` and `LinkedList` classes to handle structural route storage.
* **Dijkstra’s Algorithm:** Designed from scratch to loop through unvisited nodes and accurately solve minimum weights.
