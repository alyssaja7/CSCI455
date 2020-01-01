

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {
   string str;
   int value;
   ListType testList = NULL;
   
   char c;
   bool conti = true;
   int num;
   
   do{
      cout << "Please enter one of these commands: s, r, i, c, p" << endl;
      cin >> c;
      
      if(cin.fail()){
         cout << "input stream fails" << endl;
         conti = false;
      }else{
         switch(c){
            case 's':
               //testSearch
               cout << "lookUp key" << endl;
               cin >> str;
               search(str,testList);
               break;
               
            case 'r':
               //testRemove
               cout << "remove Key" << endl;
               cin >> str;
               listRemove(str, testList);
               break;
               
            case 'i':
               //testinsert
               cout << "insert Key" << endl;
               cin >> str;
               cout << "insert value" << endl;
               cin >> value;
               insertFront(str,value,testList);
               //num = count(testList);
               //cout << "the total number is : " << num;
               break;
               
            case 'c':
               //testCount
               num = count(testList);
               cout << "the total number is : " << num << endl;
               break;
               
            
            case 'p':
               //testPrint
               cout << "The list is:  " << endl;
               print(testList);
               cout << endl;
               break;

            case 'q':
               //quit
               conti = false;
               break;
         }
         cout << "The list: " << endl;
	     print(testList);
      }
   }while(conti);
   return 0;
}
