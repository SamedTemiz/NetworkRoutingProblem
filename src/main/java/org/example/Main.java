package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        XMLReader xml = new XMLReader();
        // Oluşturulan düğümler ve bağlantılar
        List<Node> nodes = xml.nodes; // Düğümleri listeye ekliyoruz
        List<Link> links = xml.links; // Bağlantıları listeye ekliyoruz

        // ACO parametreleri
        Node startNode = nodes.get(8);  // San Francisco
        Node endNone = nodes.get(22);   // Boston

        int antCount = 5;              // Karınca sayısı
        double alpha = 1.0;             // Alfa değeri
        double beta = 2.0;              // Beta değeri
        double evaporationRate = 0.5;   // Buharlaşma oranı
        double initialPheromone = 1.0;  // Başlangıç pheromone seviyesi
        int maxIterations = 100;        // Maksimum iterasyon sayısı

        // ACO algoritmasını başlatma
        AntColonyOptimization aco = new AntColonyOptimization(nodes, links, antCount, alpha, beta, evaporationRate, initialPheromone, maxIterations);
        aco.runOptimization(startNode, endNone);

        // En iyi yolun sonuçlarını alın
        List<Node> bestPath = aco.getBestPath();
        double bestDistance = aco.getBestDistance();

        // En iyi yolun yazdırılması
        System.out.println("En iyi yol: ");
        for (Node node : bestPath) {
            System.out.println(node.getId());
        }
        System.out.println("En iyi mesafe: " + bestDistance);
    }
}
