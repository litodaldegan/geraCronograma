package agenda;

import java.util.ArrayList;

public class Sessao {
//	Lista de palestras presentes nessa sessão
	private ArrayList<Palestra> palestras;
//	Duração da sessão, difere para Matutino e Vespertino
	private Integer tempoVago;
	
	public Sessao (Integer duracao){
		tempoVago = duracao;
		palestras = new ArrayList<Palestra>();
	}

//	Retorna o tempo livre nessa sessão
	public Integer getTempoVago (){
		return tempoVago;
	}
	
	public void reduzirTempoVago (Integer tempo){
		tempoVago -= tempo;
	}
	
//	Adiciona palestra nessa sessão se houver tempo disponível
	public Boolean addPalestra (Palestra palestra){
		if (verificaCapacidade(palestra)){
			palestras.add(palestra);
			reduzirTempoVago(palestra.getDuracao());
			return true;
		}
		
		return false;
	}
		
// Verifica se a palestra cabe nessa sessão
	public Boolean verificaCapacidade (Palestra palestra){
		if (palestra.getDuracao() > tempoVago)
			return false;
			
		return true;
	}
	
//	Verifica restrição da sessão matutina
//	Terminar ao meio-dia
	public boolean verificaRestricaoM (){
		if (tempoVago > 0)
			return false;
		return true;
	}
	
//	Verifica restrição da sessão vespertina
//	Terminar entre 4pm e 5pm
	public boolean verificaRestricaoV (){
		if (tempoVago > 60)
			return false;
		return true;
	}
	
//	Retonra uma string com todas as palestars dessa sessão
	public String listaPalestras (){
		String lista = "";
		
		for (Palestra palestra : palestras)
			lista = lista.concat(palestra.getDados() + "\n");
		
		return lista;
	}
}
