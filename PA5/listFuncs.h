


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};

typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

int * search(const std::string &theKey, ListType list);    //This function is used to search the key

bool listRemove(const std::string &theKey, ListType &list);       //This funciton is used to remove the key in the list

bool insertFront(const std::string &theKey, int theValue, ListType &list);  //This function is used to insert the key in the list

int count(ListType list);    //This function is used to count the total number of the elements in the list

void print(ListType list);   //This function is used to print the content of the list
 
bool contain(const std::string &theKey, ListType &list);    //This function is to check if this element had already in the list.

// keep the following line at the end of the file
#endif
