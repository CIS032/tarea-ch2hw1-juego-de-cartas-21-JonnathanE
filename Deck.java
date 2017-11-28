
package deber_programacion_2.pkg0;

/**
 *
 * @author Usuario
 */

/**
 * Un objeto de tipo Deck representa una baraja de cartas. La cubierta
 * es un mazo de póquer regular que contiene 52 cartas regulares y que puede
 * también opcionalmente incluye dos Jokers.
 */
public class Deck {
     /** 
     * Una matriz de 52 o 54 cartas. Un mazo de 54 cartas contiene dos Jokers, 
     * además de las 52 cartas de un mazo de póker regular. 
     */
    private Card[] deck;

    /** 
     * Realiza un seguimiento de la cantidad de cartas que se han repartido desde 
     * la baraja hasta el momento. 
     */ 
    private int cardsUsed;

    /** 
     * Construye una baraja de póker regular de 52 cartas. Inicialmente, las cartas
     * están en un orden ordenado. Se puede llamar al método shuffle () para 
     * aleatorizar el orden. (Tenga en cuenta que "nuevo Deck ()" es equivalente 
     * a "nuevo Deck (falso)".) 
     */
    public Deck() {
        this(false);  // Simplemente llama al otro constructor en esta clase.
    }

    /**
     * Construye un mazo de cartas de póquer, El mazo contiene 
     * las 52 cartas habituales y, opcionalmente, puede contener dos Comodines 
     * además de un total de 54 cartas. Inicialmente, las tarjetas 
     * están ordenadas. Se puede llamar al método shuffle () para 
     * aleatorizar el orden. 
     * @param includeJokers si es verdadero, dos comodines están incluidos en el mazo; si es falso,
     * no hay Jokers en el mazo.
     */
    public Deck(boolean includeJokers) {
        if (includeJokers)
            deck = new Card[54];
        else
            deck = new Card[52];
        int cardCt = 0; // Cuantas cartas se han creado hasta ahora.
        for ( int suit = 0; suit <= 3; suit++ ) {
            for ( int value = 1; value <= 13; value++ ) {
                deck[cardCt] = new Card(value,suit);
                cardCt++;
            }
        }
        if (includeJokers) {
            deck[52] = new Card(1,Card.JOKER);
            deck[53] = new Card(2,Card.JOKER);
        }
        cardsUsed = 0;
    }

    /**
     * Vuelva a colocar todas las tarjetas usadas en el mazo (si corresponde) y 
     * baraje el mazo en un orden aleatorio.
     */
    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }

    /**
     * A medida que las cartas se reparten desde el mazo, 
     disminuye el número de cartas que quedan 
     * Esta función devuelve la cantidad de tarjetas que
     * aún quedan en el mazo. El valor de retorno sería 
     * 52 o 54 (dependiendo de si el mazo incluye comodines) 
     * cuando se crea el mazo por primera vez o después de que el mazo ha sido 
     * barajado. Disminuye en 1 cada vez que 
     se llama al método dealCard ().
     */
    public int cardsLeft() {
        return deck.length - cardsUsed;
    }

    /**
     * Elimina la siguiente carta del mazo y la devuelve. Es ilegal 
     * llamar a este método si no hay más cartas en el mazo. Puede 
     * verificar el número de tarjetas restantes llamando a la función cardsLeft (). 
     * @return la carta que se retira de la baraja.
     * @throws IllegalStateException si no quedan cartas en el mazo 
     */
    public Card dealCard() {
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No quedan cartas en el baraja.");
        cardsUsed++;
        return deck[cardsUsed - 1];
        // Nota de programación: las tarjetas no se eliminan literalmente de la matriz 
        // que representa la plataforma. Solo hacemos un seguimiento de cuántas tarjetas 
        // se han usado.
    }

    /**
      * Comprobar si el mazo contiene comodines. 
     * @return true, si este es un mazo de 54 cartas que contiene dos comodines, o falso si 
     * este es un mazo de 52 cartas que no contiene comodines.
     */
    public boolean hasJokers() {
        return (deck.length == 54);
    }

} // end class Deck

