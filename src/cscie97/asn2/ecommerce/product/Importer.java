package cscie97.asn2.ecommerce.product;

import cscie97.asn2.ecommerce.product.exception.ImportException;
import cscie97.asn2.ecommerce.product.exception.ParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.*;

/**
 * Provides public static methods for supplying CSV files to load {@link cscie97.asn2.ecommerce.product.Country} items,
 * {@link cscie97.asn2.ecommerce.product.Device} items, and
 * {@link cscie97.asn2.ecommerce.product.Content} items (which may be one of
 * {@link cscie97.asn2.ecommerce.product.Application}, {@link cscie97.asn2.ecommerce.product.Ringtone}, or
 * {@link cscie97.asn2.ecommerce.product.Wallpaper} depending on the content type).  The input CSV files passed may
 * contain a header line or comment lines that begin with a "#" symbol; those lines will be skipped for analysis.
 *
 * For importing {@link cscie97.asn2.ecommerce.product.Country} items, the input file must be in the following
 * column format:
 * <ol>
 *     <li>2-character country code</li>
 *     <li>country name</li>
 *     <li>country export status (open or closed)</li>
 * </ol>
 * For example, here is a sample of what a valid country CSV input file might look like (country names that have
 * commas in them may be escaped by putting a backslash in front of the comma):
 * <pre>
 * #country_id, country_name, country_export_status
 * AF,AFGHANISTAN,open
 * AX,ALAND ISLANDS,open
 * AL,ALBANIA,open
 * BO,BOLIVIA\, PLURINATIONAL STATE OF,open
 * BQ,BONAIRE\, SINT EUSTATIUS AND SABA,open
 *  ...
 * </pre>
 *
 * For importing {@link cscie97.asn2.ecommerce.product.Device} items, the input file must be in the following
 * column format:
 * <ol>
 *     <li>unique device ID</li>
 *     <li>device name</li>
 *     <li>device manufacturer name</li>
 * </ol>
 * For example, here is a sample of what a valid device CSV input file might look like (device names that have
 * commas in them may be escaped by putting a backslash in front of the comma):
 * <pre>
 * # device_id, device_name, manufacturer
 * iphone5, IPhone 5, Apple
 * iphone6, IPhone 6, Apple
 * lumina800, Lumina 800, Nokia
 * lumina900, Lumina 900, Nokia
 * ...
 * </pre>
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see IProductAPI
 * @See ProductAPI
 * @see Content
 * @see Device
 * @see Application
 * @see Ringtone
 * @see Wallpaper
 */
public class Importer {

    /**
     * Private method to parse a line from a CSV input file and return an array of strings.  Splits up the string
     * based on the supplied separator, and ignores any backslash-escaped separator.
     *
     * @param line       the string to parse out and split into an array based on separator
     * @param separator  the character to use for splitting out the line
     * @return           an array of strings that were split by the separator
     */
    private static String[] parseCSVLine(String line, String separator) {
        // need to do a negative lookbehind to properly escape the backslash-preceeding characters that come
        // immediately prior to the passed separator in input strings (help from:
        // http://stackoverflow.com/questions/820172/how-to-split-a-comma-separated-string-while-ignoring-escaped-commas)
        String[] parts = line.split("(?<!\\\\)"+separator);

        // remove any remaining backslash characters from each of the parts if that backslash is immediately
        // followed by a comma (which is the way our CSVs are formatted to escape inline commas per column
        for(int i=0; i<parts.length; i++) {
            parts[i] = parts[i].replaceAll("\\\\,+", ",");
        }
        return parts;
    }

