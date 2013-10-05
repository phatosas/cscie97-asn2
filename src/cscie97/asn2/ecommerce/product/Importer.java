package cscie97.asn2.ecommerce.product;

import cscie97.asn2.ecommerce.product.exception.ImportException;
import cscie97.asn2.ecommerce.product.exception.ParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads new Triples from an input file into the KnowledgeGraph.  The input file should be a plain text file
 * consisting of lines in the following format:
 * <p><blockquote><code>[subject (Node)] [space] [Predicate] [space] [object (Node)][period]</code></blockquote></p>
 * Matching lines are imported into the KnowledgeGraph as Triples.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see IProductAPI
 * @See ProductAPI
 */
public class Importer {

    /**
     * Public method for importing {@link cscie97.asn2.ecommerce.product.Country} items into the product catalog.
     * Checks for valid input file name.
     * Throws ImportException on error accessing or processing the input Country File.
     *
     * @param filename                file with countries to load into the product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    public static void importCountryFile(String filename) throws ImportException, ParseException {

        int lineNumber = 1;  // keep track of what lineNumber we're reading in from the input file for exception handling
        String line = null;  // store the text on each line as it's processed

        try {
            //KnowledgeGraph kg = KnowledgeGraph.getInstance();
            IProductAPI productAPI = ProductAPI.getInstance();


            BufferedReader reader = new BufferedReader(new FileReader(filename));

            /*

            // track the Triples we want to add to the KnowledgeEngine and load them all up once we're done reading the file
            List<Triple> triplesToAdd = new ArrayList<Triple>();

            while ((line = reader.readLine()) != null) {

                // trim off any trailing periods from the string
                line = line.replaceAll("\\.+$", "");

                // check if we encountered an empty line, and just skip to the next one if so
                if (line.length() == 0) { continue; }

                // check if the line, once "cleaned", is a minimum of 5 characters in length
                // (5 being the minimum number of characters allowed to be valid -
                // Subject+space, Predicate+space, Object); if the line is less than that, continue onto the next line
                String cleanedLine = kg.cleanTripleIdentifier(line);
                if ( cleanedLine == null || cleanedLine.length() < 5) { continue; }

                // ensure that the imported line does not contain any "?" characters
                if (cleanedLine.matches("(.*)\\?(.*)")) {
                    throw new ParseException("Import Triple line must not contain any queries, but actually contained a query character (?)",
                                                line,
                                                lineNumber,
                                                filename,
                                                null);
                }

                String[] parts = line.split("\\s");

                if (parts.length < 3) {
                    throw new ParseException("Triple line should have 3 parts, but only actually had ["+parts.length+"] parts: ["+line+"]",
                                                line,
                                                lineNumber,
                                                filename,
                                                null);
                }
                else {
                    // the first part should contain the first "Node"
                    Node subject = kg.getNode(parts[0]);  // node/subjects: Joe, Sue, Mary, etc.

                    // the second part should be the Predicate
                    Predicate predicate = kg.getPredicate(parts[1]);  // predicate: has_friend, plays, etc.

                    // last part should be the "object", also a Node
                    Node object = kg.getNode(parts[2]);  // object (also a node): Bill, Sue, Mary, Ultimate_Frisbee

                    triplesToAdd.add(new Triple(subject, predicate, object));
                }
                lineNumber++;
            }

            if (triplesToAdd.size() > 0) {
                kg.importTriples(triplesToAdd);
            }
            */

        }
        catch (FileNotFoundException fnfe) {
            throw new ImportException("Could not find file ["+filename+"] to open for reading", lineNumber, filename, fnfe);
        }
        catch (IOException ioe) {
            throw new ImportException("Encountered an IOException when trying to open ["+filename+"] for reading", lineNumber, filename, ioe);
        }
        catch (Exception e) {
            throw new ImportException("Caught a generic Exception when attempting to read file ["+filename+"]", lineNumber, filename, e);
        }
    }

}
