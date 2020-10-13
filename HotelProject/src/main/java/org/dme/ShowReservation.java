package org.dme;

import java.time.LocalDate;
import java.util.List;

import org.dme.entities.Client;
import org.dme.entities.Reservation;
import org.dme.repositories.ClientRepository;
import org.dme.repositories.ReservationRepository;
import org.dme.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ShowReservation {

	public static void main(String[] args) {	
			
		
		Reservation r = ReservationRepository.getReservationById(13);
		r.setOccupation(120);
					
		ReservationRepository.updateReservation(3, r);
			System.out.println("Reservation effectuée  le " + r.getUpdated() + " pour " + r.getOccupation() +" personne(s) dans la nuit " + r.getDate() +" au nom de " + r.getClient().getFirstname() +" " + r.getClient().getLastname());
	
		
		
		
		//System.out.println(ReservationRepository.getReservationById(3).getNumber());
			
		}

}
