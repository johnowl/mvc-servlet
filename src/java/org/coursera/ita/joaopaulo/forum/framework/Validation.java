package org.coursera.ita.joaopaulo.forum.framework;

import java.util.List;


public interface Validation {

    List<Erro> getErros();
    boolean estaValido();
	
}
