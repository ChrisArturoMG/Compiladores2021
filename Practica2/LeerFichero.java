import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;


class LeerFichero {

    //private List<Transicion> transiciones;
    
    private int estadoInicial;
    private List<Integer> estadosFinales;
    private List<Transicion> transiciones;
    
    private static String PALABRA_INICIAL = "inicial";
    private static String PALABRA_FINALES = "finales";
        
    private static String SIMBOLO_SEPARADOR= ","; 
    private static String SIMBOLO_ASIGNACION = "=";
    private static String SIMBOLO_TRANSICION = "->";

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    public LeerFichero(String ruta){
        String linea;
        List <String> lineas= new ArrayList<>();

        try {
            archivo = new File (ruta);
            /*
            BufferedReader: lee texto de una entrada de caracteres.
            FileReader: para leer archivos.
            */
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);


            while((linea=br.readLine())!=null) {
                lineas.add(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        finally{
            try{                    
                if( null != fr ){   
                   fr.close();     
                }                  
             }catch (Exception e2){ 
                e2.printStackTrace();
             }
        }

        System.out.println(this.guardarEstadoInicial(lineas.get(0)));
        System.out.println(this.guardarEstadosFinales(lineas.get(1)));
        System.out.println(this.guardarTransiciones(lineas.subList(2, lineas.size())));

    }
    public int guardarEstadoInicial(String linea){
        String parametros[] = linea.split(SIMBOLO_ASIGNACION);
        int estadoInicial = -1;   // Es menos uno porque no es ningun estado
        try{
            estadoInicial = Integer.parseInt(parametros[1]);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return estadoInicial;  
    }

    public List<Integer> guardarEstadosFinales(String linea){
        
        String valores_asignados[] = linea.split(SIMBOLO_ASIGNACION);
        String estados_finales[] = valores_asignados[1].split(SIMBOLO_SEPARADOR);
        List<Integer> lista_finales = new ArrayList<>();

        try{
            for (int i = 0; i < estados_finales.length; i++) {
                lista_finales.add(Integer.parseInt(estados_finales[i]));
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return lista_finales;  
    }


    public List<Transicion> guardarTransiciones(List<String> lineas) {
       
        List<Transicion> transicion = new ArrayList<>();
        String[] parametros, parametros2;
        int estado_inicial_transicion, estado_siguiente_transicion ;
        char simbolo_transicion;
        
        for (int i = 0; i < lineas.size(); i++) {
            parametros = lineas.get(i).split(SIMBOLO_TRANSICION);
            parametros2 = parametros[1].split(SIMBOLO_SEPARADOR);
            
            try{
                estado_inicial_transicion = Integer.parseInt(parametros[0]);
                estado_siguiente_transicion = Integer.parseInt(parametros2[0]);
                simbolo_transicion = parametros2[1].charAt(0); 
                transicion.add(new Transicion(estado_inicial_transicion, estado_siguiente_transicion, simbolo_transicion));
            }catch(NumberFormatException e){
                e.printStackTrace();
                System.exit(0);
            }
        }
        return transicion;
    }

    public List<Transicion> obtenerTransiciones() {
        return transiciones;
    }

    public int obtenerEstadoInicial() {
        return estadoInicial;
    }
    public List<Integer> obtenerEstadosFinales() {
        return estadosFinales;
    }


}