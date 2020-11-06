
# [HW 2: Percolation](https://sp19.datastructur.es/materials/hw/hw2/hw2)

A Monte Carlo simulation is used to estimate the value of the percolation threshold for a porous landscape with water on 
the surface. It is modeled via an N-by-N grid of sites where the top row represents the surface of the landscape. Each site 
can be in one of three states: open, full, or blocked. When a site at the surface opens, water enters and it is 
considered full. When a site is full, its adjacent, open sites become full. Percolation occurs when a site in the bottom row
becomes full, i.e. when water flows from the surface to the bottom.

[Percolation](hw2/Percolation.java) is the data structure used to model the percolation system. It uses a "Union Find" (aka 
"Disjoint Sets") to represent the grid. [PercolationStats](hw2/PercolationStats.java) is used to perform the simulation.

## Monte Carlo Simulations
This computational experiment is used to estimate the percolation threshold:
1. Initialize all grid sites to be blocked. 
2. Repeat this until percolation occurs: uniformly and randomly select a blocked site and open it.

The percentage of sites that are open when percolation occurs provides an estimate of the percolation threshold.

The simulations were conducted using varying grid sizes: 100, 200, 400, 800, and 1600. For each grid size, 100 
simulations were performed, and then the mean, standard deviation, confidence interval, and runtimes were calculated. 

Simulations were performed with two different Disjoint Sets: WeightedQuickUnionUF and [UnionFind](hw2/UnionFind.java). I implemented UnionFind
in [lab 6](https://github.com/g-esco101/cs61b/tree/master/lab6) (where you can also see the [tests](https://github.com/g-esco101/cs61b/blob/master/lab6/tests/UnionFindTest.java) that I wrote for it).
WeightedQuickUnionUF was provided. The UnionFind was faster because it employs path compression on the methods union,
connected, and find; the WeightedQuickUnionUF performs path compression only on union.

The results of these simulations can be found in [hw2.results.txt](hw2/results.txt). 

## Challenges
Backwash is when the system percolates and open sites that are connected to the bottom become full even though they are 
not adjacent to a full site. Initially, I added two virtual sites that were off the grid: one connected to all the open 
sites at the surface (i.e. full sites), and the other connected to all the open sites at the bottom. This resulted in 
backwash: When the two virtual sites were connected, all the open sites connected to the bottom became full, 
because they were connected to the full virtual site. To prevent this, I used a two sets: one to track the roots
of full sites connected to the surface, and another to track the roots of open sites connected to the bottom. This ensured
that sets of sites were disjoint. 


## Run

To run this program please see the necessary tools and library in this [readme](https://github.com/g-esco101/cs61b). 

Open in Intellij (I used Intellij IDEA 2020.1). 

Simulations can be ran with the main method in [PercolationStats]((hw2/PercolationStats.java)) by modifying the 
gridSize and the simulationCount, or you can use one of the visual clients. 

Two clients that serve as large-scale visual traces were provided for testing and debugging the Percolation 
implementation: [InteractivePercolationVisualizer](hw2/InteractivePercolationVisualizer.java) and 
[PercolationVisualizer](hw2/PercolationVisualizer.java). InteractivePercolationVisualizer animates 
the results of opening sites in a percolation system, using the mouse as input. PercolationVisualizer is similar, but 
the input comes from a file. Please see the [Provided Files](https://sp19.datastructur.es/materials/hw/hw2/hw2) section 
on how to run these. 

## Files implemented
- [Percolation.java](hw2/Percolation.java): models a percolation system.
- [PercolationStats.java](hw2/PercolationStats.java): Monte Carlo simulation.

- [UnionFind.java](hw2/UnionFind.java)
- [UnionFindTest.java](https://github.com/g-esco101/cs61b/blob/master/lab6/tests/UnionFindTest.java)



