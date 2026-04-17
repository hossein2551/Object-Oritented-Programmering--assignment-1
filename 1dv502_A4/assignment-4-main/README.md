 Solar System Registry

This project is a console-based application where we can manage solar systems.  
It lets you create stars, add planets and moons, list them, delete them, and save everything to a file.

1. Create a new Solar System
We can create a solar system with only one central star.
Each star, planet, and moon must have a name and an average radius.
We can add multiple planets and moons to the system.

2. Radius Rules and Validation
These are the limits I decided for the :

Planet Radius:
Minimum = 1000  
Maximum = 70,000
Moon Radius:
Minimum = 500  
Maximum =10,000

Moon Orbit Radius:
Minimum = 100,000  
Maximum = 1,000,000,000  
A moon must orbit outside the planet's radius (it can’t be too close).

If we enter something outside these limits, the program throws an error (it won't crash though).

Orbiting Rules
Planets orbit stars, and moons orbit planets.
Both must have a valid average orbiting radius (as seen above).
The moon’s orbit must be further out than the planet’s size.

Main Menu
When you run the program, you get this menu:
1. Create new solar system
2. List all solar systems
3. Show solar system details
4. Add a planet to a solar system
5. Move a moon between planets
6. Delete a celestial body
7. Exit
8. Add a moon to a planet


3. Show Soalr System Details:
we can choose how to sort planets:
By size (largest radius first)
By orbital distance (closest first)
It shows a hierarchical structure:
Star at the top
Planets below
Each planet’s moons below that


6. Delete Functionality
If we delete a star, the whole solar system is deleted.
If we delete a planet, its moons are deleted too.
We can also delete individual moons.


4. Add planet or moon with rules
This was included under:
1. Create a new Solar System
Rules (radi, orbiting distance) documented in #2
Hierarchical navigation described in the menu


7. Quit Svae to File
Saving and loading from file
The application loads data from solarsystems.data when it starts.
It saves all changes automatically when we exit.


Error handling:
Basic error handling is included.
The application won't crash if we:
Type a letter where a number is expected
Enter invalid radius values
Refer to a non-existing solar system or planet