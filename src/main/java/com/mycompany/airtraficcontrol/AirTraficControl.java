/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.airtraficcontrol;

import static com.mycompany.airtraficcontrol.Graph.No_of_Airports;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author naseer
 */
//Linked List
class Node{
Route route;
Node next;
public Node(Route route){
this.route = route;
this.next = null;
}
}
class LinkedList{
Node head;
int size;
public LinkedList(){
head = null;
size = 0;
}
public void insert(Node n){
if(head == null){
head = n;

}
else{
Node temp =head;
while(temp.next!=null){
temp = temp.next;
}
temp.next = n;
}
size++;
}
public void Display(){
Node node  = head;
while(node!=null){
System.out.println(node.route.toString());
node = node.next;
}
System.out.println(size);
}

}







class Flight {
    String flightNo;
    Airport source;
    Airport destination;
    LocalDateTime departureTime;
    String status; 
    Graph graph;


    public Flight(String flightNo, Airport source, Airport destination, LocalDateTime departureTime) {
        this.flightNo = flightNo;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.status = "Scheduled";
       
    }

    public void delayFlight(int minutes) {
        this.departureTime = this.departureTime.plusMinutes(minutes);
        this.status = "Delayed";
   
 }

    public void cancelFlight() {
        this.status = "Cancelled";   }

    public void completeFlight() {
        this.status = "Completed";
 }
    @Override
    public String toString() {
        return "Flight " + flightNo + " | " + source.name + " -> " + destination.name + " | Time: " +
                departureTime + " | Status: " + status; }
}
class FlightManager {
ArrayList<Flight> flights;
Graph graph;

public FlightManager(Graph graph){
this.graph = graph;
flights = new ArrayList<>();
}

public boolean scheduleFlight(String flightNo, String source, String destination, LocalDateTime time) {
    int index1 = graph.findAirport(source);
    int index2 = graph.findAirport(destination);

    if (index1 == -1 || index2 == -1) {
        System.out.println("One or both airports not found.");
        return false;
    }

    Flight flight = new Flight(flightNo, graph.list_of_Airports[index1], graph.list_of_Airports[index2], time);
    flights.add(flight);
    System.out.println("Flight Scheduled: " + flight);
    return true;
}


public void delayFlight(String flightNo, int minutes){
for(int i = 0; i < flights.size(); i++){
if(flights.get(i).flightNo.equals(flightNo)){
flights.get(i).delayFlight(minutes);
System.out.println("Flight Delayed: " + flights.get(i));
return;
}
}
System.out.println("Flight not found.");
}

public void cancelFlight(String flightNo){
for(int i = 0; i < flights.size(); i++){
if(flights.get(i).flightNo.equals(flightNo)){
flights.get(i).cancelFlight();
System.out.println("Flight Cancelled: " + flights.get(i));
return;
}
}
System.out.println("Flight not found.");
}

public void completeFlight(String flightNo){
for(int i = 0; i < flights.size(); i++){
if(flights.get(i).flightNo.equals(flightNo)){
flights.get(i).completeFlight();
System.out.println("Flight Completed: " + flights.get(i));
return;
}
}
System.out.println("Flight not found.");
}

public void listAllFlights(){
System.out.println("\nAll Flights:");
for(int i = 0; i < flights.size(); i++){
System.out.println(flights.get(i));
}
}
}



// edges
class Route{
Airport Start;
Airport End;
int Distance;

public Route(Airport Start,Airport End,int Distance){
this.Distance = Distance;
this.End = End;
this.Start = Start;
}
    @Override
    public String toString(){
    return Start.name +"-->"+ End.name +" "+"Distance "+Distance;
    }

}
// vertex 
class Airport{
String name;
boolean isVisted;
String City;
public Airport(String name,String City){
this.name = name;
this.City = City;
isVisted = false;
}

}
// Graph
class Graph{
    int[][] Matrix;
    static Airport[] list_of_Airports;
    static int  No_of_Airports; /////////// here No of Airports are Static
     LinkedList List_of_Routes = new LinkedList();
    
