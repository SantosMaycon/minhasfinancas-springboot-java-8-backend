package com.maycon.minhasfinancas.exceptions;

public class ErrorAutenticacao extends RuntimeException {	
	private static final long serialVersionUID = 1L;
	
	public ErrorAutenticacao(String msg){
		super(msg);
	}

}
