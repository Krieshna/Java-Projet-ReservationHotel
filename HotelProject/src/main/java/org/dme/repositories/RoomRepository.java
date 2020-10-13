package org.dme.repositories;


import org.dme.entities.Registration;
import org.dme.entities.Room;
import org.dme.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class RoomRepository {

    private static final Session session;

    static {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static void createRoom(Room r) {

        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            r.setActive(1);
            r.setOccupation(0);
            r.setFinaleBasePrice(50);
            session.save(r);

            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }
    }


    public static void updateRoomByNumber(int number) {
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Query<Room> query = session.createQuery("from Room where number =:number");
            query.setParameter("number",number);
            Room r = query.getSingleResult();

            session.update(r);

            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }
    }


    public static void assignRegistationToRoomByNumber(int roomNumber, int idRegistration) {
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Room r = session.load(Room.class, roomNumber);
            Registration registration = session.load(Registration.class, idRegistration);


            r.setRegistration(registration);

            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }
    }


    public static void removeRegistationFromRoomByNumber(int roomNumber) {
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Room r = session.load(Room.class, roomNumber);
            Registration registration = new Registration();

            registration = null;

            r.setRegistration(registration);

            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }

    }
    public static Room getRoomByNumber(int number) {
        Query<Room> query = session.createQuery("from Room where number =:number");
        query.setParameter("number",number);
        Room room = query.getSingleResult();
        return room;
    }

    public static List<Room> readRoom() {

        Query<Room> query = session.createQuery("from Room order by number asc");
        List<Room> rooms = query.list();

        return rooms;
    }

    public static List<Room> readRoomAvailable() {
        Query<Room> query = session.createQuery("from Room  where occupation=0 ");
        List<Room> roomsAvalaibles = query.list();

        return roomsAvalaibles;
    }

    public static List<Room> readRoomOccuped() {
        Query<Room> query = session.createQuery("from Room  where occupation>0 ");
        List<Room> roomsOccuped = query.list();

        return roomsOccuped;
    }

    public static List<Room> readRoomArchived(){
        Query<Room> query = session.createQuery("from Room  where active=0 ");
        List<Room> roomsArchived = query.list();

        return roomsArchived;
    }

    public static List<Room> readRoomunchived(){
        Query<Room> query = session.createQuery("from Room  where active=1 ");
        List<Room> roomsunrchived = query.list();

        return roomsunrchived;
    }

    public static void archivedRoom(int roomNumber){

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Room r = session.load(Room.class, roomNumber);

            r.setActive(0);


            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }
    }

    public static void unarchive(int roomNumber){
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Room r = session.load(Room.class, roomNumber);

            r.setActive(1);


            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }
    }

    public static void deleteRoomIfNotUsed(int number) {

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Query<Room> query = session.createQuery("from Room where number =:number");
            query.setParameter("number",number);
            Room room = query.getSingleResult();


            if(room.getOccupation() == 0){
                session.delete(room);
                tx.commit();
            }else {
                session.close();

            }


        } catch (HibernateException ex) {
            ex.printStackTrace();
            //Annulation de la transaction
            tx.rollback();
        }
    }
}
