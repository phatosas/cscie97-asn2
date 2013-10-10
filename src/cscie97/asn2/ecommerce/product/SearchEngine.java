package cscie97.asn2.ecommerce.product;

import cscie97.asn2.ecommerce.product.exception.ImportException;
import cscie97.asn2.ecommerce.product.exception.ParseException;
import cscie97.asn2.ecommerce.product.exception.QueryEngineException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Provides public static methods for querying the {@link cscie97.asn2.ecommerce.product.ProductAPI} for
 * {@link cscie97.asn2.ecommerce.product.Content} items.  Delegates the actual search logic to the
 * {@link cscie97.asn2.ecommerce.product.IProductAPI}.
 *
 * For executing {@link cscie97.asn2.ecommerce.product.Content} searches by passing in a CSV with search criteria, the
 * query file must be in the following column format:
 * <ol>
 *     <li>list of content categories (pipe-separated)</li>
 *     <li>search text (will scan {@link cscie97.asn2.ecommerce.product.Content} name, description, author name)</li>
 *     <li><b>minimum</b> content rating (from 0 to 5, where 5 is best)</li>
 *     <li><b>maximum</b> price (as a float, in BitCoins)</li>
 *     <li>list of supported language codes (pipe-separated)</li>
 *     <li>list of country codes (pipe-separated)</li>
 *     <li>device ID</li>
 *     <li>list of content types (can be any or all of "application", "ringtone", or "wallpaper" currently)</li>
 * </ol>
 * For example, here is a sample of what a valid content item query CSV file might look like:
 * <pre>
 * # catgory_list, text_search, minimum_rating, max_price, language_list, country_code, device_id, content_type_list
 * # search for "Ferrari" in name or description
 *  , Ferrari, , , , , ,
 * # search for all content with a minimum rating of 4 and a price of 0 or less (i.e. free)
 *  , , 4, 0, , , ,
 * ...
 * </pre>
 * Content items found are printed to standard out.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see IProductAPI
 * @see ProductAPI
 * @see Content
 * @see Importer
 * @see Application
 * @see Ringtone
 * @see Wallpaper
 */
public class SearchEngine {

