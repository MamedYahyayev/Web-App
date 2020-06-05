package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.StudentDao;
import az.maqa.project.model.Role;
import az.maqa.project.model.Student;
import az.maqa.project.utility.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,STUDENT.ID,STUDENT.NAME,STUDENT.SURNAME,STUDENT.USERNAME,STUDENT.PASSWORD,ROLE.ROLE_NAME,ROLE.ID role_id FROM STUDENT " +
                " INNER JOIN ROLE ON STUDENT.ROLE_ID = ROLE.ID WHERE STUDENT.ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);

                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return studentList;
    }

    @Override
    public boolean add(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO STUDENT(ID,NAME,SURNAME,USERNAME,PASSWORD,ROLE_ID) " +
                " VALUES(STUDENT_SEQ.NEXTVAL,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getUsername());
                ps.setString(4, student.getPassword());
                ps.setLong(5, student.getRole().getId());
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
    public Student getStudentById(Long id) throws Exception {
        Student student = new Student();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,STUDENT.ID,STUDENT.NAME,STUDENT.SURNAME,STUDENT.USERNAME,STUDENT.PASSWORD,ROLE.ROLE_NAME,ROLE.ID role_id FROM STUDENT " +
                " INNER JOIN ROLE ON STUDENT.ROLE_ID = ROLE.ID WHERE (STUDENT.ACTIVE = 1) AND (STUDENT.ID = ?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {

                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return student;
    }

    @Override
    public boolean update(Student student, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE STUDENT SET NAME = ?,SURNAME = ? , USERNAME =? ,PASSWORD = ?, ROLE_ID = ? " +
                " WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getUsername());
                ps.setString(4, student.getPassword());
                ps.setLong(5, student.getRole().getId());
                ps.setLong(6, id);
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
        String sql = "UPDATE STUDENT SET ACTIVE = ? WHERE ID = ?";

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
    public List<Student> search(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM  r,\n" +
                "       STUDENT.ID,\n" +
                "       STUDENT.NAME,\n" +
                "       STUDENT.SURNAME,\n" +
                "       STUDENT.USERNAME,\n" +
                "       STUDENT.PASSWORD,\n" +
                "       ROLE.ROLE_NAME,\n" +
                "       ROLE.ID role_id\n" +
                "FROM STUDENT\n" +
                "         INNER JOIN ROLE ON STUDENT.ROLE_ID = ROLE.ID\n" +
                "WHERE (STUDENT.ACTIVE = 1)\n" +
                "  AND (LOWER(STUDENT.NAME) LIKE LOWER(?) OR LOWER(STUDENT.SURNAME) LIKE LOWER(?) OR\n" +
                "        LOWER(STUDENT.USERNAME) LIKE LOWER(?) OR LOWER(STUDENT.PASSWORD) LIKE LOWER(?) OR LOWER(ROLE.ROLE_NAME) LIKE LOWER(?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);

                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }
        return studentList;
    }
}
