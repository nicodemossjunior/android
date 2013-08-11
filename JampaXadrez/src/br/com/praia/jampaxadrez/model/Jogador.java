/*
 * Created on 06/08/2013 
 */
package br.com.praia.jampaxadrez.model;

/**
 * @author Junior
 *
 */
public class Jogador {
	
	private String name;
	private int vitorias;
	private int cor;
	
	public Jogador(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vitorias
	 */
	public int getVitorias() {
		return vitorias;
	}

	/**
	 * @param vitorias the vitorias to set
	 */
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	
	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Jogador) {
			return this.name.equals(((Jogador)o).getName());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + ", " + vitorias + " vitoria(s)";
	}

}
