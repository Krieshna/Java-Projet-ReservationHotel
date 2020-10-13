package org.dme;

import java.time.LocalDate;

import org.dme.entities.Client;
import org.dme.entities.Reservation;
import org.dme.repositories.ReservationRepository;
import org.dme.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class AddReservation {
	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();			
			Client c = session.load(Client.class, 2);
			
			Reservation r = new Reservation(c,2,LocalDate.of(2018, 9, 27), 126);
			
			
			session.save(r);
			tx.commit();
			
			System.out.println(ReservationRepository.getReservationByDate(LocalDate.of(2018, 9, 27)).size());
		}
	}
}
