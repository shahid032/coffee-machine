#Steps to Follow.
1. open terminal at this location
2. Run command "mvn clean install"
3. Run command "sh run.sh"


#Default Input
Coffee Machine is used prepare different bevarages.
First input is taken from the Json file stored at src/main.resources.
As soon as you run it will print the out which all beverages are prepared from the available ingredients.
Output format for success is : Beverage x is prepared with Ingredients ...
Modify the Json for different Output.

#Interactive Shell
Command can be used from terminals after default output.
1. Format => indicator 
	It will show all the ingredients and their quantity.
	
2.  Format => refill ingredient_name(case sensitive) quantitive
	E.g. => refill hot_milk 100
	It will refill the ingredient.
	
3. Format => prepare beverage_name ingredient_name(case sensitive) quantitive ingredient_name(case sensitive) quantitive ....
   E.g. prepare hot_milk 100 ginger_syrup 20 sugar_syrup 10 tea_leaves_syrup 20 hot_water 50
   It will print the prepared beverage or print exception can't prepared.
   
4. Format => exit
	It will shutdown the program.

