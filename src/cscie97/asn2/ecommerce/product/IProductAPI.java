package cscie97.asn2.ecommerce.product;

import cscie97.asn2.ecommerce.product.exception.ImportException;
import cscie97.asn2.ecommerce.product.exception.ParseException;
import cscie97.asn2.ecommerce.product.exception.QueryEngineException;
import java.util.Set;
import java.util.List;

/**
 * A public API for interacting with the Mobile Application Store which allowed for the importation of new
 * {@link cscie97.asn2.ecommerce.product.Content} items as well as provides a search interface for that
 * {@link cscie97.asn2.ecommerce.product.Content}.
 *
 * Customers may use the API to search for {@link cscie97.asn2.ecommerce.product.Content} items and supply detailed
 * search criteria.  Application Developers and Administrators may use the API to add new
 * {@link cscie97.asn2.ecommerce.product.Content} items, new {@link cscie97.asn2.ecommerce.product.Country} items, and
 * new {@link cscie97.asn2.ecommerce.product.Device} by passing in filenames of CSV files with all data.  <b>Note that
 * adding items to the product catalog is a "restricted interface"; only users that supply a valid access token
 * (e.g., GUID) value to methods for importing/adding content will be allowed.</b>  Later an Authentication Service
 * API will be built that will handle the actual authentication and authorization parts; the current version of the
 * ProductAPI will only simulate the presence of a functional Authentication Service by accepting and requiring GUIDs
 * be passed to restricted interface methods (e.g., content creation).
 *
 * The {@link cscie97.asn2.ecommerce.product.ProductAPI} is a Singleton, so there is only ever one instance to use.
 * Additionally, the ProductAPI uses the Flyweight pattern to ensure that there is only ever one instance of each
 * {@link cscie97.asn2.ecommerce.product.Content}, {@link cscie97.asn2.ecommerce.product.Device}, and
 * {@link cscie97.asn2.ecommerce.product.Country} object so as to not needlessly duplicate things and waste memory
 * and resources.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see Content
 * @see ContentSearch
 */
public interface IProductAPI {

    /**
     * Returns a reference to the single static instance of the ProductAPI.
     *
     * @return singleton instance of the ProductAPI
     */
    //public IProductAPI getInstance();


    /**
     * Verifies that the <b>guid</b> access token passed is authenticated and authorized for carrying out
     * restricted actions on the ProductAPI (such as adding new Content items, etc.).
     * <b>Note that for this version of the ProductAPI, this method is mocked and will return true for
     * any string passed.</b>
     *
     * @param guid  the string access token to check for authentication and authorization for carrying out restricted actions on the ProductAPI
     * @return  true if guid is authenticated and authorized to execute restricted actions on ProductAPI, false otherwise
     */
    public boolean validateAccessToken(String guid);







    /**
     * Public method for importing countries into the product catalog.  Every Content item has a list of Countries
     * that the content item may be exported to.
     * Checks for valid input file name.
     * Throws ImportException on error accessing or processing the input Country file.
     *
     * @param filename                file with countries to load into the Product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    //public void importCountries(String filename) throws ImportException, ParseException;

    /**
     * Public method for importing devices into the product catalog.  Every Content item has a list of supported
     * Devices that the content item may be used on.
     * Checks for valid input file name.
     * Throws ImportException on error accessing or processing the input Device file.
     *
     * @param filename                file with supported devices to load into the Product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    //public void importDevices(String filename) throws ImportException, ParseException;

    /**
     * Public method for importing Content items into the product catalog.  Every Content item has a list of supported
     * Devices, and a list of Countries that the content item may be downloaded in.
     * Checks for valid input file name.
     * Throws ImportException on error accessing or processing the input Content file.
     *
     * @param filename                file with content items to load into the Product catalog
     * @throws ImportException        thrown when encountering non-parse related exceptions in the import process
     * @throws ParseException         thrown when encountering any issues parsing the input file related to the format of the file contents
     */
    //public void importContent(String filename) throws ImportException, ParseException;

    /**
     * <b>Restricted interface only for authenticated users.</b>  When passed an authorized <b>guid</b>, allows
     * authenticated users the ability to add new {@link cscie97.asn2.ecommerce.product.Country} items into the
     * Product catalog.  There is only one instance of each {@link cscie97.asn2.ecommerce.product.Country} object
     * in the Product catalog at any time (follows the Flyweight pattern); attempts to add a pre-existing country
     * will not do anything since the {@link cscie97.asn2.ecommerce.product.Country} already exists in the catalog.
     *
     * @param guid            a string token for a validated and authenticated user to allow restricted interface actions
     * @param code            2-character country code
     * @param name            the full name of the country to add
     * @param exportStatus    can be one of: "open", "closed"
     */
    //public void addCountry(String guid, String code, String name, String exportStatus);

    //public void addDevice(String guid, String id, String name, String manufacturer);

