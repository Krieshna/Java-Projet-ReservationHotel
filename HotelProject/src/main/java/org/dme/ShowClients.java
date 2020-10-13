package org.dme;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.dme.entities.Client;
import org.dme.entities.Reservation;
import org.dme.repositories.ClientRepository;
import org.dme.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ShowClients {

	public static void main(String[] args) throws FileNotFoundException {			
		
	
		
	Client client = ClientRepository.getClientById(1);		
			
	client.setFirstname("bidule");

		
		}
	
}
