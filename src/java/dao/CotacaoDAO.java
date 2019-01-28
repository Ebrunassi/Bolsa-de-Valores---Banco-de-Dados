/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cotacao;

/**
 *
 * @author dskaster
 */
public class CotacaoDAO extends DAO<Cotacao> {

//    private static final String CREATE_QUERY      -- LINHA ANTERIOR À ALTERAÇÃO
//            = "INSERT INTO cotacoes (minima,cotacao,data,volume,empresa,variacao,maxima) VALUES(?,?,?,?,?,?,?)";
    private static final String CREATE_QUERY
            = "INSERT INTO empresa_cotacao (empresa,data,cotacao) VALUES (?,?,?);"
            + "INSERT INTO cotacao_min_max (empresa,data,minima,maxima) VALUES (?,?,?,?);"
            + "INSERT INTO empresa_var_vol (empresa,data,variacao,volume) VALUES (?,?,?,?);";
    
//    private static final String READ_QUERY        -- LINHA ANTERIOR À ALTERAÇÃO
//            = "SELECT empresa,data,minima,cotacao,volume,variacao,maxima FROM cotacoes WHERE empresa = ? AND data = ? ";
     private static final String READ_QUERY
            = "SELECT empresa,data,minima,cotacao,volume,variacao,maxima FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = ? AND data = ? ";

//    private static final String UPDATE_QUERY      -- LINHA ANTERIOR À ALTERAÇÃO
//            = "UPDATE cotacoes	SET minima = ?,cotacao = ?,volume = ?, variacao = ?,maxima = ? WHERE empresa=? AND data = ?";
    private static final String UPDATE_QUERY
            = "UPDATE empresa_cotacao SET cotacao = ? WHERE empresa= ? AND data = ?;"
            + "UPDATE cotacao_min_max SET minima = ?, maxima = ? WHERE empresa= ? AND data = ?;"
            + "UPDATE empresa_var_vol SET variacao = ?, volume = ? WHERE empresa= ? AND data = ?;";
    
//    private static final String DELETE_QUERY      -- LINHA ANTERIOR À ALTERAÇÃO
//            = "DELETE FROM cotacoes WHERE empresa = ? AND data = ?";
     private static final String DELETE_QUERY
             = "DELETE FROM empresa_var_vol WHERE empresa = ? AND data = ?;"
             + "DELETE FROM cotacao_min_max WHERE empresa = ? AND data = ?;"
             + "DELETE FROM empresa_cotacao WHERE empresa = ? AND data = ?;";

//    private static final String ALL_QUERY         -- LINHA ANTERIOR À ALTERAÇÃO
//            = "SELECT empresa,data,minima,cotacao,data,volume,variacao,maxima FROM cotacoes ORDER BY empresa";
    private static final String ALL_QUERY
            = "SELECT empresa,data,cotacao,minima,maxima,volume,variacao FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) ORDER BY empresa";

    private static final String AMBEV_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'AMBEV ON'" ;
    private static final String BAHEMA_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'BAHEMA ON'" ;
    private static final String BANRISUL_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'BANRISUL ON'" ;
    private static final String CIELO_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'CIELO ON'" ;
    private static final String ITAU_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'ITAUUNIBANCO ON'" ;
    private static final String MAGAZINE_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'MAG LUIZA ON'" ;
    private static final String PETROBRAS_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'PETROBRAS PN'" ;
    private static final String SANTANDER_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'SANTANDER BR ON'" ;
    private static final String VALE_ULT
                ="SELECT empresa,data,cotacao FROM cotacao_min_max NATURAL JOIN empresa_cotacao WHERE cotacao * 100 < ? AND empresa = 'VALE ON'" ;

