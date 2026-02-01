import controllers.MainController;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        
        MainController controller = new MainController();
        new MainFrame(controller);

        
        System.out.println("Proyecto Final - Estructura de Datos");
        System.out.println("Dayanna Chacha - Valeria Guamán");
        System.out.println("Sistema cargado correctamente");
    }
}
