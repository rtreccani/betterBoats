#Better boats
##this is a mod for minecraft using the fabric API. 
it will let you make a larger 4-6 person (undecided) ship.
The ship will have a double chest, maybe a crafting table, maybe a furnace. 
It should also be able to be moored using a lead?
This will be useful as a faster form of transport, or a mobile base, or a tool for
travelling with a group of friends or villagers.

Currently the boat model can be imported into the game, it floats, moves, and can be broken
to obtain an item. 
## Current issues:

- ~~Fix player position: currently, the player is sat in the middle of the boats bounding box.~~
- Fix bounding box: the bounding box is the wrong dimensions and not centred
- Fix boat offset
- ~~Fix player in boat offset~~ Fixed by rewritingthe updatePassengerPosition function to account for multiple people in different positions
- ~~Fix boat orientation. currently it moves backwards~~ Fixed by inverting on Z.
- Redo model(currently a bit small and lame)

Will add more to the todo once the basics are ironed out.