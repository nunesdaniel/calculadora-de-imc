import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CalculadoraDeIMC {
    public static void main(String[] args) {
        Scanner lerTeclado = new Scanner(System.in);

        Date dataHoraAtual = new Date();
        String obterHora = new SimpleDateFormat("HH").format(dataHoraAtual);
        int hora = Integer.parseInt(obterHora);

        String msgSaudacao = gerarSaudacao(hora);

        System.out.println("Olá, " + msgSaudacao + " Seja muito bem-vindo(a) à calculadora DE IMC.\n\nVamos calcular o seu índice e classificá-lo?\n");

        System.out.print("Para iniciarmos, por favor, informe seu nome: ");
        String nome = lerTeclado.nextLine().trim().replaceAll("[0-9]", "");

        while (nome.isEmpty()) {
            System.out.print("ERRO: Por favor, digite seu nome corretamente: ");
            nome = lerTeclado.nextLine().trim().replaceAll("[0-9]", "");
        }

        System.out.print("\nÓtimo, " + nome + "! Por favor, agora informe seu gênero, ex.: (M) Masculino, (F) Feminino ou (N) Prefiro não informar: ");
        String genero = lerTeclado.nextLine().trim().toUpperCase();
        /*
        char genero = lerTeclado.nextLine().charAt(0);
        */

        while (genero.isEmpty() || (genero.charAt(0) != 'M' && genero.charAt(0) != 'F' && genero.charAt(0) != 'N' || genero.length() != 1)) {
            System.out.print("ERRO: Por favor, digite informe seu gênero corretamente, ex.: (M) Masculino, (F) Feminino ou (N) Prefiro não informar: ");
            genero = lerTeclado.nextLine().trim().toUpperCase();
        }
        char caracterGenero = genero.charAt(0);
        /*
        char genero = lerTeclado.nextLine().charAt(0);
        */

        System.out.print("\nAgora, por favor informe sua altura em metros, ex.: 1.67m : ");
        String entradaAltura = lerTeclado.nextLine().trim().toLowerCase().replace(",", ".").replace("m", "");

        while (entradaAltura.isEmpty() || !isDouble(entradaAltura)) {
            System.out.print("ERRO: Por favor, informe sua altura em metros coretamente, ex.: 1.67m : ");
            entradaAltura = lerTeclado.nextLine().trim().toLowerCase().replace(",", ".").replace("m", "");
        }

        double altura = Double.parseDouble(entradaAltura);

        System.out.print("\nÓtimo " + nome + ", agora informe o seu peso atual, ex.: 63kg ou 63.6kg : ");
        String entradaPeso = lerTeclado.nextLine().trim().toLowerCase().replace(",", ".").replace("kg", "");

        while (entradaPeso.isEmpty() || !isDouble(entradaPeso)) {
            System.out.print("ERRO: Por favor, informe seu peso corretamente, ex.: 63kg ou 63.6kg : ");
            entradaPeso = lerTeclado.nextLine().trim().toLowerCase().replace(",", ".").replace("kg", "");
        }

        double peso = Double.parseDouble(entradaPeso);

        System.out.println("");

        String resultado = gerarIMC(nome, caracterGenero, peso, altura);
        System.out.println(resultado);

        System.out.println("Obrigado por testar o projeto. Até a próxima!");

        lerTeclado.close();
    }

    // Função para gerar saudação com base no horário
    public static String gerarSaudacao(int hora) {
        String saudacao;
        if (hora >= 6 && hora <= 11) {
            saudacao = "Bom dia!!!";
        } else if (hora >= 12 && hora <= 17) {
            saudacao = "Boa tarde!!!";
        } else {
            saudacao = "Boa noite!!!";
        }
        return saudacao;
    }

    // Função para calcular o IMC com base na entrada de dados
    public static String gerarIMC(String nome, char caracterGenero, double peso, double altura) {
        String resultado = "";
        double imc = peso / (altura * altura);
        switch (caracterGenero) {
            case 'M':
                if (imc >= 40) {
                    resultado = "Obesidade Mórbida";
                } else if (imc >= 30 && imc <= 39.999) {
                    resultado = "Obesidade Moderada";
                } else if (imc >= 25 && imc <= 29.999) {
                    resultado = "Obesidade Leve";
                } else if (imc >= 20 && imc <= 24.999) {
                    resultado = "Normal";
                } else if (imc <20) {
                    resultado = "Abaixo do Normal";
                }
                break;

            case 'N':
            case 'F':
                if (imc >= 39) {
                    resultado = "Obesidade Mórbida";
                } else if (imc >= 29 && imc <= 38.999) {
                    resultado = "Obesidade Moderada";
                } else if (imc >= 24 && imc <= 28.999) {
                    resultado = "Obesidade Leve";
                } else if (imc >= 19 && imc <= 23.999) {
                    resultado = "Normal";
                } else {
                    resultado = "Abaixo do Normal";
                }
                break;

            default:
                resultado = "Gênero não identificado";
                break;
        }

        return "Nome: " + nome + "\nGênero: " + caracterGenero + String.format("\nIMC: %.1f", imc) + "\nClassificação: " + resultado + "\n";
    }

    // Função para validar se a variável de entrada é Double.
    // Essa tive que pesquisar, pelo menos eu particurlamente não encontrei uma forma mais fácil.
    // Fonte: stackoverflow.com
    private static boolean isDouble(String s) {
        if (s == null)
            return false;
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

