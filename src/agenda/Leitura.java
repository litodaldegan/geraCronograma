package agenda;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Leitura {

	public static ArrayList<Palestra> lerDados (String arquivoEntrada) throws IOException{
		ArrayList<Palestra> palestras = new ArrayList<>();
		
		File file = new File(arquivoEntrada);
		Charset charset = Charset.forName("UTF-8");

		try {
//			Pega todas as linhas do arquivo
			List<String> lines = Files.readAllLines(file.toPath(), charset);
			String[] dadosLinha = new String[2]; 
			
			for (String line : lines){
//				Divide a linha entre o nome e a duração
				dadosLinha = Genericas.divideString(line);
				String nome = dadosLinha[0];
				Integer duracao = Genericas.lerDuracao(dadosLinha[1]);
				
				palestras.add(new Palestra(nome, duracao));
			}
		} catch (Exception e){
			System.err.println(e.getMessage());
			return null;
		}
		
		return palestras;
	}
}
