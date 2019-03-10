# Cache_Memory_simulator


Cache memory simulator

The program simulates a cache memory, which have less memory than the main memory, so when it reaches the maximum 
capacity, one of the existing elements will have to be replaced with the new element. Program implements three different methods to decide which element to replace: LRU (least recently used), FIFO (first in first out), LFU (least frequently used).


-- Least recently used (LRU) - the least used element will be replace with the new element.

-- First in first out (FIFO) - the element at the beginning of the queue will be removed, so the new element will be introduced at the end of the queue.

-- Least frequently used (LFU) - the element with the lowest number of accesses will be removed; if there are multiple object with the same number of accesses, first added element will be removed.


There is also a main memory represented as an array which will contain all objects added.

Object types: Free, Basic (inherits Free class), Premium (inherits  Basic class). Every subscription should contain the allowed number of Premium and Basic requests. Free requests are unlimited.


Working with memory operations:

-> "ADD object_name basic_requests premium_requests" - every time you add a new item, it will be added only in the main memory and not in the cache memory; if the item is already in the main memory or in cache also, it will be overwritten in the main memory and removed from cache;

* parameter basic_requests is mandatory and premium_requests is optional

Example:
	ADD object_name 5 - will have only 5 GET basic requests available
	ADD object_name 4 2 - will have 2 GET premium requests and 4 GET basic requests available


-> "GET object_name" - this operation will return a number according to whether the object belong to a memory or not;

* 0 -> object belongs to cache memory
* 1 -> object belongs only to main memory. After that, it will be moved to cache memory also
* 2 -> object was not found in any memory
* together with the number, it will be displayed the subscription type used (excepting the case when the object is not found);
