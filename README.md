# TSP Network Routing Problem Solution with Ant Colony Optimization

This is a project that solves TSP (Traveling Salesman Problem) Network Routing Problem using Ant Colony Optimization.

## Ant Colony Optimization - ACO
**[Source Baeldung](https://www.baeldung.com/java-ant-colony-optimization)

ACO is a genetic algorithm inspired by an ant’s natural behavior. To fully understand the ACO algorithm, we need to get familiar with its basic concepts:

- ants use pheromones to find the shortest path between home and food source
- pheromones evaporate quickly
- ants prefer to use shorter paths with denser pheromone

Let's show a simple example of ACO used in the Traveling Salesman Problem. In the following case, we need to find the shortest path between all nodes in the graph:

![](https://www.baeldung.com/wp-content/uploads/2017/03/ants1.png "1")

Following by natural behaviors, ants will start to explore new paths during the exploration. Stronger blue color indicates the paths that are used more often than the others, whereas green color indicates the current shortest path that is found:

![](https://www.baeldung.com/wp-content/uploads/2017/03/ants2.png "1")

As a result, we'll achieve the shortest path between all nodes:

![](https://www.baeldung.com/wp-content/uploads/2017/03/ants3.png "1")

## Network Routing Map and Solution Example
**[Source SNDLib](http://sndlib.zib.de/home.action)

Below is the network topology map used as an example in the project:

![](https://github.com/SamedTemiz/NetworkRoutingWithACO/blob/master/forReadme/janos-us.jpg "1")

We start our algorithm with the starting node in San Francisco and the ending node in Boston.

Our output in each run may be different. Below is a map representation of a sample output:

![](https://github.com/SamedTemiz/NetworkRoutingWithACO/blob/master/forReadme/janos-withAnt.gif "1")

Thank you.

> Contributing
To contribute to the project, please follow these steps:

> Fork:
- Create a new branch: git checkout -b new branchnewfeature
- Make your changes and commit them: git commit -am 'New feature added'
- Push to your branch: git push origin new branchfeature
- Open a Pull Request.
