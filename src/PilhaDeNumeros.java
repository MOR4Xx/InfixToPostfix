// Matéria Estrutura de Dados 1
// Alunos: João Antonio Coelho dos Reis, Jorge Afonso Rabelo de Araujo,Otávio de Queiroz Franco
// Data de Codificação: 18/08/2024

//Classe que representa uma pilha de operadores para a conversão infixa para pós-fixa
// Objetivo: Armazenar e gerenciar operadores aritméticos em uma pilha durante a conversão de uma expressão infixa para pós-fixa.
public class PilhaDeNumeros {
    int topo;
    int[] elementos;

    PilhaDeNumeros(int tamanho) {
        elementos = new int[tamanho];
        topo = -1;
    }

    public void empilhar(int elemento) {
        elementos[++topo] = elemento;
    }

    public int desempilhar() {
        return elementos[topo--];
    }
}