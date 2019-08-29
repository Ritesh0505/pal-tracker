package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTimeEntryRepository implements TimeEntryRepository {
    DataSource ds;
    JdbcTemplate jdbcTemplate;
    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.ds=dataSource;

    }

    @Override
    public TimeEntry create(TimeEntry timeEntry)  {

        ResultSet rs=null;
        Connection con =null;

        PreparedStatement ps = null;
        try {
            con= ds.getConnection();
            ps =  con.prepareStatement("insert into time_entries (project_id, user_id, date, hours) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, timeEntry.getProjectId());
            ps.setLong(2, timeEntry.getUserId());
            ps.setDate(3, Date.valueOf(timeEntry.getDate()));
            ps.setInt(4, timeEntry.getHours());

            int rowAffected= ps.executeUpdate();
            if(rowAffected>0){
                rs=ps.getGeneratedKeys();
                rs.next();
                timeEntry.setId(rs.getInt(1));

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }




        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        TimeEntry timeEntry=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        Connection con =null;
        try {
            con= ds.getConnection();
            ps =  con.prepareStatement("select * from time_entries where id=?",Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, id);

            rs= ps.executeQuery();
            while(rs.next())
            timeEntry=new TimeEntry(rs.getLong("id"),rs.getLong("project_id"),rs.getLong("user_id"),rs.getDate("date").toLocalDate(),rs.getInt("hours"));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


return timeEntry;

    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries=new ArrayList<TimeEntry>();
        ResultSet rs=null;
        Connection con =null;

        PreparedStatement ps = null;
        try {
            con= ds.getConnection();
            ps = con.prepareStatement("select * from time_entries ");
            rs= ps.executeQuery();
            while(rs.next())
                timeEntries.add(new TimeEntry(rs.getLong("id"),rs.getLong("project_id"),rs.getLong("user_id"),rs.getDate("date").toLocalDate(),rs.getInt("hours"))) ;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return timeEntries;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntry1=null;
        ResultSet rs=null;
        Connection con =null;
        PreparedStatement ps = null;
        try {
            con= ds.getConnection();
            ps = con.prepareStatement("update time_entries set project_id=? ,user_id=?,date=?,hours=? where id=?");
            ps.setLong(1, timeEntry.getProjectId());
            ps.setLong(2, timeEntry.getUserId());
            ps.setDate(3, Date.valueOf(timeEntry.getDate()));
            ps.setInt(4, timeEntry.getHours());
            ps.setLong(5, id);
            int rowAffected= ps.executeUpdate();
            if(rowAffected>0){
                timeEntry1= find(id);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return timeEntry1;
    }

    @Override
    public void delete(long id) {
        TimeEntry timeEntry1=null;
        ResultSet rs=null;

        Connection con =null;
        PreparedStatement ps = null;
        try {
            con= ds.getConnection();
            ps =  con.prepareStatement("delete from time_entries where id=?");

            ps.setLong(1, id);
            int rowAffected= ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
