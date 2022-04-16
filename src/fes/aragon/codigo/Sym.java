/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.codigo;

/**
 *
 * @author MASH
 */
public class Sym {
  public static final int PUNTOCOMA = 0;
  public static final int AND = 1;  
  public static final int NOT = 2;
  public static final int OR = 3;  
  public static final int IGUAL = 4;
  public static final int MAS = 5;
  public static final int MENOS = 6;
  public static final int POR = 7;
  public static final int DIV = 8;
  public static final int MAYOR = 9;
  public static final int MENOR = 10;
  public static final int MAYORI = 11;
  public static final int MENORI = 12;
  public static final int PA = 13;
  public static final int PC = 14;
  public static final int DIST = 15;
  public static final int MOD = 16;
  public static final int DIV_ENT = 17;
  public static final int NUM = 18;
  public static final int ID = 19;
  public static final int SALTOLINEA = 20;
  public static final int EOF = 21;
  public static final String[] terminales = new String[] {
  "PUNTOCOMA",  
  "AND",
  "NOT",
  "OR",
  "IGUAL",
  "MAS",
  "MENOS",
  "POR",
  "DIV",
  "MAYOR",
  "MENOR",
  "MAYORI",
  "MENORI",
  "PA",
  "PC",
  "DIST",
  "MOD",
  "NUM",
  "ID",
  "DIV_EVENT",
  "SALTOLINEA",
  "EOF"
  };
}
