import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;


class LeerFichero {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    public LeerFichero(String ruta){
        try {
            archivo = new File (ruta));
            /*
            BufferedReader: lee texto de una entrada de caracteres.
            FileReader: para leer archivos.
            */
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea=br.readLine())!=null) 
                System.out.println(linea);

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
    }
    private int obtenerEstadoInicial(String linea){
        return 0;  
    }
}