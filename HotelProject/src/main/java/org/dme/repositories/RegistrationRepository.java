package org.dme.repositories;


import org.dme.entities.Registration;
import org.dme.entities.Reservation;
import org.dme.entities.Room;
import org.dme.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class RegistrationRepository {
    private static final Session session;

    static {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static void CreateRegistration(int idReservation, int idRoom) {

        Transaction tx = null;

        try  {

            tx = session.beginTransaction();

            Reservation reservation = session.load(Reservation.class, idReservation);

            Room room = session.load(Room.class, idRoom);

            Registration r = new Registration(reservation, room);


            session.save(r);

            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }finally {
            session.close();
        }
    }


    public static void UpdateRegistrationById(int idRegistration, int idReservation, int idRoom) {
        Transaction tx = null;

        try  {

            tx = session.beginTransaction();

            Registration r = session.load(Registration.class, idRegistration);
            Reservation reservation = session.load(Reservation.class, idReservation);
            Room room = session.load(Room.class, idRoom);


            r.setReservation(reservation);
            r.setRoom(room);

            tx.commit();

            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }finally {
            session.close();
        }
    }

    public  static  List<Registration>ReadRegistration(){

        Query<Registration> query = session.createQuery("from Registration ",Registration.class);
        List<Registration> registrations = query.list();

        return registrations;
    }

    public  static void deleteRegistretion(int idRegistration){

        Transaction tx = null;

        try  {

            tx = session.beginTransaction();

            Registration registration = session.load(Registration.class,idRegistration);

            session.delete(registration);

            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }finally {
            session.close();
        }
    }
}

