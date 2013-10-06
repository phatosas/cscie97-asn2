package cscie97.asn2.ecommerce.test;

import cscie97.asn2.ecommerce.product.exception.ImportException;
import cscie97.asn2.ecommerce.product.exception.QueryEngineException;
import cscie97.asn2.ecommerce.product.exception.ParseException;
import cscie97.asn2.ecommerce.product.Importer;
import cscie97.asn2.ecommerce.product.IProductAPI;
import cscie97.asn2.ecommerce.product.Content;
import cscie97.asn2.ecommerce.product.ContentSearch;
import cscie97.asn2.ecommerce.product.SearchEngine;

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
     * Executes the primary test logic.  Accepts four command line arguments that should be CSV files.  Command-line
     * arguments should be:
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
     * {@link Importer#importContentFile(String guid, String filename)}.  Once all Counties, Devices, and Content are
     * loaded, calls {@link cscie97.asn2.ecommerce.product.SearchEngine#executeQueryFilename(String filename)} to
     * import a CSV of queries to run against the ProductAPI for content items.
     *
     * All content item search queries and results are printed to standard out.
     *
     * @param args  first argument should a CSV datafile of Countries, second argument should be a CSV datafile of
     *              Devices, third argument should be a CSV datafile of Content items, and fourth (and last) argument
     *              should be a CSV datafile of content search query parameters
     */
    public static void main(String[] args) {
        if (args.length == 4) {
            try {
                // later versions will require proper authentication and authorization to use restricted interface
                // methods on the ProductAPI, but for now we will mock this using a fake GUID
                String myGUID = "hope this works!";

                Importer.importCountryFile(myGUID, args[0]);

                Importer.importDeviceFile(myGUID, args[1]);

                Importer.importContentFile(myGUID, args[2]);

                SearchEngine.executeQueryFilename(args[3]);
            }
            // if we catch a ParseException, either the original import of Countries, Devices, Content or the
            // execution of the Search Query caused the problem; in either case, the entire program execution should
            // fail and the errors in the original files fixed before the program can be executed again
            catch (ParseException pe) {
                System.out.println(pe.getMessage());
                System.exit(1);
            }
            // if we catch an ImportException, the original load of Countries, Devices, or Content failed for some
            // reason, so the program should fail and exit
            catch (ImportException ie) {
                System.out.println(ie.getMessage());
                System.exit(1);
            }
            // if we catch a QueryEngineException, there was a problem running one of the queries in the file, so
            // the program should fail and exit
            catch (QueryEngineException qee) {
                System.out.println(qee.getMessage());
                System.exit(1);
            }
        }
        else {
            System.out.println("Arguments to TestDriver should be: " +
                                    "1) import Countries CSV file, " +
                                    "2) import Devices CSV file, " +
                                    "3) import Products CSV file, and " +
                                    "4) execute Search Query CSV file");
            System.exit(1);
        }
    }
}