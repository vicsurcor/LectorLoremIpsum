import java.io.*;

public class LectorLoremIpsum {

    BufferedReader fEntrada;
    BufferedWriter fSalida;

    public static void main(String[] args) {


        LectorLoremIpsum lectorLoremIpsum = new LectorLoremIpsum();
        lectorLoremIpsum.CrearDirectorioPrincipal();
        lectorLoremIpsum.CrearCarpetaParrafo();


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
    public void CrearCarpetaParrafo(){
        int nParrafos = 1;

        try {
            fEntrada = new BufferedReader(new FileReader("resources/Lorem"));
            String parrafo = fEntrada.readLine();
            while (parrafo != null)
            {
                File file = new File("resources/resultado/parrafo" +nParrafos);
                file.mkdir();


                parrafo = fEntrada.readLine();
                nParrafos++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
