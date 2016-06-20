package agenda;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main (String[] args) throws IOException{
//		Verifica se o parâmetro foi passado	
		if (Genericas.verificaParametros(args, 1)){
			
//			Lista para guardar todas configurações de conferência
			ArrayList<Conferencia> conferencias;
//			Conferência para armazenar a melhor configuração
			Conferencia conferencia;
//			Lista de palestras a serem alocadas na conferência
			ArrayList<Palestra> palestras = Leitura.lerDados(args[0]);
			
//			Verifica se houver algum erro na leitura do arquivo
			if (palestras == null){
				System.out.println("Erro ao fazer a leitura do arquivo.");
				System.out.println("Favor verificar o caminho do mesmo.");
				return;
			}
			
			Alocacao alocacao = new Alocacao(palestras.size());
			
//			Gera algumas configurações de conferência
			conferencias = alocacao.geraConfiguracoes(palestras);
			
//			Seleciona a que atende as restrições e tem um melhor
//			aproveitamento do tempo
			conferencia = alocacao.melhorConferencia(conferencias);
			
//			Imprime a melhor alocação de palestra
			conferencia.imprimeConferencia();
		}
	}	
}
