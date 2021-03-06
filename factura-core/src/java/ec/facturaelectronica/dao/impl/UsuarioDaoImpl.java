/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturaelectronica.dao.impl;


import ec.facturaelectronica.dao.UsuarioDao;
import ec.facturaelectronica.model.Empresa;
import ec.facturaelectronica.model.Perfil;
import ec.facturaelectronica.model.Usuario;
import ec.facturaelectronica.model.enumtype.EstadosGeneralesEnum;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author desarrollotic
 */
@Stateless
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements UsuarioDao {

    public UsuarioDaoImpl() {
        super(Usuario.class);
    }

    @Override
    public Usuario getUsuario(String username, String password) {

        List<Usuario> usuarios;

        Query q = em.createNamedQuery("Usuario.ValidateUser");
        q.setParameter("nickUsuario", username);
        q.setParameter("claveUsuario", password);
        q.setParameter("idEstadoCatalogo", EstadosGeneralesEnum.Activo.getOrden());

        usuarios = q.getResultList();


        if (usuarios.isEmpty()) {
            return null;
        }

        return (Usuario) usuarios.get(0);
    }

    @Override
    public Usuario getUsuarioByNick(String username) {
        List<Usuario> usuarios;

        Query q = em.createNamedQuery("Usuario.findByNick");
        q.setParameter("nickUsuario", username);

        usuarios = q.getResultList();


        if (usuarios.isEmpty()) {
            return null;
        }

        return (Usuario) usuarios.get(0);

    }

    @Override
    public List<Usuario> getUsuariosxPerfil(Perfil perfil) {
        List<Usuario> usuarios;

        Query q = em.createNamedQuery("Usuario.findByPerfil");
        q.setParameter("idPerfil", perfil);
        q.setParameter("idEstadoCatalogo", EstadosGeneralesEnum.Activo.getOrden());

        usuarios = q.getResultList();

        return usuarios;

    }

    @Override
    public List<Usuario> getUsuarios() {

        List<Usuario> usuarios;

        Query q = em.createNamedQuery("Usuario.findAll");
        q.setParameter("idEstadoCatalogo", EstadosGeneralesEnum.Activo.getOrden());

        usuarios = q.getResultList();

        return usuarios;


    }

    @Override
    public List<Usuario> getUsuarioByEmpresa(Empresa empresa) {

        List<Usuario> usuarios;

        Query q = em.createNamedQuery("Usuario.findByEmpresa");
        q.setParameter("idEstadoCatalogo", EstadosGeneralesEnum.Activo.getOrden());
        q.setParameter("idEmpresa", empresa);

        usuarios = q.getResultList();

        return usuarios;

    }

    @Override
    public Usuario getUsuarioByCedula(final String cedula) {
        List<Usuario> usuarios;
        Usuario usuario = null;
        
        Query qry = em.createNamedQuery("Usuario.findByCedulaUsuario");
        qry.setParameter("cedula", cedula);
        
        usuarios = qry.getResultList();
        if(!usuarios.isEmpty()){
            usuario = usuarios.get(0);
        }
        return usuario;
    }

    @Override
    public Usuario getUsuarioByToken(final String token) {
        List<Usuario> usuarios;
        Usuario usuario = null;
        
        Query qry = em.createNamedQuery("Usuario.findByUsuarioToken");
        qry.setParameter("token", token);
        
        usuarios = qry.getResultList();
        if(!usuarios.isEmpty()){
            usuario = usuarios.get(0);
        }
        return usuario;        
    }
}
