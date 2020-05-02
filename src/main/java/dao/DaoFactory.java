package dao;

import dessin.Carre;
import dessin.Cercle;
import dessin.FormeGroupe;
import dessin.Rectangle;
import dessin.Triangle;

public class DaoFactory implements AbstractFactoryDao{

	@Override
	public Dao<Carre> createCarreDao() {
		// TODO Auto-generated method stub
		return new CarreDao();
	}

	@Override
	public Dao<Triangle> createTriangleDao() {
		// TODO Auto-generated method stub
		return new TriangleDao();
	}

	@Override
	public Dao<Rectangle> createRectangleDao() {
		// TODO Auto-generated method stub
		return new RectangleDao();
	}

	@Override
	public Dao<Cercle> createCercleDao() {
		// TODO Auto-generated method stub
		return new CercleDao();
	}

	@Override
	public Dao<FormeGroupe> createGroupeDao() {
		// TODO Auto-generated method stub
		return new GroupeDao();
	}

}
