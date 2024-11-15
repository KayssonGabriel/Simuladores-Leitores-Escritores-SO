import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class LeitoresEscritoresTrocaDeMensagens {

    public static void main(String[] args) {
        BlockingQueue<Mensagem> filaMensagens = new ArrayBlockingQueue<>(50);
        RecursoCompartilhado recurso = new RecursoCompartilhado();

        // Processador de mensagens
        Thread processador = new Thread(new ProcessadorMensagens(recurso, filaMensagens));
        processador.start();

        List<Thread> produtores = new ArrayList<>();
        for (int id = 1; id <= 5; id++) {
            produtores.add(iniciarProdutor(filaMensagens, Mensagem.Tipo.LEITURA, id, "Leitor"));
        }
        for (int id = 1; id <= 2; id++) {
            produtores.add(iniciarProdutor(filaMensagens, Mensagem.Tipo.ESCRITA, id, "Escritor"));
        }

        try {
            for (Thread produtor : produtores) {
                produtor.join();
            }
            filaMensagens.put(new Mensagem(Mensagem.Tipo.FIM, 0));
            processador.join();
        } catch (InterruptedException e) {
            processador.interrupt();
            Thread.currentThread().interrupt();
        }
    }

    private static Thread iniciarProdutor(BlockingQueue<Mensagem> fila, Mensagem.Tipo tipo, int id, String nome) {
        Thread produtor = new Thread(() -> {
            try {
                fila.put(new Mensagem(tipo, id));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, nome + " " + id);
        produtor.start();
        return produtor;
    }
}
