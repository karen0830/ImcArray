import javax.swing.JOptionPane;

public class Proceso{
	//menu
	double datos[][];
	public String[][] nombres = new String[0][0];
	public void menu() {
		String menu = "";
		menu = "IMC\n";
		menu+="1. Ingresar datos\n";
		menu+= "2. Imprimir listas\n";
		menu += "3. Consulta individual\n";
		menu += "4. Actualizar\n";
		menu+= "5. Salir\n";
		int opcion = 0;
		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));;
			opcionIngresada(opcion);
		}while(opcion != 5);
		
	}

	public void opcionIngresada(int opcion) {
		switch (opcion) {
		case 1:
			if(nombres.length > 0) {
				System.out.println("Usted ya lleno los datos");
			}else ingresarP();
			break;
		case 2: 
			if(nombres.length != 0) {
				imprimirListas();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 3: 
			if(nombres.length != 0) {
				nombresConsulta();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 4: 
			if(nombres.length != 0) {
				menuActualizar();
			}else System.out.println("Llene los datos");
			System.out.println("------");
			break;
		case 5: 
			System.out.println("ADIOS");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Ingrese opcion valida", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ingresarP() {
		int x = validarNegativos("Ingrese la cantidad de personas a consultar su IMC");
		nombres = new String[x][2];
		datos = new double[x][3];
		String resultado = "";
		double imc = 0;
		for(int i = 0; i < nombres.length; i++) {
			nombres[i][0] = JOptionPane.showInputDialog("Ingrese nombre de la persona " + (i+1));
			datos[i][0] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso de "+ nombres[i][0])); 
			datos[i][1] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese estatura de "+ nombres[i][0]));
			imc = datos[i][0] / (datos[i][1] * datos[i][1]);
			datos[i][2] = imc;
			resultado = resultadoImc(imc);
			nombres[i][1] = resultado;
		}
	}

	public void imprimirListas() {
		// TODO Auto-generated method stub
		for(int i = 0; i < nombres.length; i++){
			System.out.println("Nombre: " + nombres[i][0]+" Peso: " 
					+ datos[i][0] + " Estatura: " + datos[i][1] + " IMC: " + Math.round(datos[i][2])
							+"  Resultado: " + nombres[i][1]);
		}
	}

	private void consultaPorNombre(int x) {
		String nombre= "";
		if(x >= 0) {
			System.out.println("Nombre: " + nombres[x][0]+" Peso: " 
		+ datos[x][0] + " Estatura: " + datos[x][1] + " IMC: " + Math.round(datos[x][2])
				+" Resultado: " + nombres[x][1]);
		}if(x == -1) {
			int cont = 0;
			nombre = JOptionPane.showInputDialog("Ingrese nombre a buscar");
			for(int i = 0; i < nombres.length; i++) {
				if(nombres[i][0].equalsIgnoreCase(nombre)) {
					System.out.println("Nombre: " + nombres[i][0]+" Peso: " 
							+ datos[i][0] + " Estatura: " + datos[i][1] + " IMC: " + Math.round(datos[i][2])
									+" Resultado: " + nombres[i][1]);
					cont++;
				}
			}
			if(cont == 0) {
				System.out.println(nombre + " no fue registrado");
			}
		}
		
	}
	
	public void nombresConsulta() {
		String nombre = "Ingrese: \n";
		int j = 1, x;
		nombre += j + ". Consulta individual\n";
		for(int i = 0; i < nombres.length; i++){
			j+=1;
			nombre += (j)+". " + nombres[i][0] + "\n";
		}
		nombre += j+1 + ". Salir";
		do {
			x = validarOpcionNombre(nombre, j+1);
			if(x != j+1) {
				consultaPorNombre(x-2);
			}
		}while(x != j+1);
	}
	
	public void actualizarPersona(int x) {
		
	}

	private void menuActualizar() {
		String nombre = "Ingrese: \n";
		int j = 1, x;
		nombre += j + ". Buscar nombre para actualizar\n";
		for(int i = 0; i < nombres.length; i++){
			j+=1;
			nombre += (j)+". " + nombres[i][0] + "\n";
		}
		nombre += j+1 + ". Salir";
		do {
			x = validarOpcionNombre(nombre, j+1);
			if(x != j+1) {
				actualizarDato(x-2);
			}
		}while(x != j+1);
	}
	
	public void actualizarDato(int x) {
		String nombre = "";
		if(x >= 0) {
			datoActualizado(x);
		}else if(x == -1) {
			int cont = 0;
			nombre = JOptionPane.showInputDialog("Inrese nombre a actualizar");
			for(int i = 0; i < nombres.length; i++) {
				if(nombres[i][0].equalsIgnoreCase(nombre)) {
					datoActualizado(i);
					cont++;
				}
			}
			if(cont == 0) {
				System.out.println(nombre + " no fue registrado");
			}
		}
		
	}
	
	public void datoActualizado(int x) {
		int actualizar = 0;
		double imc = 0;
		do {
			actualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desea actualizar\n"
					+ "1. Peso\n2. Talla\n3. salir"));
			if(actualizar == 1) {
				datos[x][0] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo peso de: " + nombres[x][0]));
			}else if(actualizar == 2) {
				datos[x][1] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo peso de: " + nombres[x][0]));
			}else if(actualizar < 1 || actualizar > 3) {
				JOptionPane.showMessageDialog(null, "Ingrese opción valida");
			}
			imc = datos[x][0] / (datos[x][1] * datos[x][1]);
			datos[x][2] = imc;
			nombres[x][1] = resultadoImc(imc);
		}while(actualizar != 3);
		
		
	}
	
	public String resultadoImc(double imc) {
		String resultado = ""; 
		if(imc < 18) {
			resultado = "Anorexia";
		}else if(imc >= 18 && imc< 20) {
			resultado = "Delgadez";
		}else if(imc >= 20 && imc < 27) {
			resultado = "Normalidad";
		}else if(imc >= 27 &&imc < 30) {
			resultado ="Obesidad (grado 1)";
		}else if(imc >= 30 && imc < 35) {
			resultado = "Obesidad (grado 2)";
		}else if(imc >= 35 && imc < 40) {
			resultado = "Obesidad (grado 3)";
		}else if(imc >= 40) {
			resultado = "Obesidad m�rbida";
		}
		return resultado;
	}
	
	private int validarOpcionNombre(String msj, int j) {
		int x =0;
		do {
			x = Integer.parseInt(JOptionPane.showInputDialog(msj));
			if(x < 1 || x > j) {
				JOptionPane.showMessageDialog(null, "Ingrese opcion valida", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}while(x < 1 || x > j);
		return x;
	}

	
	private int validarNegativos(String msj) {
		int x;
		do {
			x = Integer.parseInt(JOptionPane.showInputDialog(msj));
			if(x < 0) {
				JOptionPane.showMessageDialog(null, "No se ppremiten números negativos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}while(x <= 0);
		return x;
	}

}