package conexiones;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

public class Conexion {
    public Conexion() {
    }
    public Connection getConexion() throws SQLException,Exception{
	   //String url="jdbc:oracle:thin:VS2DAW_7/VS2DAW_7@10.0.1.12:1521:oradai";  	//instancia azarquiel en clase
       String url="jdbc:oracle:thin:VS2DAW_CARRO7/VS2DAW_CARRO7@//localhost:1521/xe"; 	 	//instancia local.
      //String url="jdbc:oracle:thin:VS2DAW_7/VS2DAW_7@80.59.249.199:1521:oradai"; 	//instancia azarquiel

        Connection con;
        OracleDataSource ods;
        try{
        	// Conexion tradicional sin uso del pool de conexiones
        
        	   ods=new OracleDataSource();
               ods.setURL(url);
               con=ods.getConnection();

             
      	   //Conexion usando el pool de conexiones definido en glassfish
      	   // asociado al nombre logico: jdbc/Biblioteca
           //haciendo uso de la interfaz JNDI para localizar el recurso.
         	/*
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("jdbc/Biblioteca");
            con=ds.getConnection();
            */
            DatabaseMetaData meta = con.getMetaData();
            // gets driver info:
            System.out.println("JDBC driver version is " + meta.getDriverVersion());
            System.out.println("Data Source definido y conexion establecida");
        }
        catch(SQLException sqle){
        	System.out.println(sqle.toString());
            throw sqle;
        }
        catch(Exception e){
        	System.out.println(e.toString());
            throw e;
        }
        return con;
    }
}

