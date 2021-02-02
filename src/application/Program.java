package application;

import entities.Buffer;
import entities.Monitor;
import entities.Produtora;
import entities.Consumidora;

public class Program {

    public static void main(String [] args) {

        //altere aqui as constantes que armazenam o número de threads consumidoras e produtoras
        final int NPRODUTORES = 4; //  <-------------------------------------------------------
        final int NCONSUMIDORES = 4; //<-------------------------------------------------------

        //altere aqui a constante que armazena o tamanho do buffer
        final int TBUFFER = 7; //      <-------------------------------------------------------

        Buffer buffer = new Buffer(TBUFFER);

        Monitor monitor = new Monitor(buffer);

        Thread [] threads = new Thread[NPRODUTORES + NCONSUMIDORES]; //vetor das threads instanciado

        for(int i = 0; i < NPRODUTORES; i++) { //cria as threads produtoras da aplicação

            threads[i] = new Thread(new Produtora(buffer, monitor, i));

        }

        for(int i = 0; i < NCONSUMIDORES; i++) { //cria as threads consumidoras da aplicação

            threads[NPRODUTORES + i] = new Thread(new Consumidora(buffer, monitor, i));

        }

        //inicia as threads
        for(int i = 0; i < threads.length; i++) {

            threads[i].start();

        }

        //Trecho de código que esperaria o término de todas as threads, contudo nunca é atingido
        //por conta do loop infinito das threads (que é proposital para teste da aplicação)
        for(int i = 0; i < threads.length; i++) {

            try { threads[i].join(); } catch (InterruptedException e) { return; }

        }

    }

}