    Stack stack;
    public Graph(){
       
    Matrix  = new int[20][20];
    list_of_Airports = new Airport[20];
    list_of_Airports[0] = new Airport("Lahore Airport","Lahore");
    list_of_Airports[1] = new Airport("Islamabad Airport","Islamabad");
    list_of_Airports[2] = new Airport("Karachi Airport","Karachi");
    No_of_Airports = 3;
    for(int i = 0;i<20;i++){
    for(int j = 0;j<20;j++){
    Matrix[i][j] = 0;
    }
    }
    Matrix[0][1]= 341;
    //Matrix[1][0]= 341;    NO Route from Islamabad to Lahore
    Matrix[0][2]= 1210;
    Matrix[2][0]= 1210;
    Matrix[2][1]= 1412;
    Matrix[1][2]= 1412;
    Node n1 = new Node(new Route(new Airport("Lahore AirPort","Lahore"),new Airport("Islamabad Airport","Islamabad"),341));
    List_of_Routes.insert(n1);
    Node n2 = new Node(new Route(new Airport("Lahore AirPort","Lahore"),new Airport("Karachi Airport","Karachi"),1210));
    List_of_Routes.insert(n2);
    Node n3 = new Node(new Route(new Airport("Karachi Airport","Karachi"),new Airport("Lahore AirPort","Lahore"),1210));
    List_of_Routes.insert(n3);
    Node n4 = new Node(new Route(new Airport("Islamabad Airport","Islamabad"),new Airport("Karachi Airport","Karachi"),1412));
    List_of_Routes.insert(n4);
    Node n5 = new Node(new Route(new Airport("Karachi Airport","Karachi"),new Airport("Islamabad Airport","Islamabad"),1412));
    List_of_Routes.insert(n5);
    
    
    
    
    }
 public void AddAirport(String name, String city) {
    if (findAirport(name) != -1) {
        System.out.println("Airport with this name already exists.");
        return;
    }

    Airport new_airport = new Airport(name, city);
    list_of_Airports[No_of_Airports] = new_airport;
    No_of_Airports++;

    System.out.println("Airport added: " + name + " (" + city + ")");
}

    public  int  findAirport(String Name){
        int index = -1;
        for(int i = 0;i<No_of_Airports;i++){
        if(list_of_Airports[i].name.equals(Name)){
        index = i;
        break;
        }
        }
    return index;
    }
    
    public void AddSingleRoute(String name1, String name2, int dist) {
    int index1 = findAirport(name1);
    int index2 = findAirport(name2);

    if (index1 == -1 || index2 == -1) {
        System.out.println("One or both airports not found.");
        return;
    }

    Matrix[index1][index2] = dist;

    Route r = new Route(list_of_Airports[index1], list_of_Airports[index2], dist);
    List_of_Routes.insert(new Node(r));

    System.out.println("Single route added: " + name1 + " → " + name2 + " (" + dist + " km)");
}

    
    
public void AddDoubleRoute(String name1, String name2, int dist) {
    int index1 = findAirport(name1);
    int index2 = findAirport(name2);

    if (index1 == -1 || index2 == -1) {
        System.out.println("One or both airports not found.");
        return;
    }

    Matrix[index1][index2] = dist;
    Matrix[index2][index1] = dist;

    Route r1 = new Route(list_of_Airports[index1], list_of_Airports[index2], dist);
    Route r2 = new Route(list_of_Airports[index2], list_of_Airports[index1], dist);

    List_of_Routes.insert(new Node(r1));
    List_of_Routes.insert(new Node(r2));

    System.out.println("Double route added: " + name1 + " ⇄ " + name2 + " (" + dist + " km)");
}

    
    public void DisplayRoutes(){
    List_of_Routes.Display();
    }
    


    
    
    public void ShortesDistance(String name){
    int index = findAirport(name);
    if(index!=-1){
    
    }
    else{
    System.out.println("Airport Not found plz Enter the correct Name");
    }
    }
    
    
    
public String ShortestDistance(String sourceName, String destName) {
    int sourceIndex = findAirport(sourceName);
    int destIndex = findAirport(destName);

    if (sourceIndex == -1 || destIndex == -1) {
        return "One or both airports not found.";
    }

    int[] distance = new int[No_of_Airports];
    boolean[] visited = new boolean[No_of_Airports];

    for (int i = 0; i < No_of_Airports; i++) {
        distance[i] = Integer.MAX_VALUE;
        visited[i] = false;
    }

    distance[sourceIndex] = 0;

    for (int count = 0; count < No_of_Airports - 1; count++) {
        int u = findMinDistance(distance, visited);
        visited[u] = true;

        for (int v = 0; v < No_of_Airports; v++) {
            if (!visited[v] && Matrix[u][v] != 0 && distance[u] != Integer.MAX_VALUE
                    && distance[u] + Matrix[u][v] < distance[v]) {
                distance[v] = distance[u] + Matrix[u][v];
            }
        }
    }

    if (distance[destIndex] == Integer.MAX_VALUE) {
        return "No route exists between the airports.";
    } else {
        return "Shortest distance from " + sourceName + " to " + destName + " is: " + distance[destIndex] + " km";
    }
}

    private int findMinDistance(int[] distance, boolean[] visited) {
    int min = Integer.MAX_VALUE;
    int minIndex = -1;

    for (int i = 0; i < No_of_Airports; i++) {
        if (!visited[i] && distance[i] <= min) {
            min = distance[i];
            minIndex = i;
        }
    }

    return minIndex;
}

public String getAllRoutes() {
    StringBuilder sb = new StringBuilder();
    Node node = List_of_Routes.head;
    while (node != null) {
        sb.append(node.route.toString()).append("\n");
        node = node.next;
    }
    sb.append("Total Routes: ").append(List_of_Routes.size).append("\n");
    return sb.toString();
}

    
    
    
    }







