/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.inicio;

import fes.aragon.codigo.ErrorSintactico;
import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author AEVC
 */
public class Inicio {
	private boolean error = false;
	private boolean encontroError = false;
	private Tokens tokens = null;
	private Lexico analizador = null;

	public static void main(String[] args) {
		Inicio ap = new Inicio();
		BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
			ap.analizador = new Lexico(buf);
			ap.siguienteToken();

			do {
				try {
					ap.secuencia();
					
				} catch (ErrorSintactico e) {
					System.out.println(e.getMessage());
					ap.encontroError = false ;
				}
			} while (ap.tokens.getLexema() != Sym.EOF);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void secuencia() throws ErrorSintactico {

		do {
			expresion();
			
			while (tokens.getLexema() != Sym.PUNTOCOMA) {
				if (!this.error && (tokens.getLinea() == 0)) {
					throw new ErrorSintactico("Expresión inválida: ERROR " + "en última línea");
				}	
				if (!this.error) {
					this.encontroError = true;
					throw new ErrorSintactico("Expresión inválida: ERROR línea " + (tokens.getLinea() + 1));
				}
				
			}
			
			if (!this.error && !this.encontroError) {
				System.out.println("Línea Correcta " + (tokens.getLinea() + 1));
			}
			
			siguienteToken();
		} while (tokens.getLexema() != Sym.EOF);
	}

	private void factor() throws ErrorSintactico {
		// Obtengo el lexema
		int token = tokens.getLexema();
		// Observo cual token es
		switch (token) {
		// Ejecuto accion segun sea el caso
		case Sym.ID:
			siguienteToken();
			break;

		case Sym.NUM:
			siguienteToken();
			break;

		case Sym.NOT:
			siguienteToken();
			factor();
			break;

		case Sym.PA:
			siguienteToken();
			expresion();
			if (tokens.getLexema() != Sym.PC) {
				this.error = false;
			} else {
				siguienteToken();
				break;
			}
		default:
			this.error = false;
		}
	}

	private void termino() throws ErrorSintactico {
		factor();
		while ((tokens.getLexema() == Sym.POR) || (tokens.getLexema() == Sym.DIV) || (tokens.getLexema() == Sym.DIV_ENT)
				|| (tokens.getLexema() == Sym.MOD) || (tokens.getLexema() == Sym.AND)) {
			siguienteToken();
			factor();
		}
	}

	private void exprSimple() throws ErrorSintactico {
		if ((tokens.getLexema() == Sym.MAS) || (tokens.getLexema() == Sym.MENOS)) {
			siguienteToken();
		}
		termino();
		while ((tokens.getLexema() == Sym.MAS) || (tokens.getLexema() == Sym.MENOS) || (tokens.getLexema() == Sym.OR)) {
			siguienteToken();
			termino();
		}
	}

	private void expresion() throws ErrorSintactico {
		exprSimple();
		if ((tokens.getLexema() == Sym.IGUAL) || (tokens.getLexema() == Sym.MENOR) || (tokens.getLexema() == Sym.MENORI)
				|| (tokens.getLexema() == Sym.DIST) || (tokens.getLexema() == Sym.MAYORI)
				|| (tokens.getLexema() == Sym.MAYOR)) {
			siguienteToken();
			exprSimple();
		}
	}
	
	private void siguienteToken() {
		try {
			tokens = analizador.yylex();
			if (tokens == null) {
				tokens = new Tokens("EOF", Sym.EOF, 0, 0);
				throw new IOException("Fin Archivo");
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

}
