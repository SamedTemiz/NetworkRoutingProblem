package org.example;

import java.util.ArrayList;
import java.util.List;

public class AntColonyOptimization {
    private List<Node> nodes; // Düğümlerin listesi
    private List<Link> links; // Bağlantıların listesi
    private int antCount; // Karınca sayısı
    private double alpha; // Alpha parametresi
    private double beta; // Beta parametresi
    private double evaporationRate; // Buharlaşma oranı
    private double initialPheromone; // Başlangıç feromon seviyesi
    private int maxIterations; // Maksimum iterasyon sayısı

    private double[][] pheromoneMatrix; // Feromon matrisi
    private List<Ant> ants; // Karıncaların listesi
    private List<Node> bestPath; // En iyi yol
    private double bestDistance; // En iyi yolun uzunluğu

    public AntColonyOptimization(List<Node> nodes, List<Link> links, int antCount, double alpha, double beta, double evaporationRate, double initialPheromone, int maxIterations) {
        this.nodes = nodes;
        this.links = links;
        this.antCount = antCount;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.initialPheromone = initialPheromone;
        this.maxIterations = maxIterations;

        // pheromoneMatrix'in boyutunu belirleme
        pheromoneMatrix = new double[nodes.size()][nodes.size()];
    }

    public void runOptimization(Node startNode, Node endNode) {
        initializePheromoneMatrix(); // Feromon matrisini başlangıç değerleriyle başlatma

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            ants = createAnts(startNode); // Karıncaları oluşturma

            for (Ant ant : ants) {
                while (!ant.getVisitedNodes().contains(endNode)) {
                    ant.move(getAdjacentNodes(ant.getCurrentNode()), pheromoneMatrix, alpha, beta); // Karıncaların hareket etmesi
                }
            }

            updatePheromoneMatrix(); // Feromon matrisini güncelleme

            updateBestPath(); // En iyi yolun güncellenmesi

            applyPheromoneEvaporation(); // Feromon buharlaşması
        }
    }

    private List<Ant> createAnts(Node startNode) {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < antCount; i++) {
            Ant ant = new Ant("Ant_" + (i + 1), startNode, nodes, links);
            ants.add(ant);
        }
        return ants;
    }

    private List<Node> getAdjacentNodes(Node currentNode) {
        List<Node> adjacentNodes = new ArrayList<>();
        for (Link link : links) {
            if (link.getSourceNode().equals(currentNode)) {
                adjacentNodes.add(link.getTargetNode());
            }
        }
        return adjacentNodes;
    }

    private void updatePheromoneMatrix() {
        // Pheromone matrisinin güncellenmesi
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] *= (1 - evaporationRate);
            }
        }

        for (Ant ant : ants) {
            List<Node> visitedNodes = ant.getVisitedNodes();
            double pheromoneIncrement = 1 / ant.getTotalDistance();

            for (int i = 0; i < visitedNodes.size() - 1; i++) {
                Node sourceNode = visitedNodes.get(i);
                Node targetNode = visitedNodes.get(i + 1);

                int sourceIndex = nodes.indexOf(sourceNode);
                int targetIndex = nodes.indexOf(targetNode);

                pheromoneMatrix[sourceIndex][targetIndex] += pheromoneIncrement;
                pheromoneMatrix[targetIndex][sourceIndex] += pheromoneIncrement;
            }
        }
    }

    private void initializePheromoneMatrix() {
        // Pheromone matrisinin başlangıç değerleriyle başlatılması
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] = initialPheromone;
            }
        }
    }

    private void updateBestPath() {
        // En iyi yolun güncellenmesi
        bestDistance = Double.MAX_VALUE;
        bestPath = null;

        for (Ant ant : ants) {
            if (ant.getTotalDistance() < bestDistance) {
                bestDistance = ant.getTotalDistance();
                bestPath = new ArrayList<>(ant.getVisitedNodes());
            }
        }
    }

    private void applyPheromoneEvaporation() {
        // Pheromone buharlaşması
        for (int i = 0; i < pheromoneMatrix.length; i++) {
            for (int j = 0; j < pheromoneMatrix[i].length; j++) {
                pheromoneMatrix[i][j] *= (1 - evaporationRate);
            }
        }
    }

    public List<Node> getBestPath() {
        return bestPath;
    }

    public double getBestDistance() {
        return bestDistance;
    }

}
