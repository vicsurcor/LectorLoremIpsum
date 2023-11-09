import java.io.*;
import java.util.TreeMap;

public class LectorLoremIpsum {

    String recursos = "resources/Lorem";
    BufferedReader fEntrada;
    BufferedWriter fSalida;

    public static void main(String[] args) {


        LectorLoremIpsum lectorLoremIpsum = new LectorLoremIpsum();
        lectorLoremIpsum.Limpiar();
        lectorLoremIpsum.CrearDirectorioPrincipal();
        lectorLoremIpsum.CrearCarpetaParrafoLineas();


    }
    public void Limpiar(){

        File dPrincipal = new File("resources/resultado");
        if (dPrincipal.exists())
        {
           File[] parrafos = dPrincipal.listFiles();
            if (parrafos != null) {
                for (int i = 0; i < parrafos.length; i++)
                {
                    File parrafo = parrafos[i];
                    File[] txtArchivos = parrafo.listFiles();
                    if (txtArchivos != null) {
                        for (int f = 0; f < txtArchivos.length; f++)
                        {
                            txtArchivos[f].delete();
                        }
                    }
                    parrafo.delete();
                }
            }
            dPrincipal.delete();
        }
        else
        {

            System.out.println("No se puede borrar");

        }

    }

    public void CrearDirectorioPrincipal(){

        File dPrincipal = new File("resources/resultado");
        if (dPrincipal.exists()){


            System.out.println("El directorio ya existe");

        }
        else {

            System.out.println("DirectorioPrincipal creado " + dPrincipal.mkdirs());

        }


    }
    //Si no hay lineas vacias entre parrafos
    public void CrearCarpetaParrafo(){
        int nParrafos = 1;

        try {
            fEntrada = new BufferedReader(new FileReader(recursos));
            String parrafo = fEntrada.readLine();
            while (parrafo != null)
            {
                File file = new File("resources/resultado/parrafo-" + nParrafos);
                file.mkdir();


                parrafo = fEntrada.readLine();
                nParrafos++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            fEntrada.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    //Si los parrafos están separados por lineas enteras
    public void CrearCarpetaParrafoLineas(){
        int nParrafos = 1;
        int SaltosDeLinea = 0;

        try {
            fEntrada = new BufferedReader(new FileReader(recursos));
            String parrafo = fEntrada.readLine();
            while (parrafo != null)
            {

                if (nParrafos%2 != 0){
                    File file = new File("resources/resultado/parrafo-" + (nParrafos + SaltosDeLinea));
                    file.mkdir();
                    CrearArchivoTxT(file,fEntrada);
                    parrafo = fEntrada.readLine();
                }
                else {
                    SaltosDeLinea += -1;
                    parrafo = fEntrada.readLine();
                }
                nParrafos++;

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            fEntrada.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void CrearArchivoTxT(File file, BufferedReader bufferedReader){
        int npalabras = 0;
        try {
            String[] linea = bufferedReader.readLine().split(" ");
            TreeMap<String,Integer> archivos = new TreeMap<>();
            while (linea[npalabras] != null){
                if (!archivos.containsKey(linea[npalabras])){
                    archivos.put(linea[npalabras],1);
                    File ArchivoTxT = new File(file.getPath() + linea[npalabras]);
                }
                else {
                    archivos.put(linea[npalabras],archivos.get(linea[npalabras]) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