    /**
     * Given a string line from the original query CSV file, parse out the contents of the search query and create a
     * new {@link cscie97.asn2.ecommerce.product.ContentSearch} object that can be passed to
     * {@link cscie97.asn2.ecommerce.product.IProductAPI} to execute the actual query and find matching content items.
     * Found matching content items are printed to standard out.
     *
     * @param queryLine         the original line from the search CSV to search for matching content
     * @throws ParseException   if there is an issue parsing out the search content query criteria from queryLine
     */
    public static void executeQuery(String queryLine) throws ParseException {
        // detect and reject invalid input query lines
        if (queryLine == null || queryLine.length() == 0) {
            throw new ParseException("Search query line is invalid for parsing to search for content ["+queryLine+"]", null, -1, null, null);
        }

        IProductAPI productAPI = ProductAPI.getInstance();  // reference to ProductAPI for searching for content items

        // break out the content search query into its constituent parts
        String[] cleanedColumns = Importer.parseCSVLine(queryLine, ",");

        // if there was a problem parsing out the search criteria, throw a ParseException
        if (cleanedColumns == null || cleanedColumns.length != 8) {
            throw new ParseException("Unable to properly parse search content query ["+queryLine+"]; expected 8 search criteria, but only found ["+cleanedColumns.length+"]", null, -1, null, null);
        }

        // remove any leading or trailing whitespaces from the cleaned columns
        for (int i=0; i<cleanedColumns.length; i++) {
            cleanedColumns[i] = cleanedColumns[i].trim();
        }

        // search criteria will be added to this object as the individual criteria columns are parsed
        ContentSearch searchCriteria = new ContentSearch();
        searchCriteria.setRawQuery(queryLine);

        // get the search content categories
        if (cleanedColumns[0] != null && cleanedColumns[0].length() > 0) {
            // need to parse out the search categories by splitting on the pipe character
            String[] parsedCategories = Importer.parseCSVLine(cleanedColumns[0], "\\|");
            if (parsedCategories != null && parsedCategories.length > 0) {
                searchCriteria.setCategories(new HashSet<String>(Arrays.asList(parsedCategories)));
            }
        }
        // get the search content text string
        if (cleanedColumns[1] != null && cleanedColumns[1].length() > 0) {
            searchCriteria.setTextSearch(cleanedColumns[1].trim());
        }
        // get the search content minimum rating
        if (cleanedColumns[2] != null && cleanedColumns[2].length() == 1) {
            try {
                searchCriteria.setMinimumRating(Integer.parseInt(cleanedColumns[2]));
            }
            catch (NumberFormatException nfe) {
                throw new ParseException("Execute Query line contains invalid data for the minimum content rating ["+cleanedColumns[2].toString()+"].",
                        queryLine,
                        -1,
                        null,
                        null);
            }
        }
        // get the search content maximum price
        if (cleanedColumns[3] != null && cleanedColumns[3].length() == 1) {
            try {
                searchCriteria.setMaximumPrice(Float.parseFloat(cleanedColumns[3]));
            }
            catch (NumberFormatException nfe) {
                throw new ParseException("Execute Query line contains invalid data for the maximum content price ["+cleanedColumns[3].toString()+"].",
                        queryLine,
                        -1,
                        null,
                        null);
            }
        }
        // get the search content supported languages
        if (cleanedColumns[4] != null && cleanedColumns[4].length() > 0) {
            // need to parse out the supported languages by splitting on the pipe character
            String[] parsedLanguages = Importer.parseCSVLine(cleanedColumns[4], "\\|");
            if (parsedLanguages != null && parsedLanguages.length > 0) {
                searchCriteria.setSupportedLanguages(new HashSet<String>(Arrays.asList(parsedLanguages)));
            }
        }
        // get the search content countries
        if (cleanedColumns[5] != null && cleanedColumns[5].length() > 0) {
            // need to parse out the countries by splitting on the pipe character
            String[] parsedCountries = Importer.parseCSVLine(cleanedColumns[5], "\\|");
            if (parsedCountries != null && parsedCountries.length > 0) {
                HashSet<Country> foundCountries = new HashSet<Country>();
                for (String countryCode : parsedCountries) {
                    Country foundCountry = productAPI.getCountryByCode(countryCode);
                    if (foundCountry != null) {
                        foundCountries.add(foundCountry);
                    }
                }
                searchCriteria.setCountries(foundCountries);
            }
        }
        // get the search content supported devices
        if (cleanedColumns[6] != null && cleanedColumns[6].length() > 0) {
            // need to parse out the devices by splitting on the pipe character
            String[] parsedDevices = Importer.parseCSVLine(cleanedColumns[6], "\\|");
            if (parsedDevices != null && parsedDevices.length > 0) {
                HashSet<Device> foundDevices = new HashSet<Device>();
                for (String deviceID : parsedDevices) {
                    Device foundDevice = productAPI.getDeviceByID(deviceID);
                    if (foundDevice != null) {
                        foundDevices.add(foundDevice);
                    }
                }
                searchCriteria.setDevices(foundDevices);
            }
        }
        // get the search content types
        List<ContentType> allContentTypes = Arrays.asList( ContentType.values());
        if (cleanedColumns[7] != null && cleanedColumns[7].length() > 0) {
            // need to parse out the content types by splitting on the pipe character
            String[] parsedContentTypes = Importer.parseCSVLine(cleanedColumns[7], "\\|");
            if (parsedContentTypes != null && parsedContentTypes.length > 0) {
                HashSet<ContentType> foundContentTypes = new HashSet<ContentType>();
                for (String contentTypeID : parsedContentTypes) {
                    ContentType matchingContentType = ContentType.valueOf(contentTypeID.toUpperCase());
                    if (matchingContentType != null) {
                        foundContentTypes.add(matchingContentType);
                    }
                }
                if (foundContentTypes.size() > 0) {
                    searchCriteria.setContentTypes(foundContentTypes);
                }
            }
        }

        // show the original query as a ContentSearch
        System.out.println(String.format("CONTENT SEARCH QUERY: %s\n", searchCriteria));

        List<Content> foundContent = productAPI.searchContent(searchCriteria);
        if (foundContent.size() > 0) {
            System.out.println(String.format("\t[%d] CONTENT ITEMS MATCH YOUR SEARCH CRITERIA:\n\n", foundContent.size()));
        } else {
            System.out.println("\tNO CONTENT ITEMS MATCH YOUR SEARCH CRITERIA.\n\n");
        }

        int resultsCounter = 1;
        for (Content item : foundContent) {
            System.out.println(String.format("MATCHING CONTENT ITEM #%d:\n%s", resultsCounter, item));
            resultsCounter++;
        }
        System.out.println(String.format("\n******************************\n"));
    }

    /**
     * Public method for executing search queries against the {@link cscie97.asn2.ecommerce.product.ProductAPI} for
     * {@link cscie97.asn2.ecommerce.product.Content} items.  Content may be searched for using any of the following
     * properties of {@link cscie97.asn2.ecommerce.product.Content} items:
     * <ol>
     *     <li>list of content categories (pipe-separated)</li>
     *     <li>search text (will scan {@link cscie97.asn2.ecommerce.product.Content} name, description, author name)</li>
     *     <li><b>minimum</b> content rating (from 0 to 5, where 5 is best)</li>
     *     <li><b>maximum</b> price (as a float, in BitCoins)</li>
     *     <li>list of supported language codes (pipe-separated)</li>
     *     <li>list of country codes (pipe-separated)</li>
     *     <li>device ID</li>
     *     <li>list of content types (can be any or all of "application", "ringtone", or "wallpaper" currently)</li>
     * </ol>
     * Checks for valid input file name.
     * Delegates to {@link cscie97.asn2.ecommerce.product.SearchEngine#executeQuery(String)} for processing
     * individual search queries.
     * Throws ImportException on error accessing or processing the input search query file.
     *
     * @param filename                file with CSV search criteria for Content items in the product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the file import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    public static void executeQueryFilename(String filename) throws QueryEngineException, ImportException, ParseException {
        int lineNumber = 0;  // keep track of what lineNumber we're reading in from the input file for exception handling
        String line = null;  // store the text on each line as it's processed
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<Content> contentItemsToAdd = new ArrayList<Content>();
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // FIRST check if we encountered an empty line, and just skip to the next one if so
                if (line.length() == 0) { continue; }

                // SECOND check if the line contains column headers, since some lines may contain comments
                // (preceeded by hash character); if first character is a hash, skip to next line
                if (line.substring(0,1).matches("#")) { continue; }

                // delegate individual query lines to the executeQuery method
                SearchEngine.executeQuery(line);
            }
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
