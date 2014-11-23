/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.*;

/**
 *
 * @author A 46 CB i3
 */
@ManagedBean
@RequestScoped
public class ArrPost {
    
    // attribute
    private List<Post> post;
    private Connection con;
    private Statement ps;
    
    // default constructor
    public ArrPost() {
        post = new ArrayList<Post>();
    }
    
    // getter
    public List getPost() {
        return post;
    }
    
    // function
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2?zeroDateTimeBehavior=convertToNull", "root", "");
            ps = con.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void listPost() {
        connect();
        try {
            ResultSet rs = ps.executeQuery("select * from postdata");
            while(rs.next() == true) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setJudul(rs.getString(2));
                p.setTanggal(rs.getString(3));
                p.setKonten(rs.getString(4));
                p.setStatus(rs.getString(5));
                post.add(p);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}