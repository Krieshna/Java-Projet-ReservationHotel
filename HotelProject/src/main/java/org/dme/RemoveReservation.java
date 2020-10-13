package org.dme;

import org.hibernate.Transaction;
import org.dme.entities.Client;
import org.dme.entities.Reservation;
import org.dme.utils.HibernateUtil;
import org.hibernate.Session;

public class RemoveReservation {
	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			
			Reservation r = session.load(Reservation.class, 3);
			
			System.out.println(r.getOccupation());
			//session.remove(r);
			tx.commit();
		}
	}
}