    private static final String ALL_AMBEV_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'AMBEV ON'" ;
    private static final String ALL_BAHEMA_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'BAHEMA ON'" ;
    private static final String ALL_BANRISUL_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'BANRISUL ON'" ;
    private static final String ALL_CIELO_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'CIELO ON'" ;
    private static final String ALL_ITAU_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'ITAUUNIBANCO ON'" ;
    private static final String ALL_MAGAZINE_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'MAG LUIZA ON'" ;
    private static final String ALL_PETROBRAS_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'PETROBRAS PN'" ;
    private static final String ALL_SANTANDER_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'SANTANDER BR ON'" ;
    private static final String ALL_VALE_DIF
              ="SELECT empresa,data, (maxima-minima) AS diferenca FROM cotacao_min_max WHERE empresa = 'VALE ON'" ;
    
    
    
    
    private static final String ALL_AMBEV
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'AMBEV ON'" ;
    private static final String ALL_BAHEMA
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'BAHEMA ON'" ;
    private static final String ALL_BANRISUL
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'BANRISUL ON'" ;
    private static final String ALL_CIELO
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'CIELO ON'" ;
    private static final String ALL_ITAU
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'ITAUUNIBANCO ON'" ;
    private static final String ALL_MAGAZINE
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'MAG LUIZA ON'" ;
    private static final String ALL_PETROBRAS
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'PETROBRAS PN'" ;
    private static final String ALL_SANTANDER
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'SANTANDER BR ON'" ;
    private static final String ALL_VALE
              ="SELECT * FROM empresa_var_vol NATURAL JOIN (empresa_cotacao NATURAL JOIN cotacao_min_max) WHERE empresa = 'VALE ON'" ;
    
    
    
    private static final String MIN_MAX_AMBEV
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'AMBEV ON'" ;
    private static final String MIN_MAX_BAHEMA
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'BAHEMA ON'" ;
    private static final String MIN_MAX_BANRISUL
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'BANRISUL ON'" ;
    private static final String MIN_MAX_CIELO
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'CIELO ON'" ;
    private static final String MIN_MAX_ITAU
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'ITAUUNIBANCO ON'" ;
    private static final String MIN_MAX_MAGAZINE
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'MAG LUIZA ON'" ;
    private static final String MIN_MAX_PETROBRAS
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'PETROBRAS PN'" ;
    private static final String MIN_MAX_SANTANDER
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'SANTANDER BR ON'" ;
    private static final String MIN_MAX_VALE
              ="SELECT empresa,data,((minima+maxima)/2) AS media FROM cotacao_min_max WHERE empresa = 'VALE ON'" ;
    
    private static final String COTACAO_MEDIA
              ="SELECT empresa, AVG(cotacao)as media FROM empresa_cotacao GROUP BY empresa";
    
    private static final String GET_NUM_EMPRESAS
              = "SELECT COUNT(DISTINCT empresa) AS num_empresa FROM cotacao_min_max";
    
    private static final String GET_CRESCIMENTO_POSITIVO
              = "SELECT empresa, SUM(maxima - minima) AS variacao FROM cotacao_min_max GROUP BY empresa HAVING SUM(maxima - minima) > 0";
    private static final String GET_CRESCIMENTO_NEGATIVO
              = "SELECT empresa, SUM(volume) AS variacao FROM empresa_var_vol GROUP BY empresa HAVING SUM(variacao) < 0";
//    private static final String AUTHENTICATE_QUERY
//            = "SELECT id, nome, nascimento, avatar "
//            + "FROM j2ee.usuario "
//            + "WHERE login = ? AND senha = md5(?);";

    public CotacaoDAO(Connection connection) {

        super(connection);
    }

    @Override
    public void create(Cotacao cotacao) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
//            statement.setFloat(1, cotacao.getMinima());               ANTES
//            statement.setFloat(2, cotacao.getCotacaoValor());
//            statement.setString(3, cotacao.getData());
//            statement.setFloat(4, cotacao.getVolume());
//            statement.setString(5, cotacao.getEmpresa());
//            statement.setFloat(6, cotacao.getVariacao());
//            statement.setFloat(7, cotacao.getMaxima());               fim antes
            statement.setString(1, cotacao.getEmpresa());           // Iserção na tabela 'empresa.cotacao'
            statement.setString(2, cotacao.getData());              // 
            statement.setFloat(3, cotacao.getCotacaoValor());       // Fim!
            
