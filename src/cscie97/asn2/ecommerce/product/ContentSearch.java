package cscie97.asn2.ecommerce.product;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user search query of the {@link cscie97.asn2.ecommerce.product.IProductAPI} for
 * {@link cscie97.asn2.ecommerce.product.Content} items that match the criteria in the search object.  Since content
 * objects will be changing (adding new {@link cscie97.asn2.ecommerce.product.ContentType} in the future to add more
 * types that may have different attributes, the ContentSearch object can abstract away the differences in types and
 * also simplify the querying of content rather than pass around very long parameter lists to query (just pass around
 * a ContentSearch object instead).  Future expandability and features based on this object could be things such as:
 * <ul>
 *     <li>logging search queries to the ProductAPI</li>
 *     <li>saving and analyzing historical queries for trends, popular searches, terms, etc.</li>
 * </ul>
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see IProductAPI
 * @see Content
 * @see Application
 * @see Ringtone
 * @see Wallpaper
 */
public class ContentSearch {

    /**
     * Save the original string that was used to create this content search object
     */
    private String rawQuery = "";

    /**
     * Content categories to search content for
     */
    private Set<String> categories = new HashSet<String>(){ };

    /**
     * Text to use for searching content items for; will search on content name, description, and author name
     */
    private String textSearch = "";

    /**
     * Minimum rating to use when searching for content
     */
    private int minimumRating = 0;

    /**
     * Maximum price to use when searching for content; if the prize is zero, all content will match
     */
    private float maximumPrice = 0;

    /**
     * All the supported language codes to match on
     */
    private Set<String> supportedLanguages = new HashSet<String>(){ };

    /**
     * All the allowed countries where the searched content can be downloaded
     */
    private Set<Country> countries = new HashSet<Country>(){ };

    /**
     *
     */
    private Set<Device> devices = new HashSet<Device>(){ };

    private Set<ContentType> contentTypes = new HashSet<ContentType>(){ };

    public ContentSearch() { }

    /**
     * Class constructor.  ContentSearch objects are created by the {@link cscie97.asn2.ecommerce.product.SearchEngine}
     * to capture the search criteria that is from the input CSV files, and then the ContentSearch objects are passed
     * to the Singleton instance of the {@link cscie97.asn2.ecommerce.product.ProductAPI} for executing the actual
     * content search.
     *
     * @param rawQuery
     * @param categories
     * @param textSearch
     * @param minimumRating
     * @param maximumPrice
     * @param supportedLanguages
     * @param countries
     * @param devices
     * @param contentTypes
     */
    public ContentSearch(String rawQuery, Set<String> categories, String textSearch, int minimumRating, float maximumPrice,
                         Set<String> supportedLanguages, Set<Country> countries, Set<Device> devices, Set<ContentType> contentTypes
    ) {
        this.rawQuery = rawQuery;
        this.categories = categories;
        this.textSearch = textSearch;
        this.minimumRating = minimumRating;
        this.maximumPrice = maximumPrice;
        this.supportedLanguages = supportedLanguages;
        this.countries = countries;
        this.devices = devices;
        this.contentTypes = contentTypes;
    }

    public String getRawQuery() {
        return rawQuery;
    }

    public void setRawQuery(String rawQuery) {
        this.rawQuery = rawQuery;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public int getMinimumRating() {
        return minimumRating;
    }

    public void setMinimumRating(int minimumRating) {
        this.minimumRating = minimumRating;
    }

    public float getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(float maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public Set<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(Set<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> country) {
        this.countries = country;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public Set<ContentType> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(Set<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }

    /**
     * Returns a string representation of the content search; useful for debugging.
     *
     * @return  string representation of the content search
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("CONTENT SEARCH: [") );
        sb.append(String.format(" RAW QUERY: [%s]", this.getRawQuery()));
        sb.append(String.format(" CATEGORIES: [%s]", this.getCategories().toString()));
        sb.append(String.format(" TEXT: [%s]", this.getTextSearch()));
        sb.append(String.format(" MINIMUM RATING: [%d]", this.getMinimumRating()));
        sb.append(String.format(" MAXIMUM PRICE: [%f]", this.getMaximumPrice()));
        sb.append(String.format(" SUPPORTED LANGUAGES: [%s]", this.getSupportedLanguages().toString()));
        sb.append(String.format(" ALLOWED COUNTRIES: [%s]", this.getCountries().toString()));
        sb.append(String.format(" DEVICES: [%s]", this.getDevices().toString()));
        sb.append(String.format(" CONTENT TYPES: [%s]", this.getContentTypes().toString()));
        sb.append("]");
        return sb.toString();
    }

}
