 - Design of program
 - Classes used, class types, and purpose of class
 - Main program fully documented, data structures explained

# Classes
 - Monetary value is displayed in cents as integers do not have floating-point accuracy issues
## Coin
 - A public abstract class that is the parent of all other coin types (i.e., Nickel, Dime, etc.)
 - Contains common methods that are required by all child classes such as getValue, toString, and compareTo
 - Has a constructor to make it easier for all child classes to set their own values
 	- That constructor can never be run directly as the class is abstract
 	- The value is private as it should only ever be accessed by the class itself

## Nickel
 - A public class that inherits Coin, setting its value to 5

## Dime
 - A public class that inherits Coin, setting its value to 10

## Quarter
 - A public class that inherits Coin, setting its value to 25

## Loonie
 - A public class that inherits Coin, setting its value to 100

## Toonie
 - A public class that inherits Coin, setting its value to 200

## Piggybank
 - A public class that manages Coins by exposing higher-level methods to let the user more easily work with Coins
### Prominent Data Structures
 - 1 ArrayList that holds Coins
 - 1 constant treasury that makes it simpler to add new Coins
 - 1 bank that acts as a frequency array for tracking of each individual coin

### Methods
 - getTotal - accumulates all values in the arraylist and returns it
 - addCoins - accepts a number of coins and adds each type into the frequency array and arraylist
 - 5 methods (getNickels, getDimes, getQuarters, getLoonies, getToonies) that retrieve a stored value in the frequency array
 - clear - re-initialises the arraylist and frequency array
 - removeCoin - tries to remove one type of coin and returns whether it was successful
 - removeValue - uses a greedy algorithm to determine if a value can be removed from the piggybank, and reports if the removal was successful

# Main Program
## Prominent Data Structures
 - 1 BufferedReader and StringTokenizer combination is used for input, as Scanner is unreliable when it comes to entering integers and lines
 - 1 Piggybank object is used to hold various coins

## Methods
 - 3 methods (next, nextInt, and nextDouble) are created to handle input from the BufferedReader
 - 1 method (showCoins) gets a list of all coins from the Piggybank and displays it nicely to the user
 - 1 method (removeCoins) asks for what coin a user wants removed then attempts to remove that coin from the Piggybank, displaying a message upon success or failure
 - 1 method (removeValue) asks for a dollar value from the user removes the values, if possible, using the built-in Piggybank method, reporting whether the removal was successful or not
 - 1 method (addCoins) accepts a parameter from main and asks the user how many of that coin they entered from main they wish to add, then adds them to the Piggybank
 - 1 method (main) which holds the main interactive menu and calls most methods


