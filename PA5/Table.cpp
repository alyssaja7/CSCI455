

// Table.cpp  Table class implementation

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
   hashTable = new ListType[HASH_SIZE];
   hashSize = HASH_SIZE;
   for(int i = 0; i < hashSize; i++){
      hashTable[i] = NULL;    //initialize the pointer
   }
}


/**
 * Constructor of Table class using the input size of hashTable
 */
Table::Table(unsigned int hSize) {
   hashTable =  new ListType[hSize];
   hashSize = hSize;
   for(int i = 0; i < hashSize; i++){
      hashTable[i] = NULL;
   }
}

/**
 * Based on the given key, search the value corresponding to the key.
 * @param key : the key of entry
 * @return the pointer to the value of the key.
 */
int * Table::lookup(const string &key) {
   ListType & bucket = hashTable[hashCode(key)];
   return search(key, bucket);
}

/**
 * Remove a key-value pair in the table.
 * @param key : the key of entry.
 * @return true if remove successfully.
 */
bool Table::remove(const string &key) {
   ListType & bucket = hashTable[hashCode(key)];
   return listRemove(key, bucket);
}


/**
 * Insert a key&value pair into the table
 * @param key : the key of entry
 * @return true if insert successfully.
 */
bool Table::insert(const string &key, int value) {
   ListType & bucket = hashTable[hashCode(key)];
   return insertFront(key, value, bucket);
}

/**
 * Calculate the total amount of the entries in the table.
 * @return the number of entries in the table.
 */
int Table::numEntries() const {
   int sum = 0;
   for(int i = 0; i < hashSize; i++){
      sum += count(hashTable[i]);
   }
   return sum;      
}

/**
 * Print out all of the entries in the table.
 */
void Table::printAll() const {
   for(int i = 0; i < hashSize; i++){
      print(hashTable[i]);
   }
}

/**
 * Print out data related to the hashTable: 
 * number of buckets, number of entries, number of non-empty buckets and the longest   chain
 */
void Table::hashStats(ostream &out) const {
  cout << "number of buckets: " << hashSize << endl;
  cout << "number of entries: " << numEntries() << endl;
  cout << "number of non-empty buckets: " << numOfNonEmptyBuckets()<< endl; 
  cout << "longest chain: " << longestChain() << endl; 
}

// add definitions for your private methods here

/**
 * Calculate the total amount of non-empty buckets in the table.
 * @return the number of non-empty buckets in the table.
 */
int Table::numOfNonEmptyBuckets() const{
  int numOfNonEmptyBuckets=0;
  for(int i = 0; i < hashSize; i++){
    if(hashTable[i] != NULL){
       numOfNonEmptyBuckets++;
    } 
 }
   return numOfNonEmptyBuckets;
}

/**
 * To find the longest chain in the table.
 * @return the longest chain in the table
 */
int Table::longestChain() const{
   int longestChain = 0;
   for (int i = 0; i < hashSize; i++) {
      if (count(hashTable[i]) > longestChain) {
         longestChain = count(hashTable[i]);
        }
    }
    return longestChain;    
}


