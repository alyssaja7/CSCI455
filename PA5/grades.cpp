

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;     // Table is dynamically allocated below, so we can call
                     // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);     //atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }

   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   string name;
   int score;
   string command;
   bool continueLoop = true;
   
   while(continueLoop){
      cout << "cmd>" << flush;
      cin >> command;     //Read in command
      
      if(command == "insert"){
         cin >> name;     //Read in the key
         cin >> score;    //Read in the value
         grades->insert(name,score);   //implement insert operation
         
      }else if(command == "change"){
         cin >> name;     //Read in the key
         cin >> score;    //Read in the value
         //If the key isn't exist, print out the wrong message. 
         //Otherwise, print out the corresponding value.
         if(grades->lookup(name) == NULL){
            cout << "Name isn't exist. No change happened!" << endl;
         }else{
            *(grades->lookup(name)) = score;
         }
         
      }else if(command == "lookup"){
         cin >> name;    //Read in name.
         //If the key isn't exist, print out the wrong message. 
         //Otherwise, print out the name and the value.
         if(grades -> lookup(name) == NULL){
            cout << "Name isn't exist. No change happened!" << endl;
         }else{
            cout << "The score of " << name << ": "<< *(grades->lookup(name)) << endl;
         }
         
      }else if(command == "remove"){ 
         cin >> name;  
         grades->remove(name);
         
      }else if(command == "print"){
         grades->printAll();    
         
      }else if(command == "size"){
         cout << "The number of entries is: " << grades->numEntries() << endl;
         
      }else if(command == "stats"){
         grades->hashStats(cout); 
         
      }else if(command == "help"){  
         cout << "All commands are as follow: " << endl;
         cout << "insert name score" << endl;
         cout << "change name newscore" << endl;
         cout << "lookup name" << endl;
         cout << "remove name" << endl;
         cout << "print" << endl;
         cout << "size" << endl;
         cout << "stats" << endl;
         cout << "help" << endl;
         cout << "quit: Exit the program." << endl; 
         
      }else if(command == "quit"){     //Exit the program
         continueLoop = false;
         
      }else{
         cout << "ERROR: invalid command, type help to get all commands" << endl;     
      }
   }
   return 0;
}

