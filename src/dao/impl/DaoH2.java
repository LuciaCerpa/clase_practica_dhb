package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoH2 implements IDao<Veterinario> {
    public static final Logger logger = Logger.getLogger(DaoH2.class);
    public static final String INSERT = "INSERT INTO VETERINARIOS VALUES(DEFAULT,?,?,?,? )";
    public static final String SELECT_ALL = "SELECT * FROM VETERINARIOS";

    @Override
    public Veterinario guardar(Veterinario veterinario) {
        Connection connection = null;
        Veterinario veterinarioARetornar = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, veterinario.getNumeroLicencia());
            preparedStatement.setString(2, veterinario.getNombre());
            preparedStatement.setString(3, veterinario.getApellido());
            preparedStatement.setString(4, veterinario.getEspecialidad());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                veterinarioARetornar = new Veterinario(id, veterinario.getNumeroLicencia(), veterinario.getNombre(), veterinario.getApellido(),
                         veterinario.getEspecialidad());
            }
            if(veterinarioARetornar != null) logger.info("veterinario "+ veterinarioARetornar.toString());
        }catch (Exception e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                } finally {
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return veterinarioARetornar;
    }

    @Override
    public List<Veterinario> listaTodos() {
        Connection connection = null;
        List<Veterinario> listaVeterinarios = new ArrayList<>();
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                String numeroLicencia = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                String especialidad = resultSet.getString(5);

                Veterinario veterinario = new Veterinario(idDB, numeroLicencia, nombre, apellido, especialidad);
                listaVeterinarios.add(veterinario);
            }

            if (!listaVeterinarios.isEmpty()) {
                logger.info("Veterinarios encontrados: " + listaVeterinarios);
            } else {
                logger.info("No se encontraron veterinarios");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return listaVeterinarios;
    }
}
