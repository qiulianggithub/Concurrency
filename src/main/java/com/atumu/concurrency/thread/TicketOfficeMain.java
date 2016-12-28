package com.atumu.concurrency.thread;

import com.atumu.entity.Cinema;

public class TicketOfficeMain {

	public static void main(String[] args) {
		Cinema cinema = new Cinema();

		TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
		Thread thread11 = new Thread(ticketOffice1, "TicketOffice1");
		Thread thread12 = new Thread(ticketOffice1, "TicketOffice1");

		TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
		Thread thread21 = new Thread(ticketOffice2, "TicketOffice2");
		Thread thread22 = new Thread(ticketOffice2, "TicketOffice2");

		thread11.start();
		thread12.start();
		thread21.start();
		thread22.start();

		try {
            thread11.join();
            thread12.join();
            thread21.join();
            thread22.join();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Room 1 Vacancies: %d\n",
				cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n",
				cinema.getVacanciesCinema2());
	}
}
