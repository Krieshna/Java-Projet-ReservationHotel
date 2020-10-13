package org.dme.utils;

import java.util.List;

import org.dme.entities.Reservation;

public class TempData {

    private static List<String> reservationClientSelectionData;
    private static String lastPageData;
    private static Reservation tempsReservationObjecteData;

    public static void setReservationClientSelectionData(List<String> prevReservationClientSelectionData) {
        reservationClientSelectionData = prevReservationClientSelectionData;
    }

    public static List<String> getReservationClientSelectionData() {
        return reservationClientSelectionData;
    }

	public static String getLastPageData() {
		return lastPageData;
	}

	public static void setLastPageData(String lastPage) {
		TempData.lastPageData = lastPage;
	}

	public static Reservation getTempsReservationObjecteData() {
		return tempsReservationObjecteData;
	}

	public static void setTempsReservationObjecteData(Reservation tempsReservationObjecteData) {
		TempData.tempsReservationObjecteData = tempsReservationObjecteData;
	}    
    
    
}
