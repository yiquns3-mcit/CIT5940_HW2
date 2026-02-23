# Homework 2: Custom Hash Table Implementation

---

## Project Overview
> This project involves implementing a custom **Hash Table** in Java that uses **Chaining** to handle collisions. 
> Specifically, each bucket in the hash table is implemented as a **Binary Search Tree (BST)** using the `MyNode` class. 
> The implementation focuses on efficient data retrieval and single-traversal modification logic.

---

## Assignment Reflection

### 1. How long did it take you to complete this assignment (in hours)?
> 8–12 hours

### 2. What parts of this assignment did you find most difficult?
> The most difficult part was implementing the BST **remove()** method, especially the case where a node has two children. 
> At first, I tried using setItem() to replace values, but I realized that the item should not be modified. I then redesigned the logic. 
> My original idea was to list all possible cases and handle them one by one, but designing the helper function transplant() and understanding how to restructure the tree correctly was the most challenging part.

### 3. Did you use any outside resources to complete this assignment? If so, what resources and in what way did you use them?
> I used ChatGPT after designing my own code. 
> I noticed that some parts of my implementation were repetitive and could be simplified with a helper function, but I struggled to design one that maintained correct logic. 
> I shared my code and asked for guidance on creating a logical helper function. 
> After reviewing the suggested approach, I worked through the logic myself to fully understand how it worked.

### 4. Please write one or two sentences about something that you learned while completing this assignment.
> While implementing the remove() function, I learned that a well-designed helper function can greatly simplify complex logic. 
> It makes the code cleaner, easier to understand, and easier to maintain.