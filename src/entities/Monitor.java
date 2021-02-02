package entities;

public class Monitor {

    int produtores, consumidores; //contador de threads
    Buffer buffer; //armazena instância do buffer que será necessária para checagem de situações (está cheio ou vazio)

    public Monitor(Buffer buffer) {

        this.buffer = buffer; //inicializa o atributo do tipo Buffer no objeto

    }

    public synchronized void iniciaInsercao(int id) {

        try {

            //se o buffer estiver cheio ou houver mais produtores ou consumidores ativos, se bloqueia
            while(this.buffer.isCheio() || this.produtores > 0 || this.consumidores > 0) {

                System.out.printf("Thread produtora %d se bloqueando.\n", id + 1);
                wait();

            }

            this.produtores++;
            System.out.printf("Thread produtora %d adicionando no buffer.\n", id + 1);

        } catch (InterruptedException e) {}

    }

    public synchronized void finalizaInsercao(int id) {

        this.produtores--;
        System.out.printf("Thread produtora %d terminou inserção.\n", id + 1);
        notifyAll();

    }

    public synchronized void iniciaRemocao(int id) {

        try {

            //se o buffer estiver vazio ou houver mais produtores ou consumidores ativos, se bloqueia
            while(this.buffer.isVazio() || this.produtores > 0 || this.consumidores > 0) {

                System.out.printf("Thread consumidora %d se bloqueando.\n", id + 1);
                wait();

            }

            this.consumidores++;
            System.out.printf("Thread consumidora %d removendo no buffer.\n", id + 1);

        } catch (InterruptedException e) {}

    }

    public synchronized void finalizaRemocao(int id) {

        this.consumidores--;
        System.out.printf("Thread consumidora %d terminou remoção.\n", id + 1);
        notifyAll();

    }

}
