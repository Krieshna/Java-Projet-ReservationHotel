package org.dme.repositories;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.dme.entities.Client;
import org.dme.entities.Reservation;
import org.dme.utils.HibernateUtil;
import org.hibernate.Session;

public class ClientRepository {
	private static final Session session;
	
	static {		
		session = HibernateUtil.getSessionFactory().openSession();
	}
		
	public static List<Client> getAllClient(){
		Query<Client> query = session.createQuery("from Client", Client.class);
		List<Client> clients = query.list();
		return clients;
	}
	
	public static List<Client> searchClient(String prenom, String nom, String mail){
		Query<Client> query = session.createQuery("from Client where firstname like :prenom "
				+ "									AND lastname like :nom "
				+ "									AND mail like :mail", Client.class);
		query.setParameter("prenom",prenom +"%");
		query.setParameter("nom",nom +"%");
		query.setParameter("mail",mail+"%");

		List<Client> clients = query.list();
		return clients;
	}
	
	public static List<Client> getAllClientAndIgnoreWhere (){
		Query<Client> query = session.createSQLQuery("select *  from client").addEntity("client", Client.class);
		List<Client> clients = query.list();
		return clients;
	}
	
	public static Client getClientById(int id) {
		return session.load(Client.class, id);
	}
		
	public static List<Client> getClientsByName(String name){
		Query<Client> query = session.createQuery("from Client where lastname='"+name+"'", Client.class);
		List<Client> clients = query.list();
		return clients;
	}

	public static List<Client> getClientsByAnniveraireYear(int year){
		Query<Client> query = session.createQuery("from Client where anniversaire LIKE  '"+year+"%'", Client.class);
		List<Client> clients = query.list();
		return clients;
	}
	
	public static List<Client> getClientsByMail(int mail){
		Query<Client> query = session.createQuery("from Client where mail='"+mail+"'", Client.class);
		List<Client> clients = query.list();
		return clients;
	}	
	
	public static void addClient(Client c) {
		Transaction tx = session.beginTransaction();
		session.save(c);
		tx.commit();
	}
	
	public static void updateClient(int id, Client nc) {
		Transaction tx = session.beginTransaction();
		Client c = session.load(Client.class, id);
		c.setAnniversaire(nc.getAnniversaire());
		c.setFirstname(nc.getFirstname());
		c.setLastname(nc.getLastname());
		c.setMail(nc.getMail());		
		session.update(c);
		tx.commit();
	}	
	
	public static void removeClient(int id) {
		Client c = session.load(Client.class, id);
		Transaction tx = session.beginTransaction();
		if(c.completedReservationNumber() > 0) {
			c.desactive();
			session.update(c);
			
		} else {	
			session.remove(c);
		}
		tx.commit();		
	}
	
	public static void printClients() throws FileNotFoundException {
		LocalDate date =  LocalDate.now();
		
		PrintWriter out;
		out = new PrintWriter( "Client le "+date+".csv" );
		out.write("Fiche client enregistrée le" +date +"\n" );
		out.write("Prenom \t nom \t mail \t anniversaire \t Reservation Complétée  \t Reservation en cours \t CA \t Rentrée d'argent prévu \t CA prévisionnelle  \n" );
		for(Client c : ClientRepository.getAllClient()) {
			out.write(c.getFirstname() + "\t"+ c.getLastname() + "\t"+ c.getMail()  + "\t"+ c.getAnniversaire() + "\t" + c.completedReservationNumber() + "\t" + c.ongoingReservationNumber()  + "\t"+ c.turnover()+ " \t"+ c.anticipatedIncome()+  " \t"+ c.projectedTurnover() +" \n" );
		};
		out.close();
	}
}
