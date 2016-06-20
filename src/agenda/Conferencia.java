package agenda;

import java.util.ArrayList;

public class Conferencia {
//	Lista de trilhas que compõem essa conferência
	private ArrayList<Trilha> trilhas;
	
	public Conferencia (){
		trilhas = new ArrayList<>();
		addTrilha();
	}
	
//	Cria uma trilha vazia para poder adicionar mais palestras
	public void addTrilha (){
		trilhas.add(new Trilha());
	}
	
//	Pega a soma de tempo vago de todas as trilhas
	public Integer tempoObsoleto (){
		Integer tempoVago = 0;
		
		for (Trilha trilha : trilhas)
			tempoVago += trilha.getTempoVago();
		
		return tempoVago;
	}
	
//	Faz a verificação de validade de todas as trilhas
	public boolean verificaValidade (){
		boolean valido = true;
		
		for (Trilha trilha : trilhas)
			valido = valido & trilha.verificaRestricoes();
		
		return valido;
	}
	
	public boolean addPalestra (Palestra palestra){
		
//		Tenta adicionar palestra nas trilhas disponíveis
		for (Trilha trilha : trilhas)
			if (trilha.alocaPalestra(palestra))
				return true;
		
//		Caso não consiga, adiciona uma nova trilha vazia
		addTrilha();
//		E adiciona palestra
		return addPalestra(palestra);
	}
	
//	Imprime os dados da conferência
	public void imprimeConferencia (){
		int i = 1;
		
		for (Trilha trilha : trilhas){
			System.out.println("Trilha " + i + ":");
			trilha.imprimeTrilha();
			i++;
		}
	}
}
