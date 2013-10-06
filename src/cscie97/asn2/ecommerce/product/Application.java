package cscie97.asn2.ecommerce.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Set;

/**
 * Represents an Application in the Mobile Application Store.  Applications are
 * {@link cscie97.asn2.ecommerce.product.Content} items, but have an additional property - they have a file size
 * expressed in bytes.  Each {@link cscie97.asn2.ecommerce.product.Application} item that lives in the Product
 * catalog is a unique item.  Application items may be added to the Product catalog and made publicly searchable;
 * those use cases are supported by means of the {@link cscie97.asn2.ecommerce.product.IProductAPI}.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see Content
 * @see ContentSearch
 * @see Ringtone
 * @see Wallpaper
 */
public class Application extends Content {

    /**
     * The file size of the application expressed in bytes.
     */
    private int fileSizeBytes = 0;


    /**
     * Returns the file size of the application in bytes.
     *
     * @return  filesize of the application in bytes
     */
    public int getFileSizeBytes() {
        return fileSizeBytes;
    }

    /**
     * Sets the file size of the application in bytes.
     *
     * @param fileSizeBytes  how many bytes large the application is
     */
    public void setFileSizeBytes(int fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    /**
     * Class constructor.  Uses primary constructor from {@link cscie97.asn2.ecommerce.product.Content} to set nearly
     * all object properties, except for the Application-specific fileSizeBytes.
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
     * @param fileSizeBytes       the size of the application file (in bytes)
     */
    public Application(String id, String name, String description, String authorName, int rating, Set<String> categories,
                   Set<Device> devices, float price, Set<Country> allowedInCountries, Set<String> supportedLanguages,
                   String imageURL, ContentType type, int fileSizeBytes)
    {
        super(id, name, description, authorName, rating, categories, devices, price, allowedInCountries, supportedLanguages, imageURL, type);
        this.fileSizeBytes = fileSizeBytes;
        this.setContentType(ContentType.APPLICATION);
    }

    /**
     * Returns a string representation of an Application item.  Useful for debugging.
     *
     * @return  a string representing the Application
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( super.toString() );
        sb.append(String.format("\tFILESIZE: [%d]\n", this.getFileSizeBytes()));
        return sb.toString();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Application} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all Application items be unique, this method
     * provides a way to get the unique hash code for the current content item.  Uses the Apache Commons
     * {@link org.apache.commons.lang3.builder.HashCodeBuilder} to generate a unique hash code for the current item
     * based on two randomly chosen unique prime numbers and all the object properties.
     *
     * @return  a unique integer hash code for this particular object
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html</a>
     * @see <a href="http://primes.utm.edu/lists/small/1000.txt">http://primes.utm.edu/lists/small/1000.txt</a>
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(7121, 3083)
                .appendSuper(super.hashCode())
                .append(this.fileSizeBytes)
                .toHashCode();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Application} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all Application items be unique, this method
     * provides a way to determine if another {@link cscie97.asn2.ecommerce.product.Application} item is the same as the
     * current one.  Uses the Apache Commons {@link org.apache.commons.lang3.builder.EqualsBuilder} to determine if
     * the two objects are indeed equal to each other.
     *
     * @param compare  the {@link cscie97.asn2.ecommerce.product.Application} item to compare to the current object to test for equality
     * @return  true if the objects are the same, false otherwise
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder">http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html</a>
     */
    @Override
    public boolean equals(Object compare) {
        if (compare == null)
            return false;
        if (compare == this)
            return true;
        if (!(compare instanceof Application))
            return false;

        Application rhs = (Application) compare;
        return new EqualsBuilder()
                .appendSuper(super.equals(compare))
                .append(this.fileSizeBytes, rhs.fileSizeBytes)
                .isEquals();
    }
}
