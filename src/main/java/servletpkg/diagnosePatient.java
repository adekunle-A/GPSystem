package servletpkg;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;


/**
 * Servlet implementation class diagnosePatient
 */
public class diagnosePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//String owlfile = "https://raw.githubusercontent.com/aaa90/GPSystem/master/clinicalowlfile.owl";
	String owlfile = "https://github.com/aaa90/GPSystem/blob/master/clinicalowlfile.owl";
    PrintWriter out; 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("diagnosePatient.jsp");
		doPost(request, response);
	}//end doGet
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchby = request.getParameter("getselected");
		String getinfo = request.getParameter("searchinfo");
		System.out.println(searchby);
		System.out.println(getinfo);
		 // Set response content type
	     response.setContentType("text/html");
	     out = response.getWriter();
		//read file into ontology model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		try {
				File f = new File(owlfile);
				FileReader reader = new FileReader(f);
				model.read(reader,null);
		 if(!getinfo.contains(",") ==false) {
			String[] part = getinfo.split(",");
			System.out.println(part[1].trim());
			//query	
			String sparqlQuery =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"prefix owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"prefix info:<http://www.semanticweb.org/akintunde/ontologies/2017/7/untitled-ontology-3#> "
			+ "select *  where { \n" + 
			" \n" + 
			"  ?Diseases info:hasSymptoms ?Symptoms.\n" + 
			"  ?Diseases info:isLocatedIn ?Partof\n" + 
			"  FILTER (regex(str(?Symptoms), \""+part[0]+"\", \"i\") || "
					+ "regex(str(?Symptoms), \""+part[1].trim()+"\", \"i\"))\n" 
					+ "?Diseases rdfs:comment ?description}";
			
			String diseasesparqlQuery =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
					"prefix owl: <http://www.w3.org/2002/07/owl#>\n" + 
					"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
					"prefix info:<http://www.semanticweb.org/akintunde/ontologies/2017/7/untitled-ontology-3#> "
					+ "select *  where { \n" + 
					"  ?Diseases info:hasSymptoms ?Symptoms.\n" + 
					"  ?Diseases info:isLocatedIn ?Partof\n" + 
					"  FILTER (regex(str(?Diseases), \""+part[0]+"\", \"i\") || "
							+ "regex(str(?Diseases), \""+part[1].trim()+"\", \"i\"))\n" 
							+ "?Diseases rdfs:comment ?description}";
			if(searchby.equals("symptoms")) {
				Query query = QueryFactory.create(sparqlQuery);
				QueryExecution qe = QueryExecutionFactory.create(query,model);
				org.apache.jena.query.ResultSet rs = qe.execSelect();
				// list the statements in the Model
				while(rs.hasNext()){
					//write to a ByteArrayOutputStream
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					ResultSetFormatter.outputAsJSON(outputStream, rs);
					// and turn that into a String which is then sent to the client side
					String json = new String(outputStream.toByteArray());
					out.write(json);
				}
			}else if(searchby.equals("diseases")){
				Query query = QueryFactory.create(diseasesparqlQuery);
				QueryExecution qe = QueryExecutionFactory.create(query,model);
				org.apache.jena.query.ResultSet rs = qe.execSelect();
				while(rs.hasNext()){
					//write to a ByteArrayOutputStream
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					ResultSetFormatter.outputAsJSON(outputStream, rs);
					// and turn that into a String which is then sent to the client side
					String json = new String(outputStream.toByteArray());
					out.write(json);		
					//ResultSetFormatter.out(System.out,rs, query);
			     }
			 }else {
				 out.write("Invalid input enterd, please try Again");	
			 }//end  else 
			}else {
				if(searchby.equals("symptoms")) {
					String sparqlQuery2 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
							"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
							"prefix owl: <http://www.w3.org/2002/07/owl#>\n" + 
							"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
							"prefix info:<http://www.semanticweb.org/akintunde/ontologies/2017/7/untitled-ontology-3#> "
							+ "select * where {"+
							"  ?Diseases info:hasSymptoms ?Symptoms.\n" + 
							"  ?Diseases info:isLocatedIn ?Partof\n" + 
							"  FILTER (regex(str(?Symptoms), \""+getinfo+"\", \"i\")" +
							
							")"
							+ "?Diseases rdfs:comment ?description}";
					Query query = QueryFactory.create(sparqlQuery2);
					QueryExecution qe = QueryExecutionFactory.create(query,model);
					org.apache.jena.query.ResultSet rs = qe.execSelect();
					// list the statements in the Model
					while(rs.hasNext()){
						//write to a ByteArrayOutputStream
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						ResultSetFormatter.outputAsJSON(outputStream, rs);
						// and turn that into a String which is then sent to the client side
						String json = new String(outputStream.toByteArray());
						out.write(json);
					}//end while
				}else if(searchby.equals("diseases")){
					String diseasesparqlQuery2 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
							"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
							"prefix owl: <http://www.w3.org/2002/07/owl#>\n" + 
							"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
							"prefix info:<http://www.semanticweb.org/akintunde/ontologies/2017/7/untitled-ontology-3#> "
							+ "select * where {"+
							"  ?Diseases info:hasSymptoms ?Symptoms.\n" + 
							"  ?Diseases info:isLocatedIn ?Partof\n" + 
							"  FILTER (regex(str(?Diseases), \""+getinfo+"\", \"i\")" +
							
							")"
							+ "?Diseases rdfs:comment ?description}";
					Query query = QueryFactory.create(diseasesparqlQuery2);
					QueryExecution qe = QueryExecutionFactory.create(query,model);
					org.apache.jena.query.ResultSet rs = qe.execSelect();
					while(rs.hasNext()){
						//write to a ByteArrayOutputStream
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						ResultSetFormatter.outputAsJSON(outputStream, rs);
						// and turn that into a String which is then sent to the client side
						String json = new String(outputStream.toByteArray());
						out.write(json);		
						//ResultSetFormatter.out(System.out,rs, query);
				     }//end while
				   }//end else if
				}//end else
				}catch(Exception e) {
			System.out.println(e);
		}//end catch
	}//end doPost method
}//end diagnose patient
