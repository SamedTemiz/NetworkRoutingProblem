package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String id;
    private double x;
    private double y;
    private List<Link> links;

    public Node(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.links = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addLink(Link link) {
        links.add(link);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
