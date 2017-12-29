package com.namalliv.software.aprendisaje.model;


public class Contenido {
    private String preguntaContenido;
    private int punto;
    private String textPregunta;
    private String pregunta1;
    private String pregunta2;
    private String pregunta3;
    private String pregunta4;
    private String correcta;

    private String[] mQuestions = {
            "¿Qué imprime este bloque de código, si h = \"10\"?",
            "¿Cual es el tipo de data apropiado para este valor 70.7?",
            "¿Cuál es la firma apropiada para escribir el método main?",
            "Un objeto puede ser…",
            "¿Cuál no es una palabra reservada por Java?",
            "¿Qué pasa en este bloque de código?",
            "Los métodos compuestos por dos o más palabras se identifican ...",
            "El último elemento de un arreglo identificada por el nombre cosas se obtiene ...",
            "¿Qué pasa en este bloque de código?",
            "¿Qué puedes ver en este bloque de código?",
            "Imprime el número 90 desde el siguiente arreglo.",
            "¿Qué se imprime en este bloque de código?",
            "JVM son las iniciales para …",
            "¿Cuál de estos circuitos se ejecuta por lo menos una vez de seguro?",
            "¿Qué inicializa un objeto?",
            "¿Cuál de estas declaraciones de ArrayList es la más apropiada?",
            "¿Qué no es verdad en cuanto al bloque \"finally\"?",
            "El código de java se compila en …",
            "Las siglas JDK en su forma completa es …",
            "¿Cómo se usa super() con constructores?",
            "¿Qué se produce cuando usas este bloque de código?",
            "Dada la declaración: int[] arreglo = {1,2,3,4,5}; ¿Cuál es el valor de arreglo[3]?"
    };

    private String[][] mOpciones = {
            {"Error, no puedes sumar palabras a números.",
                    "No imprime nada porque es una suma de palabra y número.",
                    "15",
                    "105",
            },

            {"double",
                    "int",
                    "C String",
                    "float",
            },

            {"public static String main(String[] args)",
                    "public static void main (String[] args)",
                    "public static void main (string[] args)",
                    "public static Void main(String[] args)",
            },

            {"una clase",
                    "un programa java",
                    "cualquier cosa",
                    "todas las anteriores",
            },
            {"New",
                    "String",
                    "final",
                    "protected",
            },
            {"Se imprime la suma de los enteros en muchas líneas.",
                    "Se produce un error de compilación marcado porque no tiene retorno.",
                    "No se produce nada porque el código necesita un argumento.",
                    "Todas las repuestas son correctas.",
            },
            {"Con letras mayúsculas por cada palabra que forman.",
                    "Se les pone un espacio en blanco para dividir las palabras.",
                    "Hacer una sola palabra que tenga la primera escrita en minúscula y cada vez que sea el inicio de una palabra, se usa mayúsculas.",
                    "No se pueden identificar.",
            },
            {"cosas[longitud]",
                    "cosas[longitud - 1]",
                    "cosas[longitud + 1]",
                    "cosas[longitud] - 1;",
            },
            {"No hay problema con este bloque si la clase Persona existe.",
                    "Error de compilación porque la variable dentro del método tiene el mismo identificador que el método.",
                    "No puedes asignar estudiante1 a estudiante porque estudiante1 no existe.",
                    "El retorno no está en la línea correcta. ",
            },
            {"Polimorfismo.",
                    "Overload método.",
                    "Override método.",
                    "Un error de compilador.",
            },
            {"System.out.println(numeros[7]);",
                    "System.out.println(numeros[90]);",
                    "System.out.println(numeros[numeros.lengh/2]);",
                    "System.out.println(numeros[i]);",
            },
            {"Arroz es un trigo",
                    "Arroz no es un trigo",
                    "Error. No puedes usar new String()",
                    "No se imprime nada",
            },
            {"Java Virus Malware",
                    "Java Jeneric Machine",
                    "Job Virtual Machinery",
                    "Java Virtual Machine",
            },
            {"while",
                    "do-while",
                    "for",
                    "ninguno",
            },
            {" El constructor",
                    " La palabra new",
                    " Nombre de la clase",
                    " La declaración de una clase",
            },
            {"List<> miArrayLista = new ArrayList<>();",
                    "List miArrayLista = new ArrayList();",
                    "List<String> miArrayLista = new ArrayList<String>();",
                    "List<> miArrayLista = new arrayList<String>();",
            },
            {"Finally se ejecuta siempre después del bloque try.",
                    "Finally se ejecuta después del retorno.",
                    "Finally puede ser ejecutado más de una vez.",
                    "Finallay no se ejecuta si la aplicación sale antes de lo esperado.",
            },
            {".exe",
                    ".bytecode",
                    ".obj",
                    "Código fuente",
            },
            {"Java Development Kit",
                    "Java Data Keeper",
                    "Java Design Keys",
                    "Java Definition Kit",
            },
            {"super() invoca el constructor de cualquier clase en el paquete y fuera del paquete.",
                    "super() invoca el constructor de la misma clase.",
                    "super() invoca el constructor de la clase extendida.",
                    "super() invoca el constructor de una interfaz.",
            },
            {"NEGATIVO AFIRMATIVO",
                    "Error porque no tiene argumento",
                    "Imprime el tiempo y el lugar donde se encuentra el arreglo",
                    "No se produce nada.",
            },
            {"5", "2", "4", "3"}
    };

    private String respuetaCorrecta[] = {
            "105",
            "double",
            "public static void main (String[] args)",
            "cualquier cosa",
            "New",
            "Se produce un error de compilación marcado porque no tiene retorno.",
            "Hacer una sola palabra que tenga la primera escrita en minúscula y cada vez que sea el inicio de una palabra, se usa mayúsculas.",
            "cosas[longitud - 1]",
            "No hay problema con este bloque si la clase Persona existe.",
            "Un error de compilador.",
            "System.out.println(numeros[numeros.lengh/2]);",
            "Arroz no es un trigo",
            "Java Virtual Machine",
            "do-while",
            "El constructor",
            "List<String> miArrayLista = new ArrayList<String>();",
            "Finally puede ser ejecutado más de una vez.",
            ".bytecode",
            "Java Development Kit",
            "super() invoca el constructor de la clase extendida.",
            "Imprime el tiempo y el lugar donde se encuentra el arreglo",
            "4",

    };

    public Contenido() {
    }

    public int cantidadDePreguntas() {
        return mQuestions.length;
    }

    public String getPregunta(int a) {
        if(a < 22) {
            return mQuestions[a];
        } else
            return "";
    }

    public String getOpcione1(int a) {
        return mOpciones[a][0];
    }

    public String getOpcione2(int a) {
        return mOpciones[a][1];
    }

    public String getOpcione3(int a) {
        return mOpciones[a][2];
    }

    public String getOpcione4(int a) {
        return mOpciones[a][3];
    }

    public String optenCorrecta(int a) {
        return respuetaCorrecta[a];
    }

    public String getPreguntaContenido() {
        return preguntaContenido;
    }

    public void setPreguntaContenido(String preguntaContenido) {
        this.preguntaContenido = preguntaContenido;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public String getTextPregunta() {
        return textPregunta;
    }

    public void setTextPregunta(String textPregunta) {
        this.textPregunta = textPregunta;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(String pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }
}
