package cscie97.asn2.ecommerce.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Set;

/**
 * Represents a Wallpaper in the Mobile Application Store.  Wallpapers are {@link cscie97.asn2.ecommerce.product.Content}
 * items, but have two additional properties - they have pixelWidth and pixelHeight, so that consumers can determine if
 * the Wallpaper is a good fit for their particular {@link cscie97.asn2.ecommerce.product.Device}'s screen.  Each
 * {@link cscie97.asn2.ecommerce.product.Wallpaper} item that lives in the Product catalog is a unique item.
 * Wallpaper items may be added to the Product catalog and made publicly searchable; those use cases are supported by
 * means of the {@link cscie97.asn2.ecommerce.product.IProductAPI}.
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see Content
 * @see ContentSearch
 * @see Application
 * @see Ringtone
 */
public class Wallpaper extends Content {

    /**
     * The width of the wallpaper in pixels
     */
    private int pixelWidth = 0;

    /**
     * The height of the wallpaper in pixels
     */
    private int pixelHeight = 0;

    /**
     * Returns the width of the wallpaper in pixels.
     *
     * @return  the width of the wallpaper in pixels
     */
    public float getPixelWidth() {
        return pixelWidth;
    }

    /**
     * Sets the width of the wallpaper in pixels.
     *
     * @param pixelWidth  how many pixels wide the wallpaper should be
     */
    protected void setPixelWidth(int pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    /**
     * Returns the height of the wallpaper in pixels.
     *
     * @return  the height of the wallpaper in pixels
     */
    public float getPixelHeight() {
        return pixelHeight;
    }

    /**
     * Sets the height of the wallpaper in pixels.
     *
     * @param pixelHeight  how many pixels in height the wallpaper should be
     */
    protected void setPixelheight(int pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    /**
     * Class constructor.  Uses primary constructor from {@link cscie97.asn2.ecommerce.product.Content} to set nearly
     * all object properties, except for the Wallpaper-specific pixelWidth and pixelHeight properties.
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
     * @param pixelWidth          the width of the wallpaper (in pixels)
     * @param pixelHeight         the height of the wallpaper (in pixels)
     */
    public Wallpaper(String id, String name, String description, String authorName, int rating, Set<String> categories,
                   Set<Device> devices, float price, Set<Country> allowedInCountries, Set<String> supportedLanguages,
                   String imageURL, ContentType type, int pixelWidth, int pixelHeight)
    {
        super(id, name, description, authorName, rating, categories, devices, price, allowedInCountries, supportedLanguages, imageURL, type);
        this.pixelHeight = pixelHeight;
        this.pixelWidth = pixelWidth;
        this.setContentType(ContentType.WALLPAPER);
    }

    /**
     * Returns a string representation of a Wallpaper item.  Useful for debugging.
     *
     * @return  a string representing the Wallpaper
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( super.toString() );
        sb.append(String.format("\tPIXEL WIDTH: [%d]\n", this.getPixelWidth()));
        sb.append(String.format("\tPIXEL HEIGHT: [%d]\n", this.getPixelHeight()));
        return sb.toString();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Wallpaper} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all Wallpaper items be unique, this method
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
        return new HashCodeBuilder(827, 4421)
                .appendSuper(super.hashCode())
                .append(this.pixelWidth)
                .append(this.pixelHeight)
                .toHashCode();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Wallpaper} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all Wallpaper items be unique, this method
     * provides a way to determine if another {@link cscie97.asn2.ecommerce.product.Wallpaper} item is the same as the
     * current one.  Uses the Apache Commons {@link org.apache.commons.lang3.builder.EqualsBuilder} to determine if
     * the two objects are indeed equal to each other.
     *
     * @param compare  the {@link cscie97.asn2.ecommerce.product.Wallpaper} item to compare to the current object to test for equality
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
        if (!(compare instanceof Wallpaper))
            return false;

        Wallpaper rhs = (Wallpaper) compare;
        return new EqualsBuilder()
                .appendSuper(super.equals(compare))
                .append(this.pixelWidth, rhs.pixelWidth)
                .append(this.pixelHeight, rhs.pixelHeight)
                .isEquals();
    }

}
