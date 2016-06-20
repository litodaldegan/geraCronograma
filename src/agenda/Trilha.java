package agenda;

public class Trilha {
// 	Sessão matutinas
	Sessao sessaoM;
// 	Sessao vespertinas
	Sessao sessaoV;
//	Marca o primeiro horário disponível
//	da sessão matutina
	Integer horarioM;
//	Marca o primeiro horário disponível
//	da sessão vespertina
	Integer horarioV;
	private Integer tempoVago;

	public Trilha() {
//		Inicia sessão com 3 horas de duração
		sessaoM = new Sessao(Genericas.emMinutos(3));
//		Inicia sessão com 4 horas de duração
		sessaoV = new Sessao(Genericas.emMinutos(4));
//		Tempo vago total da trilha, 7 horas
		tempoVago = Genericas.emMinutos(7);
//		Horário de início da sessão matutina
		horarioM = Genericas.emMinutos(9);
//		Horário de início da sessão vespertina
		horarioV = Genericas.emMinutos(13);
	}
	
//	Retorna o tempo vago nessa trilha
	public Integer getTempoVago(){
		return tempoVago;
	}

//	Verifica se a palestra cabe na trilha, na seção matutina e vespertina
	public boolean verificaCapacidade(Palestra palestra) {
		return (sessaoM.verificaCapacidade(palestra) || sessaoV
				.verificaCapacidade(palestra));
	}

//	Verifica restrições de horário para as sessões
	public boolean verificaRestricoes() {
		return (sessaoM.verificaRestricaoM() && sessaoV.verificaRestricaoV());
	}

	public boolean alocaPalestra(Palestra palestra) {
//		Verifica se a palestra pode ser alocada na parte da manhã
		if (sessaoM.verificaCapacidade(palestra)) {
			tempoVago -= palestra.getDuracao();
//			Registra o horário de início da sessão
			palestra.setHorario(horarioM);
//			Incrementa o horário para a próxima palestra
			horarioM += palestra.getDuracao();
//			Salva palestra na sessão e retorna se houve sucesso
			return sessaoM.addPalestra(palestra);
		}
		if (sessaoV.verificaCapacidade(palestra)) {
			tempoVago -= palestra.getDuracao();
//			Registra o horário de início da sessão
			palestra.setHorario(horarioV);
//			Incrementa o horário para a próxima palestra
			horarioV += palestra.getDuracao();
//			Salva palestra na sessão e retorna se houve sucesso
			return sessaoV.addPalestra(palestra);
		}

		return false;
	}
	
//	Imprime em tela essa trilha
	public void imprimeTrilha (){
		System.out.print(sessaoM.listaPalestras());
		System.out.println(Genericas.tempoEmHoras(horarioM) + " Lunch");
		System.out.print(sessaoV.listaPalestras());
		System.out.println(Genericas.tempoEmHoras(horarioV) + " Networking Event");
	}
}
