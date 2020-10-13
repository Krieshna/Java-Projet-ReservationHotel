package org.dme.windows;

import org.dme.utils.HibernateUtil;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

        //Temporaire
        Session loading = HibernateUtil.getSessionFactory().openSession();
        loading.close();

        MenuGUI.load();
       
    }
}
