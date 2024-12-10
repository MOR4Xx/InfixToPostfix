// Matéria Estrutura de Dados 1
// Alunos: João Antonio Coelho dos Reis, Jorge Afonso Rabelo de Araujo,Otávio de Queiroz Franco
// Data de Codificação: 18/08/2024

// Classe principal que realiza a conversão de uma expressão infixa para pós-fixa e a avaliação da expressão pós-fixa
// Objetivo: Interagir com o usuário, converter expressões infixas para pós-fixas e avaliar o resultado da expressão pós-fixa usando pilhas.
import java.util.Scanner;

public class InfixParaPostfix {
    // Função principal que faz a interação com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada da expressão infixa
        System.out.println("Digite uma expressão infixa: ");
        String expressaoInfixa = scanner.nextLine().replaceAll("\\s", ""); // Remove espaços em branco

        // Converte para pós-fixa e exibe o resultado
        String expressaoPosfixa = converterParaPosfixa(expressaoInfixa);
        System.out.println("Expressão Pós-fixa: " + expressaoPosfixa);

        // Avalia a expressão pós-fixa e exibe o resultado
        int resultado = avaliarPosfixa(expressaoPosfixa);
        System.out.println("Resultado: " + resultado);
    }

    // Função que define a precedência dos operadores
    public static int obterPrecedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '(':
                return 0;
        }
        return -1; // Retorna -1 se o caractere não for um operador conhecido
    }

    // Função que converte expressão infixa para pós-fixa
    public static String converterParaPosfixa(String expressaoInfixa) {
        StringBuilder expressaoPosfixa = new StringBuilder();
        PilhaDeOperadores pilhaDeOperadores = new PilhaDeOperadores(expressaoInfixa.length());

        // Percorre cada caractere da expressão
        for (int i = 0; i < expressaoInfixa.length(); i++) {
            char caractereAtual = expressaoInfixa.charAt(i);

            // Ignora espaços em branco
            if (caractereAtual == ' ') continue;

            // Se for um número ou letra, adiciona diretamente ao resultado
            if (Character.isLetterOrDigit(caractereAtual)) {
                expressaoPosfixa.append(caractereAtual);
            }
            // Se for um parêntese de abertura, empilha
            else if (caractereAtual == '(') {
                pilhaDeOperadores.empilhar(caractereAtual);
            }
            // Se for um parêntese de fechamento, desempilha até encontrar o parêntese de abertura
            else if (caractereAtual == ')') {
                while (!pilhaDeOperadores.estaVazia() && pilhaDeOperadores.espiar() != '(') {
                    expressaoPosfixa.append(pilhaDeOperadores.desempilhar());
                }
                pilhaDeOperadores.desempilhar(); // Remove o '(' da pilha
            }
            // Se for um operador
            else {
                while (!pilhaDeOperadores.estaVazia() &&
                        obterPrecedencia(caractereAtual) <= obterPrecedencia(pilhaDeOperadores.espiar())) {
                    expressaoPosfixa.append(pilhaDeOperadores.desempilhar());
                }
                pilhaDeOperadores.empilhar(caractereAtual); // Empilha o operador atual
            }
        }
        // Desempilha todos os operadores restantes na pilha
        while (!pilhaDeOperadores.estaVazia()) {
            expressaoPosfixa.append(pilhaDeOperadores.desempilhar());
        }

        return expressaoPosfixa.toString();
    }

    // Função para avaliar uma expressão pós-fixa
    public static int avaliarPosfixa(String expressaoPosfixa) {
        PilhaDeNumeros pilhaDeNumeros = new PilhaDeNumeros(expressaoPosfixa.length());

        // Percorre cada caractere da expressão pós-fixa
        for (int i = 0; i < expressaoPosfixa.length(); i++) {
            char caractereAtual = expressaoPosfixa.charAt(i);

            // Se for um número, empilha
            if (Character.isDigit(caractereAtual)) {
                pilhaDeNumeros.empilhar(caractereAtual - '0'); // Converte o caractere para número
            } else {
                // Se for um operador, desempilha dois números e realiza a operação
                int valor2 = pilhaDeNumeros.desempilhar();
                int valor1 = pilhaDeNumeros.desempilhar();

                switch (caractereAtual) {
                    case '+':
                        pilhaDeNumeros.empilhar(valor1 + valor2);
                        break;
                    case '-':
                        pilhaDeNumeros.empilhar(valor1 - valor2);
                        break;
                    case '*':
                        pilhaDeNumeros.empilhar(valor1 * valor2);
                        break;
                    case '/':
                        pilhaDeNumeros.empilhar(valor1 / valor2);
                        break;
                }
            }
        }
        // O último valor restante na pilha é o resultado da expressão
        return pilhaDeNumeros.desempilhar();
    }

}
