import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

/**
 * Programa simples que implementa a cifra Cesar. -> Contém bloco principal com
 * exemplo de funcionamento.
 *
 * Mais informações: http://pt.wikipedia.org/wiki/Cifra_de_C%C3%A9sar
 *
 * @author Hercules Lemke Merscher
 * @version 1.0
 */

class HuffmanNode {

    public Map<String, String> mapaC = new HashMap<String, String>();
    public Map<String, String> mapaD = new HashMap<String, String>();

    public void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null) {
            // c is the character in the node 
            System.out.println(root.c + ":" + s);
            String c = String.valueOf(root.c);
            mapaC.put(c, s);
            mapaD.put(s, c);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
    int data;
    char c;

    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {

    public int compare(HuffmanNode x, HuffmanNode y) {

        return x.data - y.data;
    }
}

public class JCriptoCesar {

    /**
     * Metodo que criptografa um texto, utilizando a famosa cifra de Cesar.
     */
    public static String encriptar(int chave, String texto) {
        // Variavel que ira guardar o texto crifrado

        StringBuilder textoCifrado = new StringBuilder();
        // Variavel com tamanho do texto a ser encriptado
        int tamanhoTexto = texto.length();

        // Criptografa cada caracter por vez 
        for (int c = 0; c < tamanhoTexto; c++) {
            
           char letraCifradaASCII = 0;
            if(texto.charAt(c)>='a' && texto.charAt(c)<='z')
            {
              
               letraCifradaASCII = (char) ((((int) texto.charAt(c)-'a')+chave)%26+'a');
            }
            
            else if(texto.charAt(c)>='A' && texto.charAt(c)<='Z')
            {
               letraCifradaASCII =(char) ((((int) texto.charAt(c)-'A')+chave)%26+'A');
            }
            else 
            {
                letraCifradaASCII = texto.charAt(c);
            }
            // Transforma codigo ASCII criptografado em caracter ao novo texto
            textoCifrado.append((char) letraCifradaASCII);
        }

        // Por fim retorna a mensagem criptografada por completo
        return textoCifrado.toString();
    }

    /**
     * Metodo que descriptografa um texto, utilizando a famosa cifra Cesar.
     */
    public static String decriptar(int chave, String textoDecifrado) {
        // Variavel que ira guardar o texto decifrado
        StringBuilder texto = new StringBuilder();
        // Variavel com tamanho do texto a ser decriptado
        int tamanhoTexto = textoDecifrado.length();

        // Descriptografa cada caracter por vez
        for (int c = 0; c < tamanhoTexto; c++) {
           
char letraDecifradaASCII = 0;
            if(textoDecifrado.charAt(c)>='a' && textoDecifrado.charAt(c)<='z')
            {
              
               letraDecifradaASCII = (char) ((((int) textoDecifrado.charAt(c)-'a')-chave+26)%26+'a');
            }
            
            else if(textoDecifrado.charAt(c)>='A' && textoDecifrado.charAt(c)<='Z')
            {
               letraDecifradaASCII =(char) ((((int) textoDecifrado.charAt(c)-'A')-chave+26)%26+'A');
            }
            
            else 
            {
                letraDecifradaASCII = textoDecifrado.charAt(c);
            }
      
            texto.append((char) letraDecifradaASCII);
        }

        // Por fim retorna a mensagem descriptografada por completo
        return texto.toString();
    }

    public static void main(String[] args) throws IOException {
        int vec[] = new int[50];
        JCriptoCesar a = new JCriptoCesar();
        String textoCriptografado = "";
        String textoDescriptografado;
        Scanner ler = new Scanner(System.in);
        System.out.print("Informe a chave de deslocamento: ");
        int chave = ler.nextInt();

        ler.nextLine();
        System.out.printf("Informe o nome de arquivo texto a ser criptografado:\n");
        String nome = ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            System.out.println("*****************************************************");
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linhas = lerArq.readLine();
            String linha = linhas;
            linhas = linhas + "\n";
            textoCriptografado = a.encriptar(chave, linha);
            textoCriptografado = textoCriptografado + "\n";
            // lê a primeira linha
// a variável "linha" recebe o valor "null" quando o processo
// de repetição atingir o final do arquivo texto
            while (linha != null) {

                System.out.printf("%s\n", linha);
                linha = lerArq.readLine();
                if (linha != null) {
                    linhas = linhas + linha;
                    linhas = linhas + "\n";
                    textoCriptografado = textoCriptografado + a.encriptar(chave, linha);
                    textoCriptografado = textoCriptografado + "\n";
                }
            }

            System.out.println("\n\nTEXTO ORIGINAL: " + linhas);
            System.out.println("\n\nTEXTO CRIPTOGRAFADO: " + textoCriptografado);
            arq.close();
            String s = textoCriptografado;
            int p = 0, i, j, cont = 0;
            String v = "";
            System.out.println("Frequencias de letras");
             System.out.println("*****************************************************\n\n\n\n\n\n");
            for (i = 0; i < s.length(); i++) {
                for (j = 0; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        cont++;
                    }
                }
                char c = s.charAt(i);
                
                if (!v.contains("" + c)) {
                    v = v + c;
                    System.out.println("A letra " + s.charAt(i) + " aparece " + cont + " vezes.");

                    vec[p] = cont;
                    p++;
                }

                cont = 0;
            }
            char[] charArray = textoCriptografado.toCharArray();
            PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(p, new MyComparator());
            for (i = 0; i < charArray.length; i++) {
cont = 1;
                // creating a Huffman node object 
                // and add it to the priority queue. 
                for (int l =0;l<i;l++)
                {
                    if (charArray[l] == charArray[i])
                    {
                        cont = 0;
                    }
                }
                if(cont==1)
                {
                     HuffmanNode hn = new HuffmanNode();
               
                hn.c = charArray[i];
                hn.data = vec[i];
                hn.left = null;
                hn.right = null;
                q.add(hn);
                }
            }
            HuffmanNode root = null;

            // Here we will extract the two minimum value 
            // from the heap each time until 
            // its size reduces to 1, extract until 
            // all the nodes are extracted. 
            while (q.size() > 1) {

                // first min extract. 
                HuffmanNode x = q.peek();
                q.poll();

                // second min extarct. 
                HuffmanNode y = q.peek();
                q.poll();

                // new node f which is equal 
                HuffmanNode f = new HuffmanNode();

                // to the sum of the frequency of the two nodes 
                // assigning values to the f node. 
                f.data = x.data + y.data;
                f.c = '-';

                // first extracted node as left child. 
                f.left = x;

                // second extracted node as the right child. 
                f.right = y;

                // marking the f node as the root node. 
                root = f;

                // add this node to the priority-queue. 
                q.add(f);
            }
            System.out.println("Codificações de Huffmann");
             System.out.println("*****************************************************\n\n\n\n\n\n");
            root.printCode(root, "");
            FileWriter arq1 = new FileWriter("RicardoC.txt");
            PrintWriter gravarArq = new PrintWriter(arq1);
            int k = 0;
            for (k = 0; k < charArray.length; k++) {
                gravarArq.printf("%s", root.mapaC.get(String.valueOf(charArray[k])));
            }

            arq1.close();
             System.out.println("Texto Decriptografado de Huffmann");
            System.out.println("*****************************************************\n\n\n");
            FileReader arq2 = new FileReader("RicardoC.txt");
            BufferedReader lerArq2 = new BufferedReader(arq2);
            String linhas2 = lerArq2.readLine();
            char []huff = linhas2.toCharArray();
            String l = "";
String o ="";
            for ( i=0;i<huff.length;i++)
            {
                l = l + huff[i];
        
                    if(root.mapaD.get(l)!=null)
                    {
                            o =  o+root.mapaD.get(l);
                           gravarArq.printf("%s", root.mapaD.get(l));
                           l="";
                    }
              
            }
            System.out.println(o);
            System.out.println("Texto Decriptografado de Cesar");
             System.out.println("*****************************************************\n\n\n\n\n\n");
             
     
            String resp = "";
            
           
                resp = a.decriptar(chave,o);
                
           
            System.out.println(resp);
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        System.out.println("*****************************************************\n\n\n\n\n\n");
        

//fim da criptografia

            
    }
}
