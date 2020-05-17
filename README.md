
# Drawing APP


### How to use:

#### Creations commands: 

`` cer = cercle(1,(1,1)) or simply cer cercle 1 1 1``
- This creates a cercle with rayon of 1 and 1,1 as center.

`` car  = carre(1,(1,1)) or simply car carre 1 1 1``
- This creates a carre with cote of 1 and on position 1,1.

`` tri = triangle((1,1),(0,0),(4,4))  or simply tri 1 1 0 0 4 4``
- This creates a triangle with 3 points (1,1), (0,0), (4,4).

`` rec = rectangle((1,2),(5,5)) or simply rec rectangle 1 2 5 5``
- This creates a rectangle with h: 5, w : 5 on position 1,2.

#### Deletion commands:
- Type delete forme to delete one forme.
   For example:\
   ``delete c1``
   - this one deletes c1.
- To delete everything, type deleteall.\
   ``deleteall``


#### Movement commands: 
- Type move forme new coordinates to move one forme.
For example :
``move(c1,(1,2)) `` or simply ``move c1 1 2``
- To move many formes at one time, types moveall(f1,f2..., coordinates) or simply moveall f1 f2... cooridnates\
For example :\
``moveall c1 c2 5,5``
#### Show commands:
- Type show forme new coordinates to show one forme.\
For example:\
``show c1``
- Type showall to show all formes.\
``showall``
#### Save/Load commands:
- Type save name to save the dessin.\
``save example1``
   - will erase existing dessin if it already exists.
- Type load name to load the dessin.\
``load example1``

