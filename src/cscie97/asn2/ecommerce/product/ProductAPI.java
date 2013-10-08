package cscie97.asn2.ecommerce.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;

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
public class ProductAPI implements IProductAPI {

    /**
     * The unique countries contained in the Product catalog; each country is unique and only one instance of each is permitted.
     */
    private Set<Country> countries;

    /**
     * The unique devices contained in the Product catalog; each device is unique and only one instance of each is permitted.
     */
    private Set<Device> devices;

    /**
     * The unique content items contained in the Product catalog; each content item is unique and only one instance of each is permitted.
     */
    private Set<Content> contentItems;

    /**
     * Singleton instance of the ProductAPI
     */
    private static IProductAPI instance = null;

    /**
     * Class constructor.  Initially sets all collections to be empty HashSets.
     */
    private ProductAPI() {
        this.countries = new HashSet<Country>() { };
        this.devices = new HashSet<Device>(){ };
        this.contentItems = new HashSet<Content>(){ };
    }

    /**
     * Returns a reference to the single static instance of the ProductAPI.
     *
     * @return  singleton instance of ProductAPI
     */
    public static synchronized IProductAPI getInstance() {
        if (instance == null) {
            instance = new ProductAPI();
        }
        return instance;
    }

    /**
     * Verifies that the <b>guid</b> access token passed is authenticated and authorized for carrying out
     * restricted actions on the ProductAPI (such as adding new Content items, etc.).
     * <b>Note that for this version of the ProductAPI, this method is mocked and will return true for
     * any string passed.</b>
     *
     * @param guid the string access token to check for authentication and authorization for carrying out restricted actions on the ProductAPI
     * @return true if guid is authenticated and authorized to execute restricted actions on ProductAPI, false otherwise
     */
    public boolean validateAccessToken(String guid) {
        if (guid != null && guid.length() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Public method for importing countries into the product catalog.  Every
     * {@link cscie97.asn2.ecommerce.product.Content} item has a list of {@link cscie97.asn2.ecommerce.product.Country}
     * objects that the content item may be exported to.  The {@link cscie97.asn2.ecommerce.product.ProductAPI}
     * maintains one unique instance of each {@link cscie97.asn2.ecommerce.product.Country} object (follows the
     * Flyweight pattern).  Countries will be validated before being added to the product catalog; invalid countries
     * will be skipped over and not added.
     *
     * @param guid      a string token for a validated and authenticated user to allow restricted interface actions
     * @param countries list of {@link cscie97.asn2.ecommerce.product.Country} objects to add to the product catalog
     */
    public void importCountries(String guid, List<Country> countries) {
        if (validateAccessToken(guid)) {
            for (Country country : countries) {
                if (Country.validateCountry(country)) {
                    this.countries.add(country);
                }
            }
        }
    }

    /**
     * Public method for importing devices into the product catalog.  Every
     * {@link cscie97.asn2.ecommerce.product.Content} item has a list of supported {@link cscie97.asn2.ecommerce.product.Device}
     * objects that the content item may be downloaded onto.  The {@link cscie97.asn2.ecommerce.product.ProductAPI}
     * maintains one unique instance of each {@link cscie97.asn2.ecommerce.product.Device} object (follows the
     * Flyweight pattern).  Devices will be validated before being added to the product catalog; invalid devices
     * will be skipped over and not added.
     *
     * @param guid    a string token for a validated and authenticated user to allow restricted interface actions
     * @param devices list of {@link cscie97.asn2.ecommerce.product.Device} objects to add to the product catalog
     */
    public void importDevices(String guid, List<Device> devices) {
        if (validateAccessToken(guid)) {
            for (Device device : devices) {
                if (Device.validateDevice(device)) {
                    this.devices.add(device);
                }
            }
        }
    }

    /**
     * Public method for importing {@link cscie97.asn2.ecommerce.product.Content} items into the product catalog
     * (content may be may be {@link cscie97.asn2.ecommerce.product.Application} items,
     * {@link cscie97.asn2.ecommerce.product.Ringtone} items, or {@link cscie97.asn2.ecommerce.product.Wallpaper}
     * items).  Each Content item in the product catalog is unique, so only one instance of each content item is
     * allowed (follows the Flyweight pattern).  {@link cscie97.asn2.ecommerce.product.Content} items will be validated
     * based on the {@link cscie97.asn2.ecommerce.product.ContentType} of each item before being added to the product
     * catalog; invalid content items will be skipped over and not added.
     *
     * @param guid         a string token for a validated and authenticated user to allow restricted interface actions
     * @param contentItems list of {@link cscie97.asn2.ecommerce.product.Content} objects to add to the product catalog
     */
    public void importContent(String guid, List<Content> contentItems) {
        if (validateAccessToken(guid)) {
            for (Content contentItem : contentItems) {
                if (contentItem instanceof Application && Application.validateContent(contentItem)) {
                    this.contentItems.add(contentItem);
                }
                else if (contentItem instanceof Wallpaper && Wallpaper.validateContent(contentItem)) {
                    this.contentItems.add(contentItem);
                }
                else if (contentItem instanceof Ringtone && Ringtone.validateContent(contentItem)) {
                    this.contentItems.add(contentItem);
                }
            }
        }
    }

    /**
     * Search the Product catalog for all matching content items that correspond to the criteria in the supplied
     * search object.  If any content item in the product catalog has an attribute that matches any one of the
     * supplied criteria in the search object, that content item will be included in the returned list of items.
     *
     * The search implicitly uses OR logic for determining if a {@link cscie97.asn2.ecommerce.product.Content} item
     * should match a given search criteria if that criteria is collection-based.  For example, if the ContentSearch
     * object passed has a non-null "devices" property and lists multiple devices, then all content items that match
     * any of those devices will be returned.
     *
     * @param search a search object containing the criteria to use when searching the Product catalog
     * @return list of all content items that match the supplied criteria in the search object
     */
    public List<Content> searchContent(ContentSearch search) {
        List<Content> foundContent = new ArrayList<Content>();

        // for each attribute in the search object, determine if the content matches any of the values; if so, add the
        // content item to the foundContent result list and continue onto the next item (no need to continue to match
        // on other attributes - once a content matches any parameter it's included in the result set regardless of
        // other attribute matches)
        for (Content item : this.contentItems) {
            // check for content category matches
            if ( search.getCategories() != null && CollectionUtils.intersection(item.getCategories(), search.getCategories()).size() > 0 ) {
                foundContent.add(item);
                System.out.println("content matched on categories!");
                continue;
            }
            // check for device matches
            if ( search.getDevices() != null && CollectionUtils.intersection(item.getCompatibleDevices(), search.getDevices()).size() > 0 ) {
                foundContent.add(item);
                System.out.println("content matched on devices!");
                continue;
            }
            // check for country matches
            if ( search.getCountries() != null && CollectionUtils.intersection(item.getAllowedInCountries(), search.getCountries()).size() > 0 ) {
                foundContent.add(item);
                System.out.println("content matched on countries!");
                continue;
            }
            // check for language code matches
            if ( search.getSupportedLanguages() != null && CollectionUtils.intersection(item.getSupportedLanguages(), search.getSupportedLanguages()).size() > 0 ) {
                foundContent.add(item);
                System.out.println("content matched on supported languages!");
                continue;
            }
            // check for content type matches
            if ( item.getContentType() != null && search.getContentTypes().contains(item.getContentType()) ) {
                foundContent.add(item);
                System.out.println("content matched on content types!");
                continue;
            }

            // check for text search string matches
            boolean searchTextIsSet = (search.getTextSearch() != null && search.getTextSearch().length() > 0);
            boolean searchTextInItemName = item.getName().toLowerCase().contains(search.getTextSearch().toLowerCase());
            boolean searchTextInItemDescription = item.getDescription().toLowerCase().contains(search.getTextSearch().toLowerCase());
            boolean searchTextInItemAuthorName = item.getAuthorName().toLowerCase().contains(search.getTextSearch().toLowerCase());

            if ( searchTextIsSet && (searchTextInItemName || searchTextInItemDescription || searchTextInItemAuthorName) ) {
                foundContent.add(item);
                System.out.println("content matched on string matches!\nsearchTextIsSet: ["+searchTextIsSet+"]\nsearchTextInItemName: ["+searchTextInItemName+"]\nsearchTextInItemDescription: ["+searchTextInItemDescription+"]\nsearchTextInItemAuthorName: ["+searchTextInItemAuthorName+"]\n");
                continue;
            }
            // check for minimum rating matches (must ensure that the item rating is also at least 1, or this would
            // match all content, since the default value for the rating parameter is zero when uninitialized)
            if ( item.getRating() >= search.getMinimumRating() && item.getRating() >= 1) {
                foundContent.add(item);
                System.out.println("content matched on minimum rating!   item rating: ["+item.getRating()+"]  search minimum rating: ["+search.getMinimumRating()+"]");
                continue;
            }
            // check for maximum price matches (in this case, if the search maximum price IS zero, we want
            // to return all content that is free and has a zero price)
            if ( search.getMaximumPrice() >= item.getPrice() ) {
                foundContent.add(item);
                System.out.println("content matched on maximum price!");
                continue;
            }
        }
        return foundContent;
    }

    /**
     * Given a 2-character country code, search for any country that matches in the product catalog.
     * @param code  a 2-character country code
     * @return      the found country with the matching code
     */
    public Country getCountryByCode(String code) {
        for (Country country : this.countries) {
            if (country.getCode().equalsIgnoreCase(code)) {
                return country;
            }
        }
        return null;
    }

    /**
     * Given a device ID, search for any {@link cscie97.asn2.ecommerce.product.Device} that matches that code
     * in the product catalog.
     *
     * @param deviceID  a unique device ID
     * @return          the found {@link cscie97.asn2.ecommerce.product.Device} with the matching ID
     */
    public Device getDeviceByID(String deviceID) {
        for (Device device : this.devices) {
            if (device.getId().equalsIgnoreCase(deviceID)) {
                return device;
            }
        }
        return null;
    }

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Application} objects in the product catalog.
     *
     * @return all {@link cscie97.asn2.ecommerce.product.Application} objects in the product catalog
     */
    public List<Application> getAllApplications() {
        List<Application> allApplications = new ArrayList<Application>();
        for (Content contentItem : this.contentItems) {
            if (contentItem instanceof Application) {
                allApplications.add((Application)contentItem);
            }
        }
        return allApplications;
    }

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Ringtone} objects in the product catalog.
     *
     * @return all {@link cscie97.asn2.ecommerce.product.Ringtone} objects in the product catalog
     */
    public List<Ringtone> getAllRingtones() {
        List<Ringtone> allRingtones = new ArrayList<Ringtone>();
        for (Content contentItem : this.contentItems) {
            if (contentItem instanceof Ringtone) {
                allRingtones.add((Ringtone)contentItem);
            }
        }
        return allRingtones;
    }

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Wallpaper} objects in the product catalog.
     *
     * @return all {@link cscie97.asn2.ecommerce.product.Wallpaper} objects in the product catalog
     */
    public List<Wallpaper> getAllWallpapers() {
        List<Wallpaper> allWallpapers = new ArrayList<Wallpaper>();
        for (Content contentItem : this.contentItems) {
            if (contentItem instanceof Wallpaper) {
                allWallpapers.add((Wallpaper)contentItem);
            }
        }
        return allWallpapers;
    }

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Content} objects in the product catalog, regardless of
     * each individual item's content type (can include objects that are {@link cscie97.asn2.ecommerce.product.Application},
     * {@link cscie97.asn2.ecommerce.product.Ringtone}, or {@link cscie97.asn2.ecommerce.product.Wallpaper} types).
     *
     * @return all {@link cscie97.asn2.ecommerce.product.Content} objects in the product catalog regardless of type
     */
    public List<Content> getAllContent() {
        List<Content> allContent = new ArrayList<Content>();
        allContent.addAll(this.contentItems);
        return allContent;
    }

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Country} objects in the product catalog.
     *
     * @return all {@link cscie97.asn2.ecommerce.product.Country} objects in the product catalog
     */
    public Set<Country> getCountries() {
        return this.countries;
    }

    /**
     * Returns all {@link cscie97.asn2.ecommerce.product.Device} objects in the product catalog.
     *
     * @return all {@link cscie97.asn2.ecommerce.product.Device} objects in the product catalog
     */
    public Set<Device> getDevices() {
        return this.devices;
    }

    /**
     * Convenience method that returns the total number of {@link cscie97.asn2.ecommerce.product.Content} items in
     * the product catalog, regardless of their content type.
     *
     * @return total number of content items in the product catalog irrespective of content type
     */
    public int getNumberContentItems() {
        return this.contentItems.size();
    }
}
