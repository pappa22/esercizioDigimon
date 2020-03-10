package it.dstech.digimon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GestioneDigimon {

	public static void main(String[] args) {

		try {
			Scanner input = new Scanner(System.in);
			List<Digimon> listaDigimon = new ArrayList<>();

			listaDigimon = caricaDigimon(input);

			while (true) {

				menu();
				System.out.println("cosa vuoi fare?");
				int scegli = input.nextInt();
				input.nextLine();

				switch (scegli) {
				case 1: {
					Digimon digi = creaDigimon(input);

					checkEvoluzione(digi, listaDigimon);
					
					System.out.println(digi);
					listaDigimon.add(digi);
					break;
				}
				case 2: {
					ordinaPerAttacco(listaDigimon);
					break;
				}
				case 3: {
					ordinaPerDifesa(listaDigimon);
					break;
				}
				case 4: {
					ordinaPerResistenza(listaDigimon);
					break;
				}
				case 5: {
					ordinaPerEvoluzione(listaDigimon);
					break;
				}
				case 6: {
					salvaDigimon(listaDigimon);
					break;
				}
				case 7: {
					System.exit(0);
					break;
				}
				case 8: {
					stampaListaDigimon(listaDigimon);
					break;
				}
				default:
					salvaDigimon(listaDigimon);
					input.close();
					System.exit(0);

				}

			}
		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void stampaListaDigimon(List<Digimon> listaDigimon) {

		for (Digimon digimon : listaDigimon) {
			System.out.println(digimon);
		}
	}

	private static void menu() {
		System.out.println("########################");
		System.out.println("1. crea Digimon");
		System.out.println("2. ordina Digimon per ATTACCO");
		System.out.println("3. ordina Digimon per DIFESA");
		System.out.println("4. ordina Digimon per RESISTENZA");
		System.out.println("5. ordina Digimon per EVOLUZIONE");
		System.out.println("6. Salva!");
		System.out.println("7. Esci");
		System.out.println("8. Stampa Lista Digimon");

	}

	private static void ordinaPerAttacco(List<Digimon> listaDigimon) {

		Collections.sort(listaDigimon, new Comparator<Digimon>() {

			@Override
			public int compare(Digimon o1, Digimon o2) {

				if (o1.getAtk() > o2.getAtk())
					return -1;
				if (o1.getAtk() < o2.getAtk())
					return 1;
				return 0;
			}

		});
		for (Digimon digimon : listaDigimon) {
			System.out.println(digimon);
		}

	}

	private static void ordinaPerDifesa(List<Digimon> listaDigimon) {

		Collections.sort(listaDigimon, new Comparator<Digimon>() {

			@Override
			public int compare(Digimon o1, Digimon o2) {

				if (o1.getDef() > o2.getDef())
					return -1;
				if (o1.getDef() < o2.getDef())
					return 1;
				return 0;
			}

		});
		for (Digimon digimon : listaDigimon) {
			System.out.println(digimon);
		}

	}

	private static void ordinaPerResistenza(List<Digimon> listaDigimon) {

		Collections.sort(listaDigimon, new Comparator<Digimon>() {

			@Override
			public int compare(Digimon o1, Digimon o2) {

				if (o1.getRes() > o2.getRes())
					return -1;
				if (o1.getRes() < o2.getRes())
					return 1;
				return 0;
			}

		});
		for (Digimon digimon : listaDigimon) {
			System.out.println(digimon);
		}

	}

	private static void ordinaPerEvoluzione(List<Digimon> listaDigimon) {

		Collections.sort(listaDigimon, new Comparator<Digimon>() {

			@Override

			public int compare(Digimon o1, Digimon o2) {

				if (o1.getEvol() == Evoluzione.ULTRA_DIGIEVOLUZIONE) {

					if (o2.getEvol() == Evoluzione.BASICA

							|| o2.getEvol() == Evoluzione.MEGA_DIGIEVOLUZIONE
							|| o2.getEvol() == Evoluzione.DIGIEVOLUZIONE) {

						return -1;

					}

				}

				if (o1.getEvol() == Evoluzione.MEGA_DIGIEVOLUZIONE) {

					if (o2.getEvol() == Evoluzione.BASICA || o2.getEvol() == Evoluzione.DIGIEVOLUZIONE) {

						return -1;

					}

					if (o2.getEvol() == Evoluzione.ULTRA_DIGIEVOLUZIONE) {

						return 1;

					}

				}

				if (o1.getEvol() == Evoluzione.DIGIEVOLUZIONE) {

					if (o2.getEvol() == Evoluzione.MEGA_DIGIEVOLUZIONE
							|| o2.getEvol() == Evoluzione.ULTRA_DIGIEVOLUZIONE) {

						return 1;

					}

					if (o2.getEvol() == Evoluzione.BASICA) {
						return -1;
					}

				}

				if (o1.getEvol() == Evoluzione.BASICA) {

					if (o2.getEvol() == Evoluzione.MEGA_DIGIEVOLUZIONE
							|| o2.getEvol() == Evoluzione.ULTRA_DIGIEVOLUZIONE
							|| o2.getEvol() == Evoluzione.DIGIEVOLUZIONE) {

						return 1;

					}

				}

				return 0;

			}

		});

		for (Digimon digimon : listaDigimon) {
			System.out.println(digimon);
		}

	}

	private static void salvaDigimon(List<Digimon> listaDigimon) throws IOException {
		File file = new File("listaDigimon.bu");
		if (!file.exists()) {

			file.createNewFile();

		}
		FileOutputStream out = new FileOutputStream(file);

		ObjectOutputStream stream = new ObjectOutputStream(out);

		stream.writeObject(listaDigimon);

		stream.close();
	}

	private static List<Digimon> caricaDigimon(Scanner input) throws IOException, ClassNotFoundException {

		File file = new File("listaDigimon.bu");
		if (!file.exists()) {

			file.createNewFile();

			return new ArrayList<>();

		}
		FileInputStream in = new FileInputStream(file);
		ObjectInputStream stream = new ObjectInputStream(in);
		List<Digimon> digimon = (List<Digimon>) stream.readObject();

		return digimon;
	}

	private static Digimon creaDigimon(Scanner input) {

		System.out.println("dammi il nome del tuo digimon");
		String nome = input.nextLine();
		System.out.println("dammi i punti attacco del tuo digimon");
		int atk = input.nextInt();
		input.nextLine();
		System.out.println("dammi i punti difesa del tuo digimon");
		int def = input.nextInt();
		input.nextLine();
		System.out.println("dammi i punti resistenza del tuo digimon");
		int res = input.nextInt();
		input.nextLine();
		System.out.println("dammi l'evoluzione del tuo digimon");

		System.out.println("1 BASICA");
		System.out.println("2 DIGIEVOLUZIONE");
		System.out.println("3 MEGA_DIGIEVOLUZIONE");
		System.out.println("4 ULTRA_DIGIEVOLUZIONE");
		int scegliEvoluzione = input.nextInt();
		input.nextLine();

		if (scegliEvoluzione == 1) {
			Evoluzione evoluzione = Evoluzione.BASICA;
			Digimon digimon = new Digimon(nome, atk, def, res, evoluzione);

			return digimon;
		}
		if (scegliEvoluzione == 2) {
			Evoluzione evoluzione = Evoluzione.DIGIEVOLUZIONE;
			Digimon digimon = new Digimon(nome, atk, def, res, evoluzione);

			return digimon;
		}
		if (scegliEvoluzione == 3) {
			Evoluzione evoluzione = Evoluzione.MEGA_DIGIEVOLUZIONE;
			Digimon digimon = new Digimon(nome, atk, def, res, evoluzione);

			return digimon;
		} else {
			Evoluzione evoluzione = Evoluzione.ULTRA_DIGIEVOLUZIONE;
			Digimon digimon = new Digimon(nome, atk, def, res, evoluzione);

			return digimon;
		}

	}

	public static void checkEvoluzione(Digimon digimon, List<Digimon> listaDigimon) {

		for (int i = 0; i < listaDigimon.size(); i++) {

			if (digimon.getNome().equals(listaDigimon.get(i).getNome())) {
				
				if (digimon.getEvol() == Evoluzione.BASICA) {
					digimon.setEvol(Evoluzione.DIGIEVOLUZIONE);
					break;
				}
				else if (digimon.getEvol() == Evoluzione.DIGIEVOLUZIONE) {
					digimon.setEvol(Evoluzione.MEGA_DIGIEVOLUZIONE);
					break;
				}
				else if (digimon.getEvol() == Evoluzione.MEGA_DIGIEVOLUZIONE) {
					digimon.setEvol(Evoluzione.ULTRA_DIGIEVOLUZIONE);
					break;
				}

				else if (digimon.getEvol() == Evoluzione.ULTRA_DIGIEVOLUZIONE) {

					digimon.setEvol(Evoluzione.BASICA);
					break;
				}

			}

		}
	}

}
