# Recursive Linked Lists

Overview

Imagine you are an engineer of DroneDrop, a company that deploys drones to deliver life essentials in a post-apocalypse world, where everyone stays home because of biohazards outside. The drones are assigned to different districts, each with a pre-calculated route to visit all households. In order to provide fast and responsive customer services, the drone will update the route to skip those with no active order. Your goal is to provide a linear data structure that represents the route, and allows fast update, so we can beat our competitor. Luckily, some insider told us they use inefficient ArrayLists for the same job.

In this project, you will write a linked-based implementation for the List abstract data type, as specified in ListInterface.java. You’ll be building a singly linked list that can act as a drone route. The requirement is all methods you implement must use recursion and you are NOT allowed to use any loop (such as for, while, do-while). You must also stay within Big-O runtime bounds so that the drones are efficient in delivering life essentials.
