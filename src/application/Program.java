package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import models.entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("US"));
	
		String strPath = "C:\\Projetos\\CURSO_UDEMY_NELIO\\TrabalhandoComArquvivos - Exercicio\\src\\files";
		List<Product> products = new ArrayList<>();
		File inPath = new File(strPath+"\\in.txt");
		File outPath = new File(strPath+"\\out\\summary.csv");
		
//		Lendo dados do arquivo, e criando uma lista de produtos
		try(BufferedReader br = new BufferedReader(new FileReader(inPath))){
			System.out.println("In file: ");
			String line = br.readLine();
			while(line != null) {
				System.out.println(line);
				String[] infos = line.split(",");
				Product p = new Product(infos[0], Double.parseDouble(infos[1]),Integer.parseInt(infos[2]));
				products.add(p);
				line = br.readLine();
				}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
//		Testando se a pasta de saida existe, caso não a mesma é criada
		if(!outPath.exists()) {
			boolean success = new File(strPath+"\\out").mkdir();
			System.out.println("Out directory created: " + success);
		}
		
//		Criando arquivo com o valor total(quantiade x valor) de cada produto
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(outPath))){
			products.stream().forEach(p -> {
				try {
					bw.write(p.getDados());
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
//		Lendo arquivo com valor total que foi criado
		try (BufferedReader br = new BufferedReader(new FileReader(outPath))){
			System.out.println();
			System.out.println("Out file: ");
			String line = br.readLine();
			while(line != null) {
				System.out.println(line);
				line = br.readLine();
				}
		}
		catch(IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			}
		}



