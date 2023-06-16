package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    private String id; // Karınca kimliği
    private Node currentNode; // Mevcut düğüm
    private List<Node> visitedNodes; // Ziyaret edilen düğümlerin listesi
    private double totalDistance; // Toplam yol uzunluğu
    private List<Node> nodes; // Düğümlerin listesi
    private List<Link> links; // Bağlantıların listesi

    public Ant(String id, Node currentNode, List<Node> nodes, List<Link> links) {
        this.id = id;
        this.currentNode = currentNode;
        this.visitedNodes = new ArrayList<>();
        visitedNodes.add(currentNode);
        this.totalDistance = 0.0;
        this.nodes = nodes;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public List<Node> getVisitedNodes() {
        return visitedNodes;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void move(List<Node> adjacentNodes, double[][] pheromoneMatrix, double alpha, double beta) {
        double[] probabilities = calculateProbabilities(adjacentNodes, pheromoneMatrix, alpha, beta);
        int nextNodeIndex = selectNextNodeIndex(probabilities);
        Node nextNode = adjacentNodes.get(nextNodeIndex);
        double distance = calculateDistance(currentNode, nextNode);

        if(!visitedNodes.contains(nextNode)){
            visitedNodes.add(nextNode);
            totalDistance += distance;
        }

        currentNode = nextNode;
    }

    private double[] calculateProbabilities(List<Node> adjacentNodes, double[][] pheromoneMatrix, double alpha, double beta) {
        double[] probabilities = new double[adjacentNodes.size()];
        double total = 0.0;

        for (int i = 0; i < adjacentNodes.size(); i++) {
            Node node = adjacentNodes.get(i);
            double pheromone = pheromoneMatrix[nodes.indexOf(currentNode)][nodes.indexOf(node)];
            double distance = calculateDistance(currentNode, node);

            probabilities[i] = Math.pow(pheromone, alpha) * Math.pow(1.0 / distance, beta);
            total += probabilities[i];
        }

        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= total;
        }

        return probabilities;
    }

    private int selectNextNodeIndex(double[] probabilities) {
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }

        // Eğer seçim yapılamazsa son düğümü seç
        return probabilities.length - 1;
    }

    private double calculateDistance(Node node1, Node node2) {
        for(Link link : links){
            if(link.getSourceNode().equals(node1) && link.getTargetNode().equals(node2)){
                return link.getDistance();
            }
        }
        return -1;
    }
}
