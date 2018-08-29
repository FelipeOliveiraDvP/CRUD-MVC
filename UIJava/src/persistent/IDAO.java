/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistent;

import java.util.List;
/**
 *
 * @author felipe
 */
public interface IDAO <T>{
    
    public void inserir() throws Exception; 
    List<T> listar() throws Exception;
    public void atualizar() throws Exception;
    public void excluir() throws Exception; 
}
