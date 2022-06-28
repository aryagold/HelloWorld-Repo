package za.co.vzap.Communication.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Communication.Model.EmailTemplate;
import za.co.vzap.Communication.Model.EmailTypeEnum;
import za.co.vzap.Interface.Repository.RepositoryBase;

public class EmailTemplateRepository extends RepositoryBase<EmailTemplate> {
     private static String tableName = "emailtemplate";
    
    public EmailTemplateRepository() {
        super(tableName, null);
    }
    
    @Override
    protected List<EmailTemplate> executeQuery(String statement) {
        List<EmailTemplate> templates = new ArrayList<>();

        if(con != null) {
            try {
                ps = con.prepareStatement(statement);

                rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");

                    EmailTemplate template = new EmailTemplate(
                            EmailTypeEnum.valueOf(rs.getInt("type")),
                            rs.getString("bodyTemplate"),
                            rs.getString("subjectTemplate")
                    );

                    template.Id = id;
                    templates.add(template);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return templates;
    }

    @Override
    public int add(EmailTemplate arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String add2(EmailTemplate arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(EmailTemplate arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmailTemplate getById(int Id) {
        EmailTemplate template = null;
        
        if(con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE ID = ?");
            
                ps.setInt(1, Id);
            
                rs = ps.executeQuery();

                if(rs.next()) {
                    template = new EmailTemplate(
                            EmailTypeEnum.valueOf(rs.getInt("type")),
                            rs.getString("bodyTemplate"),
                            rs.getString("subjectTemplate")
                    );
                    
                    template.Id = rs.getInt("id");
                }
           } catch(SQLException e) {
                e.printStackTrace();
            }
            finally {
                
                closeStreams(rs, ps);
                
            }
        }

        return template;
    }

    @Override
    public EmailTemplate getById(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
