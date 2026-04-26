package model.aplication;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.entities.Jogador;
import model.entities.ListaServico;

public class Programa {

	public static int FunctionLenght(String text) {

		Function<String, Integer> cont = (texto) -> texto.length();
		return cont.apply(text.replace(" ", ""));

	}

	public static void main(String[] args) throws IOException {

		// "C:\Users\\jails\\temp"
		String enderecoDir = "C:\\Users\\jails\\temp\\doc";
		String nomeArquivo = "jogadores.txt";

		ListaServico jg = new ListaServico();

		List<Jogador> jogador = jg.getListaDeJogadores(Paths.get(enderecoDir + "\\" + nomeArquivo));

		// jogador.forEach(System.out::println); // Imprime toda à lista de jogadores
		double mediaIdade = jogador.stream().mapToDouble(m -> m.getIdade()).reduce(Double::sum).getAsDouble()
				/ jogador.size();

		System.out.printf("Maior idade = %.2f\n ", mediaIdade);

		List<Jogador> porTime = jogadorPorTime(jogador, "Santos");
		// porTime.forEach(System.out::println);

		List<Jogador> primeiraLetra = jogador.stream()
				.filter(j -> j.getNome().startsWith("N") && j.getGolsMarcados() > 5).collect(Collectors.toList());
		primeiraLetra.forEach(System.out::println);

	}

	public static List<Jogador> jogadorPorTime(List<Jogador> j, String time) {
		return j.stream().filter(jogador -> jogador.getTimeAtual().equals(time)).collect(Collectors.toList());
	}

}
