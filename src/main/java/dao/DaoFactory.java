package dao;

import dessin.AbstractFactoryDao;
import dessin.Carre;
import dessin.Cercle;
import dessin.Rectangle;
import dessin.Triangle;

public class DaoFactory implements AbstractFactoryDao{

	@Override
	public Dao<Carre> createCarreDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<Triangle> createTriangleDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<Rectangle> createRectangleDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<Cercle> createCercleDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
