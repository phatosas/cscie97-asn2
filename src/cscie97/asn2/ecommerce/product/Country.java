package cscie97.asn2.ecommerce.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * Content in the Mobile Application Store may be downloaded to consumers devices all over the world.  Some
 * countries may not allow certain types of encyption, applications, etc. based on local, regional, or national
 * laws.  This class represents a Country and denotes the export status of that country.  Countries that have a
 * CLOSED export status disallow application downloads.  Note that the {@link cscie97.asn2.ecommerce.product.IProductAPI}
 * will only contain one instance of each unique Country to conserve resources (following the Flyweight pattern).
 *
 * @author David Killeffer <rayden7@gmail.com>
 * @version 1.0
 * @see ProductAPI
 * @see Content
 * @see Device
 */
public class Country {

    /**
     * Unique 2-character country code
     */
    private String code;

    /**
     * Name of the country
     */
    private String name;

    /**
     * Export status of trade agreements with this country; can be OPEN or CLOSED
     */
    private String exportStatus;


    /**
     * Class constructor.
     *
     * @param code          unique 2-character country code
     * @param name          name of the country
     * @param exportStatus  export status of trade agreements with this country; can be OPEN or CLOSED
     */
    public Country(String code, String name, String exportStatus) {
        this.code = code;
        this.name = name;
        this.exportStatus = exportStatus;
    }

    /**
     * Returns the unique 2-character country code.
     *
     * @return  2-character country code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the unique 2-country character code.
     *
     * @param code  2-character country code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the name of the country
     * @return  the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the country
     *
     * @param name  the name of the country
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the export status of trade agreements with this country; can be OPEN or CLOSED
     *
     * @return  export status of the country; can be OPEN or CLOSED
     */
    public String getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
    }

    /**
     * Convenience method for debugging; returns a string representation of all the object's properties.
     *
     * @return  string containing all the object properties
     */
    public String toString() {
        return "Country: [code:"+this.code+", name:"+this.name+", exportStatus:"+this.exportStatus+"]";
    }

    /**
     * Checks that all required fields are set, and that all country item property values are valid.
     *
     * @param country  the item to be validated for correct properties
     * @return  true if all properties are valid, false otherwise
     */
    public static boolean validateCountry(Country country) {
        return (
                   (country.getCode() != null && country.getCode().length() == 2) &&
                   (country.getName() != null && country.getName().length() > 0) &&
                   (country.getExportStatus() != null &&
                           (country.getExportStatus().equalsIgnoreCase("OPEN") || country.getExportStatus().equalsIgnoreCase("CLOSED")))
        );
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Country} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all country items be unique, this method
     * provides a way to determine if another {@link cscie97.asn2.ecommerce.product.Country} item is the same as the
     * current one.  Uses the Apache Commons {@link org.apache.commons.lang3.builder.EqualsBuilder} to determine if
     * the two objects are indeed equal to each other.
     *
     * @param compare  the {@link cscie97.asn2.ecommerce.product.Country} item to compare to the current object to test for equality
     * @return  true if the objects are the same, false otherwise
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder">http://www.java-tutorial.ch/core-java-tutorial/equalsbuilder</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/EqualsBuilder.html</a>
     */
    public boolean equals(Object compare){
        if (compare == null)
            return false;
        if (!(compare instanceof Country))
            return false;
        if (compare == this)
            return true;

        Country rhs = (Country) compare;
        return new EqualsBuilder()
                .appendSuper(super.equals(compare))
                .append(this.code, rhs.getCode())
                .append(this.name, rhs.getName())
                .append(this.exportStatus, rhs.getExportStatus())
                .isEquals();
    }

    /**
     * Since {@link cscie97.asn2.ecommerce.product.Country} objects may be added to collections, and also since
     * the {@link cscie97.asn2.ecommerce.product.IProductAPI} enforces that all country items be unique, this method
     * provides a way to get the unique hash code for the current country item.  Uses the Apache Commons
     * {@link org.apache.commons.lang3.builder.HashCodeBuilder} to generate a unique hash code for the current item
     * based on two randomly chosen unique prime numbers and all the object properties.
     *
     * @return  a unique integer hash code for this particular object
     * @see <a href="http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java">http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java</a>
     * @see <a href="http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html">http://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/builder/HashCodeBuilder.html</a>
     * @see <a href="http://primes.utm.edu/lists/small/1000.txt">http://primes.utm.edu/lists/small/1000.txt</a>
     */
    public int hashCode() {
        return new HashCodeBuilder(2549, 4261)
                .append(this.code)
                .append(this.name)
                .append(this.exportStatus)
                .toHashCode();
    }

}
