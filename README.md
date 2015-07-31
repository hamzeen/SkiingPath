## SkiingPath Challenge ##

This is an eclipse project with a solution for the below described algorithmic problem.

##Problem Description

Let’s assume you hopped on a flight to a ski resort in Japan. Being a software engineer you can’t help but value efficiency, so naturally you want to ski as long as possible and as fast as possible without having to ride back up on the ski lift. So you take a look at the map of the mountain and try to find the longest ski run down.

In digital form the map looks like the number grid below.

4 4

4 8 7 3

2 5 9 3

6 3 2 5

4 4 1 6

The first line (4 4) indicates that this is a 4x4 map. Each number represents the elevation of that area of the mountain. From each area (i.e. box) in the grid you can go north, south, east, west - but only if the elevation of the area you are going into is less than the one you are in. I.e. you can only ski downhill. You can start anywhere on the map and you are looking for a starting point with the longest possible path down as measured by the number of boxes you visit. And if there are several paths down of the same length, you want to take the one with the steepest vertical drop, i.e. the largest difference between your starting elevation and your ending elevation.

On this particular map the longest path down is of length=5 and it’s highlighted in bold below: 9-5-3-2-1.

4 4

4 8 7 3

2 5 9 3

6 3 2 5

4 4 1 6

There is another path that is also length five: 8-5-3-2-1. However the tie is broken by the first path being steeper, dropping from 9 to 1, a drop of 8, rather than just 8 to 1, a drop of 7.

Your challenge is to write a program in your favorite programming language to find the longest (and then steepest) path on the map file specified in this project's parent path (map.txt). It’s 1000x1000 in size, and all the numbers on it are between 0 and 1500.

[source: http://geeks.redmart.com/2015/01/07/skiing-in-singapore-a-coding-diversion/]

##ScreenShot

![https://github.com/hamzeen/SkiingPath/raw/master/skiing.png](https://github.com/hamzeen/SkiingPath/raw/master/skiing.png)

## LICENSE ##
Copyright © 2015 Hamzeen. H.
Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0

Permission is hereby granted, free of charge, to any person
obtaining a copy of this project. It includes the rights to use,
fork, modify, merge, publish and distribute.

THE SOURCE OF THIS PROJECT IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.