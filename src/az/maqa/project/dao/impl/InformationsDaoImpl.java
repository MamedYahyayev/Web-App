package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.InformationsDao;
import az.maqa.project.model.Informations;
import az.maqa.project.utility.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InformationsDaoImpl implements InformationsDao {

    @Override
    public List<Informations> getInformationsList() throws Exception {
        List<Informations> informationsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,INFORMATION FROM INFORMATIONS WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Informations informations = new Informations();
                    informations.setId(rs.getLong("ID"));
                    informations.setRownum(rs.getLong("r"));
                    informations.setInformations(rs.getString("INFORMATION"));

                    informationsList.add(informations);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return informationsList;
    }

    @Override
    public boolean add(Informations information) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO INFORMATIONS(ID, INFORMATION) " +
                " VALUES(INFORMATION_SEQ.nextval,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, information.getInformations());
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, null);
        }
        return result;
    }

    @Override
    public Informations getInformationById(Long id) throws Exception {
        Informations informations = new Informations();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,INFORMATION FROM INFORMATIONS WHERE (ACTIVE = 1) AND (ID = ?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {

                    informations.setId(rs.getLong("ID"));
                    informations.setRownum(rs.getLong("r"));
                    informations.setInformations(rs.getString("INFORMATION"));
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return informations;
    }

    @Override
    public boolean update(Informations informations, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE INFORMATIONS SET INFORMATION = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, informations.getInformations());
                ps.setLong(2, id);
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE INFORMATIONS SET ACTIVE = ? WHERE ID = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, 0);
                ps.setLong(2, id);
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Informations> search(String keyword) throws Exception {
        List<Informations> informationsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,INFORMATION FROM INFORMATIONS WHERE (ACTIVE = 1) AND LOWER(INFORMATION) LIKE  LOWER(?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Informations informations = new Informations();
                    informations.setId(rs.getLong("ID"));
                    informations.setRownum(rs.getLong("r"));
                    informations.setInformations(rs.getString("INFORMATION"));

                    informationsList.add(informations);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return informationsList;
    }
}
