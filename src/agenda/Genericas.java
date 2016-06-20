package agenda;

import java.util.ArrayList;
import java.util.Random;

public final class Genericas {
	
//	Função para verificar os parâmetros passados
	public static boolean verificaParametros(String[] args, int numeroParametros) {
//		Se a quantidade de argumentos for menor que a necessária
		if (args.length < numeroParametros) {
			System.out.println("Por favor passar o local do arquivo de entrada.");
			System.out.println("Ex. java -jar cronogramaConferencia /user/home/conferenceTakes");
			return false;
		}
		
//		Se o usuário passa o comando para help
		if (args[0].equals("-h") || args[0].equals("-help")){
			System.out.println("Esse programa ajuda a alocar palestra em uma conferência.");
			System.out.print("Para executar, forneça um arquivo com os nomes das palestras e durações.");
			System.out.println("Ex. java -jar cronogramaConferencia /user/home/conferenceTakes");
			return false;
		}
		
		return true;
	}
	
//	Função que transforma um horário de minutos em horas
	public static String tempoEmHoras (Integer minutos){
//		Pegandos as horas
		Integer horas = minutos / 60;
//		Pegandos os minutos
		Integer minutosRestantes = minutos % 60;
		
//		Caso seja horário depois do meio dia
		if (horas >= 12){
			
//			Se for acima de 12 horas (trata condição de meio dia)
			if (horas > 12)
				horas -= 12;
			
			return (String.format("%02d", horas) + ":" + 
					String.format("%02d", minutosRestantes) + "PM");
		}
			
		return (String.format("%02d", horas) + ":" + 
				String.format("%02d", minutosRestantes) + "AM");
	}
	
//	Transforma horas em minutos
	public static Integer emMinutos (Integer horas){
		return (horas * 60);
	}
	
//	Função que divide a string na última palavra da string
	public static String[] divideString (String dados){
		String[] subStrings = new String[2];
		int i = (dados.length() -1);

//		Descobrindo onde a string tem que ser dividida
		while (dados.charAt(i) != ' ' && i > 0)
			i--;
		
//		Substring com o nome da palestra
		subStrings[0] = dados.substring(0,i);
//		Substring com a duração da palestra
		subStrings[1] = dados.substring(i+1);
		
		if (subStrings[0].isEmpty() || subStrings[1].isEmpty())
			return null;
		
		return subStrings;
	}
	
//	Função para ler a string duração para numérico
	public static Integer lerDuracao (String duracao){
//		Palestra lightning tem duração de 5minutos
		if (duracao.equals("lightning"))
			return 5;
		
//		Remove tudo que não é número
		String duracao1 = duracao.replaceAll("[^0-9]", "");
		return Integer.parseInt(duracao1);
	}
	
//	Escolhe um número de uma lista de forma aleatória
	public static Integer aleatorio (ArrayList<Integer> lista){
		Random gerador = new Random();
		Integer maximo = lista.size();
		
		return lista.remove(gerador.nextInt(maximo));
	}
}