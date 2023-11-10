import java.io.*;
import java.util.TreeMap;

public class LectorLoremIpsum {

    String recursos = "resources/Lorem";
    BufferedReader fEntrada;
    BufferedWriter fSalida;


    public void limpiar(){

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
    public void crearDirectorioPrincipal(){

        File dPrincipal = new File("resources/resultado");
        if (dPrincipal.exists()){


            System.out.println("El directorio ya existe");

        }
        else {

            System.out.println("DirectorioPrincipal creado " + dPrincipal.mkdirs());

        }


    }
    //Si no hay lineas vacias entre parrafos
    public void crearCarpetaParrafo(){
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
    public void crearCarpetaParrafoLineas(){
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
                    crearArchivoTxT(file,nParrafos);
                }
                else {
                    SaltosDeLinea += -1;
                }
                parrafo = fEntrada.readLine();
                nParrafos++;
            }
            try {
                fEntrada.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
    public void crearArchivoTxT(File file,int nParrafos){
        int npalabras = 0;
        int parrafo = 0;
        try {
            BufferedReader fIn = new BufferedReader(new FileReader(recursos));
            while((nParrafos - 1) != parrafo){
                fIn.readLine();
                parrafo++;
            }
            String[] linea = fIn.readLine().split(" ");
            TreeMap<String,Integer> archivos = new TreeMap<>();
            while (linea[npalabras] != null && npalabras < (linea.length) - 1) {

                    if (linea[npalabras].contains(",")) {
                        linea[npalabras] = linea[npalabras].substring(0, linea[npalabras].indexOf(","));
                    }
                    if (!archivos.containsKey(linea[npalabras].toLowerCase())) {
                        archivos.put(linea[npalabras].toLowerCase(), 1);
                        File archivoTxT = new File(file.getPath() + "/" + linea[npalabras].toLowerCase());
                        archivoTxT.createNewFile();
                        fSalida = new BufferedWriter(new FileWriter(archivoTxT));
                        fSalida.write(String.valueOf(archivos.get(linea[npalabras].toLowerCase())));
                    } else {
                        File archivoTxT = new File(file.getPath() + "/" + linea[npalabras].toLowerCase());
                        archivos.put(linea[npalabras].toLowerCase(), archivos.get(linea[npalabras].toLowerCase()) + 1);
                        fSalida = new BufferedWriter(new FileWriter(archivoTxT));
                        fSalida.write(String.valueOf(archivos.get(linea[npalabras].toLowerCase())));
                    }
                    npalabras++;
                    fSalida.close();
            }
            fIn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
