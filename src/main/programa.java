package main;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class programa {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String[] lista;
		int suma=0;
		ArrayList<Integer> ar = new ArrayList<Integer>();
		
		// "C:\\Users\\alumnado\\Desktop\\ProgServ"
		System.out.println("Escribe el path del directorio");
		String dir = s.nextLine().replace("/", "\\");
		
		File f = new File(dir);

		if (f.isDirectory()) {
			String[] arch = f.list();
			try {
				for (int i = 0; i < arch.length; i++) {
					 f = new File(dir + "\\" + arch[i]);
					if (f.isFile()) {
						ProcessBuilder pb = new ProcessBuilder("find", "/V", "/C", "",
								"\"" + dir + "\\" + arch[i] + "\"");
						Process p = pb.start();
						Scanner sc = new Scanner(new InputStreamReader(p.getInputStream()));
						while (sc.hasNext()) {
							sc.nextLine();
							lista=sc.nextLine().split(" ");
							ar.add(Integer.parseInt(lista[lista.length-1]));
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Integer i : ar) {
			suma += i; 
		}
		System.out.println("En total hay "+suma+" lineas en todos los archivos de esta carpeta");
		s.close();
	}

}
