package entities;

public class Consumidora implements Runnable {

    private int id; //variável que armazenará o id da thread que está sendo executada
    private Monitor monitor; //variável que armazena internamente a instância do monitor
    private Buffer buffer; //variável que armazena internamente a instância do buffer

    public Consumidora(Buffer buffer,Monitor monitor, int id) {

        this.buffer = buffer;
        this.monitor = monitor;
        this.id = id;

    }

    public void run() { //método que será executado pelas threads consumidoras

        int removido; //armazenará os elementos removidos pela thread consumidora

        while(true) { //loop infinito para validar funcionamento do programa

            monitor.iniciaRemocao(this.id);
            synchronized (this) {buffer.imprimeBuffer();} //coloquei synchronized para tentar consertar "bugs" no print
            removido = buffer.removeElemento();
            System.out.printf("Thread consumidora %d removeu o elemento %d do buffer.\n", id + 1, removido);
            synchronized (this) {buffer.imprimeBuffer();} //coloquei synchronized para tentar consertar "bugs" no print
            monitor.finalizaRemocao(this.id);

        }

    }

}
