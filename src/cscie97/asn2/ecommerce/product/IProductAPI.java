package cscie97.asn2.ecommerce.product;

import java.util.List;
import java.util.Set;

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
     * Given a 2-character country code, search for any country that matches in the product catalog.
     * @param code  a 2-character country code
     * @return      the found country with the matching code
     */
    public Country getCountryByCode(String code);

    /**
     * Given a device ID, search for any {@link cscie97.asn2.ecommerce.product.Device} that matches that code
     * in the product catalog.
     *
     * @param deviceID  a unique device ID
     * @return          the found {@link cscie97.asn2.ecommerce.product.Device} with the matching ID
     */
    public Device getDeviceByID(String deviceID);

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