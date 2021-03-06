/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturaelectronica.service.impl;

import ec.facturaelectronica.dao.UsuarioDao;
import ec.facturaelectronica.model.Usuario;
import ec.facturaelectronica.service.UsuarioService;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Armando
 */
@Stateless
public class UsuarioServiceImpl implements UsuarioService{

    @EJB
    private UsuarioDao usuarioDao;
    
    @Override
    public Usuario getUsuarioPorCedula(final String cedula) {
        return usuarioDao.getUsuarioByCedula(cedula);
    }

    @Override
    public void crearUsuario(final Usuario usuario) {
        usuarioDao.insert(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    @Override
    public Usuario getUsuarioPorToken(String token) {
        return usuarioDao.getUsuarioByToken(token);
    }

    @Override
    public Usuario getUsuarioPorNick(String nick) {
        return usuarioDao.getUsuarioByNick(nick);
    }
    
}
