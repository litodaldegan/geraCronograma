package agenda;

public class Palestra {
	private String nome;
//	Duração em minutos
	private Integer duracao;
//	Em minutos, apartir do início do dia (00:00)
	private Integer horarioInicio;
	
	public Palestra (String nome, Integer duracao){
		this.nome = nome;
		this.duracao = duracao;
		horarioInicio = 0;
	}
	
	public String getNome (){
		return nome;
	}
	
	public Integer getDuracao (){
		return duracao;
	}
	
	public void setHorario (Integer horario){
		horarioInicio = horario;
	}
	
//	Retorna string com os dados da palestra
	public String getDados (){
		String dados = "";

		dados = dados.concat(Genericas.tempoEmHoras(horarioInicio));
		dados = dados.concat(" " + nome + " " + duracao + "min");
		
		return dados;
	}
}
