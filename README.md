# Proceso con LS y GREP

Hecho por Sara Pérez

> El link del repositorio es: <https://github.com/SaraPF3/proceso_ls-grep>

En este código se ejecuta primero el comando ls, que muestra el contenido de la carpeta introducida en la constante RUTA y posteriormente se filtra el resultado mostrando solo los elementos que contengan una "a".

En mi archivo App.java hay:

- Método ejecutar:

```java
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
```

- Método main, en el que se introducen los comandos a través de las constantes:

```java
public static void main(String[] args) throws Exception {

        String resultadoLs;
        String resultadoGrep;

        resultadoLs = ejecutar(COMANDOLS, null);
        System.out.println(TEXTO_SALIDA_LS + N + resultadoLs + DECORACION + N);

        resultadoGrep = ejecutar(COMANDOGREP, resultadoLs);
        System.out.println(TEXTO_SALIDA_GREP + N + resultadoGrep + DECORACION);

    }
```
