package dao;

import dessin.Carre;
import dessin.Cercle;
import dessin.FormeGroupe;
import dessin.Rectangle;
import dessin.Triangle;

public class DaoFactory implements AbstractFactoryDao {

  @Override
  public Dao<Carre> createCarreDao() {
    return new CarreDao();
  }

  @Override
  public Dao<Triangle> createTriangleDao() {
    return new TriangleDao();
  }

  @Override
  public Dao<Rectangle> createRectangleDao() {
    return new RectangleDao();
  }

  @Override
  public Dao<Cercle> createCercleDao() {
    return new CercleDao();
  }

  @Override
  public Dao<FormeGroupe> createGroupeDao() {
    return new GroupeDao();
  }

}
