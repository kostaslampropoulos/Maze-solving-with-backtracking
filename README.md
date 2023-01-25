# This is an implementation of backtracking to solve a maze. 

 StringQueueImpl.java / StringStackImpl.java

 Abstract:

 A queue is structured as an ordered collection of items which are added from one end called “rear” and accessed, before being removed, from the other end called “front”. As a result, the queue data structure maintains a FIFO (First In First Out) property meaning that the sooner the item is being entered, the sooner it will be accessed.

 A stack is structured as collection of items with two main operations. The Push and Pop. The Push operation adds an element to the data structure and the Pop operation removes the most recently added item from the stack.

 Implementation of Node:

 To implement the concept of a Queue or a Stack, a class is needed to represent the Node(“items”) of our Queue. The Node class has 4 methods and 2 variables. Each Node knows its data (item data) and next (neighboring) Node closer to the “rear”, as the 2 attributes of the class. The 1rst method of the Node class initializes the data from the user as its “data” attribute. The getData() method returns the data of the Node. The getNext() returns the next (neighboring) Node. Lastly the setNext() sets the neighboring Node in the queue.
 Also the use of generics helps us determine the data type of the Nodes During the compilation time depending on the first Node being entered. That way there is no predetermined data type for the queue.

 Implementation of Queue:

 The whole implementation of a queue data structure revolves around a single class. It has 5 methods and 3 attributes. There are the “front” and “rear” which are the names for the first and last node being entered in the queue respectively, and the “size” attribute which represents the number of Nodes in the data structure at any given time. The method isEmpty() returns True if there are no Nodes in the queue, else it returns False. The get() method returns the first item of the queue and then sets the second item as the new front of the queue. Then the size is decreased by 1. But if the size initially was 1, then the front and rear are set to NULL to show that the queue is empty. The method peek() returns the first item of the queue (front) unless the data structure is empty. The printQueue() linearly print the nodes of the queue. Lastly the method size() returns the current number of Nodes in the queue (size of the Queue).

 Implementation of Stack:

 The whole implementation of a stack data structure revolves around a single class. It has 6 methods and 2 attributes. There are the “head” ,which represents the most recent addition on the structure, and the “size” attribute which represents the number of Nodes in the data structure at any given time. The method isEmpty() returns True if there are no Nodes in the stack, else it returns False. The method push(T item) sets the “head” of the stack to be equal to the newly created Node if the size was previously 0. Else it set the nest(neighboring) Node of the new node to be the current “head”. Then the new Node is set to be the “head”. The pop() method returns the data of the Node being represented by the “head” attribute. Then the size of the stack is decreased by 1. If after the operation the size is equal to 0 then the “head” is set to Null to show that there is no top Node in the stack. The method peek() returns the data of the Node being represented by the “head” without removing it. The method printStack() linearly prints the elements of the stack if it’s not empty. Lastly the size() method returns the current size of the stack.

 StringQueueWithOnePointer.java

 Abstract:

 With the Node class already defined we can continue to the Queue with one pointer. The abstract idea is that we can have the same operations and background logic of a queue but only using the “rear” attribute in the implemented class. This way the next Node of the last element (most recently added) is the front itself. So, we don’t need an extra attribute (pointer) to represent it. Each Node has its next as we go further from the front. As a result the “rear”(latest) Node has as its next the front itself.

 Implementation of Queue with one pointer:

 The fundamentals if this structure are the same as with the normal queue. What severely changes are the put(t item) and get() methods. More thoroughly, if the queue is empty , then the “rear” is set to be the new Node. Else if the size of the structure is 1, then the new node is next in the queue after the current rear and the “next” of the new Node is again the “rear”. Else we need the new Node to have its next to be the current’s “rear” ‘s next. So then the “rear” is set to be our new Node. After the put(T item) operation the size is increased by 1.
 The method get() returns the date of the current “rear” ’s next which is the front if the size if greater than 1. Else it returns the next of the next of the current “rear” and then is sets it(rear) to NULL. The rest of the methods require a similar understanding of a normal Queue.

 Thiseas.java

 Abstract:

 The player randomly moves towards a path that is available (“0”) until he finds the exit or a dead end (the player also marks each spot he has been on as a “2”). If a dead end is found or if the next available spot is a spot he has already been on (“2”), then the player marks the spot of the dead end as a wall (“1”) and backtracks using the stacks “stack_LR” & “stack_UD” to the previous position. This way, all dead ends that the player steps into can be eliminated until an exit can be found.

 Runtime:

 After reading the maze without errors, the “player” is set on the entrance point (“E”). The Firstmove static method is called to determine what the first move should be, depending on the position of the entrance. As long as the maze has a solution the following happens: The algorithm checks if the player is positioned on the exit (edge of the maze). If he is, then the solution is found. If not, it calculates a possible next move using the Nextmove static method. If the method returns the current position of the player as the next possible move (dead end) then the player backtracks (pops from the stacks its last move) or if there is nowhere to backtrack to, then we can determine that there is no solution to the maze. If a next move is available, the move is “pushed” into the stacks, the position of the player is updated and the new spot of the player is set as a spot he has been on (“2”). Whenever a player moves, both stacks are updated according to the direction it moved to.
