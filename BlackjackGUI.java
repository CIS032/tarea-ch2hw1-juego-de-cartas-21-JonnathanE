package deber_programacion_2.pkg0;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class BlackjackGUI {
      public static void main(String[] args) {

        //Scanner scan = new Scanner(System.in);

        int money;          // Cantidad de dinero que tiene el usuario.
        int bet;            // Cantidad de apuestas de usuario en un juego.
        boolean userWins;   // ¿El usuario ganó el juego?

//        System.out.println("Bienvenido al juego de blackjack");
//        System.out.println();
          JOptionPane.showMessageDialog(null, "Bienvenido al juego de blackjack");

        money = 100;  // El usuario comienza con $ 100.

        while (true) {
//            System.out.println("Tiene " + money + " dólares.");
             JOptionPane.showMessageDialog(null, "Tiene " + money + " dólares.");
            do {
//                System.out.println("¿Cuántos dólares quieres apostar?  (Ingresa 0 para finalizar.)");
//                System.out.print("? ");
//                bet = scan.nextInt();//TextIO.getlnInt();
                bet = Integer.parseInt(JOptionPane.showInputDialog(null,"¿Cuántos dólares quieres apostar?  (Ingresa 0 para finalizar.)" ));
                if (bet < 0 || bet > money) {
//                    System.out.println("Su respuesta debe estar entre 0 y " + money + '.');
                    JOptionPane.showMessageDialog(null, "Su respuesta debe estar entre 0 y " + money + '.');
                }
            } while (bet < 0 || bet > money);
            if (bet == 0) {
                break;
            }
            userWins = playBlackjack();
            if (userWins) {
                money = money + bet;
            } else {
                money = money - bet;
            }
//            System.out.println();
            if (money == 0) {
//                System.out.println("Parece que te has quedado sin dinero!");
                JOptionPane.showMessageDialog(null, "Parece que te has quedado sin dinero!");
                break;
            }
        }

//        System.out.println();
//        System.out.println("Sales con $ " + money + '.');
        JOptionPane.showMessageDialog(null, "Sales con $ " + money + '.');

    } // end main()

    /**
     * Permita que el usuario juegue un juego de Blackjack, con la computadora como distribuidor.
     *
     * @return true si el usuario gana el juego, falso si el usuario pierde.
     */
    static boolean playBlackjack() {

//        Scanner scan = new Scanner(System.in);

        Deck deck;                  // Una baraja de cartas. Una nueva baraja para cada juego.
        BlackjackMano dealerHand;   // La mano del crupier.
        BlackjackMano userHand;     // La mano del usuario.

        deck = new Deck();
        dealerHand = new BlackjackMano();
        userHand = new BlackjackMano();

        /*  Baraja la baraja, reparte dos cartas a cada jugador. */
        deck.shuffle();
        dealerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());

//        System.out.println();
//        System.out.println();

        /* Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21).
         El jugador con Blackjack gana el juego. El distribuidor gana lazos.
         */
        if (dealerHand.getBlackjackValue() == 21) {
//            System.out.println("El distribuidor tiene la " + dealerHand.getCard(0)
//                    + " and the " + dealerHand.getCard(1) + ".");
//            System.out.println("El usuario tiene la " + userHand.getCard(0)
//                    + " and the " + userHand.getCard(1) + ".");
//            System.out.println();
//            System.out.println("El distribuidor tiene Blackjack. El distribuidor gana.");
            JOptionPane.showMessageDialog(null, "El distribuidor tiene la " + dealerHand.getCard(0)
                    + " and the " + dealerHand.getCard(1) + "." 
                    + "\n"
                    + "El usuario tiene la " + userHand.getCard(0)
                    + " and the " + userHand.getCard(1) + "."
                    + "\n"
                    + "El distribuidor tiene Blackjack. El distribuidor gana.");
            return false;
        }

        if (userHand.getBlackjackValue() == 21) {
//            System.out.println("El distribuidor tiene la " + dealerHand.getCard(0)
//                    + " and the " + dealerHand.getCard(1) + ".");
//            System.out.println("El usuario tiene la " + userHand.getCard(0)
//                    + " and the " + userHand.getCard(1) + ".");
//            System.out.println();
//            System.out.println("Tienes Blackjack. Tú ganas.");
            JOptionPane.showMessageDialog(null, "El distribuidor tiene la " + dealerHand.getCard(0)
                    + " and the " + dealerHand.getCard(1) + "."
                    + "\n"
                    + "El usuario tiene la " + userHand.getCard(0)
                    + " and the " + userHand.getCard(1) + "."
                    + "\n"
                    + "Tienes Blackjack. Tú ganas.");
            return true;
        }

        /*  Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero el usuario 
          tiene la oportunidad de robar cartas (es decir, de "golpear/Hit"). El bucle while termina
          cuando el usuario elige "Stand". Si el usuario supera los 21,
          el usuario pierde inmediatamente.
         */
        while (true) {

            /*  Mostrar cartas de usuario, y dejar que el usuario decida golpear(Hit) o pararse(Stand). */
//            System.out.println();
//            System.out.println();
//            System.out.println("Tus cartas son:");
            String out = "";
            for (int i = 0; i < userHand.getCardCount(); i++) {
//                System.out.println("    " + userHand.getCard(i));
                out += "\n    " + userHand.getCard(i);
            }
//            System.out.println("Su total es " + userHand.getBlackjackValue());
//            System.out.println();
//            System.out.println("El concesionario muestra el " + dealerHand.getCard(0));
//            System.out.println();
//            System.out.print("Hit (H) or Stand (S)? ");
//              char userAction;  // Respuesta del usuario, 'H' o 'S'.
            String userAction;
            do {
//                userAction = Character.toUpperCase(scan.next().charAt(0));//TextIO.getlnChar()
                userAction = JOptionPane.showInputDialog(null,"Tus cartas son:\n" + out 
                + "\nSu total es " + userHand.getBlackjackValue()
                + "\nEl concesionario muestra el " + dealerHand.getCard(0)
                + "\nHit (H) or Stand (S)? ");
                if (!userAction.equalsIgnoreCase("H") && !userAction.equalsIgnoreCase("S")) {
//                    System.out.print("Por favor Responda H o S:  ");
                    JOptionPane.showMessageDialog(null, "Por favor Responda H o S:  ");
                }
            } while (!userAction.equalsIgnoreCase("H") && !userAction.equalsIgnoreCase("S"));

            /* If the user Hits, el usuario recibe una tarjeta.  If the user Stands,
              el bucle termina (y es el turno del crupier de robar cartas).
             */
            if (userAction.equalsIgnoreCase("S")) {
                // Loop ends; el usuario ha terminado de tomar cartas.
                break;
            } else {  // userAction is 'H'.  Dale al usuario una tarjeta.  
                //  Si el usuario supera los 21, el usuario pierde.
                Card newCard = deck.dealCard();
                userHand.addCard(newCard);
//                System.out.println();
//                System.out.println("El usuario acierta");
//                System.out.println("Su carta es la " + newCard);
//                System.out.println("Su total es ahora " + userHand.getBlackjackValue());
                JOptionPane.showMessageDialog(null, "El usuario acierta\n"
                + "Su carta es la " + newCard + "\n"
                + "Su total es ahora " + userHand.getBlackjackValue());
                if (userHand.getBlackjackValue() > 21) {
//                    System.out.println();
//                    System.out.println("Has fallado al pasar de 21. Pierdes.");
//                    System.out.println("Otra carta del distribuidor fue el "
//                            + dealerHand.getCard(1));
                    
                    JOptionPane.showMessageDialog(null, "Has fallado al pasar de 21. Pierdes."
                    + "\nOtra carta del distribuidor fue el "
                            + dealerHand.getCard(1));
                    return false;
                }
            }

        } // end while loop

        /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora es
         la oportunidad del distribuidor para dibujar. El distribuidor roba cartas hasta que el crupier
         el total es> 16. Si el crupier supera los 21, el crupier pierde.
         */
//        System.out.println();
//        System.out.println("Usuario parado.");
//        System.out.println("Las cartas del distribuidor son");
//        System.out.println("    " + dealerHand.getCard(0));
//        System.out.println("    " + dealerHand.getCard(1));
        JOptionPane.showMessageDialog(null, "Usuario parado."
        + "\nLas cartas del distribuidor son"
        + "\n    " + dealerHand.getCard(0)
        + "\n    " + dealerHand.getCard(1));
        while (dealerHand.getBlackjackValue() <= 16) {
            Card newCard = deck.dealCard();
//            System.out.println("El distribuidor hits y obtiene la " + newCard);
            JOptionPane.showMessageDialog(null, "El distribuidor hits y obtiene la " + newCard);
            dealerHand.addCard(newCard);
            if (dealerHand.getBlackjackValue() > 21) {
//                System.out.println();
//                System.out.println("Distribuidor detenido por pasar de 21. Usted gana..");
                JOptionPane.showMessageDialog(null, "Distribuidor detenido por pasar de 21. Usted gana..");
                return true;
            }
        }
//        System.out.println("El total del distribuidor es " + dealerHand.getBlackjackValue());
        JOptionPane.showMessageDialog(null, "El total del distribuidor es " + dealerHand.getBlackjackValue());

        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos. */
//        System.out.println();
        if (dealerHand.getBlackjackValue() == userHand.getBlackjackValue()) {
//            System.out.println("El distribuidor gana en un empate. Pierdes");
            JOptionPane.showMessageDialog(null, "El distribuidor gana en un empate. Pierdes");
            return false;
        } else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
//            System.out.println("Dealer wins, " + dealerHand.getBlackjackValue()
//                    + " points to " + userHand.getBlackjackValue() + ".");
            JOptionPane.showMessageDialog(null, "Dealer wins, " + dealerHand.getBlackjackValue()
                    + " points to " + userHand.getBlackjackValue() + ".");
            return false;
        } else {
//            System.out.println("You win, " + userHand.getBlackjackValue()
//                    + " points to " + dealerHand.getBlackjackValue() + ".");
            JOptionPane.showMessageDialog(null, "You win, " + userHand.getBlackjackValue()
                    + " points to " + dealerHand.getBlackjackValue() + ".");
            return true;
        }

    } 
}
