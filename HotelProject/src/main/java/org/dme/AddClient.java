package org.dme;

import java.time.LocalDate;

import org.dme.entities.Client;
import org.dme.repositories.ClientRepository;
import org.dme.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddClient {
	public static void main(String[] args) {		
		Client c = new Client("Delobele","Emmeline",LocalDate.of(2000, 11,18),"edelobel@epsi.fr");
		ClientRepository.updateClient(5, c);;		
	}

}
