# Simuladores de leitores e escritores

Exercício da disciplina de Sistemas Operacionais que explora cinco formas de coordenar threads leitoras e escritoras em Java. Cada pasta tem seu próprio ponto de entrada e uma versão do recurso compartilhado.

| Pasta | Mecanismo | Classe principal |
| --- | --- | --- |
| [SemaforoJava](SemaforoJava/) | Semáforos para coordenar acesso | `LeitoresEscritoresSemaforo` |
| [MutexJava](MutexJava/) | Bloqueio separado para leitura e escrita | `LeitoresEscritoresMutex` |
| [MonitoresJava](MonitoresJava/) | Monitor com métodos sincronizados | `LeitoresEscritoresMonitores` |
| [TrocaDeMensagens](TrocaDeMensagens/) | Fila bloqueante e uma thread que processa mensagens | `LeitoresEscritoresTrocaDeMensagens` |
| [BarreirasJava](BarreirasJava/) | Barreiras cíclicas por grupo e acesso sincronizado | `LeitoresEscritoresBarreiras` |

## Como executar

Instale um JDK. Compile um exemplo por vez, pois os projetos têm classes com o mesmo nome. Na raiz, execute no PowerShell:

~~~powershell
$fontes = (Get-ChildItem -LiteralPath MutexJava/src -Filter '*.java').FullName
javac -encoding UTF-8 -d build/mutex $fontes
java -cp build/mutex LeitoresEscritoresMutex
~~~

Para trocar de versão, selecione a pasta e a classe da tabela e use outro diretório de saída. A ordem das mensagens varia entre execuções. A versão com troca de mensagens encerra depois de processar todas as leituras e escritas.

## Enunciado original

Disciplina: Sistemas Operacionais

Considere o problema dos Leitores e Escritores apresentados na aula passada.
Implementar em JAVA um simulador para o problema dos Leitores e Escritores usando uma versão em:
1) Semáforos;
2) Mutex;
3) Monitores;
4) Troca de Mensagens;
5) Barreiras.
