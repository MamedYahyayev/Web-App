package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.model.Lesson;
import az.maqa.project.utility.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl {
    public List<Lesson> getLessonList() throws Exception {
        List<Lesson> lessonList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,LESSON_NAME FROM LESSON WHERE ACTIVE = 1";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setRownum(rs.getLong("r"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return lessonList;
    }

    public boolean add(Lesson lesson) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO LESSON(ID,LESSON_NAME)  VALUES(LESSON_SEQ.nextval,?) ";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, lesson.getLessonName());
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public Lesson getLessonById(Long id) throws Exception {
        Lesson lesson = new Lesson();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,LESSON_NAME FROM LESSON WHERE (ACTIVE = 1) AND ID = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    lesson.setId(rs.getLong("ID"));
                    lesson.setRownum(rs.getLong("r"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return lesson;
    }

    public boolean update(Lesson lesson, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LESSON SET LESSON_NAME = ? WHERE ID = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, lesson.getLessonName());
                ps.setLong(2, id);
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LESSON SET ACTIVE = ? WHERE ID = ?";

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
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public List<Lesson> search(String keyword) throws Exception {
        List<Lesson> lessonList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,ID,LESSON_NAME FROM LESSON " +
                " WHERE (ACTIVE = 1) AND LOWER(LESSON_NAME) LIKE LOWER(?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setRownum(rs.getLong("r"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            Utility.closeJDBC(c, ps, rs);
        }

        return lessonList;
    }
}

