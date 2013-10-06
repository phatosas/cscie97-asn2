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

    private Set<String> categories = new HashSet<String>(){ };

    private String textSearch = "";

    private int minimumRating = 0;

    private float maximumPrice = new Float(Float.MAX_VALUE);

    private Set<String> supportedLanguages = new HashSet<String>(){ };

    private Country country = null;

    private Device device = null;

    private Set<ContentType> contentTypes = new HashSet<ContentType>(){ };

    public ContentSearch() { }

    public ContentSearch(Set<String> categories, String textSearch, int minimumRating, float maximumPrice,
                         Set<String> supportedLanguages, Country country, Device device, Set<ContentType> contentTypes
    ) {
        this.categories = categories;
        this.textSearch = textSearch;
        this.minimumRating = minimumRating;
        this.maximumPrice = maximumPrice;
        this.supportedLanguages = supportedLanguages;
        this.country = country;
        this.device = device;
        this.contentTypes = contentTypes;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Set<ContentType> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(Set<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }

}
