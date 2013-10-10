package cscie97.asn2.ecommerce.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Set;

/**
 * Abstract class representing a content item that is included in the Mobile Application Store.  Attributes here
 * are common to all items in the store, regardless of type.  All content items (regardless of type) have
 * the following attributes:
 * <ul>
 *     <li>must have an <b>ID</b></li>
 *     <li>must have a <b>name</b></li>
 *     <li>must have a <b>description</b></li>
 *     <li>must have an <b>authorName</b></li>
 *     <li>must have a <b>rating</b>; 0 to 5, where 5 is best</li>
 *     <li>may have any number of unique <b>categories</b></li>
 *     <li>must have 1 or more <b>{@link cscie97.asn2.ecommerce.product.Country}</b> objects where the content may be legally downloaded</li>
 *     <li>must have 1 or more associated compatible <b>{@link cscie97.asn2.ecommerce.product.Device}</b> objects</li>
 *     <li>must have a <b>price</b> (unit is BitCoins)</li>
 *     <li>must have 1 or more <b>supported language</b> codes</li>
 *     <li>must have an <b>imageURL</b> where a picture of the content lives</li>
 * </ul>
 *
 * Certain specific types of content may have additional required attributes.  Each
 * {@link cscie97.asn2.ecommerce.product.Content} item that lives in the Product catalog is a unique item.  Content
 * items may be added to the Product catalog and made publicly searchable; those use cases are supported by means of
 * the {@link cscie97.asn2.ecommerce.product.IProductAPI}.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see ContentSearch
 * @see Application
 * @see Ringtone
 * @see Wallpaper
 */
public abstract class Content {

    /**
     * A unique string identifier for each content item
     */
    private String id;

    /**
     * Name of the Content item
     */
    private String name;

    /**
     * A brief description of what this Content item is and it's features.
     */
    private String description;

    /**
     * The author name of the Content item
     */
    private String authorName;

    /**
     * A rating for this content item as voted on by the public; range is from 0 to 5 where 5 is best.
     */
    private int rating = 0;

    /**
     * A list of unique categories that the author of the Content item wanted this to be categorized under.
     */
    private Set<String> categories;

    /**
     * All the unique Devices that this content item is compatible with for use.
     */
    private Set<Device> compatibleDevices;

    /**
     * The price to purchase this item in BitCoins.
     */
    private float price = 0;

    /**
     * All the countries where downloading this content is legal.
     */
    private Set<Country> allowedInCountries;

    /**
     * A list of the languages that are supported by the content item.
     */
    private Set<String> supportedLanguages;

    /**
     * A link to a public image of the Content item.
     */
    private String imageURL;

    /**
     * Which type of content the item is; currently only APPLICATION, RINGTONE, and WALLPAPER are supported
     * in the Mobile Application Store.
     */
    private ContentType contentType;

