package cscie97.asn2.ecommerce.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Set;

/**
 * Represents a Ringtone in the Mobile Application Store.  Ringtones are {@link cscie97.asn2.ecommerce.product.Content}
 * items, but have an additional property - they have a duration in seconds.  Each
 * {@link cscie97.asn2.ecommerce.product.Ringtone} item that lives in the Product catalog is a unique item.
 * Ringtone items may be added to the Product catalog and made publicly searchable; those use cases are supported by
 * means of the {@link cscie97.asn2.ecommerce.product.IProductAPI}.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see Content
 * @see ContentSearch
 * @see Application
 * @see Wallpaper
 */
public class Ringtone extends Content {

    /**
     * The durating of the Ringtone when played.
     */
    private float durationInSeconds = 0;


    /**
     * Returns the duration of the ringtone when played in seconds.
     *
     * @return  the duration of the ringtone when played (in seconds)
     */
    public float getDurationInSeconds() {
        return durationInSeconds;
    }

    /**
     * Sets the duration in seconds of the ringtone.
     *
     * @param durationInSeconds  how long the ringtone is when played (in seconds)
     */
    protected void setDurationInSeconds(float durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    /**
     * Class constructor.  Uses primary constructor from {@link cscie97.asn2.ecommerce.product.Content} to set nearly
     * all object properties, except for the Ringtone-specific durationInSeconds.
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
     * @param durationInSeconds   the duration of the ringtone when played (in seconds)
     */
    public Ringtone(String id, String name, String description, String authorName, int rating, Set<String> categories,
                   Set<Device> devices, float price, Set<Country> allowedInCountries, Set<String> supportedLanguages,
                   String imageURL, ContentType type, float durationInSeconds)
    {
        super(id, name, description, authorName, rating, categories, devices, price, allowedInCountries, supportedLanguages, imageURL, type);
        this.durationInSeconds = durationInSeconds;
        this.setContentType(ContentType.RINGTONE);
    }

    /**
     * Public static method that checks that all required fields are set, and that all content item values are
     * valid (e.g., ratings are 0 to 5, where 5 is best, item price should only allow positive numbers, etc.).
     * For Ringtone objects, a valid non-zero duration in seconds is required.
     *
     * @param content  the item to be validated for correct properties
     * @return  true if all properties are valid, false otherwise
     */
    public static boolean validateContent(Ringtone content) {
        return (Content.validateContent(content) && content.durationInSeconds > 0);
    }

    /**
     * Returns a string representation of a Ringtone item.  Useful for debugging.
     *
     * @return  a string representing the Ringtone
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( super.toString() );
        sb.append(String.format("\tDURATION IN SECONDS: [%f]\n", this.getDurationInSeconds()));
        return sb.toString();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Ringtone} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all Ringtone items be unique, this method
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
        return new HashCodeBuilder(7561, 5)
                .appendSuper(super.hashCode())
                .append(this.durationInSeconds)
                .toHashCode();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Ringtone} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all Ringtone items be unique, this method
     * provides a way to determine if another {@link cscie97.asn2.ecommerce.product.Ringtone} item is the same as the
     * current one.  Uses the Apache Commons {@link org.apache.commons.lang3.builder.EqualsBuilder} to determine if
     * the two objects are indeed equal to each other.
     *
     * @param compare  the {@link cscie97.asn2.ecommerce.product.Ringtone} item to compare to the current object to test for equality
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
        if (!(compare instanceof Ringtone))
            return false;

        Ringtone rhs = (Ringtone) compare;
        return new EqualsBuilder()
                .appendSuper(super.equals(compare))
                .append(this.durationInSeconds, rhs.durationInSeconds)
                .isEquals();
    }

}
