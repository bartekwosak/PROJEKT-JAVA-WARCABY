package warcaby.serwer;

/** Główna klasa aplikacji, która wywołuje konstruktory pozostałych klas, odpowiedzialna za uruchomienie rozgrywki po stronie Serwera. 
 *  Klasa ta zawiera w swoim konstruktorze wywołanie konstruktora klasy Plansza, która zawiera całą logikę aplikacji - ruchy, bicia itd.
 *  @see warcaby.serwer.Plansza
 *  @author Bartłomiej Osak, Tomasz Pasternak
 */
public class Warcaby {

	/** Konstruktor klasy Warcaby bezargumentowy, w którym wywoływany jest konstruktor klasy {@link warcaby.serwer.Plansza#Plansza()}.
	 * Dzięki temu możliwa jest rozgrywka, ponieważ w klasie Plansza zawarta jest cała logika aplikacji - ruchy, bicia itd,
	 * po stronie Serwera.
	 */
	public Warcaby() {
		new Plansza();
	}

	/** Metoda odpowiedzialna za wywołanie konstruktora klasy Warcaby ({@link warcaby.serwer.Warcaby#Warcaby()}). 
	 *  Dzięki wywołaniu konstruktora w tej metodzie możliwe jest prawidłowe uruchomienie gry poprzez jej kompilację 
	 *  lub bezpośrednio z poziomu archiwum .jar dla użytkownika Serwera.
	 * @param args - domyślna tablica Stringów w celu poprawnego wywołania metody statycznej main(). 
	 */
	public static void main(String args[]) {
		new Warcaby();
	}
}