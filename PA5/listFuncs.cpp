

#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}


//*************************************************************************
// put the function definitions for your list functions below

/**
 * traverse the Linkedlist to search: no special case
 *
 * @param theKey: the key of the entry
 * @param list: the list used to search the value
 * @return the value. 
 * @return NULL if the key isn't exist.
 *
 */
int * search(const string &theKey, ListType list){
   ListType p = list;
   while(p != NULL){
      if(p->key == theKey){
         return &(p->value);
      }
      p = p->next;
   }
   return NULL;
}

/**
 * Remove the target element in the Linkedlist.
 *
 * There are three cases: 
 * 1.the list is empty, return false; 
 * 2.the node we want to remove is the first node. If removed, return true.
 * 3.genral case: if remove the node successfully, return true.
 *
 * @param theKey: the key of the entry
 * @param list: the list used to remove the entry
 * @return false if the list is NULL.
 * @return true if remove successfully.
 *
 */
bool listRemove(const string &theKey, ListType &list){
   //special case: the list is empty.
   if(list == NULL){
      return false;
   }
   //special case: the node we want to remove is the first node.
   Node * p = list;
   if(p->key == theKey){
      p = p->next;
      delete list;
      list = p;
      return true;
   }
   //general case: the node is in the middle of the Linkedlist
   Node * prev = list;
   p = p->next;
   while(p != NULL){
      if(p->key == theKey){
         p = p->next;
         delete prev->next;
         prev->next = p;
      }else{
         prev = p;
         p = p->next;
      }
   }
   return true;
}
   
/**  
 * insert the node in the front of the linkedList.
 * No special case: this code also works for the empty list.
 *
 * @param theKey: the key of the entry.
 * @param theValue: the value of the entry.
 * @param list: the list used to insert the entry
 * @return false if the key has been in the linkedList.
 * @return true if insert the node successfully.
 *  
 */
bool insertFront(const string & theKey, int theValue, ListType &list){
   if(contain(theKey, list)){
      cout << "The key has already in the list, insert fails." << endl;
      return false;
   }      //No duplicate key. 
   ListType p = new Node(theKey, theValue);
   p->next = list;
   list = p;
   return true;
}

/**
 * check if the key has already in the linkedList to avoid duplicate key.
 *
 * @param theKey: the key of the entry.
 * @param list: the list used to check if the key has already been in there.
 * @return true if exist.Otherwise, return false.
 *
 * precondition: list is not empty.
 *
 */
bool contain(const string &theKey, ListType &list){
   Node * p = list;
   while(p != NULL){
      if(p->key == theKey){
         return true;
      }
      p = p->next;
   }
   return false;
}

/**
 * count the totol number of node in the LinkedList
 * special case: the list is empty,return 0.
 *
 * @param list: the list used to count the amount of entries.
 * @return the total number of entries in the Linkedlist.
 *
 */
int count(ListType list){
   Node * p = list;
   int count = 0;
   if(p == NULL){return count;}
   while(p->next != NULL){
      count++;
      p = p->next;
   }
   return count += 1;
}

/**
 * print out all the information in the each node of the list.
 *
 * special case: list is empty, print out the error message and return.
 * otherwise, print out the value and key in each node. 
 *
 * @param list: the list to print the entry.
 * 
 */
void print(ListType list){
   Node * p = list;
   while(p != NULL){
      cout << p->key << " " << p->value << endl;
      p = p->next;
   } 
}

