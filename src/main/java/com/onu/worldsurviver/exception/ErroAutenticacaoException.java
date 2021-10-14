package com.onu.worldsurviver.exception;

/**
 * Classe: ErroAutenticacaoExceptio
 * 
 * Essa classe é responsável por enviar a mensagem ao compilador
 * atraves de um "throws exception".
 * asasas
 * 
 * @author: Mateus Santos de Jesus  
 */

public class ErroAutenticacaoException extends RuntimeException {
    
    public ErroAutenticacaoException(String mensagem) {
        
        super(mensagem);

    }

}
