package dessin;

import dao.Dao;

public interface AbstractFactoryDao {

  Dao<Carre> createCarreDao();

  Dao<Triangle> createTriangleDao();

  Dao<Rectangle> createRectangleDao();
  Dao<Cercle> createCercleDao();
}