            statement.setString(4, cotacao.getEmpresa());           // Iserção na tabela 'cotacao_min_max'
            statement.setString(5, cotacao.getData());              //
            statement.setFloat(6, cotacao.getMinima());             //
            statement.setFloat(7, cotacao.getMaxima());             // Fim!
            
            statement.setString(8, cotacao.getEmpresa());           // Iserção na tabela 'cotacao_min_max'
            statement.setString(9, cotacao.getData());              //
            statement.setFloat(10, cotacao.getVariacao());          //
            statement.setFloat(11, cotacao.getVolume());            // Fim!           

            statement.execute();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("pk_cotacao")) {
                throw new SQLException("Erro ao inserir na tabela, empresa já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir dados, pelo menos um campo estÃ¡ em branco.");
            } else {
                System.err.println("ERro:" + ex.getMessage());
                throw new SQLException("Erro ao inserir dados.");
            }
        }
    }

    @Override
    public Cotacao read(String empresa, String data) throws SQLException {
        Cotacao cotacao = new Cotacao();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, empresa);
            statement.setString(2, data);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    cotacao.setEmpresa(empresa);
                    cotacao.setData(data);
                    cotacao.setMinima(result.getInt("minima"));
                    cotacao.setCotacaoValor(result.getInt("cotacao"));
                    cotacao.setVolume(result.getInt("volume"));
                    cotacao.setVariacao(result.getInt("variacao"));
                    cotacao.setMaxima(result.getInt("maxima"));
                } else {
                    cotacao.setEmpresa("");
                    cotacao.setData("1");
//                    throw new SQLException("Erro ao visualizar: dados nÃ£o encontrado.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: dados nÃ£o encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar dados.");
            }
        }

        return cotacao;
    }

    @Override
    public void update(Cotacao cotacao) throws SQLException {
        String query;
            
//        if (cotacao.getSenha() == null) {
            query = UPDATE_QUERY;
//        } else {
//            query = UPDATE_WITH_PASSWORD_QUERY;
//        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setFloat(1, cotacao.getCotacaoValor());
            statement.setString(2, cotacao.getEmpresa());
            statement.setString(3, cotacao.getData());
            
            statement.setFloat(4, cotacao.getMinima());
            statement.setFloat(5, cotacao.getMaxima());
            statement.setString(6, cotacao.getEmpresa());
            statement.setString(7, cotacao.getData());
            
            statement.setFloat(8, cotacao.getVariacao());
            statement.setFloat(9, cotacao.getVolume());  
            statement.setString(10, cotacao.getEmpresa());
            statement.setString(11, cotacao.getData());
            

//            if (cotacao.getSenha() == null) {
//                statement.setInt(4, cotacao.getId());
//            } else {
//                statement.setString(4, cotacao.getSenha());
//                statement.setInt(5, cotacao.getId());
//            }

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: dados nÃ£o encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: dados nÃ£o encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_cotacoes_empresa")) {
                throw new SQLException("Erro ao editar dados: empresa jÃ¡ existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar dados: pelo menos um campo estÃ¡ em branco.");
            } else {
                throw new SQLException("Erro ao editar dados.");
            }
        }
    }

    @Override
    public void delete(String empresa, String data) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1,empresa);
            statement.setString(2,data);
            statement.setString(3,empresa);
            statement.setString(4,data);
            statement.setString(5,empresa);
            statement.setString(6,data);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: empresa nÃ£o encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: empresa nÃ£o encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir empresa.");
            }
        }
    }

    @Override
    public ArrayList<Cotacao> all() throws SQLException {
        ArrayList<Cotacao> usuarioList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setData(result.getString("data"));
                usuario.setCotacaoValor(result.getFloat("cotacao"));
                usuario.setMinima(result.getFloat("minima"));
                usuario.setMaxima(result.getFloat("maxima"));
                usuario.setVolume(result.getFloat("volume"));
                usuario.setVariacao(result.getFloat("variacao"));

                usuarioList.add(usuario);
            }   
            System.out.println(usuarioList.get(0).getEmpresa());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuÃ¡rios.");
        }

        return usuarioList;
    }
    
    public ArrayList<Cotacao> all_empresa(int num) throws SQLException {
        ArrayList<Cotacao> usuarioList = new ArrayList<>();
        ResultSet result = null;
        switch (num) {
            case 1:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_AMBEV);
                    result = statement.executeQuery();
                    break;
                }
            case 2:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_BAHEMA);
                    result = statement.executeQuery();
                    break;
                }
            case 3:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_BANRISUL);
                    result = statement.executeQuery();
                    break;
                }
            case 4:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_CIELO);
                    result = statement.executeQuery();
                    break;
                }
            case 5:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_ITAU);
                    result = statement.executeQuery();
                    break;
                }
            case 6:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_MAGAZINE);
                    result = statement.executeQuery();
                    break;
                }
            case 7:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_PETROBRAS);
                    result = statement.executeQuery();
                    break;
                }
            case 8:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_SANTANDER);
                    result = statement.executeQuery();
                    break;
                }
            case 9:
                {
                    PreparedStatement statement = connection.prepareStatement(MIN_MAX_VALE);
                    result = statement.executeQuery();
                    break;
                }
            default:
                break;
        }
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setData(result.getString("data"));
                usuario.setMinima(result.getFloat("media"));
                usuarioList.add(usuario);
            }
        return usuarioList;
    }
    public ArrayList<Cotacao> cotacao_media() throws SQLException {
        ArrayList<Cotacao> usuarioList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(COTACAO_MEDIA);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setCotacaoValor(result.getFloat("media"));

                usuarioList.add(usuario);
            }   
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuÃ¡rios.");
        }

        return usuarioList;
    }
    
    public int get_num_emp() throws SQLException{
        int num = 0;
        try (PreparedStatement statement = connection.prepareStatement(GET_NUM_EMPRESAS);
            ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                num = result.getInt("num_empresa");
            }  
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuÃ¡rios.");
        }
        return(num);
    }
    
    public ArrayList<Cotacao> get_cresc_positivo() throws SQLException{
         ArrayList<Cotacao> usuarioList = new ArrayList<>();
        int num = 0;
        try (PreparedStatement statement = connection.prepareStatement(GET_CRESCIMENTO_POSITIVO);
            ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setCotacaoValor(result.getFloat("variacao"));

                usuarioList.add(usuario);
            }  
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuÃ¡rios.");
        }
        return(usuarioList);
    }
        
    public ArrayList<Cotacao> get_cresc_negativo() throws SQLException{
         ArrayList<Cotacao> usuarioList = new ArrayList<>();
        int num = 0;
        try (PreparedStatement statement = connection.prepareStatement(GET_CRESCIMENTO_NEGATIVO);
            ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setCotacaoValor(result.getFloat("variacao"));

                usuarioList.add(usuario);
            }  
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuÃ¡rios.");
        }
        return(usuarioList);
    }
    
    public ArrayList<Cotacao> all_empresa_tudo(int num) throws SQLException {
        ArrayList<Cotacao> usuarioList = new ArrayList<>();
        ResultSet result = null;
        switch (num) {
            case 1:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_AMBEV);
                    result = statement.executeQuery();
                    break;
                }
            case 2:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_BAHEMA);
                    result = statement.executeQuery();
                    break;
                }
            case 3:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_BANRISUL);
                    result = statement.executeQuery();
                    break;
                }
            case 4:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_CIELO);
                    result = statement.executeQuery();
                    break;
                }
            case 5:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_ITAU);
                    result = statement.executeQuery();
                    break;
                }
            case 6:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_MAGAZINE);
                    result = statement.executeQuery();
                    break;
                }
            case 7:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_PETROBRAS);
                    result = statement.executeQuery();
                    break;
                }
            case 8:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_SANTANDER);
                    result = statement.executeQuery();
                    break;
                }
            case 9:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_VALE);
                    result = statement.executeQuery();
                    break;
                }
            default:
                break;
        }
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setData(result.getString("data"));
                usuario.setMinima(result.getFloat("minima"));
                usuario.setMaxima(result.getFloat("maxima"));
                usuario.setVariacao(result.getFloat("variacao"));
                usuario.setVolume(result.getFloat("volume"));
                usuario.setCotacaoValor(result.getFloat("cotacao"));
                usuarioList.add(usuario);
            }
        return usuarioList;
    }
    
    public ArrayList<Cotacao> all_empresa_dif(int num) throws SQLException {
        ArrayList<Cotacao> usuarioList = new ArrayList<>();
        ResultSet result = null;
        switch (num) {
            case 1:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_AMBEV_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 2:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_BAHEMA_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 3:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_BANRISUL_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 4:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_CIELO_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 5:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_ITAU_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 6:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_MAGAZINE_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 7:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_PETROBRAS_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 8:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_SANTANDER_DIF);
                    result = statement.executeQuery();
                    break;
                }
            case 9:
                {
                    PreparedStatement statement = connection.prepareStatement(ALL_VALE_DIF);
                    result = statement.executeQuery();
                    break;
                }
            default:
                break;
        }
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setData(result.getString("data"));
                usuario.setMinima(result.getFloat("diferenca"));
                usuarioList.add(usuario);
            }
        return usuarioList;
    }
    
    
    
    public ArrayList<Cotacao> all_empresa_pode_investir(int num, float valor) throws SQLException {
        ArrayList<Cotacao> usuarioList = new ArrayList<>();
        ResultSet result = null;                  
        
        switch (num) {
            case 1:
                {
                    PreparedStatement statement = connection.prepareStatement(AMBEV_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 2:
                {
                    PreparedStatement statement = connection.prepareStatement(BAHEMA_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 3:
                {
                    PreparedStatement statement = connection.prepareStatement(BANRISUL_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 4:
                {
                    PreparedStatement statement = connection.prepareStatement(CIELO_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 5:
                {
                    PreparedStatement statement = connection.prepareStatement(ITAU_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 6:
                {
                    PreparedStatement statement = connection.prepareStatement(MAGAZINE_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 7:
                {
                    PreparedStatement statement = connection.prepareStatement(PETROBRAS_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 8:
                {
                    PreparedStatement statement = connection.prepareStatement(SANTANDER_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            case 9:
                {
                    PreparedStatement statement = connection.prepareStatement(VALE_ULT);
                    statement.setFloat(1,valor);
                    result = statement.executeQuery();
                    break;
                }
            default:
                break;
        }
            while (result.next()) {
                Cotacao usuario = new Cotacao();
                usuario.setEmpresa(result.getString("empresa"));
                usuario.setData(result.getString("data"));
                usuario.setCotacaoValor(result.getFloat("cotacao"));
                usuarioList.add(usuario);
            }
        return usuarioList;
    }
    
//    public void authenticate(Usuario usuario) throws SQLException {
//        try (PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_QUERY)) {
//            statement.setString(1, usuario.getLogin());
//            statement.setString(2, usuario.getSenha());
//
//            try (ResultSet result = statement.executeQuery()) {
//                if (result.next()) {
//                    usuario.setId(result.getInt("id"));
//                    usuario.setNome(result.getString("nome"));
//                    usuario.setNascimento(result.getDate("nascimento"));
//                    usuario.setAvatar(result.getString("avatar"));
//                } else {
//                    throw new SecurityException("Login ou senha incorretos.");
//                }
//            }
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//
//            throw new SQLException("Erro ao autenticar usuÃ¡rio.");
//        }
//    }

}
