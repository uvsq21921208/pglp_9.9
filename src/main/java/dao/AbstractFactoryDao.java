package dao;

import dessin.Carre;
import dessin.Cercle;
import dessin.FormeGroupe;
import dessin.Rectangle;
import dessin.Triangle;

public interface AbstractFactoryDao {

  Dao<Carre> createCarreDao();

  Dao<Triangle> createTriangleDao();

  Dao<Rectangle> createRectangleDao();

  Dao<Cercle> createCercleDao();
  
  Dao<FormeGroupe> createGroupeDao();
}
