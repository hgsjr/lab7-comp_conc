package entities;

import java.util.Random;

public class Produtora implements Runnable {

    private int id; //variável que armazenará o id da thread que está sendo executada
    private Monitor monitor; //variável que armazena internamente a instância do monitor
    private Buffer buffer; //variável que armazena internamente a instância do buffer

    public Produtora(Buffer buffer,Monitor monitor, int id) {

        this.buffer = buffer;
        this.monitor = monitor;
        this.id = id;

    }

    public void run() { //método que será executado pelas threads produtoras

        Random rand = new Random();

        int inserido; //variável que armazenará o elemento inserido no buffer para mostrá-lo no log de saída

        while(true) { //loop infinito para validar funcionamento do programa

            monitor.iniciaInsercao(this.id);
            synchronized (this) {buffer.imprimeBuffer();} //coloquei synchronized para tentar consertar "bugs" no print
            inserido = rand.nextInt(9) + 1;
            buffer.insereElemento(inserido); //insere elemento aleatório no intervalo 1 <= x <= 9
            System.out.printf("Thread produtora %d inseriu o elemento %d no buffer.\n", id + 1, inserido);
            synchronized (this) {buffer.imprimeBuffer();} //coloquei synchronized para tentar consertar "bugs" no print
            monitor.finalizaInsercao(this.id);

        }

    }

}
