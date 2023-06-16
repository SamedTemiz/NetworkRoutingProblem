package org.example;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    public List<Node> nodes;
    public List<Link> links;
    public XMLReader(){
        this.nodes = new ArrayList<>();
        this.links = new ArrayList<>();
        readXML();
    }

    public void readXML(){
        try {
            // XML dosyasını ağaç yapısı olarak yükleme
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("https://raw.githubusercontent.com/SamedTemiz/NetworkRoutingProblem/main/janos-us.xml");

            // Nodes
            NodeList nodeElements = doc.getElementsByTagName("node");
            for (int i = 0; i < nodeElements.getLength(); i++) {
                Element nodeElement = (Element) nodeElements.item(i);
                String id = nodeElement.getAttribute("id");

                // "node" öğesinin "x" ve "y" alt öğelerini seçme
                Element coordinatesElement = (Element) nodeElement.getElementsByTagName("coordinates").item(0);
                Element xElement = (Element) coordinatesElement.getElementsByTagName("x").item(0);
                Element yElement = (Element) coordinatesElement.getElementsByTagName("y").item(0);

                // "x" ve "y" verilerine erişme
                double x = Double.parseDouble(xElement.getTextContent());
                double y = Double.parseDouble(yElement.getTextContent());

                Node node = new Node(id, x, y);
                // Liste içine ekleme işlemi
                nodes.add(node);
            }

            // Links
            NodeList linkElements = doc.getElementsByTagName("link");
            for (int i = 0; i < linkElements.getLength(); i++) {
                Element linkElement = (Element) linkElements.item(i);

                String id = linkElement.getAttribute("id");

                Element sourceElement = (Element) linkElement.getElementsByTagName("source").item(0);
                Element targetElement = (Element) linkElement.getElementsByTagName("target").item(0);

                String source = sourceElement.getTextContent();
                String target = targetElement.getTextContent();

                // "link" öğesinin "additionalModules" alt öğesini seçme
                Element additionalModulesElement = (Element) linkElement.getElementsByTagName("additionalModules").item(0);

                // "additionalModules" öğesinin "addModule" alt öğesini seçme
                Element addModuleElement = (Element) additionalModulesElement.getElementsByTagName("addModule").item(0);

                // "addModule" öğesinin "capacity" ve "cost" alt öğelerini seçme
                Element capacityElement = (Element) addModuleElement.getElementsByTagName("capacity").item(0);
                Element costElement = (Element) addModuleElement.getElementsByTagName("cost").item(0);

                // "capacity" ve "cost" verilerine erişme
                //double capacity = Double.parseDouble(capacityElement.getTextContent());
                double cost = Double.parseDouble(costElement.getTextContent());

                Node sourceNode = nodes.stream().filter(node -> node.getId().equals(source)).findFirst().orElse(null);
                Node targetNode = nodes.stream().filter(node -> node.getId().equals(target)).findFirst().orElse(null);

                Link link = new Link(id, sourceNode, targetNode, cost);
                // Liste içine ekleme işlemi
                links.add(link);
            }

            addLinkToNode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLinkToNode(){
        for(Node node : nodes){
            for(Link link : links){

                if(link.getSourceNode().equals(node) || link.getTargetNode().equals(node)){
                    node.addLink(link);
                }

            }
        }
    }
}
