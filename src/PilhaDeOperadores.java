// Matéria Estrutura de Dados 1
// Alunos: João Antonio Coelho dos Reis, Jorge Afonso Rabelo de Araujo,Otávio de Queiroz Franco
// Data de Codificação: 18/08/2024

// Classe que representa uma pilha de operadores para a conversão infixa para pós-fixa
// Objetivo: Armazenar e gerenciar operadores aritméticos em uma pilha durante a conversão de uma expressão infixa para pós-fixa.
public class PilhaDeOperadores {
    int topo;
    char[] elementos;

    PilhaDeOperadores(int tamanho) {
        elementos = new char[tamanho];
        topo = -1;
    }

    public boolean estaVazia() {
        return topo == -1;
    }

    public void empilhar(char elemento) {
        elementos[++topo] = elemento;
    }

    public char desempilhar() {
        return elementos[topo--];
    }

    public char espiar() {
        return elementos[topo];
    }
}