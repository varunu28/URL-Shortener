package com.urlshort.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "fivepoint";

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		theModel.addAttribute("user", new User());
		return "user-form";
	}
	
	@RequestMapping("/processForm")
	public String showForm(@Valid @ModelAttribute("user") User user,
							BindingResult bindingResult) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        Statement stmt = null;

        conn = DriverManager.getConnection(URL,USER,PASSWORD);
        stmt = conn.createStatement();

        String sql = "CREATE DATABASE IF NOT EXISTS URL";
        stmt.executeUpdate(sql);

        sql = "USE URL";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE IF NOT EXISTS urls(id int(100) NOT NULL AUTO_INCREMENT,"
        		+ "originalUrl VARCHAR(255),shortenedUrl VARCHAR(255), PRIMARY KEY (id))";
        
        stmt.executeUpdate(sql);
        
        String inpURL = user.getUrl();
        
        // System.out.println(inpURL);

        ResultSet rs=stmt.executeQuery("select COUNT(*) from urls WHERE originalUrl='" + 
        				inpURL + "'");

        if (rs.next() && rs.getInt(1) == 0) {

            ShortURL shortURL = new ShortURL();
            int id = 0;

            rs = stmt.executeQuery("SELECT * FROM urls ORDER BY id DESC LIMIT 1");
            if (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }
            
            // System.out.println(id);

            String shortenedURL = shortURL.idToShortURL(++id);

            sql = "INSERT INTO urls(originalUrl,shortenedUrl) VALUES('" + inpURL + "','" + 
            			shortenedURL + "')";
            stmt.executeUpdate(sql);
            
            user.setShortUrl(shortenedURL);

            // System.out.println("The shortened URL is: " + shortenedURL);
            
            return "user-confirmation";
        }
        else {
            System.out.println("The url already exists in the database");
            
            return "form-already-exist";
        }
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
}