public class AirTraficControl extends JFrame {

    Graph graph;
    FlightManager manager;

    JTextField flightNoField, sourceField, destinationField, dateTimeField, delayMinutesField;
    JTextArea outputArea;

    public AirTraficControl() {
        setTitle("Air Traffic Control System");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        graph = new Graph();
        manager = new FlightManager(graph);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Flight No:"));
        flightNoField = new JTextField();
        inputPanel.add(flightNoField);

        inputPanel.add(new JLabel("Source Airport:"));
        sourceField = new JTextField();
        inputPanel.add(sourceField);

        inputPanel.add(new JLabel("Destination Airport:"));
        destinationField = new JTextField();
        inputPanel.add(destinationField);

        inputPanel.add(new JLabel("Date & Time (yyyy-MM-ddTHH:mm):"));
        dateTimeField = new JTextField("2025-07-02T10:30");
        inputPanel.add(dateTimeField);

        inputPanel.add(new JLabel("Delay Minutes:"));
        delayMinutesField = new JTextField("30");
        inputPanel.add(delayMinutesField);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));

        JButton addAirportBtn = new JButton("➕ Add Airport");
        JButton addRouteBtn = new JButton("➕ Add Route (Double)");
        JButton scheduleBtn = new JButton("🛫 Schedule Flight");
        JButton delayBtn = new JButton("⏳ Delay Flight");
        JButton cancelBtn = new JButton("❌ Cancel Flight");
        JButton completeBtn = new JButton("✅ Complete Flight");
        JButton listBtn = new JButton("📋 List All Flights");
        JButton shortestPathBtn = new JButton("📍 Shortest Distance");
        JButton showRoutesBtn = new JButton("🗺️ Show All Routes");

        // === BUTTON FUNCTIONALITY ===

        addAirportBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter Airport Name:");
            String city = JOptionPane.showInputDialog(this, "Enter City:");
            if (name != null && city != null) {
                graph.AddAirport(name, city);
                outputArea.append("Airport Added: " + name + ", " + city + "\n");
            }
        });

        addRouteBtn.addActionListener(e -> {
            String a1 = JOptionPane.showInputDialog(this, "Enter Airport 1:");
            String a2 = JOptionPane.showInputDialog(this, "Enter Airport 2:");
            String d = JOptionPane.showInputDialog(this, "Enter Distance:");

            try {
                int dist = Integer.parseInt(d);
                graph.AddDoubleRoute(a1, a2, dist);
                outputArea.append("Route Added: " + a1 + " <--> " + a2 + " : " + dist + " km\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Distance!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        scheduleBtn.addActionListener(e -> {
            try {
                String fNo = flightNoField.getText();
                String src = sourceField.getText();
                String dest = destinationField.getText();
                LocalDateTime time = LocalDateTime.parse(dateTimeField.getText());

                boolean success = manager.scheduleFlight(fNo, src, dest, time);
                if (success)
                    outputArea.append("Flight Scheduled: " + fNo + "\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for flight schedule!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        delayBtn.addActionListener(e -> {
            String fNo = flightNoField.getText();
            try {
                int mins = Integer.parseInt(delayMinutesField.getText());
                manager.delayFlight(fNo, mins);
                outputArea.append("Flight Delayed: " + fNo + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid delay minutes!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> {
            String fNo = flightNoField.getText();
            manager.cancelFlight(fNo);
            outputArea.append("Flight Cancelled: " + fNo + "\n");
        });

        completeBtn.addActionListener(e -> {
            String fNo = flightNoField.getText();
            manager.completeFlight(fNo);
            outputArea.append("Flight Completed: " + fNo + "\n");
        });

        listBtn.addActionListener(e -> {
            outputArea.append("\n--- All Flights ---\n");
            for (Flight f : manager.flights) {
                outputArea.append(f.toString() + "\n");
            }
        });

        shortestPathBtn.addActionListener(e -> {
            String src = sourceField.getText();
            String dest = destinationField.getText();
            String result = graph.ShortestDistance(src, dest);
            outputArea.append(result + "\n");
        });

      showRoutesBtn.addActionListener(e -> {
    outputArea.append("\n--- All Routes ---\n");
    outputArea.append(graph.getAllRoutes());
});

        buttonPanel.add(addAirportBtn);
        buttonPanel.add(addRouteBtn);
        buttonPanel.add(scheduleBtn);
        buttonPanel.add(delayBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(completeBtn);
        buttonPanel.add(listBtn);
        buttonPanel.add(shortestPathBtn);
        buttonPanel.add(showRoutesBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AirTraficControl();
    }
}

    
    
    
    
    










