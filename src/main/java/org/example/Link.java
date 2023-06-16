package org.example;

public class Link {
    private String id;
    private Node sourceNode;
    private Node targetNode;
    private double distance;

    public Link(String id, Node sourceNode, Node targetNode, double distance) {
        this.id = id;
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public double getDistance() {
        return distance;
    }
    @Override
    public String toString() {
        return "Link{" +
                "name='" + id + '\'' +
                ", sourceNode=" + sourceNode +
                ", targetNode=" + targetNode +
                ", distance=" + distance +
                '}';
    }

}