package neo4j0;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Value;

import display.Display;

import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import static org.neo4j.driver.v1.Values.parameters;

import java.util.Iterator;
import java.util.List;

public class HelloWorldExample implements AutoCloseable
{
    private Driver driver;
    List<Record> list ;
    Display dispaly =  new Display(this) ;
    private String uri ;
    private String user ;
    private String password ;
    

    public HelloWorldExample( String uri, String user, String password )
    {
    	this.uri = uri ;
    	this.user = user ;
    	this.password = password ;
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
        
    }
    
    public void start() {
    	driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    StatementResult result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }
    public List<Record> printRead()
    {   System.out.println("2");
        try ( Session session = driver.session() )
        {  System.out.println("3");
            String greeting = session.readTransaction( new TransactionWork<String>()
            {      
                @Override
                public String execute( Transaction tx )
                {    System.out.println("4");
                     StatementResult result = tx.run("MATCH (n:Individus) RETURN n LIMIT 25");
                     list = result.list();
                     return "";
                }
            } );
           
        }
        System.out.println("5");
		return list;
        
    }
    
    public void printresult() {
    	for(int i =0; i<list.size();i++) {
    	 Iterator it =  list.get(i).get(0).asNode().values().iterator();
    	  while(it.hasNext())
    	  System.out.println(it.next().toString());
    		System.out.println(list.get(i).get(0).asNode().get("code_postal"));
    	}
    }

    public static void main( String... args ) throws Exception
    {
        try ( HelloWorldExample greeter = new HelloWorldExample( "bolt://localhost:7687", "neo4j", "nkhrjln" ) )
        { while(true)
           {
        	
           }
        }
    }
}