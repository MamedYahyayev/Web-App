package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.ClassDao;
import az.maqa.project.model.ClassRoom;
import az.maqa.project.utility.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoImpl implements ClassDao {

    @Override
    public List<ClassRoom> getClassList() throws Exception {
        List<ClassRoom> classRoomsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  ROWNUM r,ID,CLASS_NUMBER FROM CLASS WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("ID"));
                    classRoom.setRownum(rs.getLong("r"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    classRoomsList.add(classRoom);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return classRoomsList;
    }

    @Override
    public boolean add(ClassRoom classRoom) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO CLASS(ID,CLASS_NUMBER) " +
                " VALUES(CLASS_SEQ.NEXTVAL,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, classRoom.getClassNumber());
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
    public ClassRoom getClassRoomById(Long id) throws Exception {
        ClassRoom classRoom = new ClassRoom();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  ROWNUM r,ID,CLASS_NUMBER FROM CLASS WHERE (ACTIVE = 1) AND (ID = ?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    classRoom.setId(rs.getLong("ID"));
                    classRoom.setRownum(rs.getLong("r"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return classRoom;
    }

    @Override
    public boolean update(ClassRoom classRoom, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE CLASS SET CLASS_NUMBER=? WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, classRoom.getClassNumber());
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
        String sql = "UPDATE CLASS SET ACTIVE = ? WHERE ID = ?";

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
    public List<ClassRoom> search(String keyword) throws Exception {
        List<ClassRoom> classRoomsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  ROWNUM r,ID,CLASS_NUMBER FROM CLASS WHERE (ACTIVE = 1) AND  LOWER(CLASS_NUMBER) LIKE LOWER(?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("ID"));
                    classRoom.setRownum(rs.getLong("r"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    classRoomsList.add(classRoom);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return classRoomsList;
    }
}
