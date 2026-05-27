
package Negocio;
import Datos.DCondIVA;
import Modelos.CondIVA;
import java.util.List;


public class NCondIVA {
    private DCondIVA ci = new DCondIVA();
    
    
    public List<CondIVA> getLista(){
        return ci.Listar();
    }
}
