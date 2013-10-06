package cscie97.asn2.ecommerce.test;

import cscie97.asn2.ecommerce.product.*;
import cscie97.asn2.ecommerce.product.exception.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

/**
 * Test harness for the CSCI-E 97 Assignment 2.  Reads in several supplied input files to load
 * {@link cscie97.asn2.ecommerce.product.Country} objects, {@link cscie97.asn2.ecommerce.product.Device} objects, then
 * {@link cscie97.asn2.ecommerce.product.Content} items, and finally runs several search queries against the
 * {@link cscie97.asn2.ecommerce.product.IProductAPI} to find the content that was loaded.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see Importer
 * @see IProductAPI
 * @see Content
 * @see ContentSearch
 */
public class TestDriver {

    /**
     * Executes the primary test logic.  Accepts four command line arguments that should be CSV files.  Arguments should be:
     * <ol>
     *     <li>filename of Countries CSV datafile</li>
     *     <li>filename of Devices CSV datafile</li>
     *     <li>filename of Products CSV datafile</li>
     *     <li>filename of Search Queries CSV datafile</li>
     * </ol>
     *
     * Calls several methods on the {@link cscie97.asn2.ecommerce.product.Importer} class to load the CSV datafile
     * arguments, including {@link Importer#importCountryFile(String guid, String filename)},
     * {@link Importer#importDeviceFile(String guid, String filename)},
     * {@link Importer#importContentFile(String guid, String filename)}, and
     * {@link Importer#importSearchQueryFile(String filename)}
     *
     *
     *
     * {@link Importer#importCountryFile(String)} method, passing in
     * the name of the provided triple file (first argument).
     * After loading the input triples, invokes the {@link QueryEngine#executeQueryFilename(String)} method passing
     * in provided query file name (second argument).  Outputs all results to standard out.
     *
     * @param args  first argument should be an input file containing one Triple per line, second argument should
     *              be a query file containing one Triple query per line
     */


//java ­cp . cscie97.asn2.ecommerce.test.TestDriver countries.csv devices.csv products.csv queries.csv

    public static void main(String[] args) {
        if (args.length == 4) {
            try {

                // later versions will require proper authentication and authorization to use restricted interface
                // methods on the ProductAPI, but for now we will mock this using a fake GUID
                String myGUID = "hope this works!";


                //Importer.importTripleFile(args[0]);

                Importer.importCountryFile(myGUID, args[0]);
                Importer.importDeviceFile(myGUID, args[1]);

                Importer.importContentFile(myGUID, args[2]);

                //ContentSearch searchObject = Importer.importContentFile(args[2]);

                //QueryEngine.executeQueryFilename(args[1]);

            }
            // if we catch a ParseException, either the original import of Triples failed or the Query on those
            // Triples; in either case, the entire program execution should fail and the errors in the original files
            // fixed before the program can be executed again
            catch (ParseException pe) {
                System.out.println(pe.getMessage());
                System.exit(1);
            }
            // if we catch an ImportException, the original load of Triples failed for some reason, so the program
            // should fail and exit
            catch (ImportException ie) {
                System.out.println(ie.getMessage());
                System.exit(1);
            }
            /*
            // if we catch an ImportException, the original load of Triples failed for some reason, so the program
            // should fail and exit
            catch (QueryEngineException qee) {
                System.out.println(qee.getMessage());
                System.exit(1);
            }
            */
        }
        else {
            System.out.println("Arguments to TestDriver should be: " +
                                    "1) import Countries CSV file, " +
                                    "2) import Devices CSV file, " +
                                    "3) import Products CSV file, and " +
                                    "4) input Search Query CSV file");
            System.exit(1);
        }
    }


}
