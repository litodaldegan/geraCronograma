package agenda;

import java.util.ArrayList;

public class Alocacao {
	// Número de palestra disponíveis
	private Integer nPalestras;

	// Lista com as permutações possíveis das palestras
	ArrayList<ArrayList<Integer>> permut = new ArrayList<>();

	public Alocacao(Integer nPalestras) {
		this.nPalestras = nPalestras;
// 		Gera algumas permutações possíveis
		permutacoes();
//		Gera algumas permutações aleatórias
//		aleatorias();
	}

//	Gera algumas permutações para fazer as variações de alocações
	public void permutacoes() {

		for (int i = 0; i < nPalestras; i++) {
			ArrayList<Integer> sequencia = new ArrayList<>();

			for (int j = i; j < (nPalestras + i); j++)
				sequencia.add(j % nPalestras);

			// Salva sequência
			permut.add(sequencia);
		}
	}
	
//	Gera algumas configurações aleatória. Devido ao fato que
//	cobrir todas pode ser inviável dependendo do número de palestras
	public void aleatorias() {
		ArrayList<Integer> configuracao = null;
		ArrayList<Integer> opcao = null;
		Integer aleatorio;
		
//		Amostragem de %50 de configurações aleatórias
		for (int i = 0; i < (nPalestras/2); i++){
			configuracao = new ArrayList<>();
			opcao = new ArrayList<>();
			
//			Lista de 0 ao número de palestras
			for (int h = 0; h < nPalestras; h++)
				opcao.add(h);
				
			for (int j = 0; j < nPalestras; j++){
//				Escolhe um número aleatório referente a uma palestra
				aleatorio = Genericas.aleatorio(opcao);
				
//				Adiciona esse número para formar uma configuração
				configuracao.add(aleatorio);
			}
			
//			Salva essa configuração
			permut.add(configuracao);
		}
	}

	// Descobre qual a melhor configuração de conferência
	public Conferencia melhorConferencia(ArrayList<Conferencia> conferencias) {
		// Guarda o tempo da conferência com menor tempo vago
		Integer menorTempo = Integer.MAX_VALUE;
		Integer tempoVago;
		Conferencia melhorConferencia = new Conferencia();

		// Percorrendo todas as configurações
		for (Conferencia conferencia : conferencias) {

			// Se for uma conferência válida (atende as restrições)
			if (conferencia.verificaValidade()) {

				// Pega o tempo vago da conferência
				tempoVago = conferencia.tempoObsoleto();

				// Verifica se é o menor
				if (tempoVago < menorTempo) {
					menorTempo = tempoVago;
					melhorConferencia = conferencia;
				}
			}
		}

		return melhorConferencia;
	}

//	Cria conferências com as configurações geradas
//	Alocando as palestras na trilhas.
	public ArrayList<Conferencia> geraConfiguracoes(
			ArrayList<Palestra> palestras) {
		// Lista para salvar as configurações de conferência
		ArrayList<Conferencia> conferencias = new ArrayList<Conferencia>();

		// Para cada umas das configurações possíveis
		for (ArrayList<Integer> configuracao : permut) {

			Conferencia conferencia = new Conferencia();

			// Para cada uma das palestras
			for (Integer idPalestra : configuracao) {
				//
				// Pega a palestra correspondente
				Palestra palestra = palestras.get(idPalestra);

				// Adiciona palestra à conferencia
				conferencia.addPalestra(new Palestra(palestra.getNome(),
						palestra.getDuracao()));
			}

			// Salva configuração de conferencia
			conferencias.add(conferencia);
		}

		return conferencias;
	}
}