    //public void addContent(String guid, Content item);







    /**
     * Public method for importing countries into the product catalog.  Every
     * {@link cscie97.asn2.ecommerce.product.Content} item has a list of {@link cscie97.asn2.ecommerce.product.Country}
     * objects that the content item may be exported to.  The {@link cscie97.asn2.ecommerce.product.ProductAPI}
     * maintains one unique instance of each {@link cscie97.asn2.ecommerce.product.Country} object (follows the
     * Flyweight pattern).  Countries will be validated before being added to the product catalog; invalid countries
     * will be skipped over and not added.
     *
     * @param guid       a string token for a validated and authenticated user to allow restricted interface actions
     * @param countries  list of {@link cscie97.asn2.ecommerce.product.Country} objects to add to the product catalog
     */
    public void importCountries(String guid, List<Country> countries);

    /**
     * Public method for importing devices into the product catalog.  Every
     * {@link cscie97.asn2.ecommerce.product.Content} item has a list of supported {@link cscie97.asn2.ecommerce.product.Device}
     * objects that the content item may be downloaded onto.  The {@link cscie97.asn2.ecommerce.product.ProductAPI}
     * maintains one unique instance of each {@link cscie97.asn2.ecommerce.product.Device} object (follows the
     * Flyweight pattern).  Devices will be validated before being added to the product catalog; invalid devices
     * will be skipped over and not added.
     *
     * @param guid       a string token for a validated and authenticated user to allow restricted interface actions
     * @param devices  list of {@link cscie97.asn2.ecommerce.product.Device} objects to add to the product catalog
     */
    public void importDevices(String guid, List<Device> devices);

    /**
     * Public method for importing {@link cscie97.asn2.ecommerce.product.Content} items into the product catalog
     * (content may be may be {@link cscie97.asn2.ecommerce.product.Application} items,
     * {@link cscie97.asn2.ecommerce.product.Ringtone} items, or {@link cscie97.asn2.ecommerce.product.Wallpaper}
     * items).  Each Content item in the product catalog is unique, so only one instance of each content item is
     * allowed (follows the Flyweight pattern).  {@link cscie97.asn2.ecommerce.product.Content} items will be validated
     * based on the {@link cscie97.asn2.ecommerce.product.ContentType} of each item before being added to the product
     * catalog; invalid content items will be skipped over and not added.
     *
     * @param guid          a string token for a validated and authenticated user to allow restricted interface actions
     * @param contentItems  list of {@link cscie97.asn2.ecommerce.product.Content} objects to add to the product catalog
     */
    public void importContent(String guid, List<Content> contentItems);


    /*
    public boolean validateCountry(Country country);

    public boolean validateDevice(Device device);

    public boolean validateContent(Content content);
    */


    //public ContentSearch parseSearchString(String searchString);


    /**
     * Search the Product catalog for all matching content items that correspond to the criteria in the supplied
     * search object.  If any content item in the product catalog has an attribute that matches any one of the
     * supplied criteria in the search object, that content item will be included in the returned list of items.
     *
     * @param search  a search object containing the criteria to use when searching the Product catalog
     * @return  list of all content items that match the supplied criteria in the search object
     */
    public List<Content> searchContent(ContentSearch search);

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Application} objects in the product catalog.
     *
     * @return  all {@link cscie97.asn2.ecommerce.product.Application} objects in the product catalog
     */
    public List<Application> getAllApplications();

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Ringtone} objects in the product catalog.
     *
     * @return  all {@link cscie97.asn2.ecommerce.product.Ringtone} objects in the product catalog
     */
    public List<Ringtone> getAllRingtones();

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Wallpaper} objects in the product catalog.
     *
     * @return  all {@link cscie97.asn2.ecommerce.product.Wallpaper} objects in the product catalog
     */
    public List<Wallpaper> getAllWallpapers();

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Content} objects in the product catalog, regardless of
     * each individual item's content type (can include objects that are {@link cscie97.asn2.ecommerce.product.Application},
     * {@link cscie97.asn2.ecommerce.product.Ringtone}, or {@link cscie97.asn2.ecommerce.product.Wallpaper} types).
     *
     * @return  all {@link cscie97.asn2.ecommerce.product.Content} objects in the product catalog regardless of type
     */
    public List<Content> getAllContent();

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Country} objects in the product catalog.
     *
     * @return  all {@link cscie97.asn2.ecommerce.product.Country} objects in the product catalog
     */
    public Set<Country> getCountries();

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Device} objects in the product catalog.
     *
     * @return  all {@link cscie97.asn2.ecommerce.product.Device} objects in the product catalog
     */
    public Set<Device> getDevices();

    /**
     * Convenience method that returns the total number of {@link cscie97.asn2.ecommerce.product.Content} items in
     * the product catalog, regardless of their content type.
     *
     * @return  total number of content items in the product catalog irrespective of content type
     */
    public int getNumberContentItems();

}