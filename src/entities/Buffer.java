package entities;

public class Buffer { //classe que implementa um buffer simples que segue a lógica "first in, last out"

    int [] vetor; //vetor que armazenará dados do buffer
    int proximoIndex; //variável que guarda a posição onde será inserido o próximo elemento

    public Buffer(int tamanho) { //construtor que inicializa o vetor do buffer e a variável de controle

        this.vetor = new int[tamanho];
        for(int i = 0; i < vetor.length; i++ ) {

            this.vetor[i] = 0; //inicializa o buffer com zero em todas as suas
                               //posições (0 representa que a posição está vazia)

        }
        this.proximoIndex = 0;

    }

    public void insereElemento(int elemento) { //não é necessário checar se há espaço disponível
                                               //pois tal lógica já é implementada no monitor
        this.vetor[proximoIndex] = elemento;
        this.proximoIndex++;

    }

    public int removeElemento() { //não é necessário checar se há elemento disponível
                                  //pois tal lógica já é implementada no monitor
        this.proximoIndex--;
        int aux = this.vetor[this.proximoIndex];
        this.vetor[this.proximoIndex] = 0;
        return aux;

    }

    public void imprimeBuffer() {

        System.out.print("[");
        for(int i = 0; i < this.vetor.length; i++) {

            System.out.printf("%d ", this.vetor[i]);

        }
        System.out.println("\b]");


    }

    public boolean isCheio() { //método que avalia se o buffer está cheio

        if(proximoIndex == vetor.length) {

            return true;

        }

        else {

            return false;

        }

    }

    public boolean isVazio() { //método que avalia se o buffer está vazio

        if(proximoIndex == 0) {

            return true;

        }

        else {

            return false;

        }

    }

}