    /**
     * Returns the unique content ID of the item.
     *
     * @return  the unique content ID
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the unique content ID for this content item.
     *
     * @param id  the unique content ID to use for the content item
     */
    protected void setID(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the content item.
     *
     * @return  the content item name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for this content item.
     *
     * @param name  the name to use for the content item
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the content item description.
     *
     * @return  content item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the content item description
     *
     * @param description  the new content item description to use
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the content author's name
     *
     * @return  content author's name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets the content item author name
     *
     * @param authorName  the author's name to use for the content item
     */
    protected void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Returns the current content item rating.  Ratings should range from 0 to 5, where 5 is best.
     *
     * @return  the current rating of the content item
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the current content item rating.  Ratings should range from 0 to 5, where 5 is best.
     *
     * @param rating  new rating for the content item
     */
    protected void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Returns the list of discrete categories that the content item belongs to.
     *
     * @return  list of categories the content item belongs to
     */
    public Set<String> getCategories() {
        return categories;
    }

    /**
     * Sets the categories that the content item should belong to.
     *
     * @param categories  the list of categories the content item should belong to
     */
    protected void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    /**
     * Returns a list of all the compatible {@link cscie97.asn2.ecommerce.product.Device} that the content item is compatible with
     *
     * @return  all the unique {@link cscie97.asn2.ecommerce.product.Device} objects that the content item is compatible with
     */
    public Set<Device> getCompatibleDevices() {
        return compatibleDevices;
    }

    /**
     * Sets the list of {@link cscie97.asn2.ecommerce.product.Device} objects the content item is compatible with
     *
     * @param compatibleDevices  all the {@link cscie97.asn2.ecommerce.product.Device} that the content item is compatible with
     */
    protected void setCompatibleDevices(Set<Device> compatibleDevices) {
        this.compatibleDevices = compatibleDevices;
    }

    /**
     * Returns the current price of the content item, expressed in BitCoins
     *
     * @return  price of the content in BitCoins
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the current price of the content item in BitCoins
     *
     * @param price  current price for the content in BitCoins
     */
    protected void setPrice(float price) {
        this.price = price;
    }

    /**
     * Returns the list of {@link cscie97.asn2.ecommerce.product.Country} where the content may be legally downloaded and used
     *
     * @return  list of {@link cscie97.asn2.ecommerce.product.Country} objects where the content may be downloaded
     */
    public Set<Country> getAllowedInCountries() {
        return allowedInCountries;
    }

    /**
     * Sets the list of {@link cscie97.asn2.ecommerce.product.Country} objects that allow the content to be owned and used legally
     *
     * @param allowedInCountries  the list of {@link cscie97.asn2.ecommerce.product.Country} where the content is legal to own and use
     */
    protected void setAllowedInCountries(Set<Country> allowedInCountries) {
        this.allowedInCountries = allowedInCountries;
    }

    /**
     * Returns the list of supported languages by the content.  Language codes are each 5-character long strings.
     *
     * @return  list of 5-character supported language codes
     */
    public Set<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    /**
     * Sets the list of supported languages by the content.  Language codes are each 5-character long strings.
     *
     * @param supportedLanguages  list of strings 5-characters long that represents the supported languages by the content
     */
    protected void setSupportedLanguages(Set<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    /**
     * Returns a string URL to an image (screenshot, box art, etc.) of the content item.
     *
     * @return  a string to a public URL image of the content
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets the URL to a publicly-available image of the content (screenshot, box art, etc.)
     *
     * @param imageURL  the URL for a publicly available image of the content
     */
    protected void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Returns the {@link cscie97.asn2.ecommerce.product.ContentType} of the content item.
     *
     * @return  the {@link cscie97.asn2.ecommerce.product.ContentType} of the item
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * Sets the {@link cscie97.asn2.ecommerce.product.ContentType} of the item
     *
     * @param contentType  the {@link cscie97.asn2.ecommerce.product.ContentType} that best describes the item
     */
    protected void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * Class constructor.
     *
     * @param id                  the unique content ID
     * @param name                the content item name
     * @param description         content item description
     * @param authorName          content author's name
     * @param rating              the current rating of the content item
     * @param categories          list of categories the content item belongs to
     * @param devices             all the unique {@link cscie97.asn2.ecommerce.product.Device} objects that the content item is compatible with
     * @param price               price of the content in BitCoins
     * @param allowedInCountries  list of {@link cscie97.asn2.ecommerce.product.Country} objects where the content may be downloaded
     * @param supportedLanguages  list of 5-character supported language codes
     * @param imageURL            a string to a public URL image of the content
     * @param type                the {@link cscie97.asn2.ecommerce.product.ContentType} of the item
     */
    public Content(String id, String name, String description, String authorName, int rating, Set<String> categories,
                   Set<Device> devices, float price, Set<Country> allowedInCountries, Set<String> supportedLanguages,
                   String imageURL, ContentType type)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authorName = authorName;
        this.rating = rating;
        this.categories = categories;
        this.compatibleDevices = devices;
        this.price = price;
        this.allowedInCountries = allowedInCountries;
        this.imageURL = imageURL;
        this.supportedLanguages = supportedLanguages;
        this.contentType = type;
    }


    /**
     * Public static method that checks that all required fields are set, and that all content item values are
     * valid (e.g., ratings are 0 to 5, where 5 is best, item price should only allow positive numbers, etc.).
     *
     * @param content  the item to be validated for correct properties
     * @return  true if all properties are valid, false otherwise
     */
    public static boolean validateContent(Content content) {
        return (
                (content.getID() != null && content.getID().length() > 0) &&
                (content.getName() != null && content.getName().length() > 0) &&
                (content.getDescription() != null && content.getDescription().length() > 0) &&
                (content.getAuthorName() != null && content.getAuthorName().length() > 0) &&
                (content.getRating() >= 0 && content.getRating() <= 5) &&
                (content.getPrice() >= 0) &&
                (content.getAllowedInCountries() != null && content.getAllowedInCountries().size() >= 1) &&
                (content.getCompatibleDevices() != null && content.getCompatibleDevices().size() >= 1) &&
                (content.getSupportedLanguages() != null && content.getSupportedLanguages().size() >= 1) &&
                (content.getImageURL() != null && content.getImageURL().length() >= 10)
        );
    }

    /**
     * Returns a string representation of the content item; useful for debugging.
     *
     * @return  string representation of the content item
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("CONTENT ITEM:\n") );
        sb.append(String.format("\tTYPE: [%s]\n", this.getContentType()));
        sb.append(String.format("\tID: [%s]\n", this.getID()));
        sb.append(String.format("\tNAME: [%s]\n", this.getName()));
        sb.append(String.format("\tDESCRIPTION: [%s]\n", this.getDescription()));
        sb.append(String.format("\tAUTHOR NAME: [%s]\n", this.getAuthorName()));
        sb.append(String.format("\tRATING: [%d]\n", this.getRating()));
        sb.append(String.format("\tCATEGORIES: [%s]\n", this.getCategories().toString() ));
        sb.append(String.format("\tDEVICES: [%s]\n", this.getCompatibleDevices().toString()));
        sb.append(String.format("\tPRICE: [%f]\n", this.getPrice()));
        sb.append(String.format("\tALLOWED COUNTRIES: [%s]\n", this.getAllowedInCountries().toString()));
        sb.append(String.format("\tIMAGE URL: [%s]\n", this.getImageURL()));
        sb.append(String.format("\tSUPPORTED LANGUAGES: [%s]\n", this.getSupportedLanguages().toString()));
        sb.append(String.format("\tALLOWED COUNTRIES: [%s]\n", this.getAllowedInCountries().toString()));
        return sb.toString();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Content} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all content items be unique, this method
     * provides a way to determine if another {@link cscie97.asn2.ecommerce.product.Content} item is the same as the
     * current one.  Uses the Apache Commons {@link org.apache.commons.lang3.builder.EqualsBuilder} to determine if
     * the two objects are indeed equal to each other.
     *
     * @param compare  the {@link cscie97.asn2.ecommerce.product.Content} item to compare to the current object to test for equality
     * @return  true if the objects are the same, false otherwise
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder">http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html</a>
     */
    public boolean equals(Object compare){
        if (compare == null)
            return false;
        if (!(compare instanceof Content))
            return false;
        if (compare == this)
            return true;

        Content rhs = (Content) compare;
        return new EqualsBuilder()
                .appendSuper(super.equals(compare))
                .append(this.id, rhs.getID())
                .append(this.name, rhs.getName())
                .append(this.description, rhs.getDescription())
                .append(this.authorName, rhs.getAuthorName())
                .append(this.rating, rhs.getRating())
                .append(this.categories, rhs.getCategories())
                .append(this.compatibleDevices, rhs.getCompatibleDevices())
                .append(this.price, rhs.getPrice())
                .append(this.allowedInCountries, rhs.getAllowedInCountries())
                .append(this.supportedLanguages, rhs.getSupportedLanguages())
                .append(this.imageURL, rhs.getImageURL())
                .append(this.contentType, rhs.getContentType())
                .isEquals();
    }

        /**
     * Since {@link cscie97.asn2.ecommerce.product.Content} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all content items be unique, this method
     * provides a way to get the unique hash code for the current content item.  Uses the Apache Commons
     * {@link org.apache.commons.lang3.builder.HashCodeBuilder} to generate a unique hash code for the current item
     * based on two randomly chosen unique prime numbers and all the object properties.
     *
     * @return  a unique integer hash code for this particular object
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html</a>
     * @see <a href="http://primes.utm.edu/lists/small/1000.txt">http://primes.utm.edu/lists/small/1000.txt</a>
     */
    public int hashCode() {
        return new HashCodeBuilder(191, 7253)
                .append(this.name)
                .append(this.description)
                .append(this.authorName)
                .append(this.rating)
                .append(this.categories)
                .append(this.compatibleDevices)
                .append(this.price)
                .append(this.allowedInCountries)
                .append(this.supportedLanguages)
                .append(this.imageURL)
                .append(this.contentType)
                .toHashCode();
    }

}
