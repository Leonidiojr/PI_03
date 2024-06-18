package application;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe Utilitária
 * Autor: Leoni
 */
public class Utils {

    public static class DialogBoxes {

        /**
         * Método para exibir uma MessageBox com um input e uma mensagem de saudação
         *
         * @param strMessage Mensagem de input para o usuário
         * @param strTitle   Título da mensagem de saudação
         */
        public static void MessageBox(String strMessage, String strTitle) {
            if (strMessage != null) { // Verifica se a mensagem não é nula
                JOptionPane.showMessageDialog(null, strMessage, strTitle, JOptionPane.INFORMATION_MESSAGE);
            }
        }    
        
        public static String InputBox(String strMessage, String strTitle) {
            String nome;
            StringBuilder mensagem = new StringBuilder();

            nome = JOptionPane.showInputDialog(strMessage);
            if (nome != null) { // Verifica se o usuário não cancelou a entrada
                mensagem.append(strTitle).append(" ").append(nome).append("!");
                // Exibe a mensagem de saudação
                JOptionPane.showMessageDialog(null, mensagem.toString());
            }
            return nome; // Retorna o valor inserido pelo usuário
        
        }
         
    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(dateFormatter);
    }

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(timeFormatter);
    }
    
    public static LocalDateTime convertStringToDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }
    
    }        
}
