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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "  x  " + vitorias;
	}

}