    /**
     * Public method for importing {@link cscie97.asn2.ecommerce.product.Country} items into the product catalog.
     * Checks for valid input file name.
     * Throws ImportException on error accessing or processing the input Country File.
     *
     * @param filename                file with countries to load into the product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    public static void importCountryFile(String guid, String filename) throws ImportException, ParseException {
        int lineNumber = 0;  // keep track of what lineNumber we're reading in from the input file for exception handling
        String line = null;  // store the text on each line as it's processed
        IProductAPI productAPI = ProductAPI.getInstance();  // reference to ProductAPI for adding the countries
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<Country> countries = new ArrayList<Country>();

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // FIRST check if we encountered an empty line, and just skip to the next one if so
                if (line.length() == 0) { continue; }

                // SECOND check if the line contains column headers, since some lines may contain comments
                // (preceeded by hash character); if first character is a hash, skip to next line
                if (line.substring(0,1).matches("#")) { continue; }

                //String[] cleanedColumns = Importer.parseCSVLine(line);
                String[] cleanedColumns = Importer.parseCSVLine(line, ",");
                if (cleanedColumns != null && cleanedColumns.length == 3) {
                    Country country = new Country(cleanedColumns[0], cleanedColumns[1], cleanedColumns[2]);
                    countries.add(country);
                } else {
                    throw new ParseException("Import Country line contains invalid data for some of the country attributes.",
                                                line,
                                                lineNumber,
                                                filename,
                                                null);
                }
            }
            // add the countries to the Product catalog
            if (countries.size() > 0) {
                productAPI.importCountries(guid, countries);
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

    /**
     * Public method for importing {@link cscie97.asn2.ecommerce.product.Device} items into the product catalog.
     * Checks for valid input file name.
     * Throws ImportException on error accessing or processing the input Device File.
     *
     * @param filename                file with devices to load into the product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    public static void importDeviceFile(String guid, String filename) throws ImportException, ParseException {
        int lineNumber = 0;  // keep track of what lineNumber we're reading in from the input file for exception handling
        String line = null;  // store the text on each line as it's processed
        IProductAPI productAPI = ProductAPI.getInstance();  // reference to ProductAPI for adding the devices
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<Device> devices = new ArrayList<Device>();

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // FIRST check if we encountered an empty line, and just skip to the next one if so
                if (line.length() == 0) { continue; }

                // SECOND check if the line contains column headers, since some lines may contain comments
                // (preceeded by hash character); if first character is a hash, skip to next line
                if (line.substring(0,1).matches("#")) { continue; }

                //String[] cleanedColumns = Importer.parseCSVLine(line);
                String[] cleanedColumns = Importer.parseCSVLine(line, ",");
                if (cleanedColumns != null && cleanedColumns.length == 3) {
                    Device device = new Device(cleanedColumns[0], cleanedColumns[1], cleanedColumns[2]);
                    devices.add(device);
                } else {
                    throw new ParseException("Import Device line contains invalid data for some of the device attributes.",
                                                line,
                                                lineNumber,
                                                filename,
                                                null);
                }
            }
            // add the devices to the Product catalog
            if (devices.size() > 0) {
                productAPI.importDevices(guid, devices);
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

    /**
     * Public method for importing {@link cscie97.asn2.ecommerce.product.Content} items into the product catalog.
     * Note that any {@link cscie97.asn2.ecommerce.product.Device} or {@link cscie97.asn2.ecommerce.product.Country}
     * items referenced by the individual content items to add must already exist in the Product catalog first, or the
     * import of that content item will not work.  Depending on the content type of each item in the input file, will
     * conditionally add the content items to the product catalog as either an
     * {@link cscie97.asn2.ecommerce.product.Application},
     * {@link cscie97.asn2.ecommerce.product.Ringtone}, or {@link cscie97.asn2.ecommerce.product.Wallpaper}.
     * Checks for valid input file name.
     * Throws ImportException on error accessing or processing the input Content File.
     *
     * @param filename                file with content items to load into the product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    public static void importContentFile(String guid, String filename) throws ImportException, ParseException {
        int lineNumber = 0;  // keep track of what lineNumber we're reading in from the input file for exception handling
        String line = null;  // store the text on each line as it's processed
        IProductAPI productAPI = ProductAPI.getInstance();  // reference to ProductAPI for adding the content items
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

                String[] cleanedColumns = Importer.parseCSVLine(line, ",");

                // can be 12 or 13 columns long (13 contains the filesize for applications)
                if (cleanedColumns != null && cleanedColumns.length >= 12 && cleanedColumns.length <= 13) {
                    // set up empty values for the content that will be parsed out from the line
                    String contentName = "";
                    String contentDescription = "";
                    String contentAuthorName = "";
                    String contentImageURL = "";
                    int contentRating = 0;
                    int contentFilesizeBytes = 0;
                    int contentPixelWidth = 0;
                    int contentPixelHeight = 0;
                    float contentPrice = 0;
                    float contentDurationInSeconds = 0;
                    Set<String> contentCategories = new HashSet<String>(){};
                    Set<Device> contentDevices = new HashSet<Device>(){};
                    Set<Country> contentCountries = new HashSet<Country>(){};
                    Set<String> contentSupportedLanguages = new HashSet<String>(){};
                    ContentType contentType = null;

                    List<ContentType> allContentTypes = Arrays.asList( ContentType.values());
                    String upperCaseContentType = cleanedColumns[0].toUpperCase();

                    // get the content type
                    if (cleanedColumns[0] != null && allContentTypes.contains(ContentType.valueOf(upperCaseContentType)) ) {
                        contentType = ContentType.valueOf(cleanedColumns[0].toUpperCase());
                    }
                    // get the content name
                    if (cleanedColumns[2] != null && cleanedColumns[2].length() > 0) {
                        contentName = cleanedColumns[2].trim();
                    }
                    // get the content description
                    if (cleanedColumns[3] != null && cleanedColumns[3].length() > 0) {
                        contentDescription = cleanedColumns[3].trim();
                    }
                    // get the content author name
                    if (cleanedColumns[4] != null && cleanedColumns[4].length() > 0) {
                        contentAuthorName = cleanedColumns[4].trim();
                    }
                    // get the content rating
                    if (cleanedColumns[5] != null && cleanedColumns[5].length() == 1) {
                        try {
                            contentRating = Integer.parseInt(cleanedColumns[5]);
                        }
                        catch (NumberFormatException nfe) {
                            throw new ParseException("Import Content line contains invalid data for the content rating ["+cleanedColumns[5].toString()+"].",
                                                        line,
                                                        lineNumber,
                                                        filename,
                                                        null);
                        }
                    }
                    // get the content categories
                    if (cleanedColumns[6] != null && cleanedColumns[6].length() > 0) {
                        // need to parse out the categories by splitting on the pipe character
                        String[] parsedCategories = Importer.parseCSVLine(cleanedColumns[6], "\\|");
                        if (parsedCategories != null && parsedCategories.length > 0) {
                            contentCategories.addAll(Arrays.asList(parsedCategories));
                        }
                    }
                    // get the content countries
                    if (cleanedColumns[7] != null && cleanedColumns[7].length() > 0) {
                        // need to parse out the countries by splitting on the pipe character
                        String[] parsedCountries = Importer.parseCSVLine(cleanedColumns[7], "\\|");
                        if (parsedCountries != null && parsedCountries.length > 0) {
                            for (String countryCode : parsedCountries) {
                                Country foundCountry = productAPI.getCountryByCode(countryCode);
                                if (foundCountry != null) {
                                    contentCountries.add(foundCountry);
                                }
                            }
                        }
                    }
                    // get the content supported devices
                    if (cleanedColumns[8] != null && cleanedColumns[8].length() > 0) {
                        // need to parse out the devices by splitting on the pipe character
                        String[] parsedDevices = Importer.parseCSVLine(cleanedColumns[8], "\\|");
                        if (parsedDevices != null && parsedDevices.length > 0) {
                            for (String deviceID : parsedDevices) {
                                Device foundDevice = productAPI.getDeviceByID(deviceID);
                                if (foundDevice != null) {
                                    contentDevices.add(foundDevice);
                                }
                            }
                        }
                    }
                    // get the content price (in BitCoins)
                    if (cleanedColumns[9] != null && cleanedColumns[9].length() > 0) {
                        try {
                            contentPrice = Float.parseFloat(cleanedColumns[9]);
                        }
                        catch (NumberFormatException nfe) {
                            throw new ParseException("Import Content line contains invalid data for the content price ["+cleanedColumns[9].toString()+"].",
                                                        line,
                                                        lineNumber,
                                                        filename,
                                                        null);
                        }
                    }
                    // get the content supported languages
                    if (cleanedColumns[10] != null && cleanedColumns[10].length() > 0) {
                        // need to parse out the supported languages by splitting on the pipe character
                        String[] parsedLanguages = Importer.parseCSVLine(cleanedColumns[10], "\\|");
                        if (parsedLanguages != null && parsedLanguages.length > 0) {
                            contentSupportedLanguages.addAll(Arrays.asList(parsedLanguages));
                        }
                    }
                    // get the content image URL
                    if (cleanedColumns[11] != null && cleanedColumns[11].length() > 0) {
                        contentImageURL = cleanedColumns[11].trim();
                    }
                    // OPTIONAL: if there is a 13th item in the array, it is the application file size
                    if (cleanedColumns.length >= 13 && cleanedColumns[12] != null && cleanedColumns[12].length() > 0) {
                        try {
                            contentFilesizeBytes = Integer.parseInt(cleanedColumns[12]);
                        }
                        catch (NumberFormatException nfe) {
                            throw new ParseException("Import Content line contains invalid data for the content application filesize ["+cleanedColumns[12].toString()+"].",
                                                        line,
                                                        lineNumber,
                                                        filename,
                                                        null);
                        }
                    }
                    // OPTIONAL: if there is a 14th item in the array, it is the ringtone duration in seconds
                    if (cleanedColumns.length >= 14 && cleanedColumns[13] != null && cleanedColumns[13].length() > 0) {
                        try {
                            contentDurationInSeconds = Float.parseFloat(cleanedColumns[13]);
                        }
                        catch (NumberFormatException nfe) {
                            throw new ParseException("Import Content line contains invalid data for the content ringtone duration in seconds ["+cleanedColumns[13].toString()+"].",
                                                        line,
                                                        lineNumber,
                                                        filename,
                                                        null);
                        }
                    }
                    // OPTIONAL: if there are 15th and 16th columns in the array, it is the wallpaper pixel width and pixel height
                    if (cleanedColumns.length >= 16 &&
                            cleanedColumns[14] != null &&
                            cleanedColumns[15] != null &&
                            cleanedColumns[14].length() > 0 &&
                            cleanedColumns[15].length() > 0
                    ) {
                        try {
                            contentPixelWidth = Integer.parseInt(cleanedColumns[14]);
                            contentPixelHeight = Integer.parseInt(cleanedColumns[15]);
                        }
                        catch (NumberFormatException nfe) {
                            throw new ParseException("Import Content line contains invalid data for the content wallpaper pixel width and height ["+cleanedColumns[14].toString()+","+cleanedColumns[15].toString()+"].",
                                                        line,
                                                        lineNumber,
                                                        filename,
                                                        null);
                        }
                    }

                    // try to create the content
                    if (contentType == null) {
                        throw new ParseException("Import Content line contains invalid data for the content type ["+cleanedColumns[0].toString()+"].",
                                                    line,
                                                    lineNumber,
                                                    filename,
                                                    null);
                    }


                    switch (contentType) {
                        case APPLICATION :
                            Content application = new Application(contentName, contentDescription, contentAuthorName,
                                                              contentRating, contentCategories, contentDevices,
                                                              contentPrice, contentCountries, contentSupportedLanguages,
                                                              contentImageURL, contentType, contentFilesizeBytes);
                            contentItemsToAdd.add(application);
                            break;
                        case RINGTONE :
                            Content ringtone = new Ringtone(contentName, contentDescription, contentAuthorName,
                                                              contentRating, contentCategories, contentDevices,
                                                              contentPrice, contentCountries, contentSupportedLanguages,
                                                              contentImageURL, contentType, contentDurationInSeconds);
                            contentItemsToAdd.add(ringtone);
                            break;
                        case WALLPAPER :
                            Content wallpaper = new Wallpaper(contentName, contentDescription, contentAuthorName,
                                                              contentRating, contentCategories, contentDevices,
                                                              contentPrice, contentCountries, contentSupportedLanguages,
                                                              contentImageURL, contentType, 1920, 1080);
                            contentItemsToAdd.add(wallpaper);
                            break;
                    }
                } else {
                    throw new ParseException("Import Content line contains invalid data for some of the content attributes.",
                                                line,
                                                lineNumber,
                                                filename,
                                                null);
                }
            }
            // add the content items to the Product catalog
            if (contentItemsToAdd.size() > 0) {
                productAPI.importContent(guid, contentItemsToAdd);
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






    public List<ContentSearch> importSearchQueryFile(String filename) throws ImportException, ParseException {
        Set<String> categories = new HashSet<String>();
        Set<String> supportedLanguages = new HashSet<String>();
        Country country = new Country("","","");
        Device device = new Device("","","");
        Set<ContentType> contentTypes = new HashSet<ContentType>();

        ContentSearch contentSearch = new ContentSearch(categories, "", 0, 0, supportedLanguages, country, device, contentTypes);

        //List<ContentSearch> searchList = new ArrayList<ContentSearch>() { Arrays.asList<ContentSearch>(contentSearch) };
        List<ContentSearch> searchList = new ArrayList<ContentSearch>();
        searchList.add(contentSearch);

        return searchList;
        //ContentSearch(categories, "", 0, 0, supportedLanguages, country, device, contentTypes);
    }

}
