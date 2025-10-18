package es.etg.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class App {

    private static final String RUTA = "/etc";
    public static final String[] COMANDOGREP = {"grep", "a"};
    public static final String[] COMANDOLS = {"ls", RUTA};
    public static final String N = "\n";
    public static final String DECORACION = "----------------------------";
    public static final String TEXTO_SALIDA_LS = "Líneas que obtiene ls: " + N + DECORACION;
    public static final String TEXTO_SALIDA_GREP = "Líneas que tienen la letra 'a': " + N + DECORACION;

    public static void main(String[] args) throws Exception {

        String resultadoLs;
        String resultadoGrep;

        resultadoLs = ejecutar(COMANDOLS, null);
        System.out.println(TEXTO_SALIDA_LS + N + resultadoLs + DECORACION + N);

        resultadoGrep = ejecutar(COMANDOGREP, resultadoLs);
        System.out.println(TEXTO_SALIDA_GREP + N + resultadoGrep + DECORACION);

    }

    public static String ejecutar(String[] comando, String texto) throws IOException, Exception {

        Process p = Runtime.getRuntime().exec(comando);
        OutputStream out = p.getOutputStream();
        StringBuilder output = new StringBuilder();

        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(out))) {
            pw.println(texto);
            pw.close();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(N);
            }
            reader.close();
        }

        return output.toString();
    }
}
