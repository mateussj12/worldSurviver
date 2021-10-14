package com.onu.worldsurviver.exception;

/**
 * Classe: RegraNegocioException
 * 
 * Essa classe é responsável por enviar a mensagem ao compilador
 * atraves de um "throws exception".
 * 
 * @author: Mateus Santos de Jesus  
 */

public class RegraNegocioException extends RuntimeException{
    
    public RegraNegocioException (String mensagem) {
        
        super(mensagem);
    }
}
